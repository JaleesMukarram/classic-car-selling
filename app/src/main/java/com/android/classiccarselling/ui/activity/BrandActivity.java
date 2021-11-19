package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.android.classiccarselling.adapters.BrandAdapter;
import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivityBrandBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.model.Brand;

import java.util.ArrayList;
import java.util.List;

public class BrandActivity extends AppCompatActivity implements CustomHooks {

    private ActivityBrandBinding binding;

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
    }

    @Override
    public void handleIntent() {

    }

    @Override
    public void initViews() {

        List<Brand> brandList = new ArrayList<>();
//        brandList.add(new Brand(R.drawable.bmw));
//        brandList.add(new Brand(R.drawable.bmw));
//        brandList.add(new Brand(R.drawable.bmw));
//        brandList.add(new Brand(R.drawable.bmw));

        BrandAdapter adapter = new BrandAdapter(brandList);
        binding.rvBrand.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void observe() {

    }
}