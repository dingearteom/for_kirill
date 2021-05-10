package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.NotImplementedException;
import org.csc.java.spring2021.multithreading.shared.Result;
import org.csc.java.spring2021.multithreading.shared.Running;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

public class ImpComposableFuture<T> implements ComposableFuture<T> {
    private final Result<T> result;
    private final ThreadPool pool;
    private final List<Function<T, Runnable>> waitList = new ArrayList<>();

    ImpComposableFuture(Result<T> result, ThreadPool pool){
        this.result = result;
        this.pool = pool;
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
        if (this.isReady()){
            if (this.getStatus() == Status.FINISHED_WITH_EXCEPTION){
                return new ImpComposableFuture<U>(new Result<U>(this.result.exc), pool);
            }
            return pool.invoke(() -> {return mapper.apply(this.result.result);});
        }
        else{
            Result<U> result = new Result<>();
            waitList.add((T t) -> {
                return new TaskRunnable<U>(result, () -> {return mapper.apply(t);});
            });
            return new ImpComposableFuture<>(result, pool);
        }

    }


}
