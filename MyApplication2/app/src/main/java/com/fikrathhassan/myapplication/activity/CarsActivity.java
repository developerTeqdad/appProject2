package com.fikrathhassan.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.fikrathhassan.myapplication.MainActivity;
import com.fikrathhassan.myapplication.R;
import com.fikrathhassan.myapplication.adapter.CarsAdapter;
import com.fikrathhassan.myapplication.model.CarRegistration;
import com.fikrathhassan.myapplication.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class CarsActivity extends AppCompatActivity {

    private Utils utils;
    private ImageView btn_logout;

    private List<CarRegistration> carsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        utils = new Utils(CarsActivity.this);
        utils.initBottomDialog();

        initViews();
        initFunctions();
        initRecycleView();
    }

    private void initViews() {
        btn_logout = findViewById(R.id.btn_logout);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initFunctions() {
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.setLogin(false);
                Toast.makeText(CarsActivity.this, "User logged out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CarsActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void initRecycleView() {
        prepareCarData();
        mAdapter = new CarsAdapter(carsList, new CarsAdapter.ClickListener() {
            @Override
            public void onItemClick(int position) {
                CarRegistration car = carsList.get(position);

                startActivity(new Intent(CarsActivity.this, DetailsActivity.class)
                        .putExtra("data", car));
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    private void prepareCarData() {
        CarRegistration car = new CarRegistration();
        car.setName("Hyundai Venue");
        car.setImage("https://imgd.aeplcdn.com/310x174/n/cw/ec/106257/venue-exterior-right-front-three-quarter-2.jpeg?isig=0&q=75");
        car.setPrice("7,53,000");
        car.setModel("2022");
        car.setColor("Red");
        carsList.add(car);

        CarRegistration car1 = new CarRegistration();
        car1.setName("Hyundai Creta");
        car1.setImage("https://imgd.aeplcdn.com/310x174/n/cw/ec/41564/hyundai-creta-right-front-three-quarter9.jpeg?q=75");
        car1.setPrice("10,44,000");
        car1.setModel("2022");
        car1.setColor("Black");
        carsList.add(car1);

        CarRegistration car2 = new CarRegistration();
        car2.setName("Maruti Swift");
        car2.setImage("https://imgd.aeplcdn.com/310x174/n/cw/ec/54399/exterior-right-front-three-quarter-10.jpeg?q=75");
        car2.setPrice("5,91,000");
        car2.setModel("2020");
        car2.setColor("Red");
        carsList.add(car2);

        CarRegistration car3 = new CarRegistration();
        car3.setName("Tata Nexon");
        car3.setImage("https://imgd.aeplcdn.com/310x174/n/cw/ec/41645/tata-nexon-right-front-three-quarter3.jpeg?q=75");
        car3.setPrice("7,54,000");
        car3.setModel("2022");
        car3.setColor("Green");
        carsList.add(car3);

        CarRegistration car4 = new CarRegistration();
        car4.setName("Renault Kiger");
        car4.setImage("https://imgd.aeplcdn.com/310x174/n/cw/ec/115135/kiger-exterior-right-front-three-quarter-4.jpeg?isig=0&q=75");
        car4.setPrice("5,99,000");
        car4.setModel("2022");
        car4.setColor("Red");
        carsList.add(car4);

        CarRegistration car5 = new CarRegistration();
        car5.setName("Mahindra XUV700");
        car5.setImage("https://imgd.aeplcdn.com/310x174/n/cw/ec/42355/xuv700-exterior-right-front-three-quarter.jpeg?isig=0&q=75");
        car5.setPrice("13,18,000");
        car5.setModel("2022");
        car5.setColor("Blue");
        carsList.add(car5);

        CarRegistration car6 = new CarRegistration();
        car6.setName("Hyundai Grant i10");
        car6.setImage("https://imgd.aeplcdn.com/310x174/n/cw/ec/35465/grand-i10-nios-exterior-right-front-three-quarter-2.jpeg?q=75");
        car6.setPrice("5,39,000");
        car6.setModel("2019");
        car6.setColor("Red");
        carsList.add(car6);
    }
}