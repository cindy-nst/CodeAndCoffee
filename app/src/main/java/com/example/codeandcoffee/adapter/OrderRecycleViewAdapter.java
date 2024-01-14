package com.example.codeandcoffee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeandcoffee.R;
import com.example.codeandcoffee.object.CoffeeMenuItem;
import com.example.codeandcoffee.object.OrderMenu;

import java.util.List;

public class OrderRecycleViewAdapter extends RecyclerView.Adapter<OrderRecycleViewAdapter.MenuViewHolder>{

    private List<OrderMenu> menuItems;
    private Context context;

    public OrderRecycleViewAdapter(List<OrderMenu> menuItems, Context context) {
        this.menuItems = menuItems;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderRecycleViewAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View order_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_row, null);
        MenuViewHolder menuVH = new MenuViewHolder(order_row);
        return menuVH;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.itemName.setText(menuItems.get(position).getName());
        holder.itemPrice.setText(String.format("RM%.2f",menuItems.get(position).getPrice()));
        holder.itemImage.setImageResource(menuItems.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemName;
        private TextView itemPrice;
        private TextView itemQuantity;
        private TextView itemOrderDetail;


        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image_item);
            itemName = itemView.findViewById(R.id.text_name);
            itemPrice = itemView.findViewById(R.id.text_price);
        }
    }
}
