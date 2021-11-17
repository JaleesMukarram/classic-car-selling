package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivitySignInBinding;
import com.android.classiccarselling.dialog.LoadingDialogue;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.utils.CommonUtils;
import com.android.classiccarselling.viewmodel.SignInVM;

public class SignInActivity extends AppCompatActivity implements CustomHooks {

    private ActivitySignInBinding binding;
    private SignInVM viewModel;

    private LoadingDialogue loadingDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

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

        viewModel = ViewModelProviders.of(this).get(SignInVM.class);
    }

    @Override
    public void initListeners() {

        binding.tvSignUp.setOnClickListener(view -> CommonUtils.changeActivity(this, SignUpActivity.class, false));

        binding.button.setOnClickListener(view -> {

            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                Toast.makeText(SignInActivity.this, "Not a valid email", Toast.LENGTH_SHORT).show();

            } else if (password.trim().length() < 8) {

                Toast.makeText(SignInActivity.this, "Password smaller than 8 characters", Toast.LENGTH_SHORT).show();

            } else {

                showLoadingDialogue(SignInActivity.this);
                viewModel.signIn(email, password);
            }
        });
    }

    @Override
    public void observe() {

        viewModel.signedInUser.observe(this, firebaseUser -> {

            if (firebaseUser != null) {

                cancelDialogue();
                Toast.makeText(SignInActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                CommonUtils.changeActivity(this, MainActivity.class, true);
            }

        });

        viewModel.error.observe(this, error -> {

            if (error != null) {

                cancelDialogue();
                Toast.makeText(SignInActivity.this, "error " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Dialogue
    private void showLoadingDialogue(Activity activity) {

        loadingDialogue = new LoadingDialogue(activity);
        loadingDialogue.ready("Please wait...", "Please wait while we are signing in to your account");
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