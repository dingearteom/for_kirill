package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.multithreading.shared.Running;
import org.csc.java.spring2021.multithreading.shared.ThreadsDead;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadProcessor implements Runnable{
    private final Running running;
    private final LinkedBlockingQueue<Runnable> tasks;
    private final ThreadsDead threadsDead;
    private static final long waitTime = 10;

    ThreadProcessor(LinkedBlockingQueue<Runnable> tasks, ThreadsDead threadsDead, Running running){
        this.tasks = tasks;
        this.threadsDead = threadsDead;
        this.running = running;
    }

    @Override
    public void run(){
        Runnable current_task = null;
        while (running.running){
            try {
                current_task = tasks.poll(waitTime, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException ignored) {}
            if (current_task != null) {
                current_task.run();
                current_task = null;
            }
        }
        threadsDead.cntDead.getAndIncrement();
        // System.out.printf("I'm done %d%n", Thread.currentThread().getId());
    }

}
