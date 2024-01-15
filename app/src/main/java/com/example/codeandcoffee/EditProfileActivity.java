package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etPhoneNumber, etBirthday;
    private Button btnEditProfile;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_edit_profile);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Edit Profile");
            toolbar.setNavigationOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
        }
        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        etBirthday = findViewById(R.id.et_birthday);
        btnEditProfile = findViewById(R.id.btn_editprofile);

        databaseReference = FirebaseDatabase.getInstance().getReference("user_details");

        if (getIntent() != null) {
            Intent intent = getIntent();
            id = intent.getStringExtra("id");
            etUsername.setText(intent.getStringExtra("Username"));
            etEmail.setText(intent.getStringExtra("Email"));
            etPassword.setText(intent.getStringExtra("Password"));
            etPhoneNumber.setText(intent.getStringExtra("Phone Number"));
            etBirthday.setText(intent.getStringExtra("Birthday"));
        }
    }


}