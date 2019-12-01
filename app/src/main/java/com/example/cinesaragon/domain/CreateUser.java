package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class CreateUser {

    final FirebaseFirestore database;

    public CreateUser(FirebaseFirestore database) {
        this.database = database;
    }

    public void create(User user, ResultCallback<Void, Exception> callback) {
        database.collection("users")
                .document(user.getEmail())
                .set(user, SetOptions.merge())
                .addOnSuccessListener(callback::onResult)
                .addOnFailureListener(callback::onError);
    }
}
