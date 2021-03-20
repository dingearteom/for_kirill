package org.csc.java.spring2021.smartlist;

import java.util.AbstractList;
import java.util.Collection;
import java.util.RandomAccess;
import org.csc.java.spring2021.NotImplementedException;

/**
 * Оптимизированный по памяти и виртуальным вызовам список. {@link AbstractList} является базовым
 * классом для реализации списков. В результате реализации методов, представленных в данном классе,
 * вы получите полностью работоспособный список. <br/><br/> Ограничения:
 * <ul>
 * <li>У класса должно быть <b>не больше 2</b> полей (но вы можете добавлять при необходимости статические поля)</li>
 * <li>При размере 0 не хранит ничего, кроме размера.</li>
 * <li>При размере 1 хранит единственный элемент</li>
 * <li>При размере больше 1 хранит массив своих элементов (аналогично тому, как это сделано в {@link java.util.ArrayList}</li>
 * </ul>
 * Не забудьте, что вам нужно вернуть значение поля, используемого для хранения элементов, из метода
 * {@link SmartList#getInternalData}.
 * <br/><br/>
 * Цена: 4 балла
 *
 * @param <V> тип элементов в списке
 */
public class SmartList<V> extends AbstractList<V> implements RandomAccess {

  /**
   * Конструктор. Создаёт пустой список
   */
  public SmartList() {
    throw new NotImplementedException();
  }

  /**
   * Конструктор. Создаёт список, состоящий из элементов переданной коллекции
   *
   * @param collection коллекция
   */
  public SmartList(Collection<? extends V> collection) {
    throw new NotImplementedException();
  }

  /**
   * Конструктор. Создаёт список, состоящий из переданного элемента
   *
   * @param element элемент
   */
  public SmartList(V element) {
    throw new NotImplementedException();
  }

  /**
   * Конструктор. Создаёт список, состоящий из элементов переданного массива. Дальнейшие изменения в
   * переданном массиве не должны влиять на список
   *
   * @param elements массив элементов
   */
  @SafeVarargs
  public SmartList(V... elements) {
    throw new NotImplementedException();
  }

  /**
   * Получение элемента по указанному индексу в списке
   *
   * @param index позиция в списке
   * @return элемент
   * @throws IndexOutOfBoundsException если индекс попадает за пределы списка ({@code index < 0 ||
   *                                   index >= size()})
   */
  @Override
  public V get(int index) {
    throw new NotImplementedException();
  }

  /**
   * Вычисляет размер списка.
   *
   * @return количество элементов в списке
   */
  @Override
  public int size() {
    throw new NotImplementedException();
  }

  /**
   * Заменяет элемент по определенному индексу
   *
   * @param index позиция в списке
   * @return элемент, который находился по этому индексу ранее
   * @throws IndexOutOfBoundsException если индекс попадает за пределы списка ({@code index < 0 ||
   *                                   index >= size()})
   */
  @Override
  public V set(int index, V element) {
    throw new NotImplementedException();
  }

  /**
   * Удаляет элемент по определенному индексу, сдвигая элементы, находящиеся правее удаляемого,
   * влево.
   *
   * @param index позиция в списке
   * @return удалённый элемент
   * @throws IndexOutOfBoundsException если индекс попадает за пределы списка ({@code index < 0 ||
   *                                   index >= size()})
   */
  @Override
  public V remove(int index) {
    throw new NotImplementedException();
  }

  /**
   * Вставляет элемент в определенную позицию списка, сдвигая элементы вправо при необходимости.
   *
   * @param index   позиция в списке
   * @param element вставляемый элемент
   * @throws IndexOutOfBoundsException если индекс попадает за пределы списка ({@code index < 0 ||
   *                                   index > size()})
   */
  @Override
  public void add(int index, V element) {
    throw new NotImplementedException();
  }

  /**
   * Возвращает значение поля, в котором SmartList хранит свои данные. Выполнение этого метода не
   * должно влиять на состояние списка.
   * <br/>
   * Этот метод нужен для того, чтобы мы могли протестировать вашу реализацию.
   *
   * @return значение поля, с помощью которого хранятся элементы списка.
   */
  protected Object getInternalData() {
    throw new NotImplementedException();
  }
}
