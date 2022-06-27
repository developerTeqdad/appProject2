package com.fikrathhassan.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.fikrathhassan.myapplication.MainActivity;
import com.fikrathhassan.myapplication.R;
import com.fikrathhassan.myapplication.util.Utils;

public class SplashActivity extends AppCompatActivity {

    private Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        utils = new Utils(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (utils.getLogin()) {
                    startActivity(new Intent(SplashActivity.this, CarsActivity.class));
                    finish();
                    return;
                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}