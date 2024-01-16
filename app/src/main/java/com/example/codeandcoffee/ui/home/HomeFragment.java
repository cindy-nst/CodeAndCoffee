package com.example.codeandcoffee.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.codeandcoffee.MainActivity;
import com.example.codeandcoffee.R;
import com.example.codeandcoffee.model.UserDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class HomeFragment extends Fragment {
    ViewFlipper viewFlipper;
    private int[] sliderImages = {R.drawable.slider_1, R.drawable.slider_2, R.drawable.slider_3, R.drawable.slider_4, R.drawable.slider_5, R.drawable.slider_6};

    private TextView tvUsernameDisplay;
    private String greetingMessage;
    private SharedPreferences sharedPreferences;
    private UserDetails userDetails;

    private Button buttonorder;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDetails = loadUserInfo();
        LocalTime localTime = LocalTime.now();
        if (localTime.getHour() >= 6 && localTime.getHour() < 12) {
            greetingMessage = "Good Morning, " + userDetails.getUsername();
        } else if (localTime.getHour() >= 12 && localTime.getHour() < 18) {
            greetingMessage = "Good Afternoon, " + userDetails.getUsername();
        } else {
            greetingMessage = "Good Evening, " + userDetails.getUsername();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvUsernameDisplay = view.findViewById(R.id.tv_username_display);
//        userDetails = loadUserInfo();
//        LocalTime localTime = LocalTime.now();
//        if (localTime.getHour() >= 6 && localTime.getHour() < 12) {
//            tvUsernameDisplay.setText("Good Morning, " + userDetails.getUsername());
//        } else if (localTime.getHour() >= 12 && localTime.getHour() < 18) {
//            tvUsernameDisplay.setText("Good Afternoon, " + userDetails.getUsername());
//        } else {
//            tvUsernameDisplay.setText("Good Evening, " + userDetails.getUsername());
//        }
        tvUsernameDisplay.setText(greetingMessage);
        viewFlipper = view.findViewById(R.id.viewFlipper);
        for (int resId : sliderImages) {
            addImageToViewFlipper(resId);
        }

        buttonorder = view.findViewById(R.id.btn_delivery);
        buttonorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the MenuFragment when the button is clicked
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setAction(MainActivity.ACTION_SHOW_MENU_FRAGMENT);
                startActivity(intent);
            }
        });
        return view;
    }

    private void addImageToViewFlipper(int resId) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(resId);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewFlipper.addView(imageView);
        viewFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(Boolean.TRUE);
    }

    private UserDetails loadUserInfo() {
        UserDetails userDetails = new UserDetails();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("Pref", MODE_PRIVATE);
        String record = sharedPreferences.getString("User", null);
        if (record != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<UserDetails>() {
            }.getType();
            userDetails = gson.fromJson(record, type);
        }
        return userDetails;
    }
}