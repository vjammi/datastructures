package io.dev.v2.dev.usage.anonymous;

import java.util.Iterator;

/**
 * An Helper Inner Class Example
 * */
public class MyArrayIterator {

    // Create an array
    private final static int SIZE = 15;
    private final int[] arrayOfInts = new int[SIZE];

    public MyArrayIterator() {
        // fill the array with ascending integer values
        for (int i = 0; i < SIZE; i++) {
            arrayOfInts[i] = i;
        }
    }

    public ArrayIterator iterator(){
        return new ArrayIterator();
    }

    // Inner class implements the ArrayIterator interface, which extends the Iterator<Integer> interface
    private class ArrayIterator implements Iterator<Integer>{
        // Start stepping through the array from the beginning
        private int nextIndex = 0;

        public boolean hasNext() {
            // Check if the current element is the last in the array
            return nextIndex <= SIZE - 1;
        }

        public Integer next() {
            // Record a value of an even index of the array
            Integer retValue = Integer.valueOf(arrayOfInts[nextIndex]);

            // Get the next even element
            nextIndex += 2;
            return retValue;
        }
    }

    //
    //    public void printEven() {
    //
    //        // Print out values of even indices of the array
    //        ArrayIterator iterator = this.new ArrayIteratorImpl();
    //        while (iterator.hasNext()) {
    //            System.out.print(iterator.next() + " ");
    //        }
    //        System.out.println();
    //    }

    public static void main(String[] args) {
        printEven();
    }

    private static void printEven() {
        MyArrayIterator arrayIterator = new MyArrayIterator();
        // Print out values of even indices of the array
        ArrayIterator iterator = arrayIterator.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}