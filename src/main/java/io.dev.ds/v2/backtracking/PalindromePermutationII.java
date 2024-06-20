package dev.vjammi.ds.v2.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*
    267. Palindrome Permutation II
    Given a string s, return all the palindromic permutations (without duplicates) of it.
    You may return the answer in any order. If s has no palindromic permutation, return an empty list.
    Example 1:
    Input: s = "aabb"
    Output: ["abba","baab"]

* */
public class PalindromePermutationII {

    public List<String> generatePalindromes(String s) {
        Set<String> result = new HashSet();
        StringBuilder chosen = new StringBuilder();
        backtrack(new StringBuilder(s), chosen, result);
        System.out.println(result);
        return new ArrayList(result);
    }

    private void backtrack(StringBuilder input, StringBuilder chosen, Set<String> result){

        if (input.length() == 0){
            String permutation = chosen.toString();
            if (isPalindrome(permutation)){
                System.out.println(permutation);
                result.add(permutation);
            }
            return;
        }

        for (int i=0;i<input.length();i++) {
            Character c = input.charAt(i);
            chosen.append(c);
            input.deleteCharAt(i);
            backtrack(input, chosen, result);
            chosen.deleteCharAt(chosen.length()-1);
            input.insert(i, c);
        }
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

    public static void main(String[] args) {
        PalindromePermutationII obj = new PalindromePermutationII();
        obj.generatePalindromes("aabb");
    }


}
