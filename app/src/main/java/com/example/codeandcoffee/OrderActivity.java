package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    TextView name,description,quantity,total;
    Button reg,large,normal,half,slight,non;
    CheckBox whipped,caramel,drizzle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }
}