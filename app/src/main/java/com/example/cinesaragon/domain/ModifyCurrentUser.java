package com.example.cinesaragon.domain;

import android.app.Activity;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ModifyCurrentUser {

    final FirebaseAuth authentication;
    final DatabaseReference database;
    final CreateUser createUser;

    public ModifyCurrentUser(FirebaseAuth authentication, DatabaseReference database, CreateUser createUser) {
        this.authentication = authentication;
        this.database = database;
        this.createUser = createUser;
    }

    public void modify(Activity activity, User user, ResultCallback<Void, Exception> callback) {
        authentication.getCurrentUser().updateEmail(user.getEmail())
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        modifyUserInDatabase(activity, user, callback);
                    } else {
                        callback.onError(task.getException());
                    }
                });
    }

    private void modifyUserInDatabase(Activity activity, User user, ResultCallback<Void, Exception> callback) {
        this.database.child("users")
                .child(user.getId())
                .removeValue()
                .addOnCompleteListener(activity, task -> {
                });
        this.createUser.create(user, callback);
    }
}
