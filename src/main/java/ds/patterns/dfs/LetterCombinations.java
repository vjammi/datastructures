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
