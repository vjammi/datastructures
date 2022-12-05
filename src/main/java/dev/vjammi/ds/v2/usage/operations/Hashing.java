package dev.vjammi.ds.v2.usage.operations;

import java.util.Arrays;

public class Hashing {

    public static void main(String[] args) {
        char[] charSet = {'a', 'b'};
        int x = hashcodeOfArrays(charSet);
        System.out.println(x) ;

        Integer[] arr = new Integer[]{1, 2};
        String strKey = getStrRepresentation(arr);
        System.out.println(strKey);
    }

    private static int hashcodeOfArrays(char[] charSet) {
        return Arrays.hashCode(charSet);
    }

    private static String getStrRepresentation(Integer[] charSet) {
        StringBuilder keyBuilder = new StringBuilder();
        for (int k = 0; k < charSet.length; k++) {
            keyBuilder.append('#'); keyBuilder.append(k);
        }
        return keyBuilder.toString();
    }
}
