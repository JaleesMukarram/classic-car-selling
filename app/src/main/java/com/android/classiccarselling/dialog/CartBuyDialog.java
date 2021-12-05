package com.android.classiccarselling.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.DialogCartBuyBinding;


public class CartBuyDialog {

    private final Activity homeScreen;
    private Dialog mDialog;
    private Checkout checkout;


    public CartBuyDialog(Activity homeScreen, Checkout checkout) {
        this.homeScreen = homeScreen;
        this.checkout = checkout;
    }

    public void show(String cars, String price) {

        DialogCartBuyBinding binding = DataBindingUtil.inflate(homeScreen.getLayoutInflater(), R.layout.dialog_cart_buy, null, false);

        binding.tvCars.setText(cars);
        binding.tvPrice.setText(price);
        binding.btnOrder.setOnClickListener(v -> {

            binding.btnOrder.setEnabled(false);
            binding.progressBar.setVisibility(View.VISIBLE);
            checkout.onCheckOut();
        });

        View view = binding.getRoot();

        AlertDialog.Builder builder = new AlertDialog.Builder(homeScreen);
        builder.setView(view);

        mDialog = builder.create();
        mDialog.show();
    }

    public void cancel() {

        try {

            mDialog.cancel();

        } catch (Exception ignored) {

        }
    }

    public interface Checkout {

        void onCheckOut();
    }
}
