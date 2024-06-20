package dev.vjammi.ds.v1.strings;

/**
    https://leetcode.com/problems/integer-to-roman/
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
        Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000

    For example,
        2 is written as II in Roman numeral, just two one's added together.
        12 is written as XII, which is simply X + II.
        The number 27 is written as XXVII, which is XX + V + II.
    Roman numerals are usually written largest to smallest from left to right.
    However, the numeral for four is not IIII. Instead, the number four is written as IV.
    Because the one is before the five we subtract it making four.
    The same principle applies to the number nine, which is written as IX.
    There are six instances where subtraction is used:
        I can be placed before V (5) and X (10) to make 4 and 9.
        X can be placed before L (50) and C (100) to make 40 and 90.
        C can be placed before D (500) and M (1000) to make 400 and 900.

    Given an integer, convert it to a roman numeral.

         Example 1:
            Input: num = 478
            Output: "MCMXCIV"
            Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
        Example 4:
            Input: num = 58
            Output: "LVIII"
            Explanation: L = 50, V = 5, III = 3.
            50/10 =
        Example 5:
            Input: num = 1994
            Output: "MCMXCIV"
            Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

* */
public class IntegerToRoman {

    /**

         values  = {1000, 900,  500, 400,  100, 90,   50,  40,   10,  9,    5,   4,    1};
         symbols = {"M",  "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
         Num: 478

           1000 > 478 = true  - skip
            900 > 478 = true  - skip
            900 > 478 = true  - skip
            500 > 478 = true  - skip
            400 > 478 = false - 478/400 = Q:1 R:78    Result = "CD"
            100 > 78 = true   - skip
            90  > 78 = true   - skip
            50  > 78 = false  - 78/50  = Q:1 R:28    Result = "CDL"
            10  > 28 = false  - 28/10  = Q:2 R:8     Result = "CDLXX"
            5   > 8 =  false  - 5/8    = Q:1 R:3     Result = "CDLXXV"
            4   > 3 =  true   - skip
            1   > 3 =  false  - 3/1    = Q:3 R:0     Result = "CDLXXVIII"
    */

    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num > 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
