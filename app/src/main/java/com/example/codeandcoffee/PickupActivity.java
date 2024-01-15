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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.codeandcoffee.adapter.OrderRecycleViewAdapter;
import com.example.codeandcoffee.object.CoffeeMenuItem;
import com.example.codeandcoffee.object.OrderMenu;
import com.google.android.material.appbar.MaterialToolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PickupActivity extends AppCompatActivity {
    public static String selectedDate;
    public static String selectedTime;
    public static List<OrderMenu> MenuCart = new ArrayList<>();
    private TextView addItem;
    private static TextView Subtotal;
    private static TextView Tax;
    private static TextView total;
    private Button ordernow;
    static double sub;
    static double tax;

    public static final String ASAP_PREFERENCE_KEY = "asap_preference";

    private boolean asapSelected = false;
    private TextView tvTimeOrder;
    private RadioButton radFPX,radCredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        Calendar currentTime = Calendar.getInstance();
        selectedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());

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
        ordernow = findViewById(R.id.btn_ordernow);

        update();

        radFPX = findViewById(R.id.radbtn_fpx);
        radCredit = findViewById(R.id.radbtn_credit);

        // Inside your onCreate method
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radFPX = findViewById(R.id.radbtn_fpx);
        radCredit = findViewById(R.id.radbtn_credit);

        ordernow.setBackgroundTintList(getResources().getColorStateList(R.color.diselect));
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
                        //Toast.makeText(PickupActivity.this, "FPX selected", Toast.LENGTH_SHORT).show();
                    } else if (radioButton == radCredit) {
                        //radFPX.setChecked(false);
                        //radCredit.setChecked(true);
                        // Handle radCredit selection
                        //Toast.makeText(PickupActivity.this, "Credit selected", Toast.LENGTH_SHORT).show();
                    }

                    ordernow.setBackgroundTintList(getResources().getColorStateList(R.color.select));
                    ordernow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(PickupActivity.this, PaymentActivity.class);
                            intent.putExtra("selectedDate", selectedDate);
                            startActivity(intent);
                        }
                    });
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

    /*private void showTimePickerDialog() {
        Calendar currentTime = Calendar.getInstance();
        selectedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());
        selectedTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(currentTime.getTime());
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
    }*/
    private void showTimePickerDialog() {
        Calendar currentTime = Calendar.getInstance();
        selectedDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());

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
                            // Format selectedTime inside this callback
                            selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                            tvTimeOrder.setText(selectedTime);
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
            selectedTime = String.format("%02d:%02d", hour, minute);
            tvTimeOrder.setText(selectedTime);
        }
    }
    public static void update(){
        sub=0;
        for (OrderMenu e : PickupActivity.MenuCart) {
            double price = e.getPrice();
            if(e.getWhippedCreame())
                price+=1.20;
            if(e.getCaramelDrizzled())
                price+=1;
            if(e.getChocolateDrizzled())
                price+=1;
            sub+= (price*e.getQuantity());
        }

        tax = 0.06 * sub;

        Subtotal.setText(String.format("RM %.2f",sub));
        Tax.setText(String.format("RM %.2f",tax));
        total.setText(String.format("RM %.2f",sub + tax));
    }
}