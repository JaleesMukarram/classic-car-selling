package com.android.classiccarselling.adapters;

import static com.android.classiccarselling.global.Constants.FILTER_BRAND_INTENT_KEY;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.classiccarselling.databinding.ItemBrandBinding;
import com.android.classiccarselling.model.Brand;
import com.android.classiccarselling.ui.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandVH> {

    private final Context context;
    private final List<Brand> brandList;

    public BrandAdapter(Context context, List<Brand> brandList) {
        this.context = context;
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

        Picasso.get().load(brandList.get(position).getImage().getDownloadURL())
                .into(holder.binding.imageView2);

        holder.binding.rootContainer.setOnClickListener(view -> {

            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(FILTER_BRAND_INTENT_KEY, brandList.get(position).getName().toLowerCase());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }

    static class BrandVH extends RecyclerView.ViewHolder {

        private final ItemBrandBinding binding;

        public BrandVH(@NonNull ItemBrandBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
