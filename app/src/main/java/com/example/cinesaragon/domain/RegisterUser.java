package com.example.cinesaragon.domain;

import android.app.Activity;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser {

    final FirebaseAuth authentication;
    final CreateUser createUser;

    public RegisterUser(FirebaseAuth authentication, CreateUser createUser) {
        this.authentication = authentication;
        this.createUser = createUser;
    }

    public void execute(Activity activity,
                        String username, String password,
                        ResultCallback<Void, Exception> callback) {
        authentication.createUserWithEmailAndPassword(
                username, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        createUser.create(new User.Builder(username).build(), callback);
                    } else {
                        callback.onError(task.getException());
                    }
                });
    }
}
