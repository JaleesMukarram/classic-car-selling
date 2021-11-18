package com.android.classiccarselling.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.classiccarselling.databinding.ItemCarBinding;
import com.android.classiccarselling.model.Car;
import com.android.classiccarselling.ui.activity.OrderActivity;
import com.android.classiccarselling.utils.CommonUtils;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarVH> {

    private Context context;
    private final List<Car> carList;

    public CarAdapter(Context context,List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCarBinding carBinding = ItemCarBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CarVH(carBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarVH holder, int position) {

        holder.carBinding.mcvRootContainer.setOnClickListener(view -> CommonUtils.changeActivity((Activity) context, OrderActivity.class, false));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    static class CarVH extends RecyclerView.ViewHolder {

        private ItemCarBinding carBinding;

        public CarVH(@NonNull ItemCarBinding itemCarBinding) {
            super(itemCarBinding.getRoot());
            carBinding = itemCarBinding;
        }
    }
}
