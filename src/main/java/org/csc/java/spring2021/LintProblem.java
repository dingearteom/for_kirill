package org.csc.java.spring2021;

/**
 * Дескриптор проблемы
 */
public interface LintProblem {

  /**
   * @return позиция проблемы в исходном тексте программы
   */
  int getPosition();

  /**
   * @return подробное описание проблемы
   */
  String getMessage();
}
