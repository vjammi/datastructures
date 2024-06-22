package io.dev.v2.dev.systems;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 *  SSTable (Sorted String Table) is a data structure that is commonly used in database systems for efficient storage
 *  and retrieval of key-value pairs. In Java, you can implement SSTable by following these general steps:
 *
 *  Define a class to represent key-value pairs: You can create a simple POJO (Plain Old Java Object) class that
 *  contains two fields: a key and a value. For example:
 *
 *  Define a class to represent the SSTable: This class should contain methods for reading and writing data to disk,
 *  as well as for querying the table. It should also maintain an in-memory index to speed up lookups. For example:
 *
 *  Use the SSTable class to store and retrieve key-value pairs:
 *      You can create an instance of the SSTable class, and use its put method to insert key-value pairs into the
 */

public class SSTable {
    private final File file;
    private final Map<String, Long> index;

    public SSTable(File file) {
        this.file = file;
        this.index = new HashMap<>();

        // Build the index by reading the file
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                long position = raf.getFilePointer();
                KeyValue kv = readKeyValue(raf);
                index.put(kv.getKey(), position);
            }
        } catch (IOException e) {
            // Handle the exception
        }
    }

    public String get(String key) {
        Long position = index.get(key);
        if (position == null) {
            return null;
        }

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            raf.seek(position);
            KeyValue kv = readKeyValue(raf);
            if (kv.getKey().equals(key)) {
                return kv.getValue();
            } else {
                return null;
            }
        } catch (IOException e) {
            // Handle the exception
        }

        return null;
    }

    public void put(String key, String value) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.seek(raf.length());
            long position = raf.getFilePointer();
            KeyValue kv = new KeyValue(key, value);
            writeKeyValue(raf, kv);
            index.put(key, position);
        } catch (IOException e) {
            // Handle the exception
        }
    }

    private KeyValue readKeyValue(RandomAccessFile raf) throws IOException {
        int keyLength = raf.readInt();
        byte[] keyBytes = new byte[keyLength];
        raf.readFully(keyBytes);
        String key = new String(keyBytes, StandardCharsets.UTF_8);

        int valueLength = raf.readInt();
        byte[] valueBytes = new byte[valueLength];
        raf.readFully(valueBytes);
        String value = new String(valueBytes, StandardCharsets.UTF_8);

        return new KeyValue(key, value);
    }

    private void writeKeyValue(RandomAccessFile raf, KeyValue kv) throws IOException {
        byte[] keyBytes = kv.getKey().getBytes(StandardCharsets.UTF_8);
        byte[] valueBytes = kv.getValue().getBytes(StandardCharsets.UTF_8);

        raf.writeInt(keyBytes.length);
        raf.write(keyBytes);

        raf.writeInt(valueBytes.length);
        raf.write(valueBytes);
    }
}
