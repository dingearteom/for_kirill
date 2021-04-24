package org.csc.java.spring2021;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.nio.file.Path;
import java.util.Map;

public class IndexManagerClass implements IndexManager {
    private HashMap<ByteWrapper, List<FileBlockLocation>> data = new HashMap<>();
    private final File indexFile;

    public IndexManagerClass(Path workingDir) throws IOException{
        indexFile = workingDir.resolve("indexFile.txt").toFile();

        if (!indexFile.exists()){
            if (!indexFile.createNewFile()){throw new IOException();}
        }

        DataInputStream in = new DataInputStream(new FileInputStream(indexFile));
        int mapSize = in.readInt();
        for (int entryNum = 0; entryNum < mapSize; entryNum++){
            int lenByteArray = in.readInt();
            ByteWrapper key = new ByteWrapper(in.readNBytes(lenByteArray));
            List<FileBlockLocation> value = new ArrayList<>();
            int lenList = in.readInt();
            for (int entryListNum = 0; entryListNum < lenList; entryListNum++){
                int lenFileBlockLocation = in.readInt();
                value.add(new FileBlockLocation(in.readNBytes(lenFileBlockLocation)));
            }

            data.put(key, value);
        }

    }

    @Override
    public void add(byte[] key, List<FileBlockLocation> writtenBlocks) throws IOException{
        data.put(new ByteWrapper(key), writtenBlocks);
    }

    @Override
    public void remove(byte[] key) throws IOException{
        data.remove(new ByteWrapper(key));
    }

    @Override
    public List<FileBlockLocation> getFileBlocksLocations(byte[] key) throws IOException{
        return data.get(new ByteWrapper(key));
    }

    @Override
    public void close() throws IOException {

        DataOutputStream out = new DataOutputStream(new FileOutputStream(indexFile));
        out.writeInt(data.size());
        for (Map.Entry<ByteWrapper, List<FileBlockLocation>> entry: data.entrySet()){
            ByteWrapper key = entry.getKey();
            List<FileBlockLocation> value = entry.getValue();

            out.writeInt(key.getBytes().length);
            out.write(key.getBytes());

            out.writeInt(value.size());
            for (FileBlockLocation fileBlockLocation: value){
                out.write(fileBlockLocation.toBytes());
            }
        }
        out.close();
    }
}
