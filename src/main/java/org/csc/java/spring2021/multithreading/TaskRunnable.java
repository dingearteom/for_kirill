package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.multithreading.shared.Result;

import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class TaskRunnable<T> implements Runnable {
    private final Result<T> result;
    private final Supplier<T> action;

    TaskRunnable(Result<T> result, Supplier<T> action){
        this.result = result;
        this.action = action;
    }

    @Override
    public void run(){
        try {
            this.result.result = action.get();
            this.result.status = Result.STATUS.FINISHED;
        }
        catch (RuntimeException exc){
            this.result.exc = new ExecutionException(exc);
            this.result.status = Result.STATUS.FINISHED_WITH_EXCEPTION;
        }
    }
}
