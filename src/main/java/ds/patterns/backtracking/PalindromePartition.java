package ds.patterns.backtracking;

import ds.util.IndentUtil;

import java.util.ArrayList;
import java.util.List;

/**
    131 Palindrome Partition [https://leetcode.com/problems/palindrome-partitioning/]
    Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
    A palindrome string is a string that reads the same backward as forward.
    Example 1:
    Input: s = "aab"
    Output: [["a","a","b"],["aa","b"]]
*/

public class PalindromePartition {

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        ArrayList<String> chosen = new ArrayList<>();
        backtrack(s, 0, result, chosen, 0);
        return result;
    }

    /**
     aab L[a] [a]
     > aab L[a] [a, a]
     >    > aab L[b] [a, a, b]
     >    >    > aab *** OUT[[a, a, b]]
     >    > aab R[b] [a, a, b]
     > aab R[a] [a, a]

     > aab XXX[ab] [a]
     aab R[a] [a]

     aab L[aa] [aa]
     > aab L[b] [aa, b]
     >    > aab *** OUT[[aa, b]]
     > aab R[b] [aa, b]
     aab R[aa] [aa]

     aab XXX[aab] []
    * */
    public void backtrack(String s, int start, List<List<String>> result, List<String> chosen, int ind){
        String indent = IndentUtil.getIndent(ind);
        if(start == s.length()){
            result.add(new ArrayList<>(chosen));
            System.out.println(indent +s +" *** OUT["+chosen +"] ");
            return;
        }

        for(int i = start; i < s.length(); i++){
            String subString = s.substring(start, i + 1);
            //if( isPalindrome(subString) ){
                chosen.add(subString);
                System.out.println(indent +s +" L[" +subString +"] " +chosen);
                backtrack(s, i+1, result, chosen, ind+1);
                System.out.println(indent +s +" R[" +subString +"] " +chosen);
                chosen.remove(chosen.size() - 1);
            //}else{
            //    System.out.println(indent +s +" XXX[" +subString +"] " +chosen);
            //}
        }
    }

    boolean isPalindrome(String s) {
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
        PalindromePartition obj = new PalindromePartition();
        obj.partition("abc");
    }
}


/**
aab L[a] [a]
   > aab L[a] [a, a]
   >    > aab L[b] [a, a, b]
   >    >    > aab *** OUT[[a, a, b]]
   >    > aab R[b] [a, a, b]
   > aab R[a] [a, a]
   > aab L[ab] [a, ab]
   >    > aab *** OUT[[a, ab]]
   > aab R[ab] [a, ab]
aab R[a] [a]
aab L[aa] [aa]
   > aab L[b] [aa, b]
   >    > aab *** OUT[[aa, b]]
   > aab R[b] [aa, b]
aab R[aa] [aa]
aab L[aab] [aab]
   > aab *** OUT[[aab]]
aab R[aab] [aab]
*/