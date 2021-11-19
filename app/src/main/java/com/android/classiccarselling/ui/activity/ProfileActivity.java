package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivityProfileBinding;
import com.android.classiccarselling.dialog.LoadingDialogue;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.utils.CommonUtils;
import com.android.classiccarselling.viewmodel.ProfileVM;
import com.android.classiccarselling.viewmodel.SignUpVM;

public class ProfileActivity extends AppCompatActivity implements CustomHooks {

    private ActivityProfileBinding binding;
    private ProfileVM viewModel;

    private LoadingDialogue loadingDialogue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        callHooks();
    }

    @Override
    public void callHooks() {

        initViews();
        initListeners();
        observe();
    }

    @Override
    public void handleIntent() {

    }

    @Override
    public void initViews() {

        viewModel = ViewModelProviders.of(this).get(ProfileVM.class);

    }

    @Override
    public void initListeners() {

        binding.tvSkip.setOnClickListener(view -> CommonUtils.changeActivity(this, MainActivity.class, true));

        binding.button.setOnClickListener(view -> {

            String name = binding.etName.getText().toString();
            String city = binding.etCity.getText().toString();

            if (name.trim().isEmpty()) {

                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();

            } else if (city.trim().isEmpty()) {

                Toast.makeText(this, "Please enter your city", Toast.LENGTH_SHORT).show();

            } else {

                showLoadingDialogue(ProfileActivity.this);
                viewModel.saveUserInfo(name, city);
            }
        });
    }

    @Override
    public void observe() {

        viewModel.updatedUser.observe(this, updatedUser -> {

            if (updatedUser != null) {

                cancelDialogue();
                Toast.makeText(ProfileActivity.this, "Info Saved Successfully", Toast.LENGTH_SHORT).show();
                CommonUtils.changeActivity(this, MainActivity.class, true);
            }

        });

        viewModel.defaultUser.observe(this, defaultUser -> {

            if (defaultUser != null) {

                binding.etName.setText(defaultUser.getName());
                binding.etCity.setText(defaultUser.getCity());
            }

        });

        viewModel.error.observe(this, error -> {

            if (error != null) {

                cancelDialogue();
                Toast.makeText(ProfileActivity.this, "Error " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getSavedUser();
    }


    // Dialogue
    private void showLoadingDialogue(Activity activity) {

        loadingDialogue = new LoadingDialogue(activity);
        loadingDialogue.ready("Please wait...", "Please wait while we save your information");
        loadingDialogue.show();
    }

    private void cancelDialogue() {

        try {

            if (loadingDialogue != null) {

                loadingDialogue.cancel();
            }

        } catch (Exception ignored) {

        }
    }
}