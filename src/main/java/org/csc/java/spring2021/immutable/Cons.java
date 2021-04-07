package org.csc.java.spring2021.immutable;

import java.util.function.BiFunction;
import org.csc.java.spring2021.NotImplementedException;

/**
 * <p>
 * Данный класс должен позволять создавать создавать новый список, объединяя вместе новый элемент и
 * какой-то уже существующий список. Новый элемент является головой списка, а существующий список -
 * хвостом (head & tail).
 * </p>
 * <p>
 * Этот класс намеренно обозначен как package-private, чтобы его нельзя было использовать в других
 * пакетах.
 * </p>
 * <p>
 * Единственное место, в котором он может быть сконструирован - это метод {@link FListUtils#cons}.
 * Конструирование в других местах будет считаться ошибкой.
 * </p>
 */
final class Cons<T> extends FList<T> {

  Cons(T head, FList<T> tail) {
    throw new NotImplementedException();
  }

  @Override
  public int size() {
    throw new NotImplementedException();
  }

  @Override
  public T get(int index) {
    throw new NotImplementedException();
  }

  @Override
  public <R> R foldr(R zero, BiFunction<T, R, R> folder) {
    throw new NotImplementedException();
  }

  @Override
  public <R> R foldl(R zero, BiFunction<R, T, R> folder) {
    throw new NotImplementedException();
  }

  @Override
  public boolean equals(Object o) {
    throw new NotImplementedException();
  }

  @Override
  public int hashCode() {
    throw new NotImplementedException();
  }
}
