package dev.vjammi.ds.v2.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
    266. Palindrome Permutation
     Given a string s, return true if a permutation of the string could form a palindrome.
     Example 1:
        Input: s = "code"
        Output: false
     Example 2:
        Input: s = "aab"
        Output: true
     Example 3:
        Input: s = "carerac"
        Output: true
 **/
public class PalindromePermutation {

    public boolean canPermutePalindrome(String s) {
        Set<String> result = new HashSet();
        StringBuilder chosen = new StringBuilder();
        return backtrack(new StringBuilder(s), chosen, result);
    }

    private boolean backtrack(StringBuilder input, StringBuilder chosen, Set<String> result){

        if (input.length() == 0){
            String permutation = chosen.toString();
            if (isPalindrome(permutation)){
                //System.out.println(permutation);
                result.add(permutation);
                return true;
            }
            return false;
        }

        for (int i=0;i<input.length();i++) {
            Character c = input.charAt(i);
            chosen.append(c);
            input.deleteCharAt(i);
            boolean canFormPermutation = backtrack(input, chosen, result);
            if (canFormPermutation)
                return true;
            chosen.deleteCharAt(chosen.length()-1);
            input.insert(i, c);
        }

        return false;
    }

    public boolean isPalindrome(String s) {
        int i=0; int j=s.length()-1;
        while(j>=0 && i<=s.length()-1 && i<j){ // *** i<j

            while(i<j && i<=s.length()-1 && !Character.isLetterOrDigit(s.charAt(i)) )
                i++;
            while(i<j && j>=0 && !Character.isLetterOrDigit(s.charAt(j)) )
                j--;

            if ( Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))  )
                return false;

            i++; j--;
        }
        return true;
    }
}