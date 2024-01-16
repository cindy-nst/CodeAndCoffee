package com.example.codeandcoffee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class AccountActivity extends AppCompatActivity {

    private ImageButton imgUserEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        TextView orderTextView = findViewById(R.id.tv_order);
        TextView settingTextView = findViewById(R.id.tv_setting);
        TextView feedbackTextView = findViewById(R.id.tv_feedback);
        TextView termsTextView = findViewById(R.id.tv_termsandcond);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        Boolean state = sharedPreferences.getBoolean("State", false);

        if (state) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


        imgUserEdit = findViewById(R.id.img_user_edit);

        imgUserEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        orderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });

        settingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        feedbackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });

        termsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, TermsAndConditionActivity.class);
                startActivity(intent);
            }
        });
    }
}