package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.Cinema;
import com.example.cinesaragon.model.FullInfoSession;
import com.example.cinesaragon.model.Movie;
import com.example.cinesaragon.model.params.FilterByNumberParams;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetSessionsByHour extends UseCase<FilterByNumberParams, Map<Cinema, List<FullInfoSession>>> {

    private final GetSessions getSessions;

    public GetSessionsByHour(ThreadPoolExecutor executor, GetSessions getSessions) {
        super(executor);
        this.getSessions = getSessions;
    }

    public void filter(FilterByNumberParams filtering, ResultCallback<Map<Cinema, List<FullInfoSession>>, Exception> callback) {
        execute(filtering, callback);
    }

    @Override
    protected void doWork(FilterByNumberParams params, ResultCallback<Map<Cinema, List<FullInfoSession>>, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(getSessions.getSessions()
                .stream()
                .filter(session -> handleFiltering(params, session))
                .collect(Collectors.groupingBy(FullInfoSession::getCinema)));
    }

    private boolean handleFiltering(FilterByNumberParams params, FullInfoSession session) {
        switch (params.getFiltering()) {
            case GREATER_THAN:
                return session.getSession().getIntegerTime() >= params.getThreshold();
            case LESSER_THAN:
                return session.getSession().getIntegerTime() <= params.getThreshold();
            default:
                return false;
        }
    }
}
