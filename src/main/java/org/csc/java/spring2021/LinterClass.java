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

import java.util.ArrayList;
import java.util.List;


public class LinterClass implements Linter {

  public static LinterClass INSTANCE = new LinterClass();

  private LinterClass() {
  }

  public List<LintProblem> lint(Token[] tokens, String programText) {
    List<LintProblem> lintProblems = new ArrayList<LintProblem>();
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i] == UNRECOGNIZED_TOKEN) {
        lintProblems.add(new LintProblemClass(i + 1,
            "Unrecognized token was encountered."));
      }
    }
    String errorMessageWrongBracketsOrder = "Square brackets must form right bracket's sequence.";
    int balance = 0;
    boolean errorRaised = false;
    for (int i = 0; i < tokens.length; i++) {
        if (tokens[i] == LEFT_BRACKET) {
            balance++;
        } else if (tokens[i] == RIGHT_BRACKET) {
            balance--;
        }
      if (balance < 0) {
        lintProblems.add(new LintProblemClass(i + 1, errorMessageWrongBracketsOrder));
        errorRaised = true;
        break;
      }
    }
    if (!errorRaised && balance != 0) {
      lintProblems.add(new LintProblemClass(tokens.length, errorMessageWrongBracketsOrder));
    }
    return lintProblems;
  }
}
