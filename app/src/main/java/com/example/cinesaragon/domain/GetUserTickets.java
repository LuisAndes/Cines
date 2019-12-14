package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Ticket;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetUserTickets extends UseCase<Void, List<Ticket>> {

    private final FirebaseAuth authentication;
    private final FirebaseFirestore database;

    public GetUserTickets(ThreadPoolExecutor executor, FirebaseAuth authentication, FirebaseFirestore database) {
        super(executor);
        this.authentication = authentication;
        this.database = database;
    }

    @Override
    protected void doWork(Void params, ResultCallback<List<Ticket>, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(Tasks.await(database.collection("tickets")
                .whereEqualTo("user", authentication.getCurrentUser().getEmail())
                .get())
                .getDocuments()
                .stream()
                .map(element -> element.toObject(Ticket.class))
                .collect(Collectors.toList()));
    }
}
