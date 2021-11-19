package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.android.classiccarselling.adapters.BrandAdapter;
import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivityBrandBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.model.Brand;
import com.android.classiccarselling.viewmodel.MainVM;

import java.util.ArrayList;
import java.util.List;

public class BrandActivity extends AppCompatActivity implements CustomHooks {

    private ActivityBrandBinding binding;
    private MainVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_brand);

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

        viewModel = ViewModelProviders.of(this).get(MainVM.class);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void observe() {

        viewModel.brands.observe(this, brands -> {

            if (brands != null) {

                BrandAdapter adapter = new BrandAdapter(this, brands);
                binding.rvBrand.setAdapter(adapter);

            }
        });

        viewModel.getBrands();
    }
}