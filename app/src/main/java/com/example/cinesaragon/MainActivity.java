package com.example.cinesaragon;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (authentication.getCurrentUser() != null) {
            RegUsrScr.openRegisterScreen(this);
        }
    }

    public void comenzar(View view) {
        PantallaLogin.openLogin(this);
    }

}
