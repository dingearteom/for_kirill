package org.csc.java.spring2021.multithreading.shared;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsDead {
    public volatile AtomicInteger cntDead = new AtomicInteger(0);
}
