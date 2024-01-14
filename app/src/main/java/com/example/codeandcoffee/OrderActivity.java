package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_order);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Order");
        }

    }
}