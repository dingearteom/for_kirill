package org.csc.java.spring2021;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValueStoreManagerClass implements ValueStoreManager {
    private final Path workingDir;
    private final int valueFileSize;
    private int freeNum;
    private List<FileBlockLocation> free = new ArrayList<>();

    public ValueStoreManagerClass(Path workingDir, int valueFileSize) throws IOException {
        this.workingDir = workingDir;
        this.valueFileSize = valueFileSize;
        File data = workingDir.resolve("data.txt").toFile();
        if (!data.exists()) {
            if (!data.createNewFile()) {
                throw new IOException();
            }
            this.freeNum = 1;
        } else {
            DataInputStream in = new DataInputStream(new FileInputStream(data));
            this.freeNum = in.readInt();
            int lenFree = in.readInt();
            for (int blockIndex = 0; blockIndex < lenFree; blockIndex++) {
                free.add(new FileBlockLocation(in.readNBytes(in.readInt())));
            }
            in.close();
        }
    }

    public List<FileBlockLocation> add(byte[] value) throws IOException {
        List<FileBlockLocation> blocks = new ArrayList<>();

        int offset = 0;
        while (offset != value.length) {
            if (free.size() != 0) {
                FileBlockLocation block = free.get(free.size() - 1);
                free.remove(free.size() - 1);

                if (value.length - offset >= block.getSize()) {
                    blocks.add(block);
                    RandomAccessFile file =
                            new RandomAccessFile(workingDir.resolve(block.getFileName()).toFile(), "rw");
                    file.seek(block.getOffset());
                    file.write(Arrays.copyOfRange(value, offset, offset + block.getSize()));
                    file.close();
                    offset += block.getSize();
                } else {
                    blocks.add(new FileBlockLocation(block.getFileName(), block.getOffset(),
                            value.length - offset));
                    RandomAccessFile file =
                            new RandomAccessFile(workingDir.resolve(block.getFileName()).toFile(), "rw");
                    file.seek(block.getOffset());
                    file.write(Arrays.copyOfRange(value, offset, value.length));
                    file.close();

                    free.add(new FileBlockLocation(block.getFileName(), block.getOffset() + value.length - offset,
                            block.getSize() - (value.length - offset)));
                    break;
                }
            } else {
                File file = workingDir.resolve(String.format("valueStore%d", freeNum)).toFile();
                if (!file.createNewFile()) {
                    throw new IOException();
                }
                freeNum += 1;
                free.add(new FileBlockLocation(file.getName(), 0, valueFileSize));
            }
        }
        return blocks;
    }

    public InputStream openBlockStream(FileBlockLocation location) throws IOException {
        RandomAccessFile file = new RandomAccessFile(workingDir.resolve(location.getFileName()).toFile(), "r");

        file.seek(location.getOffset());
        byte[] bytes = new byte[location.getSize()];
        file.readFully(bytes);
        file.close();
        return new ByteArrayInputStream(bytes);
    }

    public void remove(List<FileBlockLocation> valueBlocksLocations) throws IOException {
        free.addAll(valueBlocksLocations);
    }

    @Override
    public void close() throws IOException {
        File data = workingDir.resolve("data.txt").toFile();
        DataOutputStream out = new DataOutputStream(new FileOutputStream(data));
        out.writeInt(freeNum);
        out.writeInt(free.size());
        for (FileBlockLocation block : free) {
            out.writeInt(block.toBytes().length);
            out.write(block.toBytes());
        }
        out.close();
    }
}
