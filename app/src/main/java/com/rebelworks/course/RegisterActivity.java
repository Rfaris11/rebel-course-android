package com.rebelworks.course;

import android.content.Intent;
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
import com.rebelworks.model.request.RegisterRequest;
import com.rebelworks.model.response.RegisterResponse;
import com.rebelworks.network.ServiceAPI;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etUsername, etPassword;
    private TextInputLayout tilUsername, tilPassword;
    private Button btnRegister;
    private ProgressBar pbBar;
    private TextView tvSignin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        etUsername = findViewById(R.id.et_username);
        tilUsername = findViewById(R.id.til_username);

        etPassword = findViewById(R.id.et_password);
        tilPassword = findViewById(R.id.til_password);

        tvSignin = findViewById(R.id.tvSignin);

        pbBar = findViewById(R.id.pb_bar);
        pbBar.setVisibility(View.GONE);

        btnRegister = findViewById(R.id.btn_register);

        openLogin();
        requestRegister();
    }

    private void openLogin() {
        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void requestRegister() {
        btnRegister.setOnClickListener(new View.OnClickListener() {

            private String username = "";
            private String password = "";

            @Override
            public void onClick(View v) {

                username = Objects.requireNonNull(etUsername.getText()).toString().trim();
                password = Objects.requireNonNull(etPassword.getText()).toString().trim();

                if (!isFieldsValid()) return;

                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setUsername(username);
                registerRequest.setPassword(password);

                buttonAndLoadingState(true);
                ServiceAPI.service().requestRegister(registerRequest).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        buttonAndLoadingState(false);
                        try {
                            if (response.isSuccessful() && response.body() != null) {
                                String json = response.body().string();
                                RegisterResponse registerResponse = new Gson().fromJson(json, RegisterResponse.class);

                                if (registerResponse.getCode() != 0) {
                                    Toast.makeText(RegisterActivity.this, registerResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                Log.i("onResponse", "Berhasil");
                                Toast.makeText(RegisterActivity.this, registerResponse.getMsg(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finishAffinity();
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
            btnRegister.setVisibility(View.INVISIBLE);
            return;
        }

        pbBar.setVisibility(View.INVISIBLE);
        btnRegister.setVisibility(View.VISIBLE);
    }
}
