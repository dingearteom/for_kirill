package org.csc.java.spring2021;


public class LintProblemClass implements LintProblem {

  private int position;
  private String message;

  public LintProblemClass(int position, String message) {
    this.position = position;
    this.message = message;
  }

  public int getPosition() {
    return this.position;
  }

  public String getMessage() {
    return this.message;
  }

}
