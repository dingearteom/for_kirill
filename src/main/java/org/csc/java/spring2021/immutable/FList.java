package org.csc.java.spring2021.immutable;

import java.util.function.BiFunction;
import org.csc.java.spring2021.NotImplementedException;

/**
 * <p>
 * Данный класс моделирует неизменяемый список, похожий на списки из функциональных языков
 * программирования (Functional List).
 * </p>
 * <p>
 * Созданный однажды список не подлежит изменению до самого конца программы. Это удобно, поскольку
 * его можно переиспользовать в нескольких местах сразу, не опасаясь совместных модификаций.
 * </p>
 * <p>
 * Список позволяет узнавать свой размер, получать элементы по индексу, а также имеет два метода
 * {@link FList#foldr} и {@link FList#foldl}, которые позволяют легко и эффективно выполнять над ним
 * различные преобразования.
 * </p>
 * <p>
 * ВАЖНО: Одной из задач является выставление корректных сигнатур у всех методов, в которых
 * используются параметры с дженериками - вам нужно проставить {@code extends} и {@code super} таким
 * образом, чтобы сигнатуры были максимально гибкими.
 * </p>
 */
public abstract class FList<T> {

  /**
   * Этот конструктор является package-private, чтобы от этого класса можно было унаследоваться
   * только в его же пакете. Так мы ограничиваем число наследников.
   */
  FList() {
  }

  /**
   * Позволяет узнать размер списка.
   */
  public abstract int size();

  /**
   * Возвращает элемент списка, находящийся по индексу.
   */
  public abstract T get(int index);

  /**
   * /**
   * <p>
   * Операция правой свертки.
   * </p>
   * <p>
   * Пример: {@code foldr([1, 2, 3, 4], 42, f) == f(1, f(2, f(3, f(4, 42))))}.
   * f в данном случае - какая-то операция (например, сложение).
   * </p>
   * <p>
   * Несколько примеров есть в тестах.
   * </p>
   * <p>
   * Этот метод является package-private, чтобы его нельзя было использовать напрямую. Если вы
   * хотите воспользоваться операцией `foldr`, см. класс `FListUtils`
   * </p>
   */
  abstract <R> R foldr(R zero, BiFunction<T, R, R> folder);

  /**
   * <p>
   * Операция левой свертки.
   * </p>
   * <p>
   * Пример: {@code foldl([1, 2, 3, 4], 42, f) == f(f(f(f(42, 1), 2), 3), 4)}.
   * f в данном случае - какая-то операция (например, сложение).
   * </p>
   * <p>
   * Несколько примеров есть в тестах.
   * </p>
   * <p>
   * Этот метод является package-private, чтобы его нельзя было использовать напрямую. Если вы
   * хотите воспользоваться операцией `foldl`, см. класс `FListUtils`
   * </p>
   */
  abstract <R> R foldl(R zero, BiFunction<R, T, R> folder);

  /**
   * Должно вернуть строковое представление списка в следующем виде: "FList [a1, a2, a3]", где
   * a1, a2, a3 - элементы списка через запятую.
   */
  @Override
  public final String toString() {
    StringBuilder representation = new StringBuilder();
    representation.append("FList [");
    for (int index = 0; index < size(); index++){
      representation.append(get(index));
      if (index != size() -1)
        representation.append(", ");
    }
    representation.append("]");
    return representation.toString();
  }

  /**
   * Два списка равны, если они имеют одинаковую длину и содержащиеся в них элементы попарно равны.
   */
  @Override
  public abstract boolean equals(Object other);

  /**
   * Хеш код списка напрямую зависит от его содержимого.
   * Воспользуйтесь {@link java.util.Objects#hash}.
   */
  @Override
  public abstract int hashCode();
}
