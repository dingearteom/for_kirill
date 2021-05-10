package org.csc.java.spring2021;

import org.csc.java.spring2021.multithreading.ComposableFuture;
import org.csc.java.spring2021.multithreading.ThreadPool;
import org.csc.java.spring2021.multithreading.ThreadPoolFactory;

import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class Hello {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPool threadPool = ThreadPoolFactory.createThreadPool(1);
        ComposableFuture<Integer> result = threadPool.invoke(() -> {
            return 1;
        });
        System.out.println(result.get());
    }
}
