package org.csc.java.spring2021;

import java.io.IOException;
import java.nio.file.Path;

public class KeyValueStoreFactory {
    private KeyValueStoreFactory() {
    }

    public static KeyValueStore create(Path workingDir, int valueFileSize) throws IOException {
        if (valueFileSize <= 0) {
            throw new IllegalArgumentException("valueFileSize must be positive integer");
        }
        if (workingDir == null) {
            throw new IllegalArgumentException("workingDir must not to be null");
        }
        if (!workingDir.toFile().exists()) {
            throw new IllegalArgumentException("workingDir must exist");
        }
        if (!workingDir.toFile().isDirectory()) {
            throw new IllegalArgumentException("workingDir must be a directory");
        }


        return new KeyValueStoreClass(workingDir, valueFileSize);
    }
}
