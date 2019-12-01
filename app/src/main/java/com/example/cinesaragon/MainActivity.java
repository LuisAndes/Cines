package com.example.cinesaragon;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.cinesaragon.domain.GetCinemas;
import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Cinema;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    private static final int USE_GPS_PERMISSION = 0x1234;

    @Inject
    FirebaseAuth authentication;
    @Inject
    GetCinemas getCinemas;
    @Inject
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestGPSPermission();
        if (authentication.getCurrentUser() != null) {
            RegUsrScr.openRegisterScreen(this);
        } else {
            requestCinemas();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case USE_GPS_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                requestCinemas();
            }
        }
    }

    private void requestGPSPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        USE_GPS_PERMISSION);
            }
        } else {
            requestCinemas();
        }
    }


    private void requestCinemas() {
        getCinemas.get(new ResultCallback<List<Cinema>, Exception>(this) {
            @Override
            public void doOnResult(List<Cinema> result) {
                Log.d("Sefford", "Loaded " + result.size() + " cinemas");
            }

            @Override
            public void doOnError(Exception error) {
                Toast.makeText(MainActivity.this, "An error happened", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void comenzar(View view) {
        PantallaLogin.openLogin(this);
    }

}
