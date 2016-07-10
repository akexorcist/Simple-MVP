package com.akexorcist.mvpsimple.network.model;

/**
 * Created by Akexorcist on 7/10/16 AD.
 */
public class ResultFailureEvent {
    private Throwable throwable;

    public ResultFailureEvent(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public ResultFailureEvent setThrowable(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }
}
