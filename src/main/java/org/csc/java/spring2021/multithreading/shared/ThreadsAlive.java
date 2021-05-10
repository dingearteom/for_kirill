package org.csc.java.spring2021.multithreading.shared;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsAlive {
    public volatile AtomicInteger cntAlive;
    public ThreadsAlive(int cntAlive){
        this.cntAlive = new AtomicInteger(cntAlive);
    }
}
