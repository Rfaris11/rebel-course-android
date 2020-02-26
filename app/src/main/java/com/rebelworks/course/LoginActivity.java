package com.rebelworks.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etUsername, etPassword;
    private TextInputLayout tilUsername, tilPassword;
    private Button btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        etUsername = findViewById(R.id.et_username);
        tilUsername = findViewById(R.id.til_username);

        etPassword = findViewById(R.id.et_password);
        tilPassword = findViewById(R.id.til_password);

        btnLogin = findViewById(R.id.btn_login);

        requestLogin();
    }

    private void requestLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {

            private String username = "";
            private String password = "";

            @Override
            public void onClick(View v) {
                username = Objects.requireNonNull(etUsername.getText()).toString().trim();
                password = Objects.requireNonNull(etPassword.getText()).toString().trim();

                if (!isFieldsValid()) return;

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("username_key", username);
                intent.putExtra("password_key", password);
                startActivity(intent);
            }

            private boolean isFieldsValid() {
                if (username.isEmpty()) {
                    tilUsername.setError("Username tidak boleh kosong");
                    return false;
                } else if (password.isEmpty()) {
                    tilPassword.setError("Password tidak boleh kosong");
                    return false;
                } else if (username.contains(" ")) {
                    tilUsername.setError("Username tidak boleh menggunakan spasi");
                    return false;
                }
                tilUsername.setError(null);
                tilPassword.setError(null);
                return true;
            }
        });
    }
}
