package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivityInfoBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.utils.CommonUtils;
import com.android.classiccarselling.viewmodel.ProfileVM;

public class InfoActivity extends AppCompatActivity implements CustomHooks {

    private ActivityInfoBinding binding;
    private ProfileVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_info);

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

        viewModel = ViewModelProviders.of(this).get(ProfileVM.class);
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void observe() {

        viewModel.defaultUser.observe(this, defaultUser -> {

            if (defaultUser != null) {

                binding.tvName.setText(defaultUser.getName());
                binding.tvCity.setText(defaultUser.getCity());
                binding.tvPersonalInformation.setOnClickListener(view -> CommonUtils.changeActivity(this, ProfileActivity.class, true));
            }
        });

        viewModel.getSavedUser();
    }
}