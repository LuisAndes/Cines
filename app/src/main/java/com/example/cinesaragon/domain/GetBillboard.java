package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.FullInfoSession;
import com.example.cinesaragon.model.Movie;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class GetBillboard extends UseCase<Void, Map<Movie, List<FullInfoSession>>> {

    private final GetSessions getSessions;

    public GetBillboard(ThreadPoolExecutor executor, GetSessions getSessions) {
        super(executor);
        this.getSessions = getSessions;
    }

    public void get(ResultCallback<Map<Movie, List<FullInfoSession>>, Exception> callback) {
        execute(null, callback);
    }

    @Override
    protected void doWork(Void params, ResultCallback<Map<Movie, List<FullInfoSession>>, Exception> callback) throws ExecutionException, InterruptedException {
        callback.onResult(getSessions.getSessions()
                .stream()
                .collect(Collectors.groupingBy(FullInfoSession::getMovie)));
    }
}
