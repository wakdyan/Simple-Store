package com.wakdyan.mystore.ui.cart;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.wakdyan.mystore.R;
import com.wakdyan.mystore.data.local.Cart;
import com.wakdyan.mystore.data.local.Checkout;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private ArrayList<Cart> mCarts = new ArrayList<>();
    private CartViewModel mCartViewModel;


    public CartAdapter(CartViewModel cartViewModel) {
        mCartViewModel = cartViewModel;
    }

    public void setCart(List<Cart> carts) {
        if (carts == null) return;

        mCarts.clear();
        mCarts.addAll(carts);
    }

    private ArrayList<Cart> getCart() {
        return mCarts;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        final Cart current = getCart().get(position);

        final int id = current.getId();
        final int amount = current.getAmount();
        final double vote = current.getVoteAverage();

        final String name = current.getName();
        final String description = current.getDescription();
        final String price = current.getPrice();
        final String url = current.getUrl();

        holder.textName.setText(name);
        holder.textPrice.setText(String.valueOf(price));
        holder.textAmount.setText(String.valueOf(amount));

        Glide.with(context)
                .load(current.getUrl())
                .into(holder.imageCart);

        holder.buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCartViewModel.insertCheckout(new Checkout(id, amount, name, price, url));
                mCartViewModel.delete(id);
                Snackbar.make(view, "Product has been purchased", Snackbar.LENGTH_LONG).show();
            }
        });

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newAmount = amount + 1;
                mCartViewModel.insert(new Cart(id, newAmount, vote, name, description, price, url));
            }
        });

        holder.buttonMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                int newAmount = amount - 1;

                if (newAmount < 1) {
                    newAmount = 1;
                    mBuilder.setMessage("Are you sure remove this item?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int amount) {
                            mCartViewModel.delete(id);
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int amount) {

                        }
                    }).show();
                }
                mCartViewModel.insert(new Cart(id, newAmount, vote, name, description, price, url));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCarts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textPrice, textAmount;
        ImageView imageCart;
        ImageButton buttonAdd, buttonMin;
        Button buttonCheckout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.tv_cart_name);
            textPrice = itemView.findViewById(R.id.tv_cart_price);
            textAmount = itemView.findViewById(R.id.tv_cart_amount);
            imageCart = itemView.findViewById(R.id.iv_cart_image);
            buttonAdd = itemView.findViewById(R.id.bt_add);
            buttonMin = itemView.findViewById(R.id.bt_min);
            buttonCheckout = itemView.findViewById(R.id.bt_checkout);
        }
    }
}
