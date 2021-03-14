package org.csc.java.spring2021;

public class EmptyCommandClass implements Command{
  public static EmptyCommandClass INSTANCE = new EmptyCommandClass();

  private EmptyCommandClass(){};

  public void execute(IOContext context, Memory memory){}
}
