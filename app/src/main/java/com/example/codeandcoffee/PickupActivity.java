package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.codeandcoffee.adapter.OrderRecycleViewAdapter;
import com.example.codeandcoffee.object.OrderMenu;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PickupActivity extends AppCompatActivity {

    public static List<OrderMenu> MenuCart = new ArrayList<>();
    public static final String ASAP_PREFERENCE_KEY = "asap_preference";

    private boolean asapSelected = false;
    private TextView tvTimeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_pickup);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Pick Up");
            toolbar.setNavigationOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PickupActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        OrderRecycleViewAdapter orderRecycleViewAdapter = new OrderRecycleViewAdapter(MenuCart, PickupActivity.this);
        recyclerView.setAdapter(orderRecycleViewAdapter);

        tvTimeOrder = findViewById(R.id.tv_time_order);

        // Set click listener for the "Change time" TextView
        TextView tvChangeTime = findViewById(R.id.tv_change_time);
        tvChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the time picker dialog with an additional option for ASAP
                showTimePickerDialog();
            }
        });

        // Load the ASAP preference and set its initial value
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        asapSelected = preferences.getBoolean(ASAP_PREFERENCE_KEY, false);

        // Handle ASAP option based on its initial value
        if (asapSelected) {
            handleASAPOption();
        }
    }

    private void showTimePickerDialog() {
        // Get the current time
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        // Create a TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Check if ASAP is selected
                        if (asapSelected) {
                            // Handle ASAP option
                            handleASAPOption();
                        } else {
                            // Update the TextView with the selected time
                            updateOrderTime(hourOfDay, minute);
                        }
                    }
                },
                hour,
                minute,
                true // 24-hour format
        );

        // Add an option for ASAP
        timePickerDialog.setButton(TimePickerDialog.BUTTON_NEUTRAL, "ASAP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle ASAP option
                handleASAPOption();
            }
        });

        // Show the TimePickerDialog
        timePickerDialog.show();
    }

    private void handleASAPOption() {
        // Handle the ASAP option here
        Toast.makeText(this, "ASAP Option Selected", Toast.LENGTH_SHORT).show();
        // Update the TextView with ASAP
        updateOrderTime(0, 0); // You can adjust this to represent ASAP in your application
    }

    private void updateOrderTime(int hour, int minute) {
        // Check if the selected time is 00:00, then set a custom label like "ASAP"
        if (hour == 0 && minute == 0) {
            tvTimeOrder.setText("ASAP");
        } else {
            // Update the TextView with the selected time
            String selectedTime = String.format("%02d:%02d", hour, minute);
            tvTimeOrder.setText(selectedTime);
        }
    }
}
