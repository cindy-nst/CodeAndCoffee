package com.example.codeandcoffee.ui.account;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.codeandcoffee.AccountActivity;
import com.example.codeandcoffee.EditProfileActivity;
import com.example.codeandcoffee.FeedbackActivity;
import com.example.codeandcoffee.MainActivity;
import com.example.codeandcoffee.OrderActivity;
import com.example.codeandcoffee.R;
import com.example.codeandcoffee.SettingsActivity;
import com.example.codeandcoffee.TermsAndConditionActivity;
import com.example.codeandcoffee.model.UserDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

public class AccountFragment extends Fragment {
    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        TextView orderTextView = view.findViewById(R.id.tv_acc_order);
        TextView settingTextView = view.findViewById(R.id.tv_acc_set);
        TextView feedbackTextView = view.findViewById(R.id.tv_acc_feed);
        TextView termsTextView = view.findViewById(R.id.tv_acc_term);
        TextView tvAccUsername = view.findViewById(R.id.tv_acc_username);
        TextView tvAccEmail = view.findViewById(R.id.tv_acc_email);
        ImageButton imgUserEdit = view.findViewById(R.id.img_user_edit);

        UserDetails userDetails = loadUserInfo();
        tvAccUsername.setText(userDetails.getUsername());
        tvAccEmail.setText(userDetails.getEmail());

        imgUserEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        orderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setAction(MainActivity.ACTION_SHOW_MENU_FRAGMENT);
                startActivity(intent);
            }
        });

        settingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        feedbackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FeedbackActivity.class);
                startActivity(intent);
            }
        });

        termsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TermsAndConditionActivity.class);
                startActivity(intent);
            }
        });
        return view;
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