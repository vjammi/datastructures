package ds.patterns.backtracking;

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

/**
            234 L(0-a) []
               > 234 L(1-d) [a]
               >    > 234 L(2-g) [a, d]
               >    >    > 234 OUT(3)[a, d, g]
               >    > 234 L(2-h) [a, d]
               >    >    > 234 OUT(3)[a, d, h]
               >    > 234 L(2-i) [a, d]
               >    >    > 234 OUT(3)[a, d, i]
               > 234 L(1-e) [a]
               >    > 234 L(2-g) [a, e]
               >    >    > 234 OUT(3)[a, e, g]
               >    > 234 L(2-h) [a, e]
               >    >    > 234 OUT(3)[a, e, h]
               >    > 234 L(2-i) [a, e]
               >    >    > 234 OUT(3)[a, e, i]
               > 234 L(1-f) [a]
               >    > 234 L(2-g) [a, f]
               >    >    > 234 OUT(3)[a, f, g]
               >    > 234 L(2-h) [a, f]
               >    >    > 234 OUT(3)[a, f, h]
               >    > 234 L(2-i) [a, f]
               >    >    > 234 OUT(3)[a, f, i]
            234 L(0-b) []
               > 234 L(1-d) [b]
               >    > 234 L(2-g) [b, d]
               >    >    > 234 OUT(3)[b, d, g]
               >    > 234 L(2-h) [b, d]
               >    >    > 234 OUT(3)[b, d, h]
               >    > 234 L(2-i) [b, d]
               >    >    > 234 OUT(3)[b, d, i]
               > 234 L(1-e) [b]
               >    > 234 L(2-g) [b, e]
               >    >    > 234 OUT(3)[b, e, g]
               >    > 234 L(2-h) [b, e]
               >    >    > 234 OUT(3)[b, e, h]
               >    > 234 L(2-i) [b, e]
               >    >    > 234 OUT(3)[b, e, i]
               > 234 L(1-f) [b]
               >    > 234 L(2-g) [b, f]
               >    >    > 234 OUT(3)[b, f, g]
               >    > 234 L(2-h) [b, f]
               >    >    > 234 OUT(3)[b, f, h]
               >    > 234 L(2-i) [b, f]
               >    >    > 234 OUT(3)[b, f, i]
            234 L(0-c) []
               > 234 L(1-d) [c]
               >    > 234 L(2-g) [c, d]
               >    >    > 234 OUT(3)[c, d, g]
               >    > 234 L(2-h) [c, d]
               >    >    > 234 OUT(3)[c, d, h]
               >    > 234 L(2-i) [c, d]
               >    >    > 234 OUT(3)[c, d, i]
               > 234 L(1-e) [c]
               >    > 234 L(2-g) [c, e]
               >    >    > 234 OUT(3)[c, e, g]
               >    > 234 L(2-h) [c, e]
               >    >    > 234 OUT(3)[c, e, h]
               >    > 234 L(2-i) [c, e]
               >    >    > 234 OUT(3)[c, e, i]
               > 234 L(1-f) [c]
               >    > 234 L(2-g) [c, f]
               >    >    > 234 OUT(3)[c, f, g]
               >    > 234 L(2-h) [c, f]
               >    >    > 234 OUT(3)[c, f, h]
               >    > 234 L(2-i) [c, f]
               >    >    > 234 OUT(3)[c, f, i]
            [[a, d, g], [a, d, h], [a, d, i], [a, e, g], [a, e, h], [a, e, i], [a, f, g], [a, f, h], [a, f, i], [b, d, g], [b, d, h], [b, d, i], [b, e, g], [b, e, h], [b, e, i], [b, f, g], [b, f, h], [b, f, i], [c, d, g], [c, d, h], [c, d, i], [c, e, g], [c, e, h], [c, e, i], [c, f, g], [c, f, h], [c, f, i]]

* */
public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        if (digits ==null || digits.length() == 0)
            return new ArrayList<String>();
        Map<String, String> map = buildNumToCharsMap();
        List<String> result = new ArrayList<>();
        combinations(digits, map, new Stack<String>(), result, 0);
        return result;
    }

    private void combinations(String input, Map<String, String> map, Stack<String> chosen, List<String> result, int level) {
        String indent = get_indent(level);
        if (level == input.length()) { // 0>1>2  when level/2 == digits.length()/2 return and backtrack
//            String chosenStr = new String(chosen.toString());
//            while(!chosen.isEmpty()){
//                res =  (String)chosen.pop() + res;
//            }
//           String res = "";
//            Object[] charArr = new Object[chosen.size()];
//            chosen.copyInto(charArr);
//            for(int i=charArr.length-1; i>=0; i--){
//               res =  (String)charArr[i] + res;
//            }
            result.add(new String(chosen.toString()));
            System.out.println(indent +input +" OUT(" +level +")" +chosen);
            return;
        }
        String digit = String.valueOf(input.charAt(level));
        String characters = map.get(digit);
        for(char character: characters.toCharArray()){
            System.out.println(indent +input +" L(" +level +"-"+character+") " +chosen);
            chosen.push(String.valueOf(character));
            combinations(input, map, chosen, result,level+1);
            chosen.pop();
        }
    }

    private Map<String, String> buildNumToCharsMap() {
        HashMap map = new HashMap<>();
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

    public String get_indent(int N) {
        String S = new String("");
        for (int i = 0; i < N; i++)
            S = S + "   > ";
        return S;
    }
}
