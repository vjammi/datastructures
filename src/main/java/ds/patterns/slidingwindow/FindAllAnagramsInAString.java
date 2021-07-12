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

    char[] chars = new char[26];
    int[] bitIndex = new int[26];
    public List<Integer> findAnagramsUsingArraySorting(String s, String p) {

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
            chars[s.charAt(i) - 97] = s.charAt(i);
            bitIndex[s.charAt(i) - 97] = bitIndex[s.charAt(i) - 97] +1;
            if(current.toString().length() == p.length()){
                if (currentVal == pVal && isAnagram(s.substring(j, i+1), p))
                    indices.add(j);
                currentVal = currentVal - s.charAt(j);
                chars[s.charAt(j)- 97] = 0;
                bitIndex[s.charAt(j) - 97] = bitIndex[s.charAt(j) - 97] - 1;
                current.deleteCharAt(0);
                j++;
            }
        }

        return indices;
    }
    private boolean isAnagram(String ss, String p){
        for (int i=0; i < p.length(); i++){
            char chSS = p.charAt(i);
            if (bitIndex[chSS - 97] == 0){
                System.out.println(ss + " " +p +" " +false);
                return false;
            }
        }
        System.out.println(ss + " " +p +" " +true);
        return true;
    }

    // Window size changes overtime
    // Create a static window of size 3 and move it across s
    //            s = "cbaebabacd"
    //            i =          ^
    //            j =        ^
    // current      =        bac
    //            p = "abc"
    // charRef      = [1,1,1,0...]
    // charMatches[]= [0,1,0,0,0,0,0...]
    //charMatchCount= 3
    // charRefCount = 3

    int[] charRef = new int[26];       int charRefCount = 0;
    int[] charMatches = new int[26];   int charMatchCount = 0;
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> indices = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        for(int i=0; i<p.length(); i++){
            charRef[p.charAt(i) - 'a']++;
            charRefCount++;
        }
        int j=0;
        for(int i=0; i<s.length(); i++){
            current.append(s.charAt(i));

            // Increment the char match count if char is in valid string p (charRef)
            if (charRef[s.charAt(i) - 'a'] > 0){
                charMatches[s.charAt(i) - 97]++;
                charMatchCount++;
            }
            if(current.length() == p.length()){
                if (charMatchCount == p.length() && isAnagram(s.substring(j, i+1))){ // *** i+1
                    indices.add(j);
                }
                // Even though there might be no matches for the current index, we still want to make sure
                // we remove the jth element from charMatches array, if present
                if (charMatches[s.charAt(j) - 97] > 0 ) { // > 0 TODO: Is there a better way to check if the jth element is in charMatches (due to duplicates)
                    charMatches[s.charAt(j) - 97]--;
                    charMatchCount--;
                }
                current.deleteCharAt(0);
                j++;
            }
        }
        return indices;
    }

    private boolean isAnagram(String ss){
        for (int i=0; i < ss.length(); i++){
            char chSS = ss.charAt(i);
            if (charMatches[chSS - 97] != charRef[chSS - 97]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        scratchPad();
        FindAllAnagramsInAString obj = new FindAllAnagramsInAString();
        obj.findAnagrams("cbaebabacd", "abc");
        obj.findAnagrams("af", "be");
    }

    private static void scratchPad() {
        char[] chars = new char[26];
        char a = 'a';
        char z = 'z';

        System.out.println((a - 97) +" " + (z - 97));
        chars[a-97] = 'a';
        chars[z-97] = 'z';

        System.out.println(chars[a - 97] +" " +chars[z - 97]);
    }

}
