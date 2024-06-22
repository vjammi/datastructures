package io.dev.v2.dev.systems;

import java.util.HashMap;
import java.util.Map;

/**
 * A database buffer pool is a component of a database management system that is responsible for caching data from disk
 * into memory to reduce the number of disk I/O operations. Here is an example of a simple buffer pool implementation in Java:
 *
 * In this implementation, the buffer pool is represented as a map from page numbers to byte arrays. The readPage method
 * checks if the requested page is already in the buffer pool, and if not, reads it from disk and adds it to the pool.
 * The writePage method writes the page data to disk and updates the corresponding entry in the buffer pool.
 * The readPageFromDisk and writePageToDisk methods are placeholders for actual file I/O operations.
 */
public class BufferPool {

    private Map<Integer, byte[]> buffer; // Buffer pool as a map of page numbers to byte arrays
    private int pageSize; // Size of each page in bytes

    public BufferPool(int poolSize, int pageSize) {
        buffer = new HashMap<Integer, byte[]>(poolSize);
        this.pageSize = pageSize;
    }

    public byte[] readPage(int pageNumber) {
        if (!buffer.containsKey(pageNumber)) {
            // If the page is not in the buffer pool, read it from disk
            byte[] pageData = readPageFromDisk(pageNumber);
            // Add the page to the buffer pool
            buffer.put(pageNumber, pageData);
        }
        return buffer.get(pageNumber);
    }

    public void writePage(int pageNumber, byte[] pageData) {
        // Write the page data to disk
        writePageToDisk(pageNumber, pageData);
        // Update the page in the buffer pool
        buffer.put(pageNumber, pageData);
    }

    private byte[] readPageFromDisk(int pageNumber) {
        // Read the page data from disk using a file I/O library
        byte[] pageData = new byte[pageSize];
        // ... read data from disk into pageData array ...
        return pageData;
    }

    private void writePageToDisk(int pageNumber, byte[] pageData) {
        // Write the page data to disk using a file I/O library
        // ... write data from pageData array to disk ...
    }

}
