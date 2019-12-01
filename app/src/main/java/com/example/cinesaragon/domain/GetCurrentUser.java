package com.example.cinesaragon.domain;

import androidx.annotation.NonNull;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class GetCurrentUser {

    final FirebaseAuth authentication;
    final DatabaseReference database;

    public GetCurrentUser(FirebaseAuth authentication, DatabaseReference database) {
        this.authentication = authentication;
        this.database = database;
    }

    public void execute(ResultCallback<User, DatabaseError> callback) {
        database.child("users")
                .child(authentication.getCurrentUser().getEmail().replaceAll("\\.", "_"))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        callback.onResult(dataSnapshot.getValue(User.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        callback.onError(databaseError);
                    }
                });
    }
}
