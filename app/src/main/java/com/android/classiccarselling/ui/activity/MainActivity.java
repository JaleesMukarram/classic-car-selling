package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.android.classiccarselling.R;
import com.android.classiccarselling.adapters.CarAdapter;
import com.android.classiccarselling.databinding.ActivityMainBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.model.Brand;
import com.android.classiccarselling.model.Car;
import com.android.classiccarselling.utils.CommonUtils;
import com.android.classiccarselling.viewmodel.MainVM;
import com.android.classiccarselling.viewmodel.SignInVM;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomHooks {

    private static final String TAG = "MainActivityTAG";
    private ActivityMainBinding binding;
    private MainVM viewModel;
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

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

        binding.ivProfile.setOnClickListener(view -> CommonUtils.changeActivity(this, InfoActivity.class, false));
        binding.ivMenu.setOnClickListener(view -> CommonUtils.changeActivity(this, BrandActivity.class, false));

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (carAdapter != null) {

                    carAdapter.searchFilter(charSequence.toString().toLowerCase());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void observe() {

        viewModel.cars.observe(this, cars -> {

            if (cars != null) {

                carAdapter = new CarAdapter(this, cars);
                binding.rvCars.setAdapter(carAdapter);
            }
        });

        viewModel.getAllCars();
    }
}