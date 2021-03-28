package org.csc.java.spring2021.bidimap;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.sun.jdi.InternalException;
import org.csc.java.spring2021.NotImplementedException;

/**
 * Реализация {@link BidiMap}, основанная на хэш-таблицах ({@link java.util.HashMap}) {@link
 * AbstractMap} является базовым классом для реализации. В результате реализации методов,
 * представленных в этом классе вы получите полностью работоспособный ассоциативный массив (map).
 * <br/><br/>
 * Цена: 4 балла
 */
public class HashBidiMap<K, V> extends AbstractMap<K, V> implements BidiMap<K, V> {

  /**
   * Конструктор. Создаёт незаполненный {@link HashBidiMap}.
   */

  public HashBidiMap() {
    throw new NotImplementedException();
  }

  /**
   * Конструктор. Создаёт {@link HashBidiMap}, заполненный данными из переданного Map.
   *
   * @param originMap Map
   */
  public HashBidiMap(Map<K, V> originMap) {
    throw new NotImplementedException();
  }

  /**
   * Реализуйте недостающие методы {@link HashBidiMapEntrySet} и воспользуйтесь им. Возвращаемое
   * множество должно поддерживать удаление пар при обходе итератором, не нарушающее инвариант
   * двустороннеей ассоциации. Данный итератор используется при удалении элементов через {@link
   * #remove(K)}. <br/>В качестве альтернативы вы можете не реализовывать удаление в этом множестве,
   * а реализовать метод {@link #remove(K)} в {@link HashBidiMap} (-1 балл)
   *
   * @return множество (set) пар ключ-значение
   */
  @Override
  public Set<Entry<K, V>> entrySet() {
    throw new NotImplementedException();
  }

  /**
   * Получить все ключи, которым соответствует переданное значение.<br/>
   * <b>Важно:</b> асимптотика метода должна быть О(1)
   *
   * @param value значение
   * @return список ключей или null (в случае, если ключей с таким значением нет)
   */
  @Override
  public List<K> getKeysByValue(V value)
  {

    throw new NotImplementedException();
  }

  /**
   * Проверяет, присутствует ли хотя бы один ключ, ассоциированный с переданным значением<br/>
   * <b>Важно:</b> асимптотика метода должна быть О(1)
   *
   * @param value значение
   * @return true, если присутствует
   */
  @Override
  public boolean containsValue(Object value) {
    throw new NotImplementedException();
  }

  /**
   * Удаляет все пары, у которых значение равно переданному. Проверка на равенство должна
   * производиться через вызов метода {@link Object#equals(Object)}
   *
   * @param value значение
   * @return список ключей, которые были удалены (или null, если ничего не было удалено)
   */
  @Override
  public List<K> removeByValue(V value) {
    throw new NotImplementedException();
  }

  /**
   * Создаёт двустороннюю ассоциацию между переданными ключом и значением. Обратите внимание, что
   * двусторонняя ассоциация должна всегда оставаться инвариантом.
   *
   * @param key   ключ
   * @param value значение
   * @return значение, которое ранее хранилось по переданному ключу
   */
  @Override
  public V put(K key, V value) {
    throw new NotImplementedException();
  }

  static class HashBidiMapEntrySet<K, V> extends AbstractSet<Entry<K, V>> {
    public HashBidiMapEntrySet(){
      throw new InternalException();
    }

    public HashBidiMapEntrySet(Map<K, V> keyToValue, Map<V, List<K>> valueToKeys) {
      throw new NotImplementedException();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
      return new EntryIterator();
    }

    @Override
    public int size() {
      throw new NotImplementedException();
    }

    private class EntryIterator implements Iterator<Entry<K, V>> {

      @Override
      public boolean hasNext() {
        throw new NotImplementedException();
      }

      @Override
      public Entry<K, V> next() {
        throw new NotImplementedException();
      }

      @Override
      public void remove() {
        throw new NotImplementedException();
      }
    }
  }
}
