package io.dev.v2.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class StringCompressionTest {
    @Test
    void shouldCompress() {
        StringCompression compression = new StringCompression();
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int actual = compression.compress(chars);
        assertEquals(6, actual);
    }
}