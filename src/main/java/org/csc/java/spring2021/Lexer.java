package org.csc.java.spring2021;

public interface Lexer {

  /**
   * Преобразует текст программы в упорядоченный набор токенов
   */
  Token[] tokenize(String programText);
}
