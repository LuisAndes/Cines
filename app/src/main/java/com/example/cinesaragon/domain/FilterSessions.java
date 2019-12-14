package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.FullInfoSession;
import com.example.cinesaragon.model.params.FilterSessionsParams;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterSessions extends UseCase<FilterSessionsParams, List<FullInfoSession>> {

    private final GetSessions getSessions;

    public FilterSessions(ThreadPoolExecutor executor, GetSessions getSessions) {
        super(executor);
        this.getSessions = getSessions;
    }

    @Override
    protected void doWork(FilterSessionsParams params, ResultCallback<List<FullInfoSession>, Exception> callback) throws ExecutionException, InterruptedException {
        final Stream<FullInfoSession> stream = getSessions.getSessions()
                .stream();
        if (params.isByDuration()) {
            stream.filter(session -> session.getMovie().getDuration() <= params.getDuration());
        }
        if (params.isByHour()) {
            stream.filter(session -> session.getSession().getIntegerTime() == params.getHour());
        }
        if (params.isByMovie()) {
            stream.filter(session -> session.getMovie().getName().toLowerCase().contains(params.getMovie()));
        }
        if (params.isByCinema()) {
            stream.filter(session -> session.getCinema().getName().contains(params.getCinema()));
        }
        if (params.isByCinema()) {
            stream.filter(session -> session.getMovie().getGenres()
                    .stream()
                    .anyMatch(genre -> genre.toLowerCase().contains(params.getGenre())));
        }
        callback.onResult(stream.collect(Collectors.toList()));
    }
}
