package org.csc.java.spring2021;

import java.io.*;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

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

    public FileBlockLocation(byte[] bytes) {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            int len = in.readInt();
            this.fileName = new String(in.readNBytes(len), StandardCharsets.UTF_8.name());
            this.offset = in.readInt();
            this.size = in.readInt();
        } catch (IOException ignored) {
        }
    }

    public byte[] toBytes() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(out);
        try {
            int len = fileName.getBytes(StandardCharsets.UTF_8.name()).length;
            dataOut.writeInt(len);
            dataOut.writeBytes(fileName);
            dataOut.writeInt(offset);
            dataOut.writeInt(size);
        } catch (IOException ignored) {
        }

        return out.toByteArray();
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
