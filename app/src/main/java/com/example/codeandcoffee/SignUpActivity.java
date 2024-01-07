package com.example.codeandcoffee;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText etUsername, etEmail, etPassword, etPhoneNumber, etBirthday;
    private Button btnSignUp;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_signup);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Registration");
            toolbar.setNavigationOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
        }

        etUsername = findViewById(R.id.et_username);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPhoneNumber = findViewById(R.id.et_phone_number);
        etBirthday = findViewById(R.id.et_birthday);
        btnSignUp = findViewById(R.id.btn_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etBirthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpUser();
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }

    private void signUpUser() {
        String email = etEmail.getText().toString().toLowerCase().trim();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
            builder.setMessage("Email and Password are required for creating an account")
                    .setTitle("Warning")
                    .setPositiveButton("OK", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                Toast.makeText(SignUpActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Failed to sign up", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}