package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.params.SignInParams;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class LoginUser extends UseCase<SignInParams, Void> {

    private final FirebaseAuth authentication;

    public LoginUser(ThreadPoolExecutor executor, FirebaseAuth authentication) {
        super(executor);
        this.authentication = authentication;
    }

    public void signIn(String username,
                       String password,
                       ResultCallback<Void, Exception> callback) {
        execute(new SignInParams(username, password), callback);
    }

    @Override
    protected void doWork(SignInParams params, ResultCallback<Void, Exception> callback) throws ExecutionException, InterruptedException {
        Tasks.await(authentication.signInWithEmailAndPassword(
                params.getUsername(), params.getPassword()));
        callback.onResult(null);
    }
}
