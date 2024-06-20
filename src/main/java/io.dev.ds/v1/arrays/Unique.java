package dev.vjammi.ds.v1.arrays;

import java.util.Hashtable;

/*
Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you
cannot use additional data structures?
 */

public class Unique {

    /*
        https://stackoverflow.com/questions/9141830/explain-the-use-of-a-bit-vector-for-determining-if-all-characters-are-unique
    */
    public boolean isUniqueChars(String str) {

    /*
    checker is the bit array, it will have a 1 on the character index that
    has appeared before and a 0 if the character has not appeared, you
    can see this number initialized as 32 0 bits:
    00000000 00000000 00000000 00000000
     */
        int checker = 0;

        //loop through each String character
        for (int i = 0; i < str.length(); ++i) {
        /*
        a through z in ASCII are charactets numbered 97 through 122, 26 characters total
        with this, you get a number between 0 and 25 to represent each character index
        0 for 'a' and 25 for 'z'

        renamed 'val' as 'characterIndex' to be more descriptive
         */
            int characterIndex = str.charAt(i) - 'a'; //char 'a' would get 0 and char 'z' would get 26

        /*
        created a new variable to make things clearer 'singleBitOnPosition'

        It is used to calculate a number that represents the bit value of having that
        character index as a 1 and the rest as a 0, this is achieved
        by getting the single digit 1 and shifting it to the left as many
        times as the character index requires
        e.g. character 'd'
        00000000 00000000 00000000 00000001
        Shift 3 spaces to the left (<<) because 'd' index is number 3
        1 shift: 00000000 00000000 00000000 00000010
        2 shift: 00000000 00000000 00000000 00000100
        3 shift: 00000000 00000000 00000000 00001000

        Therefore the number representing 'd' is
        00000000 00000000 00000000 00001000

         */
            int singleBitOnPosition = 1 << characterIndex;

        /*
        This peforms an AND between the checker, which is the bit array
        containing everything that has been found before and the number
        representing the bit that will be turned on for this particular
        character. e.g.
        if we have already seen 'a', 'b' and 'd', checker will have:
        checker = 00000000 00000000 00000000 00001011
        And if we see 'b' again:
        'b' = 00000000 00000000 00000000 00000010

        it will do the following:
        00000000 00000000 00000000 00001011
        & (AND)
        00000000 00000000 00000000 00000010
        -----------------------------------
        00000000 00000000 00000000 00000010

        Since this number is different than '0' it means that the character
        was seen before, because on that character index we already have a
        1 bit value
         */
            int temp = checker & singleBitOnPosition;
            if ( temp > 0) {
                return false;
            }

        /*
        Remember that
        checker |= singleBitOnPosition is the same as
        checker = checker | singleBitOnPosition
        Sometimes it is easier to see it expanded like that.

        What this achieves is that it builds the checker to have the new
        value it hasnt seen, by doing an OR between checker and the value
        representing this character index as a 1. e.g.
        If the character is 'f' and the checker has seen 'g' and 'a', the
        following will happen

        'f' = 00000000 00000000 00000000 00100000
        checker(seen 'a' and 'g' so far) = 00000000 00000000 00000000 01000001

        00000000 00000000 00000000 00100000
        | (OR)
        00000000 00000000 00000000 01000001
        -----------------------------------
        00000000 00000000 00000000 01100001

        Therefore getting a new checker as 00000000 00000000 00000000 01100001

         */
            checker |= singleBitOnPosition;
        }
        return true;
        /*
        The << in (1 << val) is a bit shifting operator. It takes 1 (which in binary is represented as 000000001, with as many preceding zeroes as you
        like / are allocated by memory) and shifts it to the left by val spaces. Since we're assuming a-z only and subtracting a each time, each
        letter will have a value of 0-25, which will be that letter's index from the right in the checker integer's boolean representation, since
        we will move the 1 to the left in checker val times.

        At the end of each check, we see the |= operator. This merges two binary numbers, replacing all 0's with 1's if a 1 exists in either operand
        at that index. Here, that means that wherever a 1 exists in (1 << val), that 1 will be copied over into checker, while all of checker's
        existing 1's will be preserved.

        As you can probably guess, a 1 functions here as a boolean flag for true. When we check to see if a character is already represented in
        the string, we compare checker, which at this point is essentially an array of boolean flags (1 values) at the indexes of characters that
        have already been represented, with what is essentially an array of boolean values with a 1 flag at the index of the current character.

        The & operator accomplishes this check. Similar to the |=, the & operator will copy over a 1 only if both operands have a 1 at that index.
        So, essentially, only flags already present in checker that are also represented in (1 << val) will be copied over. In this case, that
        means only if the current character has already been represented will there be a 1 present anywhere in the result of checker & (1 << val).
        And if a 1 is present anywhere in the result of that operation, then the value of the returned boolean is > 0, and the method returns false.

        This is, I'm guessing, why bit vectors are also called bit arrays. Because, even though they aren't of the array data type, they can be
        used similar to the way arrays are used in order to store boolean flags.
        */
    }

    public boolean isUnique_charset(String str) {
        boolean[] charSet = new boolean[128];
        printBoolArray(charSet);

        int[] intCharSet = new int[128];
        Print.printIntArray(intCharSet);

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            print(c +" - " +charSet[c]);
            if (charSet[c]){
                return false;
            }else{
                charSet[c] = true;
            }
        }

        printBoolArray(charSet);
        return true;
    }

    public boolean isUnique_On2(char[] a){
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
            for(int j=i+1; j<a.length; j++){
                System.out.print(a[j] +", ");
                if(a[i] == a[j]){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isUnique_On(char[] a){
        Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
        for (int i=0; i < a.length; i++){
            System.out.println("'"+a[i]+"'");
            int hash = hash(a[i]);
            print(hash+"");

            if (!ht.containsKey(Integer.valueOf(hash))){
                ht.put(new Integer(hash), a[i] + "");
            }else{
                String c = ht.get(new Integer(hash));//new String(a[i]+""
                if (String.valueOf(a[i]).equals(c)){
                    return false;
                }else{
                    //MyNotes.md
                    ht.put(new Integer(hash), a[i] + "");
                }
            }
        }
        return true;
    }

    private void print(String str) {
        System.out.println(" " +str +" ");
    }

    private void printIntArray(int[] array) {
        for (int i=0; i<array.length; i++){
            System.out.print(array[i] +", ");
        }
        System.out.println();
    }

    private void printCharArray(char[] array) {
        for (int i=0; i<array.length; i++){
            System.out.print(array[i] +", ");
        }
        System.out.println();
    }

    private void printBoolArray(boolean[] array) {
        for (int i=0; i<array.length; i++){
            System.out.print(array[i] +", ");
        }
        System.out.println();
    }

    public int hash(char c){
        if (c == 0)
            return -1;
        return String.valueOf(c).hashCode();
    }

    public void testIsUniqueue(){
        char[] a = {'a', 'b', 'c', 'd', 'e', 'f', 'g', ' ', 'b'};
        //isUnique_On2(a);
        //isUnique_On(a)
        //isUnique_charset("cat");
        //isUnique_charset("cattle");
        isUniqueChars("cattle");
    }

    public static void main(String[] args){
        Unique unique = new Unique();
        unique.testIsUniqueue();
    }

}
