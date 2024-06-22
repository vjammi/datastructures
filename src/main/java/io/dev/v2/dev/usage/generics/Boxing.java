package io.dev.v2.dev.usage.generics;

public class Boxing {

    public static void main(String[] args) {

        Integer intVal = 1;
        System.out.println(intVal);

        //Returns a Long object holding the long value represented by the argument string s.
        //If s is null, then a NullPointerException is thrown.
        Long longVal = Long.valueOf("1");
        System.out.println(longVal);

        //Returns a Float object holding the float value represented by the argument string s.
        //If s is null, then a NullPointerException is thrown.
        Float floatVal = Float.valueOf("1.001");
        System.out.println(floatVal);

        //Returns a Double object holding the double value represented by the argument string s.
        //If s is null, then a NullPointerException is thrown.
        Double doubleVal = Double.valueOf("1.001");
        System.out.println(doubleVal);
    }
}
