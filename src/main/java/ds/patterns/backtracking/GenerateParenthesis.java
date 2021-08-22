package ds.patterns.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
     22. Generate Parentheses
     Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

     Example 1:
     Input: n = 3
     Output: ["((()))","(()())","(())()","()(())","()()()"]

     Example 2:
     Input: n = 1
     Output: ["()"]
 * */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        StringBuilder chosen = new StringBuilder("");
        if (n == 0)
            return result;

        dfs(n, 0, 0, chosen, result);
        return result;
    }

    /**
       // When we have a total of n valid left and n valid right parenthesis - we add the choice to our result
       if ( left == n && right == n )
            we gather the results

       // right > left - will prevent adding right parenthesis being added before the left  - )))(((
       // left > n     - will ensure we do not add more than n left braces      - ((((
       // right > left - will ensure we do not add more than n right braces   - ((())))
       if ( right > left || left > n || right > n )
            we return / backtrack

                      []
                   (       )
              ((                ()   ))x  )(x
           x     (()
             x(()(  (())
     **/


    public void dfs(int n, int left, int right, StringBuilder chosen, List<String> result){
        // When we have a total of n valid left and n valid right parenthesis - we add the choice to our result
        if( left == n && right == n ){
            result.add(chosen.toString());
            System.out.println("*** Valid Combination: " +chosen +"***");
            return;
        }

        // right > left - will prevent adding right parenthesis being added before the left  - )))(((
        // left > n     - will ensure we do not add more than n left braces      - ((((
        // right > left - will ensure we do not add more than n right braces   - ((())))
        if( right>left || left > n || right >n ){
            return;
        }

        chosen.append("(");
        dfs(n, left+1, right, chosen, result);
        chosen.deleteCharAt(chosen.length()-1);   // Going downhill - we remove the parenthesis that was added uphill

        chosen.append(")");
        dfs(n, left, right+1, chosen, result);
        chosen.deleteCharAt(chosen.length()-1);   // Doing downhill - we remove the parenthesis that was added uphill

    }

    public static void main(String[] args) {
        GenerateParenthesis obj = new GenerateParenthesis();
        obj.generateParenthesis(3);

    }
}
