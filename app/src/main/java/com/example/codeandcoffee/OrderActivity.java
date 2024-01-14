package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    TextView name,description,quantity,total,add,minus;
    Button reg,large,normal,half,slight,non,addtocart;
    CheckBox whipped,caramel,drizzle;
    ImageView pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        name = findViewById(R.id.tv_title);
        description = findViewById(R.id.tv_desc);
        quantity = findViewById(R.id.tv_quantity);
        total = findViewById(R.id.tv_total);
        add = findViewById(R.id.tv_add);
        minus = findViewById(R.id.tv_minus);

        reg = findViewById(R.id.btn_regular);
        large = findViewById(R.id.btn_large);
        normal = findViewById(R.id.btn_normal_sugar);
        half = findViewById(R.id.btn_half_sugar);
        slight = findViewById(R.id.btn_slight_sugar);
        non = findViewById(R.id.btn_non_sugar);
        addtocart = findViewById(R.id.btn_addtocard);

        whipped = findViewById(R.id.cb_whipped_creme);
        caramel = findViewById(R.id.cb_carameldriz);
        drizzle = findViewById(R.id.cb_chocodrz);

        pic = findViewById(R.id.foodpic);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("coffeeName"));

    }
}