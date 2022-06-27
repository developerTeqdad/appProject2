package com.fikrathhassan.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fikrathhassan.myapplication.R;
import com.fikrathhassan.myapplication.util.Utils;

public class CarRegistrationActivity extends AppCompatActivity {

    private Utils utils;
    private EditText et_car_name, et_car_model, et_car_price, et_car_color;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_car);

        utils = new Utils(CarRegistrationActivity.this);

        initViews();
        initFunctions();
    }

    private void initViews() {
        et_car_name = findViewById(R.id.et_name);
        et_car_model = findViewById(R.id.et_model);
        et_car_price = findViewById(R.id.et_price);
        et_car_color = findViewById(R.id.et_color);
        btn_submit = findViewById(R.id.btn_submit);
    }

    private void initFunctions() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_car_name.getText().toString();
                String model = et_car_model.getText().toString();
                String price = et_car_price.getText().toString();
                String color = et_car_color.getText().toString();

                if (!name.isEmpty() && !model.isEmpty() && !price.isEmpty() && !color.isEmpty()) {
                    Toast.makeText(CarRegistrationActivity.this, "Car registration successfull!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CarRegistrationActivity.this, "Complete all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}