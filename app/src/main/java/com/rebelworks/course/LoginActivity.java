package com.rebelworks.course;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.rebelworks.helper.SessionManager;
import com.rebelworks.model.request.LoginRequest;
import com.rebelworks.model.response.LoginResponse;
import com.rebelworks.network.ServiceAPI;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etUsername, etPassword;
    private TextInputLayout tilUsername, tilPassword;
    private Button btnLogin;
    private ProgressBar pbBar;
    private TextView tvRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        etUsername = findViewById(R.id.et_username);
        tilUsername = findViewById(R.id.til_username);

        etPassword = findViewById(R.id.et_password);
        tilPassword = findViewById(R.id.til_password);

        tvRegister = findViewById(R.id.tvRegister);

        pbBar = findViewById(R.id.pb_bar);
        pbBar.setVisibility(View.GONE);

        btnLogin = findViewById(R.id.btn_login);

        openRegister();
        requestLogin();
    }

    private void openRegister() {
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void requestLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {

            private String username = "";
            private String password = "";

            @Override
            public void onClick(View v) {

                username = Objects.requireNonNull(etUsername.getText()).toString().trim();
                password = Objects.requireNonNull(etPassword.getText()).toString().trim();

                // Simpan username ke pref
                SessionManager.putString(LoginActivity.this, "username", username);

                if (!isFieldsValid()) return;

                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername(username);
                loginRequest.setPassword(password);

                buttonAndLoadingState(true);
                ServiceAPI.service().requestLogin(loginRequest).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        buttonAndLoadingState(false);
                        try {
                            if (response.isSuccessful() && response.body() != null) {
                                String json = response.body().string();
                                LoginResponse loginResponse = new Gson().fromJson(json, LoginResponse.class);

                                if (loginResponse.getCode() != 0) {
                                    Toast.makeText(LoginActivity.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                Log.i("onResponse", "Berhasil");
                                Toast.makeText(LoginActivity.this, loginResponse.getData().getToken(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.putExtra("login_response", loginResponse);
                                startActivity(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                        buttonAndLoadingState(false);
                    }
                });
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

    private void buttonAndLoadingState(boolean isLoading){
        if (isLoading) {
            pbBar.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            return;
        }

        pbBar.setVisibility(View.INVISIBLE);
        btnLogin.setVisibility(View.VISIBLE);
    }
}
