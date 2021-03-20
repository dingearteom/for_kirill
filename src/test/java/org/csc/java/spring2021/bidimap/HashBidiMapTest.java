package org.csc.java.spring2021.bidimap;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.csc.java.spring2021.iterables.Pair;
import org.junit.jupiter.api.Test;

public class HashBidiMapTest {

  @Test
  void testEmpty() {
    BidiMap<Integer, Integer> map = new HashBidiMap<>();
    assertThat(map).isEmpty();
    assertThat(map.get(0)).isNull();
  }

  @Test
  void testMapBased() {
    Map<Integer, Integer> originMap = new HashMap<>();
    originMap.put(5, 10);

    BidiMap<Integer, Integer> map = new HashBidiMap<>(originMap);
    assertThat(map).hasSize(1);
    assertThat(map.get(0)).isNull();
    assertThat(map.get(5)).isEqualTo(10);
    assertThat(map.getKeysByValue(10)).containsOnly(5);
    assertThat(map.getKeysByValue(5)).isNull();
  }

  @Test
  void testRemoveFromOrigin() {
    Map<Integer, Integer> originMap = new HashMap<>();

    originMap.put(5, 10);
    BidiMap<Integer, Integer> map = new HashBidiMap<>(originMap);

    originMap.remove(5);
    assertThat(map).hasSize(1);
    assertThat(map.get(5)).isEqualTo(10);
  }

  @Test
  void testRemove() {
    Map<Integer, Integer> originMap = new HashMap<>();

    originMap.put(5, 10);
    BidiMap<Integer, Integer> map = new HashBidiMap<>(originMap);

    map.remove(5);
    assertThat(map).isEmpty();
    assertThat(map.get(5)).isNull();
    assertThat(originMap.get(5)).isEqualTo(10);
  }

  @Test
  void testPut() {
    BidiMap<Integer, Integer> map = new HashBidiMap<>();

    map.put(5, 10);
    assertThat(map.get(5)).isEqualTo(10);
    assertThat(map.getKeysByValue(10)).containsOnly(5);
    assertThat(map).hasSize(1);

    map.put(6, 10);
    assertThat(map.get(6)).isEqualTo(10);
    assertThat(map.getKeysByValue(10)).containsOnly(5, 6);
    assertThat(map).hasSize(2);
  }

  @Test
  void testRemoveByValue() {
    BidiMap<Integer, Integer> map = new HashBidiMap<>();

    map.put(5, 10);
    map.put(6, 10);
    map.put(7, 11);
    assertThat(map).hasSize(3);

    map.removeByValue(10);
    assertThat(map).hasSize(1);
    assertThat(map.get(5)).isNull();
    assertThat(map.get(6)).isNull();
    assertThat(map.get(7)).isEqualTo(11);
    assertThat(map.getKeysByValue(10)).isNull();
    assertThat(map.getKeysByValue(11)).containsOnly(7);
  }

  @Test
  void testDifferentEqualObjects() {
    // Pair class has overridden `equals` method
    Pair<Integer, Integer> pair1 = Pair.of(1, 2);
    Pair<Integer, Integer> pair2 = Pair.of(1, 2);

    BidiMap<Integer, Pair<Integer, Integer>> map = new HashBidiMap<>();

    map.put(1, pair1);
    map.put(2, pair2);
    assertThat(map.getKeysByValue(Pair.of(1, 2))).containsOnly(1, 2);
    assertThat(map).isNotEmpty();

    map.removeByValue(Pair.of(1, 2));
    assertThat(map).isEmpty();
  }
}
