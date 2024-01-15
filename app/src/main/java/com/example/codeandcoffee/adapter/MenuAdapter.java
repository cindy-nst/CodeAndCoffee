package com.example.codeandcoffee.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
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
        CoffeeMenuItem currentItem = menuItems.get(position);

        holder.itemName.setText(currentItem.getName());
        holder.itemPrice.setText(String.format("RM%.2f", currentItem.getPrice()));
        holder.itemImage.setImageResource(currentItem.getImage());

        // Use currentItem.getCategory() to determine the category of the clicked item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OrderActivity.class);
                intent.putExtra("coffeeName", currentItem.getName());
                intent.putExtra("coffeeDescription", currentItem.getDescription());
                intent.putExtra("coffeePrice", currentItem.getPrice());
                intent.putExtra("coffeeImage", currentItem.getImage());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView itemName;
        private TextView itemPrice;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.image_item);
            itemName = itemView.findViewById(R.id.text_name);
            itemPrice = itemView.findViewById(R.id.text_price);
        }
    }
}
