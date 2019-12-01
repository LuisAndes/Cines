package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.concurrent.ThreadPoolExecutor;

public class CreateUser {

    final ThreadPoolExecutor executor;
    final FirebaseFirestore database;

    public CreateUser(ThreadPoolExecutor executor, FirebaseFirestore database) {
        this.executor = executor;
        this.database = database;
    }

    public void create(User user, ResultCallback<Void, Exception> callback) {
        executor.execute(() -> {
            try {
                Tasks.await(database.collection("users")
                        .document(user.getEmail())
                        .set(user, SetOptions.merge()));
                callback.onResult(null);
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }
}
