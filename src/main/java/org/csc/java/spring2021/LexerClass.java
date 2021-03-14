package org.csc.java.spring2021;

import static org.csc.java.spring2021.Token.DECREASE;
import static org.csc.java.spring2021.Token.INCREASE;
import static org.csc.java.spring2021.Token.INPUT;
import static org.csc.java.spring2021.Token.LEFT_BRACKET;
import static org.csc.java.spring2021.Token.NEXT;
import static org.csc.java.spring2021.Token.OUTPUT;
import static org.csc.java.spring2021.Token.PREVIOUS;
import static org.csc.java.spring2021.Token.RIGHT_BRACKET;
import static org.csc.java.spring2021.Token.UNRECOGNIZED_TOKEN;

public class LexerClass implements Lexer {

  public static LexerClass INSTANCE = new LexerClass();

  private LexerClass() {
  }

  public Token[] tokenize(String programText) {
    Token[] tokens = new Token[programText.length()];
    for (int i = 0; i < programText.length(); i++) {
      char command = programText.charAt(i);
      tokens[i] = switch (command) {
        case '>' -> NEXT;
        case '<' -> PREVIOUS;
        case ',' -> INPUT;
        case '.' -> OUTPUT;
        case '+' -> INCREASE;
        case '-' -> DECREASE;
        case '[' -> LEFT_BRACKET;
        case ']' -> RIGHT_BRACKET;
        default -> UNRECOGNIZED_TOKEN;
      };
    }
    return tokens;
  }
}
