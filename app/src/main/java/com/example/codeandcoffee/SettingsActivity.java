package com.example.codeandcoffee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    SwitchMaterial switchPushNotification, switchLocation, switchDarkTheme;
    TextView tvLanguage, tvPushNotification, tvLocation, tvDarkTheme, tvHelpCenter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferenceEditor;

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

        switchPushNotification = findViewById(R.id.switch_push_notification);
        switchLocation = findViewById(R.id.switch_location);
        switchDarkTheme = findViewById(R.id.switch_dark_theme);
        tvLanguage = findViewById(R.id.tv_language);
        tvPushNotification = findViewById(R.id.tv_push_notification);
        tvLocation = findViewById(R.id.tv_location);
        tvDarkTheme = findViewById(R.id.tv_dark_theme);
        tvHelpCenter = findViewById(R.id.tv_help_center);
        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        Boolean state = sharedPreferences.getBoolean("State", false);

        if(state){
            switchDarkTheme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            switchDarkTheme.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        switchDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPreferenceEditor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
                    sharedPreferenceEditor.putBoolean("State",true);
                    sharedPreferenceEditor.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPreferenceEditor.putBoolean("State", false);
                    sharedPreferenceEditor.apply();
                }
            }
        });

    }
}