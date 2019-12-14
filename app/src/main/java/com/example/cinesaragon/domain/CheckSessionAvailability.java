package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.params.PurchaseTicketParams;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class CheckSessionAvailability extends UseCase<PurchaseTicketParams, Boolean> {

    private final FirebaseFirestore database;

    public CheckSessionAvailability(ThreadPoolExecutor executor, FirebaseFirestore database) {
        super(executor);
        this.database = database;
    }

    public void checkAvailability(PurchaseTicketParams params, ResultCallback<Boolean, Exception> callback) {
        execute(params, callback);
    }

    @Override
    protected void doWork(PurchaseTicketParams params, ResultCallback<Boolean, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(Tasks.await(database.collection("tickets")
                .whereEqualTo("movie", params.getSession().getMovie().getName())
                .whereEqualTo("cinema", params.getSession().getCinema().getName())
                .whereEqualTo("day", params.getDay())
                .whereEqualTo("session", params.getSession().getSession().getTime())
                .get())
                .size() < params.getSession().getSession().getRemainingSeats());

    }
}
