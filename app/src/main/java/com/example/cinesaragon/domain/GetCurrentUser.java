package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class GetCurrentUser extends UseCase<Void, User> {

    final FirebaseAuth authentication;
    final FirebaseFirestore database;

    public GetCurrentUser(ThreadPoolExecutor executor, FirebaseAuth authentication, FirebaseFirestore database) {
        super(executor);
        this.authentication = authentication;
        this.database = database;
    }

    public void retrieve(ResultCallback<User, Exception> callback) {
        execute(null, callback);
    }

    @Override
    protected void doWork(Void params, ResultCallback<User, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(Tasks.await(database.collection("users")
                .document(authentication.getCurrentUser().getEmail())
                .get())
                .toObject(User.class));
    }
}
