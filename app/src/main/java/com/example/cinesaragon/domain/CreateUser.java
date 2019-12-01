package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.firebase.database.DatabaseReference;

public class CreateUser {

    final DatabaseReference database;

    public CreateUser(DatabaseReference database) {
        this.database = database;
    }

    public void create(User user, ResultCallback<Void, Exception> callback) {
        database.child("users")
                .child(user.getId())
                .setValue(user)
                .addOnSuccessListener(callback::onResult)
                .addOnFailureListener(e -> callback.onError(e));
    }
}
