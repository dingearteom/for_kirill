package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.multithreading.shared.Running;
import org.csc.java.spring2021.multithreading.shared.Tasks;
import org.csc.java.spring2021.multithreading.shared.ThreadsAlive;

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolFactory {

  private ThreadPoolFactory() {
  }

  public static ThreadPool createThreadPool(int numberOfThreads) {
      List<Thread> threads = new ArrayList<>();
      Tasks tasks = new Tasks();
      Running running = new Running();
      ThreadsAlive threadsAlive = new ThreadsAlive(numberOfThreads);
      for (int i = 0; i < numberOfThreads; i++){
        threads.add(new Thread(new ThreadProcessor(tasks.tasks, threadsAlive, running)));
      }
      return new ThreadPoolExecutor(threads, threadsAlive, running, tasks.tasks);
  }
}
