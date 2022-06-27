package com.fikrathhassan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fikrathhassan.myapplication.activity.CarRegistrationActivity;
import com.fikrathhassan.myapplication.activity.CarsActivity;
import com.fikrathhassan.myapplication.activity.UserRegistrationActivity;
import com.fikrathhassan.myapplication.util.Utils;

public class MainActivity extends AppCompatActivity {

    private Utils utils;
    private EditText et_email, et_password;
    private Button btn_submit;
    private TextView tvBtn_registerUser, tvBtn_registerCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initFunctions();
    }

    private void initViews() {
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_submit = findViewById(R.id.btn_submit);
        tvBtn_registerUser = findViewById(R.id.tvBtn_registerUser);
        tvBtn_registerCar = findViewById(R.id.tvBtn_registerCar);
    }

    private void initFunctions() {
        utils  =new Utils(MainActivity.this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if (email.equals(utils.getEmail()) && password.equals(utils.getPassword())) {
                    utils.setLogin(true);
                    startActivity(new Intent(MainActivity.this, CarsActivity.class));
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }

            }
        });
        tvBtn_registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserRegistrationActivity.class));
            }
        });
        tvBtn_registerCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CarRegistrationActivity.class));
            }
        });
    }
}