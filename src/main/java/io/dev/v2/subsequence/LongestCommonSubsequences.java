package io.dev.v2.subsequence;

import java.util.ArrayList;
import java.util.List;

/**
 *      Approach 1: Brute Force Approach
 *          1. Find all subsequences in text1/string1.
 *          2. Iterate over each of the subsequences from text1/string1, see if you can find it in text2/string2
 *              2.1. See that the subsequence is found in the increasing order of indexes.
 *              2.2  Keep track of the longest subsequence, when found in increasing order.
 *              2.3  Return the longest subsequence found.
 *
 *      Approach 2: Recursion with Memoization
 *          m = b   d
 *              0   1
 *              ^
 *          n = a   b   c   d
 *              0   1   2   3
 *              ^
 *
 *                                                                 lcs(0,0)
 *                                                                     b,a
 *                    lcs(1,0) d,a - 1                                                                     lcs(0,1)+1 b,b
 *
 *         lcs(2,0)x           lcs(1,1) b,b + 1                                       lcs(1,1) d,b ret 1                                             lcs(0,2) ret 1
 *
 *                     lcs(2,1)x      lcs(1,2) d,c - 1                        lcs(2,1)x       lcs(1,2)                        lcs(1,2)  ret 1                               lcs(0,3) ret 1
 *                                                                                                                           RET stored val (1)
 *                             lcs(2,2)x        lcs(1,3) +1 d,d                                                                                               lcs(1,3)                     lcs(0,4)x
 *                                                                                                                                                         RET stored val of 1,3 (1)
 *                                         lcs(2,3)x   lcs(1,4)x
 *
 *
 *      Time Complexity :
 *              O(2 ^ (longest of m, n))    [recursion]
 *              m x n                       [memoization]
 *
 *  REFERENCES
 *      https://youtu.be/sSno9rV8Rhg?t=878
 *      https://youtu.be/Qf5R-uYQRPk
 *      https://youtu.be/hYmM6unIUbs
 *      https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
 *      https://www.ics.uci.edu/~eppstein/161/960229.html
 *
 *  GeeksForGeeks
 *
 *  Given two sequences, find the length of longest subsequence present in both of them.
 *  A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous.
 *  For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”.
 *  In order to find out the complexity of brute force approach, we need to first know the number of possible
 *  different subsequences of a string with length n, i.e., find the number of subsequences with lengths ranging from 1,2,..n-1.
 *  Recall from theory of permutation and combination that number of combinations with 1 element are nC1.
 *  Number of combinations with 2 elements are nC2 and so forth and so on. We know that nC0 + nC1 + nC2 + … nCn = 2n.
 *  So a string of length n has 2n-1 different possible subsequences since we do not consider the subsequence with length 0.
 *  This implies that the time complexity of the brute force approach will be O(n * 2n). Note that it takes O(n) time to
 *  check if a subsequence is common to both the strings. This time complexity can be improved using dynamic programming.
 *
 *  The naive solution for this problem is to generate all subsequences of both given sequences and find the longest matching subsequence. This solution is exponential in term of time complexity. Let us see how this problem possesses both important properties of a Dynamic Programming (DP) Problem.
 *
 * 1) Optimal Substructure:
 * Let the input sequences be X[0..m-1] and Y[0..n-1] of lengths m and n respectively. And let L(X[0..m-1], Y[0..n-1]) be the length of LCS of the two sequences X and Y. Following is the recursive definition of L(X[0..m-1], Y[0..n-1]).
 *
 * If last characters of both sequences match (or X[m-1] == Y[n-1]) then
 * L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])
 *
 * If last characters of both sequences do not match (or X[m-1] != Y[n-1]) then
 * L(X[0..m-1], Y[0..n-1]) = MAX ( L(X[0..m-2], Y[0..n-1]), L(X[0..m-1], Y[0..n-2]) )
 *
 * Examples:
 * 1) Consider the input strings “AGGTAB” and “GXTXAYB”. Last characters match for the strings. So length of LCS can be written as:
 * L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)
 *
 * 2) Consider the input strings “ABCDGH” and “AEDFHR. Last characters do not match for the strings. So length of LCS can be written as:
 * L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) )
 * So the LCS problem has optimal substructure property as the main problem can be solved using solutions to subproblems.
 *
 * 2) Overlapping Subproblems:
 * Following is simple recursive implementation of the LCS problem. The implementation simply follows the recursive structure mentioned above.
 *
 *   int lcs( char[] X, char[] Y, int m, int n ){
 *     if (m == 0 || n == 0)
 *        return 0;
 *
 *     if (X[m-1] == Y[n-1])
 *        return 1 + lcs(X, Y, m-1, n-1);
 *     else
 *        return max(lcs(X, Y, m, n-1), lcs(X, Y, m-1, n));
 * }
 * Output
 * Length of LCS is 4
 *
 * Time complexity of the above naive recursive approach is O(2^n) in worst case and worst case happens when all
 * characters of X and Y mismatch i.e., length of LCS is 0. Considering the above implementation, following is a partial
 * recursion tree for input strings “AXYT” and “AYZX”
 *
 *                          lcs("AXYT", "AYZX")
 *                        /
 *          lcs("AXY", "AYZX")            lcs("AXYT", "AYZ")
 *          /                              /
 * lcs("AX", "AYZX") lcs("AXY", "AYZ")   lcs("AXY", "AYZ") lcs("AXYT", "AY")
 *
 * In the above partial recursion tree, lcs(“AXY”, “AYZ”) is being solved twice. If we draw the complete recursion tree, then we can see that there are many subproblems which are solved again and again. So this problem has Overlapping Substructure property and recomputation of same subproblems can be avoided by either using Memoization or Tabulation.
 *
 * Following is a Memoization implementation for the LCS problem.
 * int lcs(char* X, char* Y, int m, int n, vector<vector<int> >& dp){
 *     if (m == 0 || n == 0)
 *         return 0;
 *
 *     if (X[m - 1] == Y[n - 1])
 *         return dp[m][n] = 1 + lcs(X, Y, m - 1, n - 1, dp);
 *
 *     if (dp[m][n] != -1) {
 *         return dp[m][n];
 *     }
 *     return dp[m][n] = max(lcs(X, Y, m, n - 1, dp), lcs(X, Y, m - 1, n, dp));
 * }
 *
 *
 */
