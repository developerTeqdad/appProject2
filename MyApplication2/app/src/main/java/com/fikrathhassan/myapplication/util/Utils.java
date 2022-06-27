package com.fikrathhassan.myapplication.util;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fikrathhassan.myapplication.R;
import com.fikrathhassan.myapplication.model.UserRegistration;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Utils {

    private Activity activity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedEditor;
    private BottomSheetDialog bookDialog;

    public Utils(Activity activity) {
        this.activity = activity;
        sharedPreferences = activity.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedPreferences.Editor getSharedEditor() {
        return sharedEditor;
    }

    public void setUserDetails(UserRegistration user) {
        sharedEditor.putString(Constants.SHARED_USER_NAME, user.getName());
        sharedEditor.putString(Constants.SHARED_USER_EMAIL, user.getEmail());
        sharedEditor.putString(Constants.SHARED_USER_PHONE, user.getPhone());
        sharedEditor.putString(Constants.SHARED_USER_PASSWORD, user.getPassword());
        sharedEditor.putString(Constants.SHARED_USER_IMAGE, user.getImage());
        sharedEditor.putString(Constants.SHARED_USER_GENDER, user.getGender());
        sharedEditor.commit();
    }

    public void setLogin(boolean isLogged) {
        sharedEditor.putBoolean(Constants.SHARED_USER_LOGGED, isLogged);
        sharedEditor.commit();
    }

    public boolean getLogin() {
        return getSharedPreferences().getBoolean(Constants.SHARED_USER_LOGGED, false);
    }

    public String getEmail() {
        return getSharedPreferences().getString(Constants.SHARED_USER_EMAIL, null);
    }

    public String getPassword() {
        return getSharedPreferences().getString(Constants.SHARED_USER_PASSWORD, null);
    }

    public void initBottomDialog() {
        bookDialog = new BottomSheetDialog(activity);
        bookDialog.setContentView(R.layout.dialog_book);
        TextView tvBtn_cancel = bookDialog.findViewById(R.id.tvBtn_cancel);
        tvBtn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookDialog.cancel();
            }
        });
        TextView tvBtn_book = bookDialog.findViewById(R.id.tvBtn_book);
        tvBtn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookDialog.cancel();
                Toast.makeText(activity, "Vehicle has been booked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setDialogText(String text) {
        TextView dialog_text = bookDialog.findViewById(R.id.dialog_text);
        dialog_text.setText(text);
    }

    public void showBookDialog() {
        bookDialog.show();
    }

}
