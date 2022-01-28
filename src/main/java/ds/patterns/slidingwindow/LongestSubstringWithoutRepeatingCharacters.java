package ds.patterns.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
     3. Longest Substring Without Repeating Characters
         Given a string s, find the length of the longest substring without repeating characters.

         Example 1:
         Input: s = "abcabcbb"
         Output: 3
         Explanation: The answer is "abc", with the length of 3.

         // Window size changes overtime
         Naive Approach - Nested for loops - O(N2) time
         s = abcad
         ^ ^
         s e

         Other problems
         Longest Substring with At Most Two Distinct Characters
         Longest Repeating Character
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
         s=abcabcbb
         longest=3

         s=abcbbcbb
         longest=3
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0)
            return 0;

        Set<Character> set = new HashSet<>();
        int start=0;
        int currLength=0;
        int longestSoFar=0;
        for(int end=0; end<s.length(); end++){
            if (!set.contains(s.charAt(end))){
                set.add(s.charAt(end));
                currLength++;
            }else{
                while(set.contains(s.charAt(end))){
                    set.remove(s.charAt(start));
                    start++;
                    currLength--;
                }
                set.add(s.charAt(end));
                currLength++;
            }
            longestSoFar = Math.max(longestSoFar, currLength);
        }
        return longestSoFar;
    }

    public int lengthOfLongestSubstringFirstAfterGap_PraiseTheLord(String s) {

        Set<Character> set = new HashSet<>();
        int longest=0;
        int j=0;
        for(int i=0; i<s.length(); i++){
            if (!set.contains(s.charAt(i))) {
                longest = Math.max(longest, (i-j)+1);
                set.add(s.charAt(i));
            }else{
                while( set.contains(s.charAt(i)) && j<=i && j<s.length() ){
                    set.remove(s.charAt(j));
                    j++;
                }
                longest = Math.max(longest, (i-j)+1);
                set.add(s.charAt(i));
            }
            System.out.println(j +" - " +i +" - "+longest);
        }
        return longest;
    }

    // Input: s = "abcabcbb"
    //            "tmmzuxt"
    //        i          ^
    //        j      ^
    // Output: 3
    public int lengthOfLongestSubstring(String s) {

        int longest = 0;
        int longestSubStrLength = 0;
        int j=0;
        Set<Character> set = new HashSet<>();
        for (int i=0; i< s.length(); i++){

            Character c = s.charAt(i);

            if (!set.contains(c)){
                set.add(c);
                longestSubStrLength++; // 3
                longest = Math.max(longest, longestSubStrLength);
            }else{
                // Increment j until it reaches the duplicate char
                // In the meantime you will remove the chars you will encounter.
                while(s.charAt(j) != c) {
                    set.remove(s.charAt(j));
                    j++;
                    longestSubStrLength--;
                }

                // Once j is at char c, we remove from the set, increment j and decrement length
                set.remove(s.charAt(j));
                j++;
                longestSubStrLength--;

                // Now add new char to the set, increment longestSubStrLength and get max length
                set.add(c);
                longestSubStrLength++;
                longest = Math.max(longest, longestSubStrLength);
            }

        }

        return longest;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "adacbbcbb";

        LongestSubstringWithoutRepeatingCharacters obj = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(obj.lengthOfLongestSubstring(s1));
        System.out.println(obj.lengthOfLongestSubstring(s2));


    }

}