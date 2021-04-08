package org.csc.java.spring2021.immutable;

import java.util.function.BiFunction;

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

  private final T head;
  private final FList<T> tail;

  Cons(T head, FList<T> tail) {
    this.head = head;
    this.tail = tail;
  }

  @Override
  public int size() {
    return tail.size() + 1;
  }

  @Override
  public T get(int index) {
    if (index < 0 || index >= size()){
      throw new IndexOutOfBoundsException();
    }

    if (index == 0){
      return head;
    }
    else{
      return tail.get(index - 1);
    }
  }

  @Override
  public <R> R foldr(R zero, BiFunction<T, R, R> folder) {
    return folder.apply(head, tail.foldr(zero, folder));
  }

  @Override
  public <R> R foldl(R zero, BiFunction<R, T, R> folder) {
    return tail.foldl(folder.apply(zero, head), folder);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null)
      return false;
    if (getClass() != o.getClass())
      return false;
    Cons<T> object = (Cons<T>)o;
    if (size() != object.size())
      return false;
    for (int index = 0; index < size(); index++){
      if (!get(index).equals(object.get(index)))
        return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;
    for (int index = 0; index < size(); index++){
      T element = get(index);
      hashCode = 31 * hashCode + (element == null ? 0 : element.hashCode());
    }
    return hashCode;
  }
}
