package com.android.classiccarselling.ui.activity;

import static com.android.classiccarselling.global.Constants.FILTER_BRAND_INTENT_KEY;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.classiccarselling.R;
import com.android.classiccarselling.adapters.CarAdapter;
import com.android.classiccarselling.databinding.ActivityMainBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.utils.CommonUtils;
import com.android.classiccarselling.viewmodel.MainVM;

public class MainActivity extends AppCompatActivity implements CustomHooks {

    private static final String TAG = "MainActivityTAG";
    private ActivityMainBinding binding;
    private MainVM viewModel;
    private CarAdapter carAdapter;
    private String brandFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        callHooks();
    }

    @Override
    public void callHooks() {

        handleIntent();
        initViews();
        initListeners();
        observe();
    }

    @Override
    public void handleIntent() {

        brandFilter = getIntent().getStringExtra(FILTER_BRAND_INTENT_KEY);
    }

    @Override
    public void initViews() {

        viewModel = ViewModelProviders.of(this).get(MainVM.class);
    }

    @Override
    public void initListeners() {

        binding.ivProfile.setOnClickListener(view -> CommonUtils.changeActivity(this, InfoActivity.class, false));
        binding.ivMenu.setOnClickListener(view -> {

            if (carAdapter != null) {

                CommonUtils.changeActivity(this, BrandActivity.class, false);
            }
        });

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

        binding.srlCarsContainer.setOnRefreshListener(() -> {

            viewModel.getAllCars();
            binding.etSearch.setText("");
        });
    }

    @Override
    public void observe() {

        viewModel.cars.observe(this, cars -> {

            if (cars != null) {

                carAdapter = new CarAdapter(this, cars);
                binding.rvCars.setAdapter(carAdapter);

                if (brandFilter != null) {

                    carAdapter.searchFilter(brandFilter);
                    binding.etSearch.setText(brandFilter);
                }

                viewModel.getMyCart();
            }
        });

        viewModel.cart.observe(this, cart -> {

            if (cart != null && carAdapter != null) {

                carAdapter.reflectCart(cart);
            }

            binding.srlCarsContainer.setRefreshing(false);

        });

        viewModel.error.observe(this, error -> {

            if (error != null) {

                Toast.makeText(this, "error: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCars();
    }
}