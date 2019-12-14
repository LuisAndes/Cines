package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.FullInfoSession;
import com.example.cinesaragon.model.Ticket;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class PurchaseTicket extends UseCase<FullInfoSession, Ticket> {

    private final FirebaseAuth authentication;
    private final FirebaseFirestore database;

    public PurchaseTicket(ThreadPoolExecutor executor, FirebaseAuth authentication, FirebaseFirestore database) {
        super(executor);
        this.authentication = authentication;
        this.database = database;
    }

    public void purchase(FullInfoSession session, ResultCallback<Ticket, Exception> callback) {
        execute(session, callback);
    }

    @Override
    protected void doWork(FullInfoSession params, ResultCallback<Ticket, Exception> callback) throws ExecutionException, InterruptedException {
        final Ticket ticket = new Ticket.Builder(authentication.getCurrentUser().getEmail(), params).purchase();
        Tasks.await(database.collection("tickets")
                .document()
                .set(ticket));
        callback.onResult(ticket);
    }
}
