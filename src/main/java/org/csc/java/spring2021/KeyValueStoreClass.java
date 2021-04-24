package org.csc.java.spring2021;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class KeyValueStoreClass implements KeyValueStore {
    private final IndexManager indexManager;
    private final ValueStoreManager valueStoreManager;

    public KeyValueStoreClass(Path workingDir, int valueFileSize) throws IOException{
            indexManager = new IndexManagerClass(workingDir);
            valueStoreManager = new ValueStoreManagerClass(workingDir, valueFileSize);
    }

    @Override
    public boolean contains(byte[] key) throws IOException{
        return indexManager.getFileBlocksLocations(key) != null;
    }

    @Override
    public InputStream openValueStream(byte[] key) throws IOException{
        List<FileBlockLocation> blocks = indexManager.getFileBlocksLocations(key);
        Vector<InputStream> streams = new Vector<>();
        for (var block: blocks){
            streams.add(valueStoreManager.openBlockStream(block));
        }
        return new SequenceInputStream(streams.elements());
    }

    @Override
    public byte[] loadValue(byte[] key) throws IOException{
        return openValueStream(key).readAllBytes();
    }

    @Override
    public void upsert(byte[] key, byte[] value) throws IOException{
        indexManager.add(key, valueStoreManager.add(value));
    }

    @Override
    public boolean remove(byte[] key) throws IOException{
        if (contains(key)) {
            valueStoreManager.remove(indexManager.getFileBlocksLocations(key));
            indexManager.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public IndexManager getIndexManager(){
        return indexManager;
    }

    @Override
    public void close() throws IOException {
        indexManager.close();
        valueStoreManager.close();
    }
}
