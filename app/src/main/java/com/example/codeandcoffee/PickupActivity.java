package com.example.codeandcoffee;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.codeandcoffee.adapter.OrderRecycleViewAdapter;
import com.example.codeandcoffee.object.CoffeeMenuItem;
import com.example.codeandcoffee.object.OrderMenu;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PickupActivity extends AppCompatActivity {

    public static List<OrderMenu> MenuCart = new ArrayList<>();
    private TextView addItem, Subtotal, Tax, total;
    double sub=0, tax=0;

    public static final String ASAP_PREFERENCE_KEY = "asap_preference";

    private boolean asapSelected = false;
    private TextView tvTimeOrder;
    private RadioButton radFPX,radCredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        tvTimeOrder = findViewById(R.id.tv_time_order);

        TextView tvChangeTime = findViewById(R.id.tv_change_time);
        tvChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        asapSelected = preferences.getBoolean(ASAP_PREFERENCE_KEY, false);
        if (asapSelected) {
            handleASAPOption();
        }

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

        addItem = findViewById(R.id.tv_ad_items);
        Subtotal = findViewById(R.id.tv_subtotal);
        Tax = findViewById(R.id.tv_tax);
        total = findViewById(R.id.tv_total);

        for (OrderMenu e : PickupActivity.MenuCart) {
            sub+= (e.getPrice()*e.getQuantity());
        }

        tax = 0.06 * sub;

        Subtotal.setText(String.format("RM %.2f",sub));
        Tax.setText(String.format("RM %.2f",tax));
        total.setText(String.format("RM %.2f",sub + tax));
        radFPX = findViewById(R.id.radbtn_fpx);
        radCredit = findViewById(R.id.radbtn_credit);

        // Inside your onCreate method
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radFPX = findViewById(R.id.radbtn_fpx);
        radCredit = findViewById(R.id.radbtn_credit);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Handle radio button selection
                RadioButton radioButton = findViewById(checkedId);

                if (radioButton != null) {
                    // Check which radio button was selected
                    if (radioButton == radFPX) {
                        // Handle radFPX selection
                        //radFPX.setChecked(true);
                       //radCredit.setChecked(false);
                        Toast.makeText(PickupActivity.this, "FPX selected", Toast.LENGTH_SHORT).show();
                    } else if (radioButton == radCredit) {
                        //radFPX.setChecked(false);
                        //radCredit.setChecked(true);
                        // Handle radCredit selection
                        Toast.makeText(PickupActivity.this, "Credit selected", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickupActivity.this, MainActivity.class);
                intent.setAction(MainActivity.ACTION_SHOW_MENU_FRAGMENT);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PickupActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        OrderRecycleViewAdapter orderRecycleViewAdapter = new OrderRecycleViewAdapter(MenuCart,PickupActivity.this);
        recyclerView.setAdapter(orderRecycleViewAdapter);


    }
    private void showTimePickerDialog() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (asapSelected) {
                            handleASAPOption();
                        } else {
                            updateOrderTime(hourOfDay, minute);
                        }
                    }
                },
                hour,
                minute,
                true
        );

        timePickerDialog.setButton(TimePickerDialog.BUTTON_NEUTRAL, "ASAP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handleASAPOption();
            }
        });

        timePickerDialog.show();
    }

    private void handleASAPOption() {
        Toast.makeText(this, "ASAP Option Selected", Toast.LENGTH_SHORT).show();
        updateOrderTime(0, 0);
    }

    private void updateOrderTime(int hour, int minute) {
        if (hour == 0 && minute == 0) {
            tvTimeOrder.setText("ASAP");
        } else {
            String selectedTime = String.format("%02d:%02d", hour, minute);
            tvTimeOrder.setText(selectedTime);
        }
    }
}