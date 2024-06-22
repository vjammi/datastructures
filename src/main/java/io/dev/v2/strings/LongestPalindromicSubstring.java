package io.dev.v2.strings;

/**
    5. Longest Palindromic Substring
        Given a string s, return the longest palindromic substring in s.


        Input: s = "babad"
        Output: "bab"
        Explanation: "aba" is also a valid answer.

        Input: "babcdefghgfedcad"
                ^    ^    ^
             0123456789012345
        Output: "bab" or "aba"
*/
// TODO: Need to work on this further
public class LongestPalindromicSubstring {

    /**
     Approach 4: Expand Around Center
     In fact, we could solve it in O(n2) time using only constant space.
     We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center,
     and there are only 2n - 12n−1 such centers.
     You might be asking why there are 2n - 12n−1 but not nn centers? The reason is the center of a palindrome can be
     in between two letters. Such palindromes have even number of letters (such as "abba") and its center are
     between the two 'b's.
     */
    private String getPalindromeWithCenter(String s) {

        String longestPalindrome = "";
        for (int i=1; i < s.length()-1; i++){
            if (s.charAt(i-1) == s.charAt(i+1) ){
                int j= i-1; int k = i+1;
                while(j >= 0 && k < s.length() && (s.charAt(j) == s.charAt(k))){
                    if (longestPalindrome.length() < s.substring(j,k+1).length()) {
                        longestPalindrome = s.substring(j,k+1);
                        System.out.println(s.substring(j,k+1));
                    }
                    j--; k++;
                }
            }
        }
        return longestPalindrome;
    }

    public static void main(String[] args) {

        LongestPalindromicSubstring lpsObject = new LongestPalindromicSubstring();

        String lps2 = lpsObject.getPalindromeWithCenter("babcdefghgfedcad");
        System.out.println(lps2);
    }
}
