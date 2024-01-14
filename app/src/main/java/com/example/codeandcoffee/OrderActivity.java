package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {
    int jumlah = 1;
    double coffeePrice;
    boolean whipCreme = false, caramel=false, choco=false;
    TextView name,description,quantity,total,add,minus;
    Button reg,large,normal,half,slight,non,addtocart;
    CheckBox whipped,cardriz,chocodriz;
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
        cardriz = findViewById(R.id.cb_carameldriz);
        chocodriz = findViewById(R.id.cb_chocodrz);

        pic = findViewById(R.id.foodpic);

        Intent intent = getIntent();
        coffeePrice = getIntent().getDoubleExtra("coffeePrice", 0.0);
        name.setText(intent.getStringExtra("coffeeName"));
        description.setText(intent.getStringExtra("coffeeDescription"));
        //total.setText(intent.getStringExtra("coffeePrice"));
        showprice();
        pic.setImageResource(intent.getIntExtra("coffeeImage",0));

        //set buttonof size cup
        reg.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
        reg.setText(String.format("Regular\nRM %.2f",coffeePrice));
        large.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
        large.setText(String.format("Large\nRM %.2f",coffeePrice+2));
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg.setBackgroundTintList(getResources().getColorStateList(R.color.select));
                large.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
                coffeePrice = getIntent().getDoubleExtra("coffeePrice", 0.0);
                showprice();
            }
        });
        large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
                large.setBackgroundTintList(getResources().getColorStateList(R.color.select));
                coffeePrice+=2;
                showprice();
            }
        });
        reg.setBackgroundTintList(getResources().getColorStateList(R.color.select));
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah++;
                quantity.setText(""+jumlah);
                showprice();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlah--;
                if(jumlah < 1)
                    jumlah = 1;
                quantity.setText(""+jumlah);
                showprice();
            }
        });

        //set defaul color
        normal.setBackgroundTintList(getResources().getColorStateList(R.color.select));
        half.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
        slight.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
        non.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));

        normal.setOnClickListener(this::onClick);
        half.setOnClickListener(this::onClick);
        slight.setOnClickListener(this::onClick);
        non.setOnClickListener(this::onClick);
        addtocart.setBackgroundTintList(getResources().getColorStateList(R.color.select));

        whipped.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the CheckBox state change
                whipCreme = isChecked;
                showprice();
            }
        });
        cardriz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the CheckBox state change
                caramel= isChecked;
                showprice();
            }
        });
        chocodriz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the CheckBox state change
                choco = isChecked;
                showprice();
            }
        });

    }

    public void showprice() {
        double temp = coffeePrice;
        temp*=jumlah;
        if(whipCreme)
            temp+=1.20;
        if(caramel)
            temp+=1;
        if(choco)
            temp+=1;
        total.setText(String.format("RM %.2f",temp));

    }

    public void onClick(View view) {
        normal.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
        half.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
        slight.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
        non.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));

        int id = view.getId();
        normal = findViewById(R.id.btn_normal_sugar);
        half = findViewById(R.id.btn_half_sugar);
        slight = findViewById(R.id.btn_slight_sugar);
        non = findViewById(R.id.btn_non_sugar);
        if(id == R.id.btn_normal_sugar){
            normal.setBackgroundTintList(getResources().getColorStateList(R.color.select));
        }
        else if(id == R.id.btn_half_sugar){
            half.setBackgroundTintList(getResources().getColorStateList(R.color.select));
        }
        else if(id == R.id.btn_slight_sugar){
            slight.setBackgroundTintList(getResources().getColorStateList(R.color.select));
        }
        else if(id == R.id.btn_non_sugar){
            non.setBackgroundTintList(getResources().getColorStateList(R.color.select));
        }
    }
}