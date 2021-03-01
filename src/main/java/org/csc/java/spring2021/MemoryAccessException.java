package org.csc.java.spring2021;

public class MemoryAccessException extends RuntimeException {

  public MemoryAccessException(int pointer) {
    super("Trying to access memory at: " + pointer);
  }
}
