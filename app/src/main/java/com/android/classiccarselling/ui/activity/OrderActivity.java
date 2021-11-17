package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivityOrderBinding;
import com.android.classiccarselling.interfaces.CustomHooks;

public class OrderActivity extends AppCompatActivity implements CustomHooks {

    private ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);

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

    }

    @Override
    public void observe() {

    }
}