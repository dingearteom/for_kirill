package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.multithreading.shared.Running;
import org.csc.java.spring2021.multithreading.shared.ThreadsAlive;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadProcessor implements Runnable{
    private final Running running;
    private final LinkedBlockingQueue<Runnable> tasks;
    private final ThreadsAlive threadsAlive;
    private static final long waitTime = 10;

    ThreadProcessor(LinkedBlockingQueue<Runnable> tasks, ThreadsAlive threadsAlive, Running running){
        this.tasks = tasks;
        this.threadsAlive = threadsAlive;
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
        threadsAlive.cntAlive.getAndDecrement();
        // System.out.printf("I'm done %d%n", Thread.currentThread().getId());
    }

}
