package com.fikrathhassan.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fikrathhassan.myapplication.R;

public class PaymentsActivity extends AppCompatActivity {
    
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PaymentsActivity.this, "Vehicle booked successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}