package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.material.appbar.MaterialToolbar;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_account);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Account");
        }

        RelativeLayout llOrder = findViewById(R.id.ll_order);
        RelativeLayout llSetting = findViewById(R.id.ll_setting);
        RelativeLayout llFeedback = findViewById(R.id.ll_feedback);
        RelativeLayout llTermsAndCond = findViewById(R.id.ll_termsandcond);
        ImageButton imgUserEdit = findViewById(R.id.img_user_edit);

        llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the OrderActivity
                Intent intent = new Intent(AccountActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });

        llSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SettingActivity
                Intent intent = new Intent(AccountActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        llFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the FeedbackActivity
                Intent intent = new Intent(AccountActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });

        llTermsAndCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the TermsAndConditionsActivity
                Intent intent = new Intent(AccountActivity.this, TermsAndConditionActivity.class);
                startActivity(intent);
            }
        });

        imgUserEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the EditUserActivity (replace with your actual activity)
                Intent intent = new Intent(AccountActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });


    }
}