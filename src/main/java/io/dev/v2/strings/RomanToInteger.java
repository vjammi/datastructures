package io.dev.v2.strings;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

/*
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
        Symbol       Value
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
    For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
    Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
        I can be placed before V (5) and X (10) to make 4 and 9.
        X can be placed before L (50) and C (100) to make 40 and 90.
        C can be placed before D (500) and M (1000) to make 400 and 900.
        Given a roman numeral, convert it to an integer.

    Example 1:    Input: s = "III"
                  Output: 3
    Example 2:    Input: s = "IV"
                  Output: 4
    Example 3:    Input: s = "IX"
                  Output: 9
    Example 4:    Input: s = "LVIII"
                  Output: 58
                    Explanation: L = 50, V= 5, III = 3.
    Example 5:    Input: s = "MCMXCIV"
                  Output: 1994
                    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

https://leetcode.com/problems/roman-to-integer/
* */
public class RomanToInteger {
    // Java 8
    Map<String, Integer> values1 = new HashMap() {{
            put("M", 1000);
            put("D", 500);
            put("C", 100);
            put("L", 50);
            put("X", 10);
            put("V", 5);
            put("I", 1);
        }};
    // Java 9+
    Map<String, Integer> values = Map.ofEntries(
                        entry("M", 1000),
                        entry("D", 500),
                        entry("C", 100),
                        entry("L", 50),
                        entry("X", 10),
                        entry("V", 5),
                        entry("I", 1)
                        );

    public int romanToInt(String s) {

        // Compute the total for the last value.
        String lastSymbol = s.substring(s.length() - 1);
        int lastValue = values.get(lastSymbol);
        int total = lastValue;

        // Decrement from 2nd last element (length-2) to 0
        for (int i = s.length()-2; i >= 0; i--) {
            String currentSymbol = s.substring(i, i + 1);
            int currentValue = values.get(currentSymbol);
            if (currentValue < lastValue) {
                total -= currentValue; // In case of CD, where C(100) is lesser than D(500). So we subtract C(100) from D(500)
            } else {
                total += currentValue; // We just add the current value to the total sum
            }
            lastValue = currentValue;
        }
        return total;
    }

    public static void main(String[] args) {
        RomanToInteger obj = new RomanToInteger();
        obj.romanToInt("CDLXXVIII");
    }
}
