package com.example.cinesaragon.domain;

import android.app.Activity;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ThreadPoolExecutor;

public class ModifyCurrentUser {

    final ThreadPoolExecutor executor;
    final FirebaseAuth authentication;
    final FirebaseFirestore database;
    final CreateUser createUser;

    public ModifyCurrentUser(ThreadPoolExecutor executor,
                             FirebaseAuth authentication,
                             FirebaseFirestore database,
                             CreateUser createUser) {
        this.executor = executor;
        this.authentication = authentication;
        this.database = database;
        this.createUser = createUser;
    }

    public void modify(User user, ResultCallback<Void, Exception> callback) {
        executor.execute(() -> {
            try {
                final String oldEmail = authentication.getCurrentUser().getEmail();
                final boolean emailChange = !oldEmail.equals(user.getEmail());

                Tasks.await(authentication.getCurrentUser().updateEmail(user.getEmail()));

                if (emailChange) {
                    deleteOldUser(oldEmail);
                }

                createUser.create(user, callback);
            } catch (Exception e) {
                callback.onError(e);
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
