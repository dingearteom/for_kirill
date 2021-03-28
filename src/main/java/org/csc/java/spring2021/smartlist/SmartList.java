package org.csc.java.spring2021.smartlist;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.RandomAccess;
import org.csc.java.spring2021.NotImplementedException;
import java.util.Arrays;


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

  private static final int DEFAULT_CAPACITY = 10;
  private static final Object[] EMPTY_ELEMENTDATA = {};
  private Object singleValue = null;
  private Object[] elementData;
  private int size = 0;

  @SuppressWarnings("unchecked")
  private V singleValue(){
    return (V) singleValue;
  }

  @SuppressWarnings("unchecked")
  private V elementData(int index){
    return (V) elementData[index];
  }

  public SmartList() {
      this.elementData = EMPTY_ELEMENTDATA;
  }

  /**
   * Конструктор. Создаёт список, состоящий из элементов переданной коллекции
   *
   * @param collection коллекция
   */
  public SmartList(Collection<? extends V> collection) {
    Object[] a = collection.toArray().clone();
    this.size = a.length;
    if (size > 1){
      this.elementData = a;
    }
    else if (size == 1) {
      this.singleValue = a[0];
    }
    else{
      elementData = EMPTY_ELEMENTDATA;
    }
  }

  /**
   * Конструктор. Создаёт список, состоящий из переданного элемента
   *
   * @param element элемент
   */
  public SmartList(V element) {
    size = 1;
    singleValue = element;
  }

  /**
   * Конструктор. Создаёт список, состоящий из элементов переданного массива. Дальнейшие изменения в
   * переданном массиве не должны влиять на список
   *
   * @param elements массив элементов
   */
  @SafeVarargs
  public SmartList(V... elements) {
    Object[] a = elements.clone();
    this.size = a.length;
    if (size > 1){
      this.elementData = a;
    }
    else if (size == 1) {
      this.singleValue = a[0];
    }
    else{
      elementData = EMPTY_ELEMENTDATA;
    }
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
    Objects.checkIndex(index, size);
    if (size == 1){
      return singleValue();
    }
    else{
      return elementData(index);
    }
  }

  /**
   * Вычисляет размер списка.
   *
   * @return количество элементов в списке
   */
  @Override
  public int size() {
    return size;
  }

  private  Object[] grow(int minCapacity){
    int oldCapacity = elementData.length;
    if (oldCapacity >= DEFAULT_CAPACITY) {
      int newCapacity = oldCapacity + oldCapacity >> 1;
      return elementData = Arrays.copyOf(elementData, newCapacity);
    } else {
      return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }
  }
  private Object[] grow(){
    return grow(size + 1);
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
    Objects.checkIndex(index, size);
    if (size == 1){
      V oldValue = singleValue();
      singleValue = element;
      return oldValue;
    }
    else{
      V oldValue = elementData(index);
      elementData[index] = element;
      return oldValue;
    }
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
    Objects.checkIndex(index, size);
    V oldValue;
    if (size == 1) {
      oldValue = singleValue();
      singleValue = null;
      size--;
    }
    else{
      oldValue = elementData(index);
      System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
      size--;
      if (size == 1){
        singleValue = elementData[0];
        elementData = EMPTY_ELEMENTDATA;
      }
    }
    return oldValue;
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
    if (index < 0 && index > size){
      throw new IndexOutOfBoundsException();
    }
    if (size == 1){
      elementData = new Object[]{singleValue};
      singleValue = null;
    }
    if (size == elementData.length){
      grow();
    }
    System.arraycopy(elementData, index, elementData, index + 1, size - index);
    elementData[index] = element;
    size++;
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
    if (size == 1){
      return singleValue;
    }
    else{
      return elementData;
    }
  }
}
