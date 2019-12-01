package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Cinema;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.stream.Collectors;

public class GetCinemas {

    final FirebaseFirestore database;

    public GetCinemas(FirebaseFirestore database) {
        this.database = database;
    }

    public void get(ResultCallback<List<Cinema>, Exception> callback) {
        this.database.collection("cinemas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onResult(task.getResult()
                                .getDocuments()
                                .stream()
                                .map(element -> element.toObject(Cinema.class))
                                .collect(Collectors.toList()));
                    } else {
                        callback.onError(task.getException());
                    }
                });
    }
}
