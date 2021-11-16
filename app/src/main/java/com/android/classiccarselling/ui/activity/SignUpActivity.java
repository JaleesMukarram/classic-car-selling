package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivitySignUpBinding;
import com.android.classiccarselling.interfaces.CustomHooks;

public class SignUpActivity extends AppCompatActivity implements CustomHooks {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
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

    }

    @Override
    public void observe() {

    }
}