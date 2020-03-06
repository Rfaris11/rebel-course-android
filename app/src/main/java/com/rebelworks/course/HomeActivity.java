package com.rebelworks.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.rebelworks.helper.SessionManager;
import com.rebelworks.model.response.LoginResponse;

public class HomeActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = SessionManager.getString(HomeActivity.this,"username");


        tvWelcome = findViewById(R.id.tv_welcome);
        initData();
    }

    private void initData() {
        if (getIntent() != null) {
            LoginResponse loginResponse = (LoginResponse) getIntent().getSerializableExtra("login_response");
            String password = getIntent().getStringExtra("password_key");
            tvWelcome.setText("Your token " + loginResponse.getData().getToken() + " " + password);
            SessionManager.putString(HomeActivity.this, "token", "Bearer " + loginResponse.getData().getToken());

        }
        tvWelcome.setText(username);

    }

}
