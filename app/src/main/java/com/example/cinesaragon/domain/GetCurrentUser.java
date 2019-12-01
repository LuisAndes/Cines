package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class GetCurrentUser {

    final FirebaseAuth authentication;
    final FirebaseFirestore database;

    public GetCurrentUser(FirebaseAuth authentication, FirebaseFirestore database) {
        this.authentication = authentication;
        this.database = database;
    }

    public void retrieve(ResultCallback<User, Exception> callback) {
        database.collection("users")
                .document(authentication.getCurrentUser().getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onResult(task.getResult().toObject(User.class));
                    } else {
                        callback.onError(task.getException());
                    }
                });
    }
}
