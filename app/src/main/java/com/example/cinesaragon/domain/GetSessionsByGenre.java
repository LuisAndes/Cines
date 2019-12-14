package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.FullInfoSession;
import com.example.cinesaragon.model.Movie;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetSessionsByGenre extends UseCase<String, Map<Movie, List<FullInfoSession>>> {

    private final GetSessions getSessions;

    public GetSessionsByGenre(ThreadPoolExecutor executor, GetSessions getSessions) {
        super(executor);
        this.getSessions = getSessions;
    }

    public void filter(String genre, ResultCallback<Map<Movie, List<FullInfoSession>>, Exception> callback) {
        execute(genre, callback);
    }

    @Override
    protected void doWork(String params, ResultCallback<Map<Movie, List<FullInfoSession>>, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(getSessions.getSessions()
                .stream()
                .filter(session -> performFiltering(params, session))
                .collect(Collectors.groupingBy(FullInfoSession::getMovie)));
    }

    private boolean performFiltering(String params, FullInfoSession session) {
        return session.getMovie().getGenres()
                .stream()
                .anyMatch(genre -> genre.toLowerCase().contains(params));
    }
}
