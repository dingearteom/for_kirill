package org.csc.java.spring2021;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Устройство ввода-вывода
 */
public class IOContext {

  protected InputStream in;
  protected OutputStream out;

  public IOContext(InputStream in, OutputStream out) {
    this.in = in;
    this.out = out;
  }

  public byte readByte() {
    try {
      return (byte) in.read();
    } catch (IOException e) {
      throw new RuntimeException("readByte failed " + e);
    }
  }

  public void writeByte(byte b) {
    try {
      out.write(b);
      out.flush();
    } catch (IOException e) {
      throw new RuntimeException("writeByte failed " + e);
    }
  }
}
