package org.csc.java.spring2021.iterables;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class IterableUtilsTest {

  @Test
  void testFrom() {
    List<Integer> indices = new ArrayList<>();
    for (int i : IterableUtils.range(11, 20)) {
      indices.add(i);
    }

    assertEquals(9, indices.size());
    for (int i = 0; i < indices.size(); i++) {
      assertThat(indices).element(i).isEqualTo(i + 11);
    }

  }

  @Test
  void testFromWithStep() {
    List<Integer> indices = new ArrayList<>();
    for (int i : IterableUtils.range(11, 20, 2)) {
      indices.add(i);
    }

    assertThat(indices).hasSize(5);

    int i = 0;
    for (int forIndex = 11; forIndex < 20; forIndex += 2) {
      assertThat(indices).element(i).isEqualTo(forIndex);
      i++;
    }
  }

  @Test
  void testStringIterate() {
    String string = "The quick brown fox jumps over the lazy dog";

    int count = 0;
    for (char c : IterableUtils.iterate(string)) {
      assertThat(c)
          .withFailMessage(
              "Символ %c не равен символу %c по индексу %d", c, string.charAt(count), count
          )
          .isEqualTo(string.charAt(count));

      count++;
    }

    assertThat(count).isEqualTo(string.length());
  }

  @Test
  void testZip() {
    List<Integer> integers = new ArrayList<>();
    List<String> strings = new ArrayList<>();
    Map<Integer, String> map = new HashMap<>();

    integers.add(2016);
    strings.add("Kotlin release");
    map.put(2016, "Kotlin release");

    integers.add(2002);
    strings.add("Spring release");
    map.put(2002, "Spring release");

    integers.add(1996);
    strings.add("Java release");
    map.put(1996, "Java release");

    strings.add("Unknown date");

    int count = 0;
    for (Pair<Integer, String> pair : IterableUtils.zip(integers, strings)) {
      assertThat(pair.getSecond()).isEqualTo(map.get(pair.getFirst()));
      count++;
    }

    assertThat(count).isEqualTo(Math.min(integers.size(), strings.size()));
  }
}
