package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderKt;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivitySignUpBinding;
import com.android.classiccarselling.dialog.LoadingDialogue;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.utils.CommonUtils;
import com.android.classiccarselling.viewmodel.SignUpVM;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements CustomHooks {

    private ActivitySignUpBinding binding;
    private SignUpVM viewModel;

    private LoadingDialogue loadingDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

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

        viewModel = ViewModelProviders.of(this).get(SignUpVM.class);
    }

    @Override
    public void initListeners() {

        binding.button.setOnClickListener(view -> {

            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                Toast.makeText(SignUpActivity.this, "Not a valid email", Toast.LENGTH_SHORT).show();

            } else if (password.trim().length() < 8) {

                Toast.makeText(SignUpActivity.this, "Password smaller than 8 characters", Toast.LENGTH_SHORT).show();

            } else {

                showLoadingDialogue(SignUpActivity.this);
                viewModel.signUp(email, password);
            }
        });
    }

    @Override
    public void observe() {

        viewModel.newUser.observe(this, firebaseUser -> {

            if (firebaseUser != null) {

                cancelDialogue();
                Toast.makeText(SignUpActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                CommonUtils.changeActivity(this, ProfileActivity.class, true);
            }

        });

        viewModel.error.observe(this, error -> {

            if (error != null) {

                cancelDialogue();
                Toast.makeText(SignUpActivity.this, "error " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Dialogue
    private void showLoadingDialogue(Activity activity) {

        loadingDialogue = new LoadingDialogue(activity);
        loadingDialogue.ready("Please wait...", "Please wait while we are creating your new account");
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