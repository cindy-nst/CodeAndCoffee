package com.example.codeandcoffee;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    EditText etFeed;

    Button btnSubmit;

    //a list to store the club and member information from firebase
    List<FeedbackActivity> feeds;


    //firebase database reference object
    DatabaseReference databaseFeed;

    public FeedbackActivity(String id, String name) {
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        etFeed = findViewById(R.id.et_feedback);


        btnSubmit = findViewById(R.id.btn_submit);

        databaseFeed = FirebaseDatabase.getInstance().getReference("Feedback");


        feeds = new ArrayList<>();


        databaseFeed.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                feeds.clear();


                for (DataSnapshot clubDataSnapshot : snapshot.getChildren()) {
                    FeedbackActivity fb = clubDataSnapshot.getValue(FeedbackActivity.class);
                    feeds.add(fb);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFeed();
            }
        });
    }

    private void addFeed(){
        String name = etFeed.getText().toString().trim();

        if(!TextUtils.isEmpty(name)){
            String id = databaseFeed.push().getKey();
            FeedbackActivity newStudent = new FeedbackActivity(id, name);
            databaseFeed.child(id).setValue(newStudent);

            etFeed.setText("");


            Toast.makeText(FeedbackActivity.this, "Thank You For Your Feedback", Toast.LENGTH_SHORT).show();
        }

    }
}