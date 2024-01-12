package com.example.codeandcoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputEditText etResetEmail, etNewPassword, etConfirmPassword;
    private Button btnReset;
    private FirebaseAuth firebaseAuth;
    String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_forgotpassword);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Forgot Password");
            toolbar.setNavigationOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
        }

        etResetEmail = findViewById(R.id.et_resetemail);
        etNewPassword = findViewById(R.id.et_newpassword);
        etConfirmPassword = findViewById(R.id.et_confirmpassword);

        firebaseAuth = FirebaseAuth.getInstance();

//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
////            public void onClick(View view) {
////                strEmail = etResetEmail.getText().toString().trim();
////                if (!TextUtils.isEmpty(strEmail)) {
////
////                } else {
////                    etResetEmail.setError("Email field can't be empty");
////                }
////            }
////        });
  }

}