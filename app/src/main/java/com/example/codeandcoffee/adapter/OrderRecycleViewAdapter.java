package com.example.codeandcoffee.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

    private static List<OrderMenu> menuItems;
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
    public int getItemCount() {
        return menuItems.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private ImageView itemImage;
        private TextView itemName;
        private TextView itemPrice;
        private TextView itemQuantity;
        private TextView itemOrderDetail;
        private TextView add;
        private TextView minus;
        Boolean whipped,caramel,chocolate;
        int quantity;
        double price;


        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.imageView);
            itemName = itemView.findViewById(R.id.tv_name);
            itemPrice = itemView.findViewById(R.id.tv_price);
            itemQuantity = itemView.findViewById(R.id.tv_quantity);
            itemOrderDetail = itemView.findViewById(R.id.tv_addon);
            add = itemView.findViewById(R.id.tv_plus);
            minus = itemView.findViewById(R.id.tv_minus);

        }

        public void bind(OrderMenu orderMenu) {
            itemImage.setImageResource(orderMenu.getImage());
            itemName.setText(orderMenu.getName());
            price = orderMenu.getPrice();
            itemOrderDetail.setText(orderMenu.getOrderDetail());
            quantity = orderMenu.getQuantity();
            itemQuantity.setText(String.valueOf(quantity));
            //  get other properties like whipped, caramel, chocolate here
            whipped = orderMenu.getWhippedCreame();
            caramel = orderMenu.getCaramelDrizzled();
            chocolate = orderMenu.getChocolateDrizzled();
            showprice();

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity++;
                    itemQuantity.setText(String.valueOf(quantity));
                    showprice();
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    quantity--;
                    if(quantity<1){
                        // Show an alert message and delete the data in the list
                        showDeleteConfirmationDialog();
                    }
                    itemQuantity.setText(String.valueOf(quantity));
                    showprice();
                }
            });
        }

        private void showDeleteConfirmationDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("Remove Item");
            builder.setMessage("Are you sure you want to remove this item?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Delete the data from the list or perform the desired action
                    // You can use 'getAdapterPosition()' to get the current position in the adapter
                    // and remove the corresponding item from the list
                    int adapterPosition = getAdapterPosition();
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        menuItems.remove(adapterPosition);
                        notifyDataSetChanged(); // Notify the adapter about the dataset change

                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Restore the quantity to 1 if the user chooses not to delete
                    quantity = 1;
                    itemQuantity.setText(String.valueOf(quantity));
                    showprice();

                    // Dismiss the dialog
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }


        public void showprice() {
            double temp = price;
            temp*=quantity;
            if(whipped)
                temp+=1.20;
            if(caramel)
                temp+=1;
            if(chocolate)
                temp+=1;
            itemPrice.setText(String.format("RM %.2f", temp));

        }
    }
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        OrderMenu orderMenu = menuItems.get(position);
        holder.bind(orderMenu);
    }



}
