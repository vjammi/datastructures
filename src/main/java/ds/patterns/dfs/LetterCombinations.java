package ds.patterns.dfs;

import java.util.*;

/**
 * 17. Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
             2  abc
             3  def
             4  ghi
             5  jkl
             6  mno
             7  pqrs
             8  tuv
             9  wxyz
 * Example 1:
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *                  2         3
 *          --------------------------
 *                       -     d
 *              -   a    -     e
 *                       -     f
 *          ""  -   b
 *
 *                  c
 */
public class LetterCombinations {
    Map<String, String> map;
    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits ==null || digits.length() == 0)
            return new ArrayList<String>();
        map = pupulateNumToCharsMap();
        combinations(0, digits, new Stack<String>());
        return result;
    }

    private void combinations(int level, String digits, Stack<String> intermediaryResult) {
        if (level == digits.length()) { // 0>1>2  when level/2 == digits.length()/2 return and backtrack
           String res = "";
            Object[] charArr = new Object[intermediaryResult.size()];
            intermediaryResult.copyInto(charArr);
            for(int i=charArr.length-1; i>=0; i--){
               res =  (String)charArr[i] + res;
            }
            result.add(res);
            return;
        }
        String digit = digits.charAt(level)+"";
        char[] characters = map.get(digit).toCharArray();
        for(char character: characters){
            // 134 - a> d g h i  e g h i  f g h i  b d g h i  e g h i  f g h i  c >d g h i  eg h i f g h i
            //System.out.print(character +" "); //23 - a > d e f  b >d e f  c >d e f
            intermediaryResult.push(String.valueOf(character));
            combinations(level+1, digits, intermediaryResult);
            if (!intermediaryResult.isEmpty())
                intermediaryResult.pop();
        }
        return;
    }

    private Map<String, String> pupulateNumToCharsMap() {
        map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
        return map;
    }

    public static void main(String[] args) {
        LetterCombinations obj = new LetterCombinations();
        System.out.println(obj.letterCombinations("234"));
    }


}
