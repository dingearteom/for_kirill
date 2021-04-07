package org.csc.java.spring2021.moviedatabase;

import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.csc.java.spring2021.NotImplementedException;

/**
 * Данный класс используется как обертка вокруг {@link MovieDatabase} и реализует популярные
 * запросы, требующиеся системе.
 * <p>
 * Он не должен хранить никакого промежуточного состояния и должен отвечать на запросы только на
 * основе данных, которые возвращает {@link MovieDatabase}.
 * <p>
 * Ваша основная задача - реализовать методы данного класса.
 * <p>
 * Рекомендации:
 * <p>
 * 1. По максимуму используйте StreamAPI - старайтесь воздержаться от процедурного кода.
 * <p>
 * 2. Внимательно просмотрите классы {@link java.util.stream.Stream}, {@link Collectors}, {@link
 * Comparator}, {{@link java.util.function.BinaryOperator}}. В них есть множество полезных методов,
 * которые вам наверняка пригодятся
 */
public class MovieRepository {

  public MovieRepository(MovieDatabase database) {
  }

  /**
   * Возвращает список из самых популярных фильмов.
   *
   * @param limit ограничение на максимальное количество фильмов, которое можно вернуть.
   * @return список фильмов, упорядоченных по рейтингу и по названию.
   * @throws IllegalArgumentException в случае, если limit <= 0
   */
  public List<Movie> collectTopRatedMoviesForAllTime(int limit) {
    throw new NotImplementedException();
  }

  /**
   * Находит всех актеров, которые снимались в фильмах определенного жанра в заданный временной
   * промежуток.
   *
   * @param genre         выбранный жанр
   * @param fromInclusive начало промежутка
   * @param toExclusive   окончание промежутка (не включительно)
   * @return список всех актеров, кто хоть раз за этот период снимался в фильме выбранного жанра.
   * @throws IllegalArgumentException в случае, если некорректно задан временной промежуток.
   */
  public List<Actor> collectActorsOfGenreForPeriod(Genre genre, Year fromInclusive,
      Year toExclusive) {
    throw new NotImplementedException();
  }

  /**
   * Для каждого актера находит фильм с самым большим рейтингом. Если два фильма имеют одинаковый
   * рейтинг, то из них нужно выбрать на основе имени.
   *
   * @return словарь, из которого по актеру можно достать фильм с его участием с самым большим
   * рейтингом
   */
  public Map<Actor, Movie> collectTopRatedMovieByActor() {
    throw new NotImplementedException();
  }

  /**
   * Возвращает фильмы, сгруппированные по году выпуска. В каждой группе фильмы должны быть
   * отсортированы по рейтингу и имени. limit используется как ограничитель на количество фильмов в
   * группе.
   *
   * @param limit максимальное количество фильмов в каждой отдельной группе
   * @return словарь, из которого можно достать фильмы, снятые в определенный год и упорядоченные по
   * рейтингу и имени.
   */
  public Map<Year, List<Movie>> collectTopRatedMoviesByYears(int limit) {
    throw new NotImplementedException();
  }
}
