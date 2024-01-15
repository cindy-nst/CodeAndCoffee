package com.example.codeandcoffee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeandcoffee.object.OrderHistoryItem;
import com.example.codeandcoffee.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<OrderHistoryItem> orderHistoryList;

    public HistoryAdapter(List<OrderHistoryItem> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_history_item, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        OrderHistoryItem historyItem = orderHistoryList.get(position);
        holder.itemName.setText(historyItem.getItemName());
        holder.orderDate.setText(historyItem.getOrderDate());
        holder.ratingBar.setRating(historyItem.getRating());
        // Set the image here if you have an image property in OrderHistoryItem
    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView itemName;
        private TextView orderDate;
        private RatingBar ratingBar;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.text_name);
            orderDate = itemView.findViewById(R.id.text_order_date);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}

