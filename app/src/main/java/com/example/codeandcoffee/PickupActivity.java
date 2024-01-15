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
