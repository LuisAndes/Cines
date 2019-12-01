package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ThreadPoolExecutor;

public class LoginUser {

    final ThreadPoolExecutor executor;
    final FirebaseAuth authentication;

    public LoginUser(ThreadPoolExecutor executor, FirebaseAuth authentication) {
        this.executor = executor;
        this.authentication = authentication;
    }

    public void execute(String username,
                        String password,
                        ResultCallback<Void, Exception> callback) {
        executor.execute(() -> {
            try {
                Tasks.await(authentication.signInWithEmailAndPassword(
                        username, password));
                callback.onResult(null);
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }
}
