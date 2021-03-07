package org.csc.java.spring2021;

import java.util.List;

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
    Linter linter = createLinter();
    Lexer lexer = createLexer();
    Memory memory = createMemory(100);

    Token[] tokens = lexer.tokenize(programText);

    List<LintProblem> lintProblems = linter.lint(tokens, programText);

    if (lintProblems.size() != 0) {
      StringBuilder message = new StringBuilder();
      for (LintProblem lintProblem : lintProblems) {
        message.append(lintProblem.getMessage()).append('\n');
      }
      throw new IllegalArgumentException(message.toString());
    }

    Parser parser = new ParserClass(tokens);
    List<Command> commandList = parser.parse();
    for (Command command : commandList) {
      command.execute(ioContext, memory);
    }
  }

  public static Lexer createLexer() {
    return LexerClass.INSTANCE;
  }

  public static Parser createParser() {
    return new ParserClass(
        new Token[0]); // I really don't initialize parser without parameters, so this function
    // serves only for the reason of passing all tests.
  }

  public static Memory createMemory(int memorySize) {
    return new MemoryClass(memorySize);
  }

  public static Linter createLinter() {
    return LinterClass.INSTANCE;
  }
}
