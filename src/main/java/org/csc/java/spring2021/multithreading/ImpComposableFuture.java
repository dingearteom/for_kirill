package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.NotImplementedException;
import org.csc.java.spring2021.multithreading.shared.Result;

import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class ImpComposableFuture<T> implements ComposableFuture<T> {
    private final Result<T> result;

    ImpComposableFuture(Result<T> result){
        this.result = result;
    }

    public Status getStatus(){
        if (result.status == Result.STATUS.FINISHED){
            return Status.FINISHED;
        }
        else if (result.status == Result.STATUS.FINISHED_WITH_EXCEPTION){
            return Status.FINISHED_WITH_EXCEPTION;
        }
        else{
            return Status.NOT_FINISHED;
        }
    }

    public T get() throws ExecutionException, InterruptedException{
        while (result.status == Result.STATUS.NOT_FINISHED) {
            Thread.onSpinWait();
        }
        if (result.status == Result.STATUS.FINISHED_WITH_EXCEPTION){
            throw result.exc;
        }
        return result.result;
    }

    public T getIfReady() throws ExecutionException{
        if (result.status == Result.STATUS.NOT_FINISHED){
            throw new IllegalStateException();
        }
        else if(result.status == Result.STATUS.FINISHED_WITH_EXCEPTION){
            throw result.exc;
        }
        else {
            return result.result;
        }
    }

    public <U> ComposableFuture<U> thenApply(Function<? super T, ? extends U> mapper){
        throw new NotImplementedException();
    }


}
