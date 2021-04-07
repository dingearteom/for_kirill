package org.csc.java.spring2021.immutable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.csc.java.spring2021.NotImplementedException;

/**
 * <p>
 * Этот класс является точкой входа для работы с {@link FList}. Все операции кроме примитивных или
 * Java-специфичных (equals, toString, hashCode) должны проводиться с использованием статических
 * методов этого класса.
 * </p>
 * <p>
 * Вам необходимо реализовать все нереализованные методы, которые есть в этом классе. Для их
 * реализации постарайтесь по-максимуму использовать функции {@link FList#foldl} и {@link
 * FList#foldr}; старайтесь избегать циклов и мутируемых переменных.
 * </p>
 * <p>
 * В качестве примера оставлена реализация метода {@link FListUtils#forEach} с использованием foldl.
 * </p>
 */
public final class FListUtils {

  /**
   * Возвращает пустой список (см. {@link Nil}).
   */
  public static <T> FList<T> empty() {
    throw new NotImplementedException();
  }

  /**
   * Создает новый список на основе head и tail.
   * <p>
   * Получившийся список должен иметь head в качестве первого элемента, и список tail в качестве
   * хвоста (см. {@link Cons}).
   */
  public static <T> FList<T> cons(T head, FList<T> tail) {
    throw new NotImplementedException();
  }

  /**
   * Возвращает список, содержащий элементы из elements в оригинальном порядке.
   * <p>
   * Позволяет удобно создать список сразу из нескольких элементов.
   * <p>
   * Полученный список не должен отличаться от списка, созданного из тех же элементов с помощью
   * нескольких вызовов {@link FListUtils#cons}.
   */
  @SafeVarargs
  public static <T> FList<T> listOf(T... elements) {
    throw new NotImplementedException();
  }

  /**
   * <p>
   * Операция правой свертки.
   * </p>
   * <p>
   * Пример: {@code foldr([1, 2, 3, 4], 42, f) == f(1, f(2, f(3, f(4, 42))))}.
   * </p>
   * <p>
   * Несколько примеров есть в тестах.
   * </p>
   */
  public static <T, R> R foldr(
      FList<T> list,
      R zero,
      BiFunction<T, R, R> folder
  ) {
    throw new NotImplementedException();
  }

  /**
   * <p>
   * Операция левой свертки.
   * </p>
   * <p>
   * Пример: {@code foldl([1, 2, 3, 4], 42, f) == f(f(f(f(42, 1), 2), 3), 4)}.
   * </p>
   * <p>
   * Несколько примеров есть в тестах.
   * </p>
   */
  public static <T, R> R foldl(
      FList<T> list,
      R zero,
      BiFunction<R, T, R> folder
  ) {
    throw new NotImplementedException();
  }

  /**
   * Создает обычный Java-список на основе переданного списка.
   * <p>
   * Этот метод нужен для более удобного взаимодействия с существующим Java-кодом.
   */
  public static <T> List<T> toJavaList(FList<T> list) {
    throw new NotImplementedException();
  }

  /**
   * Объединяет два списка в один: сначала идут элементы из first, потом из second.
   */
  public static <T> FList<T> concat(FList<T> first, FList<T> second) {
    throw new NotImplementedException();
  }

  /**
   * Выполняет action на каждом элементе списка по-порядку.ъ
   * <p>
   * Вызывается ради побочных эффектов, например для печати содержимого в консоль.
   */
  public static <T> void forEach(FList<T> list, Consumer<T> action) {
    foldl(list, null, (nothing, element) -> {
      action.accept(element);
      return nothing;
    });
  }

  /**
   * Создает новый список на основе уже существующего, применяя mapper к каждому элементу
   * оригинального списка (оригинал остается неизменным).
   * <p>
   * Используется для однотипных преобразований сразу всех элементов списка.
   */
  public static <T, K> FList<K> map(FList<T> list, Function<T, K> mapper) {
    throw new NotImplementedException();
  }

  /**
   * Создает новый список из уже существующего, оставляя только те элементы, которые удовлетворяют
   * предикату filter.
   */
  public static <T> FList<T> filter(FList<T> list, Predicate<T> filter) {
    throw new NotImplementedException();
  }
}