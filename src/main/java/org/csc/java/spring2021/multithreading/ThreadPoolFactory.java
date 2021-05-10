package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.multithreading.shared.Running;
import org.csc.java.spring2021.multithreading.shared.Tasks;
import org.csc.java.spring2021.multithreading.shared.ThreadsDead;

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolFactory {

  private ThreadPoolFactory() {
  }

  public static ThreadPool createThreadPool(int numberOfThreads) {
      List<Thread> threads = new ArrayList<>();
      Tasks tasks = new Tasks();
      Running running = new Running();
      ThreadsDead threadsDead = new ThreadsDead();
      for (int i = 0; i < numberOfThreads; i++){
        threads.add(new Thread(new ThreadProcessor(tasks.tasks, threadsDead, running)));
      }
      return new ThreadPoolExecutor(threads, threadsDead, running, tasks.tasks);
  }
}
