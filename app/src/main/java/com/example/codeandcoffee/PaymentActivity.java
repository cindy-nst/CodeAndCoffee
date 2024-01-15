package com.example.codeandcoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.codeandcoffee.model.UserDetails;
import com.example.codeandcoffee.object.OrderHistoryItem;
import com.example.codeandcoffee.object.OrderMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private String selectedDate;
    public static List<OrderHistoryItem> orderHistoryItems = new ArrayList<>();
    private Button oky;
    private String gmail;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseHistory;
    private DatabaseReference databaseReference;
    private UserDetails currentUserDetails;
    public static String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        selectedDate = getIntent().getStringExtra("selectedDate");

        oky = findViewById(R.id.btn_return);
        oky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                intent.setAction(MainActivity.ACTION_SHOW_MENU_FRAGMENT);
                startActivity(intent);

                addHistory();
                PickupActivity.MenuCart.clear();
            }
        });
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            email = firebaseUser.getEmail();

        }

        databaseHistory = FirebaseDatabase.getInstance().getReference("history");
        databaseHistory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderHistoryItems.clear();

                for(DataSnapshot historyDataSnapshot: snapshot.getChildren()) {
                    OrderHistoryItem orderHistoryItem = historyDataSnapshot.getValue(OrderHistoryItem.class);
                    if (orderHistoryItem != null && orderHistoryItem.getGmail() != null && orderHistoryItem.getGmail().equals(email)) {
                        orderHistoryItems.add(orderHistoryItem);
                    }
                }

                // Notify your adapter (if you are using one) that the data has changed
                // historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });



    }
    private void addHistory() {
        for (OrderMenu e : PickupActivity.MenuCart) {
            String itemName = e.getName(); //assign
            int bil = e.getQuantity();
            double price = e.getPrice();
            if(e.getWhippedCreame())
                price+=1.20;
            if(e.getCaramelDrizzled())
                price+=1;
            if(e.getChocolateDrizzled())
                price+=1;
            price = price*bil;

            String CoffeePrice = String.format("RM %.2f",price);

            String orderDate = selectedDate; //assign

            int imageCoffee = e.getImage(); //assign

            String desc = e.getOrderDetail();

            String id =databaseHistory.push().getKey();
            OrderHistoryItem newHistory = new OrderHistoryItem(id, email,itemName, bil, CoffeePrice, orderDate, imageCoffee, desc);

            databaseHistory.child(id).setValue(newHistory);

        }


    }
}