package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivitySignInBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.utils.CommonUtils;

public class SignInActivity extends AppCompatActivity implements CustomHooks {

    private ActivitySignInBinding binding;

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
    }

    @Override
    public void handleIntent() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void initListeners() {

        binding.tvSignUp.setOnClickListener(view -> CommonUtils.changeActivity(this, SignUpActivity.class, false));
    }

    @Override
    public void observe() {

    }
}