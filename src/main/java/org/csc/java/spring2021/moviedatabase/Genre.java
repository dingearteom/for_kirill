package org.csc.java.spring2021.moviedatabase;

import java.util.Objects;

/**
 * Value-класс для описания жанра кинофильма. Для сравнения двух жанров нужно использовать `equals`.
 */
public class Genre {

  // Несколько часто используемых жанров. Потенциально их может быть сколь угодно много
  public static final Genre COMEDY = new Genre("Comedy");
  public static final Genre THRILLER = new Genre("Thriller");
  public static final Genre DRAMA = new Genre("Drama");

  private final String name;

  public Genre(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Genre genre = (Genre) o;
    return name.equals(genre.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
