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
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class BrainfuckTest {

  @Test
  void lexer_smoke() {
    Lexer lexer = Interpreter.createLexer();

    Token[] tokens = lexer.tokenize("+-[]><.,!");
    Token[] expectedTokens =
        new Token[]{INCREASE, DECREASE, LEFT_BRACKET, RIGHT_BRACKET, NEXT, PREVIOUS, OUTPUT, INPUT,
            UNRECOGNIZED_TOKEN};

    assertArrayEquals(expectedTokens, tokens);
  }

  @Test
  void linter_smoke() {
    String programText = "++[*]$-";
    Token[] tokens = Interpreter.createLexer().tokenize(programText);
    List<LintProblem> problems = Interpreter.createLinter().lint(tokens, programText);

    assertEquals(2, problems.size());

    List<Integer> sortedPositions = problems.stream()
        .map(LintProblem::getPosition).sorted()
        .collect(Collectors.toList());

    assertEquals(4, sortedPositions.get(0));
    assertEquals(6, sortedPositions.get(1));
  }

  @Test
  void memory_smoke() {
    Memory memory = Interpreter.createMemory(42);
    assertEquals(42, memory.getSize());

    memory.shiftLeft();
    assertThrows(MemoryAccessException.class, memory::getByte);

    memory.shiftRight();
    memory.setByte((byte) 67);

    memory.shiftRight();
    memory.shiftLeft();

    assertEquals(67, memory.getByte());
  }

  @Test
  void interpreter_smoke() {
    assertNotNull(Interpreter.createParser());

    TestIOContext testContext = new TestIOContext();
    String helloWorld =
        "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.";

    Interpreter.run(testContext, helloWorld);

    assertEquals("Hello World!", testContext.readOutput());
    assertThrows(IllegalArgumentException.class, () -> Interpreter.run(testContext, "+-?"));

    testContext.reset();
    testContext.setInput("lol");

    String readWriteLol = ",>,>,<<.>.>.";

    Interpreter.run(testContext, readWriteLol);

    assertEquals("lol", testContext.readOutput());
  }

  private static class TestIOContext extends IOContext {

    TestIOContext() {
      super(new ByteArrayInputStream(new byte[42]), new ByteArrayOutputStream());
    }

    String readOutput() {
      return out.toString();
    }

    void setInput(String inputStr) {
      in = new ByteArrayInputStream(inputStr.getBytes(StandardCharsets.UTF_8));
    }

    void reset() {
      ((ByteArrayInputStream) in).reset();
      ((ByteArrayOutputStream) out).reset();
    }
  }
}
