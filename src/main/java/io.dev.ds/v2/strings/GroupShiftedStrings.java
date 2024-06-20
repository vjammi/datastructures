package dev.vjammi.ds.v2.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings {
    /**
     * 249. Group Shifted Strings
     * We can shift a string by shifting each of its letters to its successive letter.
     *     For example, "abc" can be shifted to be "bcd".
     * We can keep shifting the string to form a sequence.
     *     For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
     * Given an array of strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.

     * Example 1:
     * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
     * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
     *          {
     *              abc     97 98 99            = 1, 1
     *              bcd     98 99 100           = 1, 1
     *              xyz     120 121 122         = 1, 1
     *              acef    97 99 101 102       = 2, 2, 1       2[99-97], 2[101-99], 1[102-101]
     *              az      97 122              = 25
     *              ba      98 97               = -1            -1[97-98]
     *          }

     * Example 2:
     * Input: strings = ["a"]
     * Output: [["a"]]

     *
     *  Example 3:
     *  Input  ["abc","bcd","acef","xyz","za", "yb", "xc", "md", "ba","a","z"]
     *  Output [["acef"],["za"],["md"],["abc","bcd","xyz"],["a","z"],["yb"],["xc"],["ba"]]
     *
     * Now Processing str abc
     * Now Processing str bcd
     * Now Processing str acef
     * Now Processing str xyz
     * Now Processing str za
     *  Char at i: a char at (i-1):  z Diff in ascii -25
     *  Diff after adding 26 1
     * Now Processing str yb
     *  Char at i: b char at (i-1):  y Diff in ascii -23
     *  Diff after adding 26 3
     * Now Processing str xc
     *  Char at i: c char at (i-1):  x Diff in ascii -21
     *  Diff after adding 26 5
     * Now Processing str md
     *  Char at i: d char at (i-1):  m Diff in ascii -9
     *  Diff after adding 26 17
     * Now Processing str ba
     *  Char at i: a char at (i-1):  b Diff in ascii -1
     *  Diff after adding 26 25
     * Now Processing str a
     * Now Processing str z
     */
    /**
     Reference: https://leetcode.com/problems/group-shifted-strings/discuss/282285/Python-Solution-with-Explanation-(44ms-84)

     The key can be represented as a tuple of the "differences" between adjacent characters. Characters map to integers (e.g. ord('a') = 97).
        For example, 'abc' maps to (1,1) because ord('b') - ord('a') = 1 and ord('c') - ord('b') = 1

     We need to watch out for the "wraparound" case
        for example, 'az' and 'ba' should map to the same "shift group" as a + 1 = b and z + 1 = a.
     Given the above point, the respective tuples would be (25,) (122-97) and (-1,) (79-80) and az and ba would map to different groups. This is incorrect.
     To account for this case, we add 26 to the difference between letters (smallest difference possible is -25, za) and mod by 26.
     So, (26 + 122 - 97) % 26 and (26 + 79 - 80) % 26 both equal (25,)

          (1, 1): ['abc', 'bcd', 'xyz'],
          (2, 2, 1): ['acef'],
          (25,): ['az', 'ba'],
          (): ['a', 'z']

        Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
        Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
            abc     97 98 99            = 1, 1
            bcd     98 99 100           = 1, 1
            xyz     120 121 122         = 1, 1
            acef    97 99 101 102       = 2, 2, 1       2[99-97], 2[101-99], 1[102-101]
            az      97 122              = 25
            ba      98 97               = -1            -1[97-98]
     */
    public List<List<String>> groupStrings(String[] strings) {

        Map<List<Integer>, List<String>> map = new HashMap();

        for (String str: strings){

            List<Integer> adjCharDiffs = new ArrayList();

            char[] chars = str.toCharArray();
            for (int i=0; i<chars.length; i++){
                if (chars.length > 1 && i>0){
                    int diff = (int)chars[i] - (int)chars[i-1];
                    if (diff < 0)
                        diff = diff + 26;
                    adjCharDiffs.add(diff);
                }
                if (chars.length == 1){
                    // An empty adjCharDiffs for instance for input strings like  [a], [b]
                }
            }

            if (map.containsKey(adjCharDiffs)){
                map.get(adjCharDiffs).add(str);
            }else{
                List<String> list = new ArrayList();
                list.add(str);
                map.put(adjCharDiffs, list);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for(Map.Entry<List<Integer>, List<String>> entry: map.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }
}
