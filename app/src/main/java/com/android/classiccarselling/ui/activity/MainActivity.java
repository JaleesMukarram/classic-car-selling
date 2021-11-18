package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.android.classiccarselling.R;
import com.android.classiccarselling.adapters.CarAdapter;
import com.android.classiccarselling.databinding.ActivityMainBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.model.Brand;
import com.android.classiccarselling.model.Car;
import com.android.classiccarselling.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomHooks {

    private ActivityMainBinding binding;

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
    }

    @Override
    public void handleIntent() {

    }

    @Override
    public void initViews() {

        List<Car> carList = new ArrayList<>();
        carList.add(new Car(new Brand(R.drawable.bmw), "507", "2021", "2020", "Fog Black", "200Km", 4400, null));

        binding.rvCars.setAdapter(new CarAdapter(this,carList));

    }

    @Override
    public void initListeners() {

        binding.ivProfile.setOnClickListener(view -> CommonUtils.changeActivity(this, InfoActivity.class, false));
        binding.ivMenu.setOnClickListener(view -> CommonUtils.changeActivity(this, BrandActivity.class, false));
    }

    @Override
    public void observe() {

    }
}