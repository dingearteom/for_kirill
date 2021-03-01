package org.csc.java.spring2021;

public class Interpreter {

  /**
   * Исполняет программу programText, написанную на языке Brainfuck, используя ioContext в качестве
   * устройства ввода-вывода. Для отладки предлагается передавать ConsoleIOContext, он использует
   * System.in и System.out
   *
   * @throws IllegalArgumentException если на вход дан некорректный текст программы. Текст сообщения
   *                                  – конкатенация сообщений об ошибках LintProblem#getMessage
   */
  public static void run(IOContext ioContext, String programText) throws IllegalArgumentException {
  }

  public static Lexer createLexer() {
    return null;
  }

  public static Parser createParser() {
    return null;
  }

  public static Memory createMemory(int memorySize) {
    return null;
  }

  public static Linter createLinter() {
    return null;
  }
}
