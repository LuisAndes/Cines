package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Cinema;
import com.example.cinesaragon.model.Movie;
import com.example.cinesaragon.model.Session;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetSessions extends UseCase<Long, List<Session>> {

    final FirebaseFirestore database;

    public GetSessions(ThreadPoolExecutor executor, FirebaseFirestore database) {
        super(executor);
        this.database = database;
    }

    public void get(Long day, ResultCallback<List<Session>, Exception> callback) {
        execute(day, callback);
    }

    @Override
    protected void doWork(Long day, ResultCallback<List<Session>, Exception> callback) throws ExecutionException, InterruptedException {
        final List<DocumentSnapshot> rawCinemas = Tasks.await(database.collection("cinemas")
                .get())
                .getDocuments();
        final Map<String, Cinema> cinemas = rawCinemas
                .stream()
                .collect(Collectors.toMap(element -> element.getReference().getPath(),
                        element -> element.toObject(Cinema.class)));

        final List<DocumentSnapshot> rawMovies = Tasks.await(database.collection("movies")
                .get())
                .getDocuments();
        final Map<String, Movie> movies = rawMovies
                .stream()
                .collect(Collectors.toMap(element -> element.getReference().getPath(),
                        element -> element.toObject(Movie.class)));

        for(int i = 0; i < cinemas.size() * movies.size();i++) {
            
        }

    }
}
