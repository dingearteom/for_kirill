package org.csc.java.spring2021;

import java.util.List;

public interface Linter {

  /**
   * Проверяет программу на наличие ошибок и возвращает список с детальной информацией о проблемах
   */
  List<LintProblem> lint(Token[] tokens, String programText);
}
