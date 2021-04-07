package org.csc.java.spring2021.moviedatabase;

import java.util.Objects;

/**
 * Value-класс, обозначающий рейтинг фильма.
 *
 * Рейтинг - это число в диапазоне [0, 10].
 */
public class Rating implements Comparable<Rating> {

  private final double value;

  public Rating(double value) {
    if (value < 0 || value > 10) {
      throw new IllegalArgumentException("Unacceptable rating value " + value + ", it should be in [0, 10]");
    }

    this.value = value;
  }

  public double getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rating rating = (Rating) o;
    return Double.compare(rating.value, value) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public int compareTo(Rating o) {
    return Double.compare(value, o.getValue());
  }

  public static Rating of(double value) {
    return new Rating(value);
  }
}