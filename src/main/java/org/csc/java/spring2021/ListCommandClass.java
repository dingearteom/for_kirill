package org.csc.java.spring2021;

import java.util.List;

public class ListCommandClass implements Command{
  private final List<Command> state;

  ListCommandClass(List<Command> commandList){
      this.state = commandList;
  }

  public void execute(IOContext context, Memory memory){
    for (Command command : state){
      command.execute(context, memory);
    }
  }
}
