package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Cinema;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetCinemas extends UseCase<Void, List<Cinema>> {

    private final FirebaseFirestore database;

    public GetCinemas(ThreadPoolExecutor executor,
                      FirebaseFirestore database) {
        super(executor);
        this.database = database;
    }

    public void get(ResultCallback<List<Cinema>, Exception> callback) {
        execute(null, callback);
    }

    @Override
    protected void doWork(Void params, ResultCallback<List<Cinema>, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(Tasks.await(database.collection("cinemas")
                .get())
                .getDocuments()
                .stream()
                .map(element -> element.toObject(Cinema.class))
                .collect(Collectors.toList()));
    }
}
