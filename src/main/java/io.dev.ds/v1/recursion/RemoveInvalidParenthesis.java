package dev.vjammi.ds.v1.recursion;

import java.util.ArrayList;
import java.util.List;

/*
    Solution # 1
        For every left parenthesis, we should have a corresponding right parenthesis.
        We can make use of two counters which keep track of misplaced left and right parenthesis and in one
        iteration we can find out these two values.

            0 1 2 3 4 5 6 7
            ( ) ) ) ( ( ( )

            i = 0, left = 1, right = 0
            i = 1, left = 0, right = 0
            i = 2, left = 0, right = 1
            i = 3, left = 0, right = 2
            i = 4, left = 1, right = 2
            i = 5, left = 2, right = 2
            i = 6, left = 3, right = 2
            i = 7, left = 2, right = 2

        We have 2 misplaced left and 2 misplaced right parentheses.

    Solution # 2
       Borrowed from https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution

    Key Points:
        Generate unique answer once and only once, do not rely on Set.
        Do not need preprocess.
        Runtime 3 ms.
    Explanation:
        We all know how to check a string of parentheses is valid using a stack. Or even simpler use a counter.
        The counter will increase when it is ‘(‘ and decrease when it is ‘)’. Whenever the counter is negative, we have more ‘)’ than ‘(‘ in the prefix.

        To make the prefix valid, we need to remove a ‘)’. The problem is: which one? The answer is any one in the prefix.
        However, if we remove any one, we will generate duplicate results,
        for example: s = ()), we can remove s[1] or s[2] but the result is the same (). Thus, we restrict ourself to remove the first ) in a series of concecutive )s.

        After the removal, the prefix is then valid. We then call the function recursively to solve the rest of the string. However, we need to keep another information:
        the last removal position. If we do not have this position, we will generate duplicate by removing two ‘)’ in two steps only with a different order.
        For this, we keep tracking the last removal position and only remove ‘)’ after that.

        Now one may ask. What about ‘(‘? What if s = ‘(()(()’ in which we need remove ‘(‘?
        The answer is: do the same from right to left.
        However a cleverer idea is: reverse the string and reuse the code!

        public List<String> removeInvalidParentheses(String s) {
            List<String> ans = new ArrayList<>();
            remove(s, ans, 0, 0, new char[]{'(', ')'});
            return ans;
        }

        public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
            for (int stack = 0, i = last_i; i < s.length(); ++i) {
                if (s.charAt(i) == par[0]) stack++;
                if (s.charAt(i) == par[1]) stack--;
                if (stack >= 0) continue;
                for (int j = last_j; j <= i; ++j)
                    if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                        remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
                return;
            }
            String reversed = new StringBuilder(s).reverse().toString();
            if (par[0] == '(') // finished left to right
                remove(reversed, ans, 0, 0, new char[]{')', '('});
            else // finished right to left
                ans.add(reversed);
        }
*/
public class RemoveInvalidParenthesis {

    // Borrowed Solution
    public List<String> removeInvalidParentheses(String s) {
        List<String> output = new ArrayList<>();
        removeHelper(s, output, 0, 0, '(', ')');
        return output;
    }

    public void removeHelper(String s, List<String> output, int iStart, int jStart, char openParen, char closedParen) {

        int numOpenParen = 0, numClosedParen = 0;

        for (int i = iStart; i < s.length(); i++) {
            if (s.charAt(i) == openParen) numOpenParen++;
            if (s.charAt(i) == closedParen) numClosedParen++;
            if (numClosedParen > numOpenParen) { // We have an extra closed paren we need to remove
                for (int j = jStart; j <= i; j++) // Try removing one at each position, skipping duplicates
                    if (s.charAt(j) == closedParen && (j == jStart || s.charAt(j - 1) != closedParen))
                        // Recursion: iStart = i since we now have valid # closed parenthesis thru i. jStart = j prevents duplicates
                        removeHelper(s.substring(0, j) + s.substring(j + 1), output, i, j, openParen, closedParen);
                return; // Stop here. The recursive calls handle the rest of the string.
            }
        }

        // No invalid closed parenthesis detected. Now check opposite direction, or reverse back to original direction.
        String reversed = new StringBuilder(s).reverse().toString();
        if (openParen == '(')
            removeHelper(reversed, output, 0, 0, ')','(');
        else
            output.add(reversed);
    }

    public static void main(String[] args) {
        RemoveInvalidParenthesis removeInvalidParenthesis = new RemoveInvalidParenthesis();
        System.out.println(removeInvalidParenthesis.removeInvalidParentheses("()())()"));
    }

}
