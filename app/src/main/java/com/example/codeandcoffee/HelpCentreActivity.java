package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.appbar.MaterialToolbar;

public class HelpCentreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_centre);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_HelpCentre);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Help Center");
        }

        @SuppressLint("WrongViewCast")
        RelativeLayout contactUsLayout = findViewById(R.id.linearLayoutContactUs);
        contactUsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new activity (intent) for contacting us
                Intent intent = new Intent(HelpCentreActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });
    }
}