package org.csc.java.spring2021.bidimap;

import java.util.List;
import java.util.Map;

/**
 * Коллекция, хранящая пары ключ + значение. Позволяет получить как по ключу значение с помощью
 * {@link #get(K)}, так и по значению множество ключей с указанным значением с помощью {@link
 * #getKeysByValue(V)}. Представляет собой двунаправленное отображение.
 *
 * @param <K> тип ключа
 * @param <V> тип значения
 */
public interface BidiMap<K, V> extends Map<K, V> {

  /**
   * Получить все ключи, которым соответствует переданное значение.
   *
   * @param value значение
   * @return список ключей или null (в случае, если ключей с таким значением нет)
   */
  List<K> getKeysByValue(V value);

  /**
   * Удаляет все пары, у которых значение равно переданному
   *
   * @param value значение
   * @return список ключей, которые были удалены (или null, если ничего не было удалено)
   */
  List<K> removeByValue(V value);
}
