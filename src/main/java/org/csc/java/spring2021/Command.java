package org.csc.java.spring2021;

/**
 * Команда -- единица исполнения языка Brainfuck. Команды могут быть как stateful, так и stateless
 */
public interface Command {

  /**
   * Исполняет логику команды
   *
   * @param context устройство ввода-вывода
   * @param memory  память
   */
  void execute(IOContext context, Memory memory);
}
