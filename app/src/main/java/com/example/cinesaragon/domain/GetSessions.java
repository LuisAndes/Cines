package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Cinema;
import com.example.cinesaragon.model.FullInfoSession;
import com.example.cinesaragon.model.Movie;
import com.example.cinesaragon.model.Session;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetSessions extends UseCase<Long, List<FullInfoSession>> {

    final FirebaseFirestore database;

    public GetSessions(ThreadPoolExecutor executor, FirebaseFirestore database) {
        super(executor);
        this.database = database;
    }

    public void get(Long day, ResultCallback<List<FullInfoSession>, Exception> callback) {
        execute(day, callback);
    }

    @Override
    protected void doWork(Long day, ResultCallback<List<FullInfoSession>, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(getSessions());
    }

    public List<FullInfoSession> getSessions() throws ExecutionException, InterruptedException {
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


        final List<DocumentSnapshot> rawSessions = Tasks.await(database.collection("sessions")
                .get())
                .getDocuments();
        return rawSessions
                .stream()
                .map(element -> {
                    final Session session = element.toObject(Session.class);
                    return new FullInfoSession(movies.get(session.getMovie()),
                            cinemas.get(session.getCinema()),
                            session);
                })
                .collect(Collectors.toList());
    }
}
