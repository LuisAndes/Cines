package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Cinema;
import com.example.cinesaragon.model.FullInfoSession;
import com.example.cinesaragon.model.Movie;
import com.example.cinesaragon.model.Session;
import com.example.cinesaragon.model.params.GetSessionParams;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class GetSession extends UseCase<GetSessionParams, FullInfoSession> {

    private final FirebaseFirestore database;

    public GetSession(ThreadPoolExecutor executor, FirebaseFirestore database) {
        super(executor);
        this.database = database;
    }

    public void get(GetSessionParams params, ResultCallback<FullInfoSession, Exception> callback) {
        execute(params, callback);
    }

    @Override
    protected void doWork(GetSessionParams params, ResultCallback<FullInfoSession, Exception> callback) throws ExecutionException, InterruptedException {
        final Movie movie = Tasks.await(database.document(params.getMovieId()).get()).toObject(Movie.class);
        final Cinema cinema = Tasks.await(database.document(params.getCinemaId()).get()).toObject(Cinema.class);
        final Session session = Tasks.await(database.collection("sessions")
                .whereEqualTo("movie", params.getMovieId())
                .whereEqualTo("cinema", params.getCinemaId())
                .whereEqualTo("screen", params.getScreen())
                .whereEqualTo("time", params.getTime())
                .get())
                .getDocuments()
                .get(0)
                .toObject(Session.class);

        callback.onResult(new FullInfoSession(movie, cinema, session));
    }
}
