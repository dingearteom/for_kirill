package org.csc.java.spring2021;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;


public class KeyValueStoreClass implements KeyValueStore {
    private final IndexManager indexManager;
    private final ValueStoreManager valueStoreManager;
    private boolean isClosed = false;
    private final String exceptionMessage = "Instance cannot be accessed once it's closed";

    public KeyValueStoreClass(Path workingDir, int valueFileSize) throws IOException {
        indexManager = new IndexManagerClass(workingDir);
        valueStoreManager = new ValueStoreManagerClass(workingDir, valueFileSize);
    }

    @Override
    public boolean contains(byte[] key) throws IOException {
        if (isClosed) {
            throw new IllegalStateException(exceptionMessage);
        }
        Objects.requireNonNull(key);
        return indexManager.getFileBlocksLocations(key) != null;
    }

    @Override
    public InputStream openValueStream(byte[] key) throws IOException {
        if (isClosed) {
            throw new IllegalStateException(exceptionMessage);
        }
        Objects.requireNonNull(key);
        List<FileBlockLocation> blocks = indexManager.getFileBlocksLocations(key);
        if (blocks == null) {
            throw new IOException("No such key");
        }
        Vector<InputStream> streams = new Vector<>();
        for (var block : blocks) {
            streams.add(valueStoreManager.openBlockStream(block));
        }
        return new SequenceInputStream(streams.elements());
    }

    @Override
    public byte[] loadValue(byte[] key) throws IOException {
        if (isClosed) {
            throw new IllegalStateException(exceptionMessage);
        }
        Objects.requireNonNull(key);
        return openValueStream(key).readAllBytes();
    }

    @Override
    public void upsert(byte[] key, byte[] value) throws IOException {
        if (isClosed) {
            throw new IllegalStateException(exceptionMessage);
        }
        Objects.requireNonNull(key);
        indexManager.add(key, valueStoreManager.add(value));
    }

    @Override
    public boolean remove(byte[] key) throws IOException {
        if (isClosed) {
            throw new IllegalStateException(exceptionMessage);
        }
        Objects.requireNonNull(key);
        if (contains(key)) {
            valueStoreManager.remove(indexManager.getFileBlocksLocations(key));
            indexManager.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public IndexManager getIndexManager() {
        if (isClosed) {
            throw new IllegalStateException(exceptionMessage);
        }
        return indexManager;
    }

    @Override
    public void close() throws IOException {
        if (isClosed) {
            throw new IllegalStateException(exceptionMessage);
        }
        indexManager.close();
        valueStoreManager.close();
        isClosed = true;
    }
}
