package com.wakdyan.mystore.ui.home;

import android.content.Context;
import android.content.Intent;
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
import com.wakdyan.mystore.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import static com.wakdyan.mystore.ui.detail.DetailActivity.ID;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private ArrayList<Product> mProductList = new ArrayList<>();

    public void setProductList(List<Product> productList) {
        if (productList == null) return;

        mProductList.clear();
        mProductList.addAll(productList);
    }

    private ArrayList<Product> getProductList() {
        return mProductList;
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeAdapter.HomeViewHolder holder, final int position) {
        final Context context = holder.itemView.getContext();
        final Product current = getProductList().get(position);

        holder.textName.setText(current.getName());
        holder.textPrice.setText(current.getPrice());
        holder.ratingProduct.setRating((float) current.getVoteAverage());

        final String url = "https://id.360buyimg.com/Indonesia/";

        Glide.with(context)
                .load(url + current.getUrl())
                .into(holder.imageProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product(current.getId(),
                        current.getVoteAverage(),
                        current.getName(),
                        current.getDescription(),
                        current.getPrice(),
                        url + current.getUrl());

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(ID, product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textPrice;
        ImageView imageProduct;
        RatingBar ratingProduct;

        HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.tv_product_name);
            textPrice = itemView.findViewById(R.id.tv_product_price);
            imageProduct = itemView.findViewById(R.id.iv_product_image);
            ratingProduct = itemView.findViewById(R.id.rb_product);
        }
    }
}
