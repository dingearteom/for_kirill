package org.csc.java.spring2021.moviedatabase;

import java.time.Year;
import java.util.Set;

/**
 * Интерфейс, описывающий кинофильм.
 *
 * Ни один из методов не может вернуть null.
 */
public interface Movie {
  String getTitle();

  Genre getGenre();

  Set<Actor> getCast();

  Year getReleaseYear();

  Rating getRating();
}
