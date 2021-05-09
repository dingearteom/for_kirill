package org.csc.java.spring2021.multithreading;

import org.csc.java.spring2021.NotImplementedException;

public class ThreadPoolFactory {

  private ThreadPoolFactory() {
  }

  public static ThreadPool createThreadPool(int numberOfThreads) {
    throw new NotImplementedException("Не реализован фабричный метод создания пула потоков");
  }
}
