package org.csc.java.spring2021;

import java.util.List;

public interface Parser {

  /**
   * Преобразует токены в исполняемую программу. Можно полагаться на то, что токены уже проверены на
   * корректность с помощью Linter
   */
  List<Command> parse();
}
