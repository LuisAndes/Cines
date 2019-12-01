package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ThreadPoolExecutor;

public class GetCurrentUser {

    final ThreadPoolExecutor executor;
    final FirebaseAuth authentication;
    final FirebaseFirestore database;

    public GetCurrentUser(ThreadPoolExecutor executor, FirebaseAuth authentication, FirebaseFirestore database) {
        this.executor = executor;
        this.authentication = authentication;
        this.database = database;
    }

    public void retrieve(ResultCallback<User, Exception> callback) {
        executor.execute(() -> {
            try {
                callback.onResult(Tasks.await(database.collection("users")
                        .document(authentication.getCurrentUser().getEmail())
                        .get())
                        .toObject(User.class));
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }
}
