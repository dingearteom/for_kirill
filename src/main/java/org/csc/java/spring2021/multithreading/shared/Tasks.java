package org.csc.java.spring2021.multithreading.shared;

import java.util.concurrent.LinkedBlockingQueue;

public class Tasks {
    public volatile LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
}
