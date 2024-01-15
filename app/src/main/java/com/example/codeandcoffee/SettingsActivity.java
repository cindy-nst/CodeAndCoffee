package com.example.codeandcoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

    SwitchMaterial switchDarkTheme;
    TextView tvDarkTheme, tvHelpCenter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferenceEditor;

    Button btnDeleteAccount;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MaterialToolbar toolbar = findViewById(R.id.toolbar_HelpCentre);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(Boolean.TRUE);
            getSupportActionBar().setTitle("Settings");
            toolbar.setNavigationOnClickListener(view -> getOnBackPressedDispatcher().onBackPressed());
        }


        switchDarkTheme = findViewById(R.id.switch_dark_theme);
        tvDarkTheme = findViewById(R.id.tv_dark_theme);
        tvHelpCenter = findViewById(R.id.tv_help_center);
        btnDeleteAccount = findViewById(R.id.btn_delete_account);
        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        Boolean state = sharedPreferences.getBoolean("State", false);

        if (state) {
            switchDarkTheme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            switchDarkTheme.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        switchDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPreferenceEditor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
                    sharedPreferenceEditor.putBoolean("State", true);
                    sharedPreferenceEditor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPreferenceEditor.putBoolean("State", false);
                    sharedPreferenceEditor.apply();
                }
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
                alert.setTitle("Delete Account");
                alert.setMessage("Are you sure you want to delete your account?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            FirebaseAuth.getInstance().signOut();
                            user.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext(), "Delete account successful", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e("Delete Account", "Error deleting account", e);
                                        }
                                    });
                        } else {
                            Toast.makeText(getApplicationContext(), "Something is wrong, error deleting account", Toast.LENGTH_SHORT).show();
                            Log.e("Delete Account", "User not found");
                        }
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alert.show();
            }
        });

        tvHelpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the HelpCenterActivity
                Intent intent = new Intent(SettingsActivity.this, HelpCentreActivity.class);
                startActivity(intent);
            }
        });
    }
}