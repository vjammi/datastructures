package io.dev.v2.dev.usage.java;

import java.io.PrintStream;

public class Usages {


    public static void main(String[] args) {
        System.out.println("foo");

        PrintStream out = System.out;
        out.println("foo bar");
    }
}
