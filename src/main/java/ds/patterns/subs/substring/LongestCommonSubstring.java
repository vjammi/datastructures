package ds.patterns.subs.substring;

/**
 * m: dabc
 * n: daec
 * <p>
 * 1 + lcs(m-1,n-1)
 * lcs(m-1, n)
 * lcs(m, n-1)
 * <p>
 * lcs(m, n)
 * <p>
 * <p>
 * 1 + lcs(m-1,n-1)         lcs(m-1, n)      lcs(m, n-1)
 * <p>
 * Time Complexity: 3^n
 * <p>
 * <p>
 * Approach:
 * Let m and n be the lengths of the first and second strings respectively. *
 * A simple solution is to one by one consider all substrings of the first string and for every substring check
 * if it is a substring in the second string. Keep track of the maximum length substring. There will be O(m^2)
 * substrings and we can find whether a string is substring on another string in O(n) time (See this).
 * So overall time complexity of this method would be O(n * m2)
 * <p>
 * Dynamic Programming can be used to find the longest common substring in O(m*n) time. The idea is to find the
 * length of the longest common suffix for all substrings of both strings and store these lengths in a table.
 * <p>
 * The longest common suffix has following optimal substructure property.
 * <p>
 * If last characters match, then we reduce both lengths by 1
 * LCSuff(X, Y, m, n) = LCSuff(X, Y, m-1, n-1) + 1 if X[m-1] = Y[n-1]
 * <p>
 * If last characters do not match, then result is 0, i.e.,
 * LCSuff(X, Y, m, n) = 0 if (X[m-1] != Y[n-1])
 * <p>
 * Now we consider suffixes of different substrings ending at different indexes.
 * The maximum length Longest Common Suffix is the longest common substring.
 * LCSubStr(X, Y, m, n) = Max(LCSuff(X, Y, i, j)) where 1 <= i <= m and 1 <= j <= n
 **/
public class LongestCommonSubstring {

    static String a, b;

    static int lcs(int i, int j, int count) {

        if (i == 0 || j == 0) {
            return count;
        }

        if (a.charAt(i - 1) == b.charAt(j - 1)) {
            count = lcs(i - 1, j - 1, count + 1);
        }
        count = Math.max(count, Math.max(lcs(i, j - 1, 0), lcs(i - 1, j, 0)));
        return count;
    }

    public int lcs(String strA, String strB, int i, int j, int count) {
        if (i >= strA.length() - 1 || j >= strB.length() - 1)
            return 0;

        if (strA.charAt(i + 1) == strB.charAt(j + 1))
            count = lcs(strA, strB, i + 1, j + 1, count + 1);

        return Math.max(count, Math.max(lcs(strA, strB, i, j + 1, count), lcs(strA, strB, i + 1, j, count)) );
    }

    public static void main(String[] args) {
//        int n, m;
//        a = "geeksforgeeks";
//        b = "geeks";
//        n = a.length();
//        m = b.length();
//        System.out.println(lcs(n, m, 0));


        LongestCommonSubstring obj = new LongestCommonSubstring();
        String strA = "geeksforgeeks";
        String strB = "geeks";

        int count = 0;
        System.out.println(obj.lcs(strA, strB, 0, 0, count));

    }

}



