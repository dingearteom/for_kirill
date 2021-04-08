package org.csc.java.spring2021.immutable;

import java.util.function.BiFunction;

/**
 * <p>
 * Данный класс отвечает случаю, когда список пуст. Пустой список используется как база для
 * конструирования других списков совместно с классом {@link Cons}.
 * </p>
 * <p>
 * Этот класс намеренно обозначен как package-private, чтобы его нельзя было использовать в других
 * пакетах.
 * </p>
 * <p>
 * Единственное место, в котором он может быть сконструирован - это метод {@link
 * FListUtils#empty()}. Конструирование в других местах будет считаться ошибкой.
 * </p>
 */
final class Nil<T> extends FList<T> {

  @Override
  public int size() {
    return 0;
  }

  @Override
  public T get(int index)
  {
    throw new IndexOutOfBoundsException();
  }

  @Override
  public <R> R foldr(R zero, BiFunction<T, R, R> folder) {
    return zero;
  }

  @Override
  public <R> R foldl(R zero, BiFunction<R, T, R> folder) {
    return zero;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null){
      return false;
    }
    return (getClass() == obj.getClass());
  }

  @Override
  public int hashCode() {
    return 0;
  }

}
