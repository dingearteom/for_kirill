package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.multithreading.shared.Result;
import org.csc.java.spring2021.multithreading.shared.Running;
import org.csc.java.spring2021.multithreading.shared.ThreadsDead;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

public class ThreadPoolExecutor extends ThreadPoolBase implements ThreadPool{
    private final List<Thread> threads;
    private final ThreadsDead threadsDead;
    private final LinkedBlockingQueue<Runnable> tasks;
    private final Running running;

    ThreadPoolExecutor(List<Thread> threads, ThreadsDead threadsDead, Running running, LinkedBlockingQueue<Runnable> tasks){
        this.threads = threads;
        this.threadsDead = threadsDead;
        this.running = running;
        this.tasks = tasks;
        for (Thread thread : threads){
            thread.start();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> ComposableFuture<T> invoke(Supplier<? extends T> action){
        if (!running.running){
            throw new IllegalStateException("Pool has been shutdown. New invocations cannot be applied.");
        }
        Result<T> result = new Result<>();
        TaskRunnable<T> task = new TaskRunnable<T>(result, (Supplier<T>)action);
        tasks.add(task);
        return new ImpComposableFuture<>(result);
    }

    public void shutdown(){
        running.running = false;
    }

    public void awaitFullShutdown() throws InterruptedException{
        if (running.running){
            throw new IllegalStateException("Pool is still running.");
        }
        while(threadsDead.cntDead.get() != threads.size()){

        }
    }

    public List<Thread> getThreads(){
        return threads;
    }
}
