package com.example.cinesaragon.domain.helpers;

public interface ResultCallback<R, E> {

    void onResult(R result);

    void onError(E error);
}
