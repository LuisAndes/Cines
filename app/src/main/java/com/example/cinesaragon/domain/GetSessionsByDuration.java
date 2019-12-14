package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.FullInfoSession;
import com.example.cinesaragon.model.Movie;
import com.example.cinesaragon.model.params.FilterByNumberParams;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetSessionsByDuration extends UseCase<FilterByNumberParams, Map<Movie, List<FullInfoSession>>> {

    private final GetSessions getSessions;

    public GetSessionsByDuration(ThreadPoolExecutor executor, GetSessions getSessions) {
        super(executor);
        this.getSessions = getSessions;
    }

    public void filter(FilterByNumberParams filtering, ResultCallback<Map<Movie, List<FullInfoSession>>, Exception> callback) {
        execute(filtering, callback);
    }

    @Override
    protected void doWork(FilterByNumberParams params, ResultCallback<Map<Movie, List<FullInfoSession>>, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(getSessions.getSessions()
                .stream()
                .filter(session -> handleFiltering(params, session))
                .collect(Collectors.groupingBy(FullInfoSession::getMovie)));
    }

    private boolean handleFiltering(FilterByNumberParams params, FullInfoSession session) {
        switch (params.getFiltering()) {
            case GREATER_THAN:
                return session.getMovie().getDuration() >= params.getThreshold();
            case LESSER_THAN:
                return session.getMovie().getDuration() <= params.getThreshold();
            default:
                return false;
        }
    }
}
