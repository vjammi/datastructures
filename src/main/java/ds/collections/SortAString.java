package ds.collections;

import java.util.Arrays;

public class SortAString {

    private String sortString(String string){
        System.out.println(string);
        char[] chars = string.toCharArray(); // method on input string to create a char array for input string.
        Arrays.sort(chars); // method to sort char array.
        String sortedString = new String(chars);  // Use String class constructor to create a sorted string from char array. String is immutable in java, hence in third step we have to create a new string.
        System.out.println(sortedString);
        return sortedString;
    }

    public static void main(String[] args) {
        SortAString obj = new SortAString();
        obj.sortString("algorithm");
    }
}
