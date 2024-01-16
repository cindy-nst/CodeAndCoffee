package com.example.codeandcoffee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.codeandcoffee.databinding.ActivityMainBinding;
import com.example.codeandcoffee.model.UserDetails;
import com.example.codeandcoffee.object.OrderHistoryItem;
import com.example.codeandcoffee.object.OrderMenu;
import com.example.codeandcoffee.ui.account.AccountFragment;
import com.example.codeandcoffee.ui.history.HistoryFragment;
import com.example.codeandcoffee.ui.home.HomeFragment;
import com.example.codeandcoffee.ui.menu.MenuFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION_SHOW_MENU_FRAGMENT = "show_menu_fragment";
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseHistory;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        goToFragment(new HomeFragment());

        // Handle the intent action
        handleIntentAction(getIntent());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            PaymentActivity.email = firebaseUser.getEmail();

        }
        databaseHistory = FirebaseDatabase.getInstance().getReference("history");
        databaseHistory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PaymentActivity.orderHistoryItems.clear();

                for (DataSnapshot historyDataSnapshot : snapshot.getChildren()) {
                    OrderHistoryItem orderHistoryItem = historyDataSnapshot.getValue(OrderHistoryItem.class);
                    if (orderHistoryItem != null && orderHistoryItem.getGmail() != null && orderHistoryItem.getGmail().equals(PaymentActivity.email)) {
                        PaymentActivity.orderHistoryItems.add(orderHistoryItem);
                    }
                }

                // Notify your adapter (if you are using one) that the data has changed
                // historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });


        MaterialToolbar toolbar = findViewById(R.id.toolbar_homepage);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        Boolean state = sharedPreferences.getBoolean("State", false);

        if (state) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                goToFragment(new HomeFragment());
            }
            if (item.getItemId() == R.id.nav_menu) {
                goToFragment(new MenuFragment());
            }
            if (item.getItemId() == R.id.nav_history) {

                goToFragment(new HistoryFragment());
            }
            if (item.getItemId() == R.id.nav_account) {
                goToFragment(new AccountFragment());
            }
            return true;
        });

    }

    private void handleIntentAction(Intent intent) {
        if (intent != null && intent.getAction() != null) {
            if (intent.getAction().equals(MainActivity.ACTION_SHOW_MENU_FRAGMENT)) {
                binding.bottomNavigationView.setSelectedItemId(R.id.nav_menu);
                goToFragment(new MenuFragment());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homepage_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            getApplicationContext().deleteSharedPreferences("Pref");
            getApplicationContext().deleteSharedPreferences("Settings");
            firebaseAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            saveUserInfo();
        }
    }

    private void goToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void saveUserInfo() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("user_details")
                .orderByChild("email")
                .equalTo(firebaseUser.getEmail())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);

                            SharedPreferences sharedPreferences = getSharedPreferences("Pref", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Gson gson = new Gson();
                            String str = gson.toJson(userDetails);
                            editor.putString("User", str);
                            editor.apply();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

    }

}