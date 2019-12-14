package com.example.cinesaragon.domain.helpers;

import android.app.Activity;

public abstract class ResultCallback<R, E> {

    private final Activity activity;

    public ResultCallback(Activity activity) {
        this.activity = activity;
    }

    public final void onResult(R result) {
        activity.runOnUiThread(() -> doOnResult(result));
    }

    public final void onError(E error) {
        activity.runOnUiThread(() -> doOnError(error));
    }

    public abstract void doOnResult(R result);

    public abstract void doOnError(E error);

}
