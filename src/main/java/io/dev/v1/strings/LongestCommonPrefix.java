package io.dev.v1.strings;

// LC 14 - LongestCommonPrefix
/**
    Write a function to find the longest common prefix string amongst an array of strings.
    If there is no common prefix, return an empty string "".
     Example 1:
     Input: strs = ["flower","flow","flight"]
     Output: "fl"

     Example 2:
     Input: strs = ["dog","racecar","car"]
     Output: ""
     Explanation: There is no common prefix among the input strings.
*/
public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        String longestCommonPrefix = "";
        // longestCommonPrefix     = "flower"
        // prefix("flower","flow") = "flow"
        // prefix("flight" "flow") = "fl"
        return longestCommonPrefix; // "fl"
    }

}
