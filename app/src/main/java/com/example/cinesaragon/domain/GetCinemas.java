package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Cinema;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetCinemas {

    final ThreadPoolExecutor executor;
    final FirebaseFirestore database;

    public GetCinemas(ThreadPoolExecutor executor,
                      FirebaseFirestore database) {
        this.executor = executor;
        this.database = database;
    }

    public void get(ResultCallback<List<Cinema>, Exception> callback) {
        executor.execute(() -> {
            try {
                callback.onResult(Tasks.await(database.collection("cinemas")
                        .get())
                        .getDocuments()
                        .stream()
                        .map(element -> element.toObject(Cinema.class))
                        .collect(Collectors.toList()));
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }
}