public class LongestCommonSubsequences {

    int[][] memo;
    public int longestCommonSubsequence(String text1, String text2) {

        //int longestSubsequence = lcs(text1, 0, text2, 0);

        memo = new int[text1.length()][text2.length()];
        int longestSubsequence = lcsWithMemoization(text1, 0, text2, 0);

        return longestSubsequence;
    }

    public int lcs(String text1, int m, String text2, int n) {
        if (m > text1.length() || n > text2.length()) {
            System.out.println("(" + m + "," + n + ") x");
            return 0;
        }

        if (text1.charAt(m) == text2.charAt(n)) {
            System.out.println("  (" + m + "," + n + ") " + text1.charAt(m) + " - " + text2.charAt(n) + " (+1)");
            return 1 + lcs(text1, m + 1, text2, n + 1);
        }

        //int left = lcs(text1, m+1, text2, n);
        //int right = lcs(text2, m, text2, n+1);
        //return Math.max(left, right);
        System.out.println("(" + m + "," + n + ")");
        return Math.max(lcs(text1, m + 1, text2, n), lcs(text1, m, text2, n + 1));
    }

    public int lcsWithMemoization(String text1, int m, String text2, int n) {
        if (m > text1.length()-1 || n > text2.length()-1)
            return 0;

        // This also works
        // if (memo[m][n] != 0)
        //    return memo[m][n];

        if (text1.charAt(m) == text2.charAt(n))
            //int calVal = 1 + lcs(text1, m+1, text2, n+1); memo[m][n] = calVal; return calVal;
            return memo[m][n] = 1 + lcs(text1, m+1, text2, n+1);

        if (memo[m][n] != 0)
            return memo[m][n];

        //return memo[m][n] = Math.max(lcs(text1, m + 1, text2, n), lcs(text1, m, text2, n + 1));
        int left = lcsWithMemoization(text1, m+1, text2, n);
        int right = lcsWithMemoization(text1, m, text2, n+1);
        return memo[m][n] = Math.max(left, right);
    }


