package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class HelpCentreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_centre);
        @SuppressLint("WrongViewCast")
        RelativeLayout chatWithAdminLayout = findViewById(R.id.linearLayoutChatWithAdmin);
        chatWithAdminLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a new activity (intent) for chatting with admin
                Intent intent = new Intent(HelpCentreActivity.this, ChatWithBotActivity.class);
                startActivity(intent);
            }
        });

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