package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.example.cinesaragon.model.params.SignInParams;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class RegisterUser extends UseCase<SignInParams, Void> {

    private final FirebaseAuth authentication;
    private final CreateUser createUser;

    public RegisterUser(ThreadPoolExecutor executor,
                        FirebaseAuth authentication,
                        CreateUser createUser) {
        super(executor);
        this.authentication = authentication;
        this.createUser = createUser;
    }

    public void signUp(String username, String password,
                       ResultCallback<Void, Exception> callback) {
        execute(new SignInParams(username, password), callback);
    }

    @Override
    protected void doWork(SignInParams params, ResultCallback<Void, Exception> callback) throws ExecutionException, InterruptedException {
        Tasks.await(authentication.createUserWithEmailAndPassword(
                params.getUsername(), params.getPassword()));
        createUser.create(new User.Builder(params.getUsername()).build(), callback);
    }
}
