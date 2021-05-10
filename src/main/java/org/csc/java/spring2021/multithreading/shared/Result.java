package org.csc.java.spring2021.multithreading.shared;

import org.csc.java.spring2021.multithreading.ComposableFuture;

import java.util.concurrent.ExecutionException;

public class Result<T> {
    public volatile T result = null;
    public volatile STATUS status = STATUS.NOT_FINISHED;
    public volatile ExecutionException exc = null;

    public Result(){}

    public Result(ExecutionException exc){
        this.status = STATUS.FINISHED_WITH_EXCEPTION;
        this.exc = exc;
    }

    public enum STATUS{
        FINISHED,
        NOT_FINISHED,
        FINISHED_WITH_EXCEPTION
    }
}
