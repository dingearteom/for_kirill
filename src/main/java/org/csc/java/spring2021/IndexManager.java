package org.csc.java.spring2021;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.HashMap;

interface IndexManager extends Closeable {
  /**
   * Создает связь key -> listOf(FileBlockLocation) в индексе
   */

  void add(byte[] key, List<FileBlockLocation> writtenBlocks) throws IOException;

  void remove(byte[] key) throws IOException;

  /**
   * Возвращает список блоков, в которых хранится значение
   */
  List<FileBlockLocation> getFileBlocksLocations(byte[] key) throws IOException;
}
