package com.rebelworks.course;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.rebelworks.model.response.LoginResponse;

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
            LoginResponse loginResponse = (LoginResponse) getIntent().getSerializableExtra("login_response");
            String password = getIntent().getStringExtra("password_key");
            tvWelcome.setText("Your token " + loginResponse.getData().getToken() + " " + password);
        }
    }
}
