package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class PickupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_pickup);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Pick Up");
        }
    }
}