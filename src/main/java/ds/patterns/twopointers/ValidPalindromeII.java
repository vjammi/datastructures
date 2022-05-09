package ds.patterns.twopointers;

    /**
    680. Valid Palindrome II    https://leetcode.com/problems/valid-palindrome-ii/
    Given a string s, return true if the s can be palindrome after deleting at most one character from it.
    Example 1:
        Input: s = "aba"
        Output: true
    Example 2:
        Input: s = "abca"
        Output: true
        Explanation: You could delete the character 'c'.

      1. Naive approach:
          Iterating thru the chars in the string, we remove each char and check if the remainder string is a palindrome
          Runtime of O(n^2)

      2. Two Pointers approach:

          Using the 2 pointers (left and right) approach, we check if i and j are same.
              - If true,
                  We move toward the inside
              - If false,
                  We remove each char one at a time, to check if the remainder is a palindrome.
                  We delete the letter that is not a palindrome.
                          aaaz
                          ^  ^
                  a) Remove z and check if aaa is a palindrome
                  b) remove a and check if aaz is a palindrome
          Runtime O(2n) <=> O(n). 2 n because we will itrerate thru the array twice
    */
public class ValidPalindromeII {

}
