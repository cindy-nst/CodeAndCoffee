package com.example.codeandcoffee.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeandcoffee.OrderActivity;
import com.example.codeandcoffee.R;
import com.example.codeandcoffee.object.CoffeeMenuItem;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<CoffeeMenuItem> menuItems;
    private Context context;

    public MenuAdapter(List<CoffeeMenuItem> menuItems, Context context) {
        this.menuItems = menuItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recycler_menu_row = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_menu_row, null);
        MenuViewHolder menuVH = new MenuViewHolder(recycler_menu_row);
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

    public class MenuViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        private ImageView itemImage;
        private TextView itemName;
        private TextView itemPrice;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image_item);
            itemName = itemView.findViewById(R.id.text_name);
            itemPrice = itemView.findViewById(R.id.text_price);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), OrderActivity.class);
            intent.putExtra("coffeeName",menuItems.get(getAdapterPosition()).getName());
            v.getContext().startActivity(intent);
        }
    }

}
