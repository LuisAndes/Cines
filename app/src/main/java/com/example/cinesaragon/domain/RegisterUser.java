package com.example.cinesaragon.domain;

import android.app.Activity;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ThreadPoolExecutor;

public class RegisterUser {

    final ThreadPoolExecutor executor;
    final FirebaseAuth authentication;
    final CreateUser createUser;

    public RegisterUser(ThreadPoolExecutor executor,
                        FirebaseAuth authentication,
                        CreateUser createUser) {
        this.executor = executor;
        this.authentication = authentication;
        this.createUser = createUser;
    }

    public void execute(Activity activity,
                        String username, String password,
                        ResultCallback<Void, Exception> callback) {
        executor.execute(() -> {
            try {
                Tasks.await(authentication.createUserWithEmailAndPassword(
                        username, password));
                createUser.create(new User.Builder(username).build(), callback);
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }
}
