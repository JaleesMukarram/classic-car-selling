package com.android.classiccarselling.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.DialogCartBuyBinding;


public class CartBuyDialog {

    private AlertDialog.Builder mBuilder;
    private DialogCartBuyBinding mBinding;

    private final Activity homeScreen;

    public CartBuyDialog(Activity homeScreen) {
        this.homeScreen = homeScreen;
    }

    public void show(String cars, String price) {

        mBinding = DataBindingUtil.inflate(homeScreen.getLayoutInflater(), R.layout.dialog_cart_buy, null, false);
        mBinding.tvCars.setText(cars);
        mBinding.tvPrice.setText(price);

        View mView = mBinding.getRoot();

        mBuilder = new AlertDialog.Builder(homeScreen);
        mBuilder.setView(mView);

        Dialog mDialog = mBuilder.create();
        mDialog.show();
    }
}
