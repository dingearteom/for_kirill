package org.csc.java.spring2021;

import org.csc.java.spring2021.CommandType.*;

import java.util.ArrayList;
import java.util.List;

public class CommandClass implements Command {

  public final CommandType commandType;
  public final Command state;

  public CommandClass(CommandType commandType) {
    this.commandType = commandType;
    this.state = EmptyCommandClass.INSTANCE;
  }

  public CommandClass(CommandType commandType, Command state) {
    this.commandType = commandType;
    this.state = state;
  }

  private void executeNext(IOContext context, Memory memory){
    memory.shiftRight();
  }

  private void executePrevious(IOContext context, Memory memory){
    memory.shiftLeft();
  }

  private void executeIncrease(IOContext context, Memory memory){
    byte tmp = memory.getByte();
    tmp++;
    memory.setByte(tmp);
  }

  private void executeDecrease(IOContext context, Memory memory){
    byte tmp = memory.getByte();
    tmp--;
    memory.setByte(tmp);
  }

  private void executeInput(IOContext context, Memory memory){
    byte value = context.readByte();
    memory.setByte(value);
  }

  private void executeOutput(IOContext context, Memory memory){
    context.writeByte(memory.getByte());
  }

  private void executeLoopCommand(IOContext context, Memory memory){
    while (memory.getByte() != 0) {
      this.state.execute(context, memory);
    }
  }



  public void execute(IOContext context, Memory memory) {
    switch (this.commandType) {
      case NEXT -> {
        this.executeNext(context, memory);
      }
      case PREVIOUS -> {
        this.executePrevious(context, memory);
      }
      case INCREASE -> {
        this.executeIncrease(context, memory);
      }
      case DECREASE -> {
        this.executeDecrease(context, memory);
      }
      case INPUT -> {
        this.executeInput(context, memory);
      }
      case OUTPUT -> {
        this.executeOutput(context, memory);
      }
      case LOOPCOMMAND -> {
        this.executeLoopCommand(context, memory);
      }
    }
  }
}

