package io.dev.v2.dev.usage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.regex.PatternSyntaxException;

public class ExceptionsForApi {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        api(1);
    }

    private static void api(int x) throws IOException, ClassNotFoundException {
        if(x == -1)
            throw new IllegalArgumentException("...");
        if(x == -1)
            throw new NoSuchElementException("...");
        if(x == -1)
            throw new UnsupportedOperationException("...");
        if(x == -1)
            throw new IndexOutOfBoundsException("");

        // Checked Exceptions
        if(x == -1) throw new FileNotFoundException("");
        if(x == -1) throw new IOException();
        if(x == -1) throw new IndexOutOfBoundsException();

        // Unchecked Exceptions - Runtime and Error
        // Unchecked exceptions, also known as runtime exceptions, are exceptions that do not need to be caught explicitly or declared using the throws keyword.
        // They usually represent programming errors, such as invalid calculations or incorrect usage of APIs,
        // that can be prevented by proper code design and testing.
        if(x == -1) throw new IllegalArgumentException();
        if(x == -1) throw new ClassNotFoundException();
        if(x == -1) throw new NoSuchElementException();
        if(x == -1) throw new NullPointerException();
        if(x == -1) throw new NullPointerException(); //Thrown when an attempt is made to access a null object reference.
        if(x == -1) throw new ArrayIndexOutOfBoundsException(); //Thrown when trying to access an array element with an invalid index.
        if(x == -1) throw new ArithmeticException(); //Thrown when an arithmetic operation results in an error, such as division by zero.

        //if(x == -1) throw new PatternSyntaxException();

    }
}
