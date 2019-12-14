package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class CreateUser extends UseCase<User, Void> {

    private final FirebaseFirestore database;

    public CreateUser(ThreadPoolExecutor executor, FirebaseFirestore database) {
        super(executor);
        this.database = database;
    }

    public void create(User user, ResultCallback<Void, Exception> callback) {
        execute(user, callback);
    }

    @Override
    protected void doWork(User user, ResultCallback<Void, Exception> callback) throws ExecutionException, InterruptedException {
        Tasks.await(database.collection("users")
                .document(user.getEmail())
                .set(user, SetOptions.merge()));
        callback.onResult(null);
    }
}
