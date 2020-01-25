package com.wakdyan.mystore.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wakdyan.mystore.R;
import com.wakdyan.mystore.data.local.Checkout;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<Checkout> mCheckouts = new ArrayList<>();

    public void setCheckouts(List<Checkout> checkouts) {
        if (checkouts == null) return;

        mCheckouts.clear();
        mCheckouts.addAll(checkouts);
    }

    private ArrayList<Checkout> getCheckouts() {
        return mCheckouts;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        final Checkout current = getCheckouts().get(position);

        holder.textName.setText(current.getName());
        holder.textPrice.setText(current.getPrice());
        holder.textAmount.setText(context.getString(R.string.dummy_qty, current.getAmount()));

        Glide.with(context)
                .load(current.getUrl())
                .into(holder.imageHistory);
    }

    @Override
    public int getItemCount() {
        return mCheckouts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textPrice, textAmount;
        ImageView imageHistory;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_history_name);
            textPrice = itemView.findViewById(R.id.tv_history_price);
            textAmount = itemView.findViewById(R.id.tv_history_amount);
            imageHistory = itemView.findViewById(R.id.iv_history_image);
        }
    }
}
