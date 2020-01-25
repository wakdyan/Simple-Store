package com.wakdyan.mystore.ui.favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wakdyan.mystore.R;
import com.wakdyan.mystore.data.local.Product;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private ArrayList<Product> mProducts = new ArrayList<>();

    public void setFavorites(List<Product> products) {
        if (products == null) return;

        mProducts.clear();
        mProducts.addAll(products);
    }

    private ArrayList<Product> getFavorites() {
        return mProducts;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        final Product current = getFavorites().get(position);

        holder.textName.setText(current.getName());
        holder.textPrice.setText(current.getPrice());
        holder.ratingProduct.setRating((float) current.getVoteAverage());

        Glide.with(context)
                .load(current.getUrl())
                .into(holder.imageProduct);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textPrice;
        ImageView imageProduct;
        RatingBar ratingProduct;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_favorite_name);
            textPrice = itemView.findViewById(R.id.tv_favorite_price);
            imageProduct = itemView.findViewById(R.id.iv_favorite_image);
            ratingProduct = itemView.findViewById(R.id.rb_favorite);
        }
    }
}
