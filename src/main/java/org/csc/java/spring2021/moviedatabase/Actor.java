package org.csc.java.spring2021.moviedatabase;

/**
 * Интерфейс, описывающий актера.
 *
 * Класс, который имплементирует этот интерфейс, обязан реализовать hashCode и equals.
 *
 * Ни один из методов не может вернуть null.
 */
public interface Actor {

  String getFirstName();

  String getLastName();

  @Override
  boolean equals(Object o);

  @Override
  int hashCode();
}

