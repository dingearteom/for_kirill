package org.csc.java.spring2021;

/**
 * Класс-дескриптор блока, в котором хранится значение
 */
class FileBlockLocation {
  private String fileName;
  private int offset;
  private int size;

  public FileBlockLocation(String fileName, int offset, int size) {
    this.fileName = fileName;
    this.offset = offset;
    this.size = size;
  }

  public String getFileName() {
    return fileName;
  }

  public int getOffset() {
    return offset;
  }

  public int getSize() {
    return size;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public void setSize(int size) {
    this.size = size;
  }
}
