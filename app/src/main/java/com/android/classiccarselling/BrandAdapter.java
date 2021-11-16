package com.android.classiccarselling;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.classiccarselling.databinding.ItemBrandBinding;
import com.android.classiccarselling.model.Brand;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandVH> {

    private final List<Brand> brandList;

    public BrandAdapter(List<Brand> brandList) {
        this.brandList = brandList;

    }

    @NonNull
    @Override
    public BrandVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemBrandBinding binding = ItemBrandBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BrandVH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandVH holder, int position) {

        holder.binding.imageView2.setImageResource(brandList.get(position).getResource());
    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    static class BrandVH extends RecyclerView.ViewHolder {

        private ItemBrandBinding binding;

        public BrandVH(@NonNull ItemBrandBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
