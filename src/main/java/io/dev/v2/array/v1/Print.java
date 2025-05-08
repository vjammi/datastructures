package io.dev.v2.array.v1;

public class Print {

    public Print(){
    }

    public static void print(String str) {
        System.out.println(str +", ");
    }

    public static void printInt(int c) {
        System.out.println(c);
    }

    public static void printIntArray(int[] array) {
        for (int i=0; i<array.length; i++){
            System.out.print(array[i] +", ");
        }
        System.out.println();
    }

    public static void printCharArray(char[] array) {
        for (int i=0; i<array.length; i++){
            System.out.print(array[i] +", ");
        }
        System.out.println();
    }

    public static void printBoolArray(boolean[] array) {
        for (int i=0; i<array.length; i++){
            System.out.print(array[i] +", ");
        }
        System.out.println();
    }

}
