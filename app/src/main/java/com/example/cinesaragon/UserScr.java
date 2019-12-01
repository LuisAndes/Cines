package com.example.cinesaragon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinesaragon.domain.GetCurrentUser;
import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.firebase.database.DatabaseError;

import javax.inject.Inject;

public class UserScr extends AppCompatActivity {

    @Inject
    GetCurrentUser getCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_scr);
        getCurrentUser.execute(new ResultCallback<User, DatabaseError>() {
            @Override
            public void onResult(User result) {
                Log.d("Sefford", "Result:" + result);
            }

            @Override
            public void onError(DatabaseError error) {

            }
        });
    }

    public static void goToProfile(Activity activity) {
        final Intent intent = new Intent(activity, UserScr.class);
        activity.startActivity(intent);
    }
}
