package dev.vjammi.ds.v2.dev.usage;

import java.util.NoSuchElementException;

public class ExceptionsForApi {

    public static void main(String[] args) {
        api(1);
    }

    private static void api(int x) {
        if(x == -1)
            throw new IllegalArgumentException("...");

        if(x == -1)
            throw new NoSuchElementException("...");

        if(x == -1)
            throw new UnsupportedOperationException("...");

        if(x == -1)
            throw new IndexOutOfBoundsException("");

        // NullPointerException

        //  PatternSyntaxException

        // java.util.IllegalFormatException
    }
}
