package com.example.cinesaragon.domain;

import android.app.Activity;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ModifyCurrentUser {

    final FirebaseAuth authentication;
    final FirebaseFirestore database;
    final CreateUser createUser;

    public ModifyCurrentUser(FirebaseAuth authentication, FirebaseFirestore database, CreateUser createUser) {
        this.authentication = authentication;
        this.database = database;
        this.createUser = createUser;
    }

    public void modify(Activity activity, User user, ResultCallback<Void, Exception> callback) {
        final String oldEmail = authentication.getCurrentUser().getEmail();
        final boolean emailChange = !oldEmail.equals(user.getEmail());
        authentication.getCurrentUser().updateEmail(user.getEmail())
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        if (emailChange) {
                            deleteOldUser(oldEmail);
                        }
                        modifyUserInDatabase(activity, user, callback);
                    } else {
                        callback.onError(task.getException());
                    }
                });
    }

    private void deleteOldUser(String oldEmail) {
        this.database.collection("users")
                .document(oldEmail)
                .delete()
                .addOnCompleteListener(task -> {
                });
    }

    private void modifyUserInDatabase(Activity activity, User user, ResultCallback<Void, Exception> callback) {
        this.createUser.create(user, callback);
    }
}
