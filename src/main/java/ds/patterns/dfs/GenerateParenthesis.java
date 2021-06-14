package ds.patterns.dfs;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
        if (n==0)
            return combinations;
        dfs(n, combinations, new StringBuilder(""), 0, 0);
        return combinations;
    }

    // Without returns
    public void dfs(int n, List<String> combinations, StringBuilder current, int left, int right){
        if( left == n && right == n ){
            combinations.add(current.toString());
            System.out.println("*** Valid Combination: " +current +"***");
            return;
        }

        //  right>left takes care of right parenthesis problem          - )))(((
        //  left > n ensures we do not add more than n left braces      - ((((
        //  right>left ensures we do not add more than n right braces   - ((())))
        if( right>left || left > n || right >n ){
            return;
        }
        dfs(n, combinations, current.append("("), left+1, right);
        current.deleteCharAt(current.length()-1);   // Going downhill - we remove the parenthesis that was added uphill
        dfs(n, combinations, current.append(")"), left, right+1);
        current.deleteCharAt(current.length()-1);   // Doing downhill - we remove the parenthesis that was added uphill

        return;
    }

    public static void main(String[] args) {
        GenerateParenthesis obj = new GenerateParenthesis();
        obj.generateParenthesis(3);

    }
}
