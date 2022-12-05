package dev.vjammi.ds.v1.arrays;

import java.util.Arrays;

/*
    Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
 */
public class CheckPermutation {

    public boolean isPermutation(char[] a1, char[] a2){
        Print.printCharArray(a1);
        Print.printCharArray(a2);

        sort(a1);
        sort(a2);

        Print.printCharArray(a1);
        Print.printCharArray(a2);

        return compareCharArrays(a1, a2);
    }

    private boolean compareCharArrays(char[] a1, char[] a2) {

        return false;
    }

    private void sort(char[] a1) {
        Arrays.sort(a1);
    }


    public void testIsPermutation(){
        char[] a1 = {'c', 'a', 't'};
        char[] a2 = {'t', 'a', 'c'};
        Print.print(isPermutation(a1, a2)+"");
    }

    public static void main(String[] args){
        CheckPermutation checkPermutation = new CheckPermutation();
        checkPermutation.testIsPermutation();
    }
}
