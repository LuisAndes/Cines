package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Cinema;
import com.example.cinesaragon.model.FullInfoSession;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetSessionsByCinemaName extends UseCase<String, Map<Cinema, List<FullInfoSession>>> {

    private final GetSessions getSessions;

    public GetSessionsByCinemaName(ThreadPoolExecutor executor, GetSessions getSessions) {
        super(executor);
        this.getSessions = getSessions;
    }

    public void filter(String name, ResultCallback<Map<Cinema, List<FullInfoSession>>, Exception> callback) {
        execute(name, callback);
    }

    @Override
    protected void doWork(String params, ResultCallback<Map<Cinema, List<FullInfoSession>>, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(getSessions.getSessions()
                .stream()
                .filter(session -> session.getCinema().getName().toLowerCase().contains(params))
                .collect(Collectors.groupingBy(FullInfoSession::getCinema)));
    }
}
