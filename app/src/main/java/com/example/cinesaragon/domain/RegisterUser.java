package com.example.cinesaragon.domain;

import android.app.Activity;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegisterUser {

    final FirebaseAuth authentication;
    final DatabaseReference database;

    public RegisterUser(FirebaseAuth authentication, DatabaseReference database) {
        this.authentication = authentication;
        this.database = database;
    }

    public void execute(Activity activity,
                        String username, String password,
                        ResultCallback<Void, Exception> callback) {
        authentication.createUserWithEmailAndPassword(
                username, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        createUserInDatabase(username, callback);
                    } else {
                        callback.onError(task.getException());
                    }
                });
    }

    private void createUserInDatabase(String username,
                                      ResultCallback<Void, Exception> callback) {
        database.child("users")
                .child(username.replaceAll("\\.", "_"))
                .setValue(new User.Builder(username).build())
                .addOnSuccessListener(callback::onResult)
                .addOnFailureListener(e -> callback.onError(e));
    }
}
