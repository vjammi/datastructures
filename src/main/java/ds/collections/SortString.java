package ds.collections;

import java.util.Arrays;
import java.util.Comparator;

public class SortString {

    private String sortString(String string){
        System.out.println(string);

        char[] chars = string.toCharArray(); // method on input string to create a char array for input string.
        Arrays.sort(chars); // method to sort char array.

        String sortedString = new String(chars);  // Use String class constructor to create a sorted string from char array. String is immutable in java, hence in third step we have to create a new string.
        System.out.println(sortedString);

        return sortedString;
    }

    private static void sort(String[] str) {
        Arrays.sort(str, new StringComparator()); // (a, b) -> (b + a).compareTo(a + b)
    }

    static class StringComparator implements Comparator<String>{
        public int compare(String a, String b) {
            //return (b + a).compareTo(a + b);
            return Character.valueOf(b.charAt(0)).compareTo(a.charAt(0));
        }
    }

    public static void main(String[] args) {
        SortString obj = new SortString();
        obj.sortString("algorithm");

        String[] strArr = {"4", "81", "92", "12"};
        SortString.sort(strArr);
        for (String str: strArr)
            System.out.print(str +" ");
    }
}
