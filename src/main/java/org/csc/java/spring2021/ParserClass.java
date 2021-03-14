package org.csc.java.spring2021;

import com.sun.jdi.InternalException;

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


public class ParserClass implements Parser {

  private Token[] tokens;
  private int numOfTokens;
  private int[] pairBracket;

  public ParserClass() {}

  private void initialize(Token[] tokens){
    this.tokens = tokens;
    this.numOfTokens = tokens.length;
    this.pairBracket = new int[this.numOfTokens];

    List<Integer> brackets = new ArrayList<Integer>();

    for (int i = 0; i < this.numOfTokens; i++) {
      if (this.tokens[i] == LEFT_BRACKET) {
        brackets.add(i);
      } else if (this.tokens[i] == RIGHT_BRACKET) {
        pairBracket[brackets.get(brackets.size() - 1)] = i;
        brackets.remove(brackets.size() - 1);
      }
    }
  }

  @Override
  public Command parse(Token[] tokens) {
    initialize(tokens);
    return parse(0, numOfTokens);
  }

  private Command parse(int left, int right) {
    List<Command> commandList = new ArrayList<Command>();
    int tokenIndex = left;
    while (tokenIndex < right) {
      if (this.tokens[tokenIndex] == LEFT_BRACKET) {
        int rightBracket = this.pairBracket[tokenIndex];
        commandList
            .add(new CommandClass(CommandType.LOOPCOMMAND,
                parse(tokenIndex + 1, rightBracket)));
        tokenIndex = rightBracket + 1;
      } else {
        commandList.add(
            switch (this.tokens[tokenIndex]) {
              case NEXT -> new CommandClass(CommandType.NEXT);
              case PREVIOUS -> new CommandClass(CommandType.PREVIOUS);
              case INCREASE -> new CommandClass(CommandType.INCREASE);
              case DECREASE -> new CommandClass(CommandType.DECREASE);
              case INPUT -> new CommandClass(CommandType.INPUT);
              case OUTPUT -> new CommandClass(CommandType.OUTPUT);
              default -> throw new RuntimeException();
            });
        tokenIndex++;
      }
    }
    return new ListCommandClass(commandList);
  }


}
