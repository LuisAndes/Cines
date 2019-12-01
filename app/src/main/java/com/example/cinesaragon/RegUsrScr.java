package com.example.cinesaragon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RegUsrScr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_usr_scr);
    }

    public static void openRegisterScreen(Activity activity) {
        final Intent intent = new Intent(activity, RegUsrScr.class);
        activity.startActivity(intent);
    }
}
