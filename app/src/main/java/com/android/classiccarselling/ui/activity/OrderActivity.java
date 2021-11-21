package com.android.classiccarselling.ui.activity;

import static com.android.classiccarselling.global.Constants.CART_INTENT_KEY;
import static com.android.classiccarselling.global.Constants.CAR_INTENT_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ActivityOrderBinding;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.model.Car;
import com.android.classiccarselling.model.Cart;
import com.android.classiccarselling.viewmodel.OrderVM;
import com.squareup.picasso.Picasso;

public class OrderActivity extends AppCompatActivity implements CustomHooks {

    private ActivityOrderBinding binding;
    private OrderVM viewModel;

    private Car car;
    private Cart cart;

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
        observe();
    }

    @Override
    public void handleIntent() {

        Car car = getIntent().getParcelableExtra(CAR_INTENT_KEY);
        Cart cart = getIntent().getParcelableExtra(CART_INTENT_KEY);

        if (car == null || cart == null) {
            finish();
        }

        this.car = car;
        this.cart = cart;
    }

    @Override
    public void initViews() {

        binding.setCar(car);
        Picasso.get().load(car.getImages().get(0).getDownloadURL())
                .into(binding.imageView9);

        if (cart.getCarIds().contains(car.getId())) {

            onAddedToCart();

        } else {

            onRemovedFromCart();
        }
    }

    @Override
    public void initListeners() {

        viewModel = ViewModelProviders.of(this).get(OrderVM.class);
    }

    @Override
    public void observe() {

        viewModel.error.observe(this, error -> {

            if (error != null) {

                Toast.makeText(this, "error: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.addedInCart.observe(this, addedInCart -> {

            if (addedInCart != null) {

                if (addedInCart) {

                    onAddedToCart();

                } else {

                    onRemovedFromCart();
                }
            }
        });
    }

    private void onAddedToCart() {

        binding.btnOrder.setEnabled(true);
        binding.btnOrder.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorOrange)));
        binding.btnOrder.setText(getString(R.string.cancel_order));

        binding.btnOrder.setOnClickListener(view -> {

            view.setEnabled(false);
            viewModel.removeThisCarFromMyCart(car.getId());

        });
    }

    private void onRemovedFromCart() {

        binding.btnOrder.setEnabled(true);
        binding.btnOrder.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimaryDark)));
        binding.btnOrder.setText(getString(R.string.confirm_order));

        binding.btnOrder.setOnClickListener(view -> {

            view.setEnabled(false);
            viewModel.addThisCarToMyCart(car.getId());

        });
    }
}