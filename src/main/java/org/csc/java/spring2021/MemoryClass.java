package org.csc.java.spring2021;


public class MemoryClass implements Memory {

  private byte[] memory;
  private int position = 0;
  private final int memorySize;

  public MemoryClass(int size) {
    this.memory = new byte[size];
    this.memorySize = size;
  }

  @Override
  public void shiftRight() {
    this.position++;
  }

  @Override
  public void shiftLeft() {
    this.position--;
  }

  @Override
  public void setByte(byte value) {
    if (this.position < 0 || this.position >= this.memorySize) {
      throw new MemoryAccessException(this.position);
    }
    this.memory[this.position] = value;
  }

  @Override
  public byte getByte() {
    if (this.position < 0 || this.position >= this.memorySize) {
      throw new MemoryAccessException(this.position);
    }
    return this.memory[this.position];
  }

  @Override
  public int getSize() {
    return this.memorySize;
  }
}
