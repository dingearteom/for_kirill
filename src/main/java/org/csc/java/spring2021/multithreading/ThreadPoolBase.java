package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.multithreading.shared.Running;
import org.csc.java.spring2021.multithreading.shared.ThreadsAlive;

import java.util.concurrent.LinkedBlockingQueue;

abstract class ThreadPoolBase implements ThreadPool {
  /**
   * Возвращает поток, являющийся исполнителем задач
   * <p>
   * Вы можете добавить в этот метод параметры, если требуется.
   */
  protected final Thread createWorkerThread(LinkedBlockingQueue<Runnable> tasks, ThreadsAlive threadsDead,
                                            Running running) {
    return new Thread(createWorker(tasks, threadsDead, running));
  }

  /**
   * Возвращает тело потока-исполнителя задач.
   * <p>
   * Если вы хотите передать в worker какие-то данные, добавьте параметры в этот метод.
   */
  protected final Runnable createWorker(LinkedBlockingQueue<Runnable> tasks, ThreadsAlive threadsDead, Running running) {
    return new ThreadProcessor(tasks, threadsDead, running);
  }
}