    public int lcs(char[] text1, int m, char[] text2, int n) {
        //if (m > text1.length-1 || n > text2.length-1) {
        if (text1[m] == '0' || text2[n] == '0') {
            System.out.println(" (" + m + "," + n + ")x");
            return 0;
        } else if (text1[m] == text2[n]){
            System.out.println("    (" + m + "," + n + ") " +text1[m] +" - " +text2[n] +" (+1)");
            return 1 + lcs(text1, m+1, text2, n+1);
        }else {
            System.out.println("(" + m + "," + n + ")");
            return Math.max(lcs(text1, m + 1, text2, n), lcs(text1, m, text2, n + 1));
        }
    }


    public void allSubsequencesOfText1(String text1, int i, List<String> chosen, List<List<String>> result){
        if (i > text1.length()-1) {
            result.add(new ArrayList<>(chosen));
            System.out.println(new ArrayList(chosen));
            return;
        }

        String choice = String.valueOf(text1.charAt(i));
        allSubsequencesOfText1(text1, i+1, chosen, result);

        chosen.add(choice);
        allSubsequencesOfText1(text1, i+1, chosen, result);
        chosen.remove(chosen.size()-1);
    }

    private int allSubsequencesOfText1InText2(String text2, List<List<String>> allSequencesInText1, List<List<String>> allSequencesOfText1InText2) {
        int longestSubSequence = 0;
        for (int i=0; i<allSequencesInText1.size(); i++){
            List<String> sequence = allSequencesInText1.get(i);

            boolean breakingBefore = false;
            int priorIndex = -1;
            int size = sequence.size();
            int indexesFound = 0;
            for (int j = 0; j< size; j++) {
                String s = sequence.get(j);
                int ind = text2.indexOf(s.charAt(0));
                if (ind !=-1 && ind > priorIndex) {
                    priorIndex = ind;
                    indexesFound++;
                }
            }
            if (indexesFound == size)
                longestSubSequence = Math.max(longestSubSequence, size);
        }
        return longestSubSequence;
    }

    public static void main(String[] args) {
        LongestCommonSubsequences obj = new LongestCommonSubsequences();

        // "abcde"
        //  "ace"
        List<List<String>> allSequencesInText1 = new ArrayList();
        ArrayList<String> choices = new ArrayList<>();
        obj.allSubsequencesOfText1("ace", 0, choices, allSequencesInText1);
        System.out.println(allSequencesInText1);

        List<List<String>> allSequencesOfText1InText2 = new ArrayList();
        int longestSubsequence = obj.allSubsequencesOfText1InText2("abcde", allSequencesInText1, allSequencesOfText1InText2);
        System.out.println(longestSubsequence);

        int longest = obj.lcs("bd0".toCharArray(), 0, "abcd0".toCharArray(), 0);
        System.out.println("\n"+longest);

        int longest2 = obj.lcs("abcde", 0, "ace", 0);
        System.out.println("\n" +longest2);
    }

}


/*
        (0,0)
        (1,0)
        (2,0) x
        (1,1)
            b - b (+1)
        (2,2)
            c - c (+1)
        (3,3)
            d - d (+1)
        (4,4) x
        (0,1)
        (1,1)
            b - b (+1)
        (2,2)
            c - c (+1)
        (3,3)
            d - d (+1)
        (4,4) x
        (0,2)
        (1,2)
        (2,2)
            c - c (+1)
        (3,3)
            d - d (+1)
        (4,4) x
        (1,3)
        (2,3)
        (3,3)
            d - d (+1)
        (4,4) x
        (2,4) x
        (1,4) x
        (0,3)
        (1,3)
        (2,3)
        (3,3)
            d - d (+1)
        (4,4) x
        (2,4) x
        (1,4) x
        (0,4) x

        3
*/