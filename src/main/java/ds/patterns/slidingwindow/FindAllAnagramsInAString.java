package ds.patterns.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
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

public class FindAllAnagramsInAString {

    // Window size changes overtime
    // Create a static window of size 3 and move it across s
    //          s = "cbaebabacd"
    //          i=        ^
    //          j=      ^
    //          p = "abc"
    //    current=c
    //    currVal=
    //s.charAt(i)=

    public List<Integer> findAnagrams(String s, String p) {

        int pVal = 0;
        for(int i=0;i<p.length(); i++){
            pVal = pVal + p.charAt(i);
        }

        List<Integer> indices = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        int j=0;
        int currentVal = 0;

        for(int i=0;i<s.length(); i++){
            current.append(s.charAt(i));
            currentVal = currentVal + (int)s.charAt(i);

            if(current.toString().length() == p.length()){
                if (currentVal == pVal && isAnagram(s.substring(j,i+1), p)){
                    indices.add(j);
                }
                currentVal = currentVal - s.charAt(j);
                current.deleteCharAt(0);
                j++;
            }
        }

        return indices;
    }

    private boolean isAnagram(String ss, String p){
        char[] a = ss.toCharArray();
        char[] b = p.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);

        for (int i=0;i<a.length; i++){
            if (a[i] != b[i]){
                return false;
            }
        }
        return true;
    }


}
