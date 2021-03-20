package org.csc.java.spring2021.iterables;

import org.csc.java.spring2021.NotImplementedException;

public final class IterableUtils {

  /**
   * IterableUtils - утильный класс, мы не хотим, чтобы кто-то мог его инстанционировать.
   */
  private IterableUtils() {
  }

  /**
   * Возвращает объект, позволяющий итерироваться по числам от {@code from} до {@code to}. Шаг
   * итерации всегда равен 1. <br/>Ваша реализация должна использовать O(1) дополнительной памяти.
   * <br/>Разрешено использовать анонимные и статические классы. <br/><br/> Пример использования:
   * <pre>
   * {@code
   * System.out.println("Numbers 1-99:");
   * for (int i : range(0, 100)) {
   *  System.out.println(i);
   * }
   * }
   * </pre>
   * Цена: 0.5 балла
   *
   * @param from начальный индекс
   * @param to   конечный индекс (невключительно)
   * @return итерируемый объект
   */
  public static Iterable<Integer> range(int from, int to) {
    throw new NotImplementedException();
  }

  /**
   * Возвращает объект, позволяющий итерироваться по числам от {@code from} до {@code to} с шагом
   * {@code step}. <br/>Ваша реализация должна использовать O(1) дополнительной памяти.
   * <br/>Разрешено использовать анонимные и статические классы. <br/><br/> Пример использования:
   * <pre>
   * {@code
   * System.out.println("Odd numbers:");
   * for (int i : range(1, 100, 2)) {
   *  System.out.println(i);
   * }
   * }
   * </pre>
   * Цена: 0.5 балла
   *
   * @param from начальный индекс
   * @param to   конечный индекс (невключительно)
   * @param step размер шага итерации (любое целое число)
   * @return итерируемый объект
   */
  public static Iterable<Integer> range(int from, int to, int step) {
    throw new NotImplementedException();
  }

  /**
   * Возвращает объект, позволяющий итерироваться по символам переданной строки. <br/>Ваша
   * реализация должна использовать O(1) дополнительной памяти. <br/> Разрешено использовать
   * анонимные и статические классы. <br/><br/> Пример использования:
   * <pre>
   * {@code
   * for (char c : iterate("Awesome string")) {
   *  if (Character.isUpperCase(c) {
   *    System.out.println(c + " is upper case char");
   *  }
   * }
   * }
   * </pre>
   * Цена: 1 балл
   *
   * @param string строка, по символам которой будет происходить итерирование
   * @return итерируемый объект
   */
  public static Iterable<Character> iterate(String string) {
    throw new NotImplementedException();
  }

  /**
   * Возвращает объект, позволяющий итерироваться одновременно по двум итерируемым объектам. Пусть
   * {@code first} позволяет проитерироваться по n элементам, а {@code second} - по m. Тогда
   * возвращаемый объект позволит проитерироваться по min(n, m) элементам. <br/>Разрешено
   * использовать анонимные и статические классы. <br/><br/> Пример использования:
   * <pre>
   * {@code
   * List<String> names = List.of("Миша", "Сергей", "Роман");
   * List<Integer> grades = List.of(5, 5, 4, 3);
   * for (Pair<String, Integer> gradedName : zip(names, grades)) {
   *  System.out.println(String.format("%s имеет оценку %d", gradedName.getFirst(), gradedName.getSecond());
   * }
   * }
   * </pre>
   * Цена: 1 балл
   *
   * @param first  первый итерируемый объект
   * @param second второй итерируемый объект
   * @param <A>    тип элементов первого итерируемого объекта
   * @param <B>    тип элементов второго итерируемого объекта
   * @return объект, позволяющий произвести одновременную итерацию
   */
  public static <A, B> Iterable<Pair<A, B>> zip(
      Iterable<? extends A> first,
      Iterable<? extends B> second) {
    throw new NotImplementedException();
  }
}
