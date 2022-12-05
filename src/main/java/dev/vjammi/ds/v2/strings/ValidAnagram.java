package dev.vjammi.ds.v2.strings;

import java.util.Arrays;

/*
    242. Valid Anagram
    Given two strings s and t, return true if t is an anagram of s, and false otherwise.

    Example 1:
    Input: s = "anagram", t = "nagaram"
    Output: true
    Example 2:

    Input: s = "rat", t = "car"
    Output: false
    Constraints:

    1 <= s.length, t.length <= 5 * 104
    s and t consist of lowercase English letters.
*/
public class ValidAnagram {

    /**
        An anagram is produced by rearranging the letters of sss into ttt. Therefore, if ttt is an anagram of sss, sorting both strings
        will result in two identical strings. Furthermore, if sss and ttt have different lengths, ttt must not be an anagram of sss and
        we can return early.

        Complexity analysis

        Time complexity : O(nlog n).
        Assume that n is the length of s, sorting costs O(nlog n) and comparing two strings costs O(n).
        Sorting time dominates and the overall time complexity is O(nlog n).

        Space complexity : O(1).
        Space depends on the sorting implementation which, usually, costs O(1) auxiliary space if heapsort is used.
        Note that in Java, toCharArray() makes a copy of the string so it costs O(n) extra space.
    */
    public boolean isAnagram_using_sorting(String s, String t) {

        if (s.length() != t.length())
            return false;

        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        Arrays.sort(charsS);
        Arrays.sort(charsT);

        for (int i=0; i< charsS.length; i++){
            if (charsS[i] != charsT[i])
                return false;
        }

        return true;
    }

    public boolean isAnagram_using_two_charsets(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] charSetS = new int[26];
        int[] charSetT = new int[26];

        for (int i=0; i<s.length(); i++){
            int ch = s.charAt(i);
            charSetS[ch-97] = charSetS[ch-97] + 1;
        }

        for (int i=0; i<t.length(); i++){
            int ch = t.charAt(i);
            charSetT[ch-97] = charSetT[ch-97] + 1;
        }

        for (int i=0; i<charSetS.length; i++){
            if (charSetS[i] != charSetT[i])
                return false;
        }

        return true;

    }

    /**
     Complexity analysis
     Time complexity : O(n) + O(1)
     Time complexity is O(n) + O(charSet) = O(n) + O(1) - because accessing the charSet is a constant time operation.
     Space complexity : O(1)
     Although we do use extra space, the space complexity is O(1) because the table's size stays constant no matter how large n is.

     Follow up
     What if the inputs contain unicode characters? How would you adapt your solution to such case?
     Answer
     Use a hash table instead of a fixed size array.
     Imagine allocating a large size array to fit the entire range of unicode characters [144,697 characters],
     which could go up to more than 1 million. Not 144K ???.
     A hash table is a more generic solution and could adapt to any range of characters.
     HashTable to store "cat" would be
        Character Integer
         a          1
         b          0
         c          1
         ...
         t          1

     Note:
     Unicode, the standard, which is maintained by the Unicode Consortium, defines 144,697 characters covering 159 modern and historic scripts,
     as well as symbols, emoji, and non-visual control and formatting codes.

     UTF-8 is a method for encoding Unicode characters using 8-bit sequences. Unicode is a standard for representing a great variety of
     characters from many languages.
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] charSet = new int[26];
        for (int i=0; i<s.length(); i++){
            int chAtS = s.charAt(i);
            charSet[chAtS-97] = charSet[chAtS-97] + 1; // increment

            int chAtT = t.charAt(i);
            charSet[chAtT-97] = charSet[chAtT-97] - 1; // decrement
        }

        // Should be all zeros, else not an anagram
        for (int i=0; i<charSet.length; i++){
            if (charSet[i] != 0 )
                return false;
        }

        return true;
    }

}
