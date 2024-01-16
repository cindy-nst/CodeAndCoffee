package com.example.codeandcoffee;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.codeandcoffee.model.UserDetails;
import com.example.codeandcoffee.object.EditProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private UserDetails userDetails;
    private EditText etUsername, etEmail, etPassword, etPhoneNumber, etBirthday;
    private Button btnSave;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private String id;
    private ImageButton imgBtnEditPicture;
    private ImageView ivProfile;

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
        btnSave = findViewById(R.id.btn_save);
        imgBtnEditPicture = findViewById(R.id.img_btn_edit_picture);
        ivProfile = findViewById(R.id.iv_profile);

        userDetails = loadUserInfo();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("user_details");

        etUsername.setText(userDetails.getUsername());
        etEmail.setText(userDetails.getEmail());
        etPhoneNumber.setText(userDetails.getPhoneNumber().substring(3));
        etBirthday.setText(userDetails.getBirthday());

        etEmail.setEnabled(Boolean.FALSE);
        etPhoneNumber.setEnabled(Boolean.FALSE);
        etBirthday.setEnabled(Boolean.FALSE);

        btnSave.setOnClickListener(this);
        imgBtnEditPicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            saveUserInfo();
        }
        if (view.getId() == R.id.img_btn_edit_picture) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ivProfile.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(EditProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(EditProfileActivity.this, "You haven't picked Image", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserInfo() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("user_details").orderByChild("email").equalTo(firebaseUser.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    DataSnapshot child = snapshot.getChildren().iterator().next();
                    child.getRef().child("username").setValue(etUsername.getText().toString());

                    userDetails.setUsername(etPassword.getText().toString());

                    SharedPreferences sharedPreferences = getSharedPreferences("Pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String str = gson.toJson(userDetails);
                    editor.putString("User", str);
                    editor.apply();

                    if (!TextUtils.isEmpty(etPassword.getText().toString())) {
                        if (etPassword.getText().toString().length() >= 6) {
                            updatePassword(etPassword.getText().toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "Password length must be at least 6 characters", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private UserDetails loadUserInfo() {
        UserDetails userDetails = new UserDetails();
        SharedPreferences sharedPreferences = getSharedPreferences("Pref", MODE_PRIVATE);
        String record = sharedPreferences.getString("User", null);
        if (record != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<UserDetails>() {
            }.getType();
            userDetails = gson.fromJson(record, type);
        }
        return userDetails;
    }

    private void updatePassword(String newPassword) {
        firebaseUser.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("updatePassword", "User password updated");
                } else {
                    Toast.makeText(getApplicationContext(), "Password failed to update", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}