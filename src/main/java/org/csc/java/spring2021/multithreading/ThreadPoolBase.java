package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.NotImplementedException;

abstract class ThreadPoolBase implements ThreadPool {

  /**
   * Возвращает поток, являющийся исполнителем задач
   * <p>
   * Вы можете добавить в этот метод параметры, если требуется.
   */
  protected final Thread createWorkerThread() {
    return new Thread(createWorker());
  }

  /**
   * Возвращает тело потока-исполнителя задач.
   * <p>
   * Если вы хотите передать в worker какие-то данные, добавьте параметры в этот метод.
   */
  protected final Runnable createWorker() {
    throw new NotImplementedException();
  }
}
