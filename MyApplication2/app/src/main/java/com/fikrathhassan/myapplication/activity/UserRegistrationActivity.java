package com.fikrathhassan.myapplication.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.fikrathhassan.myapplication.R;
import com.fikrathhassan.myapplication.model.UserRegistration;
import com.fikrathhassan.myapplication.util.Utils;

public class UserRegistrationActivity extends AppCompatActivity {

    private Utils utils;
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 150;

    private EditText et_name, et_email, et_phone, et_password;
    private Button btn_image, btn_submit;
    private TextView tv_image;
    private ImageView iv_image;
    private Spinner spnr_gender;

    private boolean isGenderSelected;
    private String name, email, phone, password, image, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        utils = new Utils(UserRegistrationActivity.this);

        initViews();
        initSpinner();
        initFunctions();
    }

    private void initViews() {
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        btn_image = findViewById(R.id.btn_image);
        btn_submit = findViewById(R.id.btn_submit);
        tv_image = findViewById(R.id.tv_image);
        iv_image = findViewById(R.id.iv_image);
        spnr_gender = findViewById(R.id.spnr_gender);
    }

    private void initSpinner() {
        String[] genders = { "Select Gender", "Male", "Female"};
        ArrayAdapter genderAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_gender.setAdapter(genderAdapter);
        spnr_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    isGenderSelected = true;
                    gender = genders[i];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initFunctions() {
        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(UserRegistrationActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        EXTERNAL_STORAGE_PERMISSION_CODE);

                Intent intent = new  Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_name.getText().toString();
                email = et_email.getText().toString();
                phone = et_phone.getText().toString();
                password = et_password.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && isGenderSelected) {
                    UserRegistration userRegistration = new UserRegistration();
                    userRegistration.setName(name);
                    userRegistration.setEmail(email);
                    userRegistration.setPhone(phone);
                    userRegistration.setPassword(password);
                    userRegistration.setImage(image);
                    userRegistration.setGender(gender);

                    utils.setUserDetails(userRegistration);
                    Toast.makeText(UserRegistrationActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(UserRegistrationActivity.this, "Please complete all data!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                image = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(image));
                tv_image.setText(getNameFromURI(UserRegistrationActivity.this, selectedImage));
                iv_image.setImageBitmap(thumbnail);
                iv_image.setVisibility(View.VISIBLE);
            }
        }
    }

    @SuppressLint("Range")
    public String getNameFromURI(@NonNull Context context, @NonNull Uri uri) {
        String result = null;
        Cursor c = null;
        try {
            c = context.getContentResolver().query(uri, null, null, null, null);
            c.moveToFirst();
            result = c.getString(c.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        }
        catch (Exception e){
            // error occurs
        }
        finally {
            if(c != null){
                c.close();
            }
        }
        return result;
    }

}
