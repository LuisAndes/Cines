package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Ticket;
import com.example.cinesaragon.model.params.PurchaseTicketParams;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class PurchaseTicket extends UseCase<PurchaseTicketParams, Ticket> {

    private final FirebaseAuth authentication;
    private final FirebaseFirestore database;

    public PurchaseTicket(ThreadPoolExecutor executor, FirebaseAuth authentication, FirebaseFirestore database) {
        super(executor);
        this.authentication = authentication;
        this.database = database;
    }

    public void purchase(PurchaseTicketParams params, ResultCallback<Ticket, Exception> callback) {
        execute(params, callback);
    }

    @Override
    protected void doWork(PurchaseTicketParams params, ResultCallback<Ticket, Exception> callback) throws ExecutionException, InterruptedException {
        final Ticket ticket = new Ticket.Builder(authentication.getCurrentUser().getEmail(), params.getSession())
                .onDay(params.getDay())
                .purchase();
        Tasks.await(database.collection("tickets")
                .document()
                .set(ticket));
        callback.onResult(ticket);
    }
}
