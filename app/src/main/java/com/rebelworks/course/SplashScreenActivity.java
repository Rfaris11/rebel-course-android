package com.rebelworks.course;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class SplashScreenActivity extends AppCompatActivity {

    /**
     * 1. Handle, Design & Animation of Splash Screen
     * 2. Localization Value Strings, Colors, Styles
     * 3. Learn Setup Third Party Library
     * 4. Learn Import Asset & XML (Custom View)
     */

    private static final long DELAYED_TIME = TimeUnit.SECONDS.toMillis(2);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        init();
    }

    private void init() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, DELAYED_TIME);
    }
}
