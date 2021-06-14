package ds.patterns.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
    Given a string containing digits from 2-9 inclusive,
    return all possible letter combinations that the number could represent.
    Return the answer in any order.
    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

    Input: digits = "23"
    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */

public class LetterCombinationsOfAPhoneNumber {

    //private String phoneDigits;
    private List<String> combinations = new ArrayList<>();
    private Map<Character, String> mappedLetters = new HashMap<Character, String>()
                                                              {{
                                                                put('2', "abc");
                                                                put('3', "def");
                                                                put('4', "ghi");
                                                                put('5', "jkl");
                                                                put('6', "mno");
                                                                put('7', "pqrs");
                                                                put('8', "tuv");
                                                                put('9', "wxyz");
                                                              }};

    public List<String> letterCombinations(String digits) {
        // If the input is empty, immediately return an empty answer array
        if (digits.length() == 0) {
            return combinations;
        }

        // Initiate backtracking with an empty path and starting index of 0
        //phoneDigits = digits;
        StringBuilder path = new StringBuilder();
        dfs(0, digits, path);

        return combinations;
    }

    private void dfs(int index, String digits, StringBuilder path) {
        // If the path is the same length as digits, we have a complete combination
        if (path.length() == digits.length()) {            // 23.len ==  ad.len
            combinations.add(path.toString());             // Add to result
            return; // Backtrack
        }

        // Get the letters that the current digit maps to, and loop through them
        char digit = digits.charAt(index);                  //  0>2       1>3
        String letters = mappedLetters.get(digit);          //  2>abc     3>def

        for (char letter: letters.toCharArray()) {          //  2/abc    3/def
            // Add the letter to our current path
            path.append(letter);                            //  a            ad             ae
            // Move on to the next digit
            dfs(index + 1, digits, path);              //  (0+1, 23, a) (1+1, 23, ad)  (1+1, 23, ae)
            // Backtrack by removing the letter before moving onto the next
            path.deleteCharAt(path.length() - 1);            // ad>a
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber obj =  new LetterCombinationsOfAPhoneNumber();
        String digits = "457";
        System.out.println(obj.letterCombinations(digits));
    }

}
