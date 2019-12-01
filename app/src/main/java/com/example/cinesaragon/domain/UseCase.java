package com.example.cinesaragon.domain;

import com.example.cinesaragon.domain.helpers.ResultCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

abstract class UseCase<P, R> {
    final ThreadPoolExecutor executor;

    UseCase(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    protected void execute(P params, ResultCallback<R, Exception> callback) {
        executor.execute(() -> {
            try {
                doWork(params, callback);
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }

    protected abstract void doWork(P params, ResultCallback<R, Exception> callback) throws ExecutionException, InterruptedException;
}
