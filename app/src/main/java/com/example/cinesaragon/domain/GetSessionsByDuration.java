package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.FullInfoSession;
import com.example.cinesaragon.model.Movie;
import com.example.cinesaragon.model.params.FilterByDurationParams;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetSessionsByDuration extends UseCase<FilterByDurationParams, Map<Movie, List<FullInfoSession>>> {

    final GetSessions getSessions;

    public GetSessionsByDuration(ThreadPoolExecutor executor, GetSessions getSessions) {
        super(executor);
        this.getSessions = getSessions;
    }

    public void filter(FilterByDurationParams filtering, ResultCallback<Map<Movie, List<FullInfoSession>>, Exception> callback) {
        execute(filtering, callback);
    }

    @Override
    protected void doWork(FilterByDurationParams params, ResultCallback<Map<Movie, List<FullInfoSession>>, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(getSessions.getSessions()
                .stream()
                .filter(session -> handleFiltering(params, session))
                .collect(Collectors.groupingBy(FullInfoSession::getMovie)));
    }

    private boolean handleFiltering(FilterByDurationParams params, FullInfoSession session) {
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
