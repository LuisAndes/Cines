package com.example.cinesaragon.domain;

import android.app.Activity;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUser {

    final FirebaseAuth authentication;

    public LoginUser(FirebaseAuth authentication) {
        this.authentication = authentication;
    }

    public void execute(Activity activity,
                        String username, String password,
                        ResultCallback<Void, Exception> callback) {
        authentication.signInWithEmailAndPassword(
                username, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        callback.onResult(null);
                    } else {
                        callback.onError(task.getException());
                    }
                });
    }
}
