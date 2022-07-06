package ds.patterns.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**139. Word Break
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
    Note that the same word in the dictionary may be reused multiple times in the segmentation.
     Example 1:
     Input: s = "leetcode", wordDict = ["leet","code"]
     Output: true
     Explanation: Return true because "leetcode" can be segmented as "leet code".

     Example 2:
     Input: s = "applepenapple", wordDict = ["apple","pen"]
     Output: true
     Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
     Note that you are allowed to reuse a dictionary word.

 */
// https://youtu.be/th4OnoGasMU &&
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs2(s, wordDict);
    }

    /**
     *      beginIndex – the beginning index, inclusive
     *        "unhappy".substring(2) returns "happy"
     *        "Harbison".substring(3) returns "bison"
     *        "emptiness".substring(9) returns ""       (an empty string)
     **/
    private boolean dfs2(String currentSegment, List<String> wordDict){

        if (currentSegment.equals(""))
            return true;

        for (int k=0; k<currentSegment.length(); k++){
            String leftSegment =  currentSegment.substring(0, k+1); // ***
            String rightSegment = currentSegment.substring(k+1);    // ***
            System.out.println("Left Segment " +leftSegment + " Right Segment " +rightSegment + " k " +k);
            if (wordDict.contains(leftSegment)) {
                System.out.println("> wordDict contains Left Segment " +leftSegment +" Right Segment " +rightSegment);
                boolean secondHalfIsTrue = dfs2(rightSegment, wordDict);
                if (secondHalfIsTrue)
                    return true;
            }
        }
        return false;
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        boolean canBeSegmented = false;
        Map<String, Boolean> memo = new HashMap<>();
        int[] visited = new int[s.length()];
        return dfs1(s, 0, wordDict, memo);
    }

    private boolean dfs1(String s, int i, List<String> wordDict, Map<String, Boolean> memo) { //int pos, int j,

        if (wordDict.contains(s))
            return true;

        if (memo.containsKey(s))
            return memo.get(s);

        for (int k=0; k<s.length(); k++){

            String leftSegment =  s.substring(0, k); // should be k+1. Not sure how it is working here
            String rightSegment = s.substring(k);    // should be k+1. Not sure how it is working here
            System.out.println("Left Segment " +leftSegment + " Right Segment " +rightSegment);

            if (wordDict.contains(leftSegment)){
                boolean rightSegmentContainsWord = dfs1(rightSegment ,k , wordDict, memo);
                if (rightSegmentContainsWord) {
                    memo.put(rightSegment, true);
                    System.out.println(">>> Left Segment " +leftSegment + " Right Segment " +rightSegment);
                    return true;
                }else{
                    memo.put(rightSegment, false);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreak obj = new WordBreak();
        //String s = "leetcode";   String[] wordDict = {"leet","code"};

        //String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        //String[] wordDict = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};

        //String s =  "catsandog"; String[] wordDict = {"cats","dog","sand","and","cat"};
        //String s = "applepenapple";  String[] wordDict = {"apple","pen"};
        String s = "applepenapple";  String[] wordDict = {"apple","pen"};
        //String s = "leetcode";  String[] wordDict = {"leet","code"};
        obj.wordBreak(s, Arrays.asList(wordDict));
        //obj.wordBreak1(s, Arrays.asList(wordDict));
    }

}
