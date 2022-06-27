package com.fikrathhassan.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fikrathhassan.myapplication.R;
import com.fikrathhassan.myapplication.model.CarRegistration;
import com.fikrathhassan.myapplication.util.Utils;

import java.util.Calendar;

public class DetailsActivity extends AppCompatActivity {

    private Utils utils;
    private CarRegistration car;

    private TextView tv_name, tv_price, tv_model, tv_color, tvBtn_date;
    private Button btn_submit;
    private ImageView iv_image;

    private DatePickerDialog picker;

    private boolean isDateSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null && getIntent().hasExtra("data")) {
            car = (CarRegistration) getIntent().getSerializableExtra("data");
        }

        setContentView(R.layout.activity_details);

        utils = new Utils(DetailsActivity.this);

        initViews();
        initFunctions();
    }

    private void initViews() {
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_model = findViewById(R.id.tv_model);
        tv_color = findViewById(R.id.tv_color);
        tvBtn_date = findViewById(R.id.tvBtn_date);
        btn_submit = findViewById(R.id.btn_submit);
        iv_image = findViewById(R.id.iv_image);
    }

    private void initFunctions() {
        tv_name.setText(car.getName());
        tv_price.setText("Rs. "+car.getPrice());
        tv_model.setText("Model: "+car.getModel());
        tv_color.setText("Colour: "+car.getColor());
        Glide.with(this).load(car.getImage()).into(iv_image);

        tvBtn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(DetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                isDateSelected = true;
                                btn_submit.setEnabled(true);
                                tvBtn_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsActivity.this, PaymentsActivity.class));
            }
        });
    }
}