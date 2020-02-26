package com.rebelworks.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tv_welcome);
        initData();
    }

    private void initData() {
        if (getIntent() != null) {
            String username = getIntent().getStringExtra("username_key");
            String password = getIntent().getStringExtra("password_key");
            tvWelcome.setText("Welcome mother " + username + " " + password);
        }
    }
}
