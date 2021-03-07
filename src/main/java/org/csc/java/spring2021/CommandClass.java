package org.csc.java.spring2021;

import org.csc.java.spring2021.CommandType.*;

import java.util.ArrayList;
import java.util.List;

public class CommandClass implements Command {

  public CommandType commandType;
  public List<Command> state;

  public CommandClass(CommandType commandType) {
    this.commandType = commandType;
    this.state = new ArrayList<>();
  }

  public CommandClass(CommandType commandType, List<Command> state) {
    this.commandType = commandType;
    this.state = state;
  }


  public void execute(IOContext context, Memory memory) {
    switch (this.commandType) {
      case NEXT -> {
        memory.shiftRight();
      }
      case PREVIOUS -> {
        memory.shiftLeft();
      }
      case INCREASE -> {
        byte tmp = memory.getByte();
        tmp++;
        memory.setByte(tmp);
      }
      case DECREASE -> {
        byte tmp = memory.getByte();
        tmp--;
        memory.setByte(tmp);
      }
      case INPUT -> {
        byte value = context.readByte();
        memory.setByte(value);
      }
      case OUTPUT -> {
        context.writeByte(memory.getByte());
      }
      case LOOPCOMMAND -> {
        while (memory.getByte() != 0) {
          for (Command command : this.state) {
            command.execute(context, memory);
          }
        }
      }
    }
  }
}
