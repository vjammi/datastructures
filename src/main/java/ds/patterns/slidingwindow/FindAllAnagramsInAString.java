package ds.patterns.slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. Find All Anagrams in a String
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".

 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

//  Complexity Analysis
//      Time complexity: O(Ns+Np). Since it's one pass along both strings.
//                      Additionally, we incur an O(N*26) constant number of compares to determine if the window is an anagram
//      Space complexity: O(1), because pCount and sCount contain 26 elements each.

public class FindAllAnagramsInAString {
    // Window size changes overtime
    // Create a static window of size 3 and move it across s
    //               0123456789
    //          s = cbaebabacd
    //          i=    ^
    //          j=  ^
    //          p = abc

    public List<Integer> findAnagrams(String s, String p) {

        int[] charSetForP = new int[26];
        for (int i=0; i<p.length(); i++){
            int ch = p.charAt(i);
            charSetForP[ch-97] = charSetForP[ch-97] + 1;
        }

        List<Integer> listOfIndices = new ArrayList<>();
        int anagramSize = p.length();
        int windowSize = 0;
        int[] windowChars = new int[26];
        int j=0;

        for (int i=0; i<s.length(); i++){
            int ch = s.charAt(i);
            windowChars[ch-97] = windowChars[ch-97] + 1;
            windowSize++;

            while(windowSize == anagramSize){
                if (isAnagram(windowChars, charSetForP))
                    listOfIndices.add(j);

                int charAtJ = s.charAt(j);
                windowChars[charAtJ-97] = windowChars[charAtJ-97] - 1;
                j++;
                windowSize--;
            }
        }
        return listOfIndices;
    }


    private boolean isAnagram(int[] windowChars, int[] charSetForP){
        for (int i=0; i< windowChars.length;  i++){
            if (windowChars[i] != charSetForP[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

        FindAllAnagramsInAString obj = new FindAllAnagramsInAString();
        obj.findAnagrams("cbaebabacd", "abc");
        obj.findAnagrams("af", "be");
    }

}
