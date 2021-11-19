package com.android.classiccarselling.ui.activity;

import static com.android.classiccarselling.global.Constants.CAR_INTENT_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivityOrderBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.model.Car;

public class OrderActivity extends AppCompatActivity implements CustomHooks {

    private ActivityOrderBinding binding;
    private Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);

        callHooks();
    }

    @Override
    public void callHooks() {

        handleIntent();
        initViews();
        initListeners();
    }

    @Override
    public void handleIntent() {

        Car car = getIntent().getParcelableExtra(CAR_INTENT_KEY);

        if (car == null) {
            finish();
        }

        this.car = car;
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