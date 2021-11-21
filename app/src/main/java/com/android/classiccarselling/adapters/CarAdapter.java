package com.android.classiccarselling.adapters;

import static com.android.classiccarselling.global.Constants.CART_COLLECTION;
import static com.android.classiccarselling.global.Constants.CAR_INTENT_KEY;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.classiccarselling.R;
import com.android.classiccarselling.databinding.ItemCarBinding;
import com.android.classiccarselling.model.Car;
import com.android.classiccarselling.model.Cart;
import com.android.classiccarselling.ui.activity.OrderActivity;
import com.android.classiccarselling.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarVH> {

    private final Context context;
    private final List<Car> carList;
    private final List<Car> filteredList;
    private Cart cart;

    public CarAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
        this.filteredList = new ArrayList<>(carList);
    }

    @NonNull
    @Override
    public CarVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCarBinding carBinding = ItemCarBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CarVH(carBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarVH holder, int position) {

        Car car = filteredList.get(position);
        holder.carBinding.setCar(car);

        Picasso.get().load(car.getImages().get(0).getDownloadURL())
                .into(holder.carBinding.ivCar);

        if (car.isInCart()) {

            holder.carBinding.ivCart.setColorFilter(ContextCompat.getColor(context, R.color.colorOrange), android.graphics.PorterDuff.Mode.MULTIPLY);

        } else {

            holder.carBinding.ivCart.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);

        }

        holder.carBinding.mcvRootContainer.setOnClickListener(view -> {

            if (cart != null) {

                Intent intent = new Intent(context, OrderActivity.class);
                intent.putExtra(CAR_INTENT_KEY, car);
                intent.putExtra(CART_COLLECTION, cart);
                context.startActivity(intent);

            } else {

                Toast.makeText(context, "Cart not ready", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void reflectCart(Cart cart) {

        this.cart = cart;

        for (Car car : carList) {

            if (cart.getCarIds().contains(car.getId())) {
                car.setInCart(true);
            }
        }

        filteredList.clear();
        filteredList.addAll(carList);
        notifyDataSetChanged();
    }

    static class CarVH extends RecyclerView.ViewHolder {

        private ItemCarBinding carBinding;

        public CarVH(@NonNull ItemCarBinding itemCarBinding) {
            super(itemCarBinding.getRoot());
            carBinding = itemCarBinding;
        }
    }

    public void searchFilter(String lowerKeyWord) {

        filteredList.clear();

        if (lowerKeyWord.isEmpty()) {

            filteredList.addAll(carList);
            return;
        }


        for (Car car : carList) {

            if (car.getFullName().toLowerCase().contains(lowerKeyWord)) {
                filteredList.add(car);
            }
        }

        notifyDataSetChanged();
    }

    public List<Car> getCarList() {
        return carList;
    }
}
