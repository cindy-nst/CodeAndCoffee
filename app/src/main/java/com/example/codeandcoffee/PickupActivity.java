package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.codeandcoffee.adapter.OrderRecycleViewAdapter;
import com.example.codeandcoffee.object.CoffeeMenuItem;
import com.example.codeandcoffee.object.OrderMenu;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class PickupActivity extends AppCompatActivity {

    public static List<OrderMenu> MenuCart = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_pickup);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Pick Up");
            //toolbar.setNavigationOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PickupActivity.this, MainActivity.class);
                    intent.setAction(MainActivity.ACTION_SHOW_MENU_FRAGMENT);
                    startActivity(intent);
                }
            });

        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PickupActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        OrderRecycleViewAdapter orderRecycleViewAdapter = new OrderRecycleViewAdapter(MenuCart,PickupActivity.this);
        recyclerView.setAdapter(orderRecycleViewAdapter);


    }
}