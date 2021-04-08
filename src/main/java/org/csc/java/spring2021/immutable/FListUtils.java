package org.csc.java.spring2021.immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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
    return new Nil<>();
  }

  /**
   * Создает новый список на основе head и tail.
   * <p>
   * Получившийся список должен иметь head в качестве первого элемента, и список tail в качестве
   * хвоста (см. {@link Cons}).
   */
  @SuppressWarnings("unchecked")
  public static <T> FList<T> cons(T head, FList<? extends T> tail) {
    return new Cons<>(head, (FList<T>)tail);
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
    if (elements.length == 0){
      return new Nil<>();
    }
    else
      return new Cons<>(elements[0], listOf(Arrays.copyOfRange(elements, 1, elements.length)));
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
    return list.foldr(zero, folder);
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
    return list.foldl(zero, folder);
  }

  /**
   * Создает обычный Java-список на основе переданного списка.
   * <p>
   * Этот метод нужен для более удобного взаимодействия с существующим Java-кодом.
   */
  public static <T> List<T> toJavaList(FList<T> list) {
    List<T> javaList = new ArrayList<>();
    for (int index = 0; index < list.size(); index++){
      javaList.add(list.get(index));
    }
    return javaList;
  }

  /**
   * Объединяет два списка в один: сначала идут элементы из first, потом из second.
   */
  @SuppressWarnings("unchecked")
  public static <T> FList<T> concat(FList<? extends T> first, FList<? extends T> second) {
    FList<T> result = (FList<T>)second;
    for (int index = first.size() - 1; index >= 0; index--){
      result = cons(first.get(index), result);
    }
    return result;
  }

  /**
   * Выполняет action на каждом элементе списка по-порядку.
   * <p>
   * Вызывается ради побочных эффектов, например для печати содержимого в консоль.
   */
  public static <T> void forEach(FList<T> list, Consumer<? super T> action) {
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
  public static <T, K> FList<K> map(FList<T> list, Function<? super T, ? extends K> mapper) {
    FList<K> result = new Nil<>();
    for (int index = list.size() - 1; index >= 0; index--){
      result = new Cons<>(mapper.apply(list.get(index)), result);
    }
    return result;
  }

  /**
   * Создает новый список из уже существующего, оставляя только те элементы, которые удовлетворяют
   * предикату filter.
   */
  public static <T> FList<T> filter(FList<T> list, Predicate<? super T> filter) {
    FList<T> result = new Nil<>();
    for (int index = list.size() - 1; index >= 0; index--){
      T element = list.get(index);
      if (filter.test(element)){
        result = new Cons<>(element, result);
      }
    }
    return result;
  }
}