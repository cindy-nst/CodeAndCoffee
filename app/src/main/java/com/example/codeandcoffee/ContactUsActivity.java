package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class ContactUsActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton imgBtnCall, imgBtnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_HelpCentre);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Contact Us");
            toolbar.setNavigationOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());

            imgBtnCall = findViewById(R.id. img_btn_call);
            imgBtnEmail = findViewById(R.id.img_btn_email);

            imgBtnCall.setOnClickListener(this);
            imgBtnEmail.setOnClickListener(this);

        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.img_btn_call) {
            Toast.makeText(ContactUsActivity.this, "Call Us", Toast.LENGTH_SHORT).show();
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel: 0127720396"));

            if (callIntent.resolveActivity(getPackageManager()) != null ) {
                startActivity(callIntent);

            }
            else{
                Toast.makeText(ContactUsActivity.this, "Sorry no app can handle this action and data", Toast.LENGTH_SHORT).show();
            }


        } else if (id == R.id.img_btn_email) {
            Toast.makeText(ContactUsActivity.this, "Email Us", Toast.LENGTH_SHORT).show();
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Order from Code&Coffee");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message: Share Your Feedback.");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"empatbudakbijak@gmail.com"});
            if(emailIntent.resolveActivity(getPackageManager()) != null){
                startActivity(emailIntent);
            }
            else{
                Toast.makeText(ContactUsActivity.this, "Sorry no app can handle this action and data", Toast.LENGTH_SHORT).show();
            }
        }
    }
}