package com.android.classiccarselling.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.classiccarselling.R;
import com.android.classiccarselling.adapters.CarAdapter;
import com.android.classiccarselling.databinding.ActivityInfoBinding;
import com.android.classiccarselling.dialog.CartBuyDialog;
import com.android.classiccarselling.interfaces.CustomHooks;
import com.android.classiccarselling.utils.CommonUtils;
import com.android.classiccarselling.viewmodel.ProfileVM;
import com.google.firebase.firestore.FieldValue;

public class InfoActivity extends AppCompatActivity implements CustomHooks {

    private ActivityInfoBinding binding;
    private ProfileVM viewModel;

    private CartBuyDialog dialogue;

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

        binding.textView9.setOnClickListener(v -> showCartCheckout());
        binding.imageView7.setOnClickListener(v -> showCartCheckout());
        binding.textView10.setOnClickListener(v -> showCartCheckout());
        binding.imageView8.setOnClickListener(v -> showCartCheckout());
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

        viewModel.cartCleared.observe(this, cartCleared -> {

            if (dialogue != null) {

                dialogue.cancel();
                Toast.makeText(this, "Checkout Successful", Toast.LENGTH_SHORT).show();
            }

        });

        viewModel.getSavedUser();
    }

    private void showCartCheckout() {

        if (CarAdapter.carList.isEmpty() || !CarAdapter.cartHasCar()) {

            Toast.makeText(this, "No Items", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] cartData = CarAdapter.getCartCarsFormatted();

        dialogue = new CartBuyDialog(this, () -> viewModel.clearCart());

        dialogue.show(cartData[0], cartData[1]);
    }
}