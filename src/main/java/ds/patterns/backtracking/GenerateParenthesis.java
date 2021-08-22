package ds.patterns.backtracking;

import ds.util.IndentUtil;

import java.util.ArrayList;
import java.util.List;

/**
    22. Generate Parentheses
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    Input: n = 2
    Output: [[(())],[()()]]

    Input: n = 3
    Output: ["((()))","(()())","(())()","()(())","()()()"]

 * */
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        StringBuilder chosen = new StringBuilder("");
        if (n == 0)
            return result;

        dfs(n, 0, 0, chosen, result, 0);
        return result;
    }

    /**
       // When we have a total of n valid left and n valid right parenthesis - we add the choice to our result
       if ( left == n && right == n )
            we gather the results

       // right > left - will prevent adding right parenthesis before the left  - x())
       // left > n     - will ensure we do not add more than n left braces      - x((((
       // right > left - will ensure we do not add more than n right braces     - x((( ))))
       if ( right > left || left > n || right > n )
            we return / backtrack

                                                     []
                             (                                                    x )
              ((                          ()
        x(((     (()                ()(          x())
             x(()(  (())OUT     x()((  OUT()()
     **/

    /**
     n =2
     >   2 L[0-(] (
         > 2 L[1-(] ((
         >    > 2 L[2-(] (((
         >    >    > 2 X[(((] (((
         >    > 2 R[2-(] (((
         >    > 2 L[0-)] (()
         >    >    > 2 L[2-(] (()(
         >    >    >    > 2 X[(()(] (()(
         >    >    > 2 R[2-(] (()(
         >    >    > 2 L[1-)] (())
         >    >    >    > 2 OUT[(())] (())           *** Valid Combination: (())***
         >    >    > 2 R[1-)] (())
         >    > 2 R[0-)] (()
         > 2 R[1-(] ((

     >   > 2 L[0-)] ()
         >    > 2 L[1-(] ()(
         >    >    > 2 L[2-(] ()((
         >    >    >    > 2 X[()((] ()((
         >    >    > 2 R[2-(] ()((
         >    >    > 2 L[1-)] ()()
         >    >    >    > 2 OUT[()()] ()()           *** Valid Combination: ()()***
         >    >    > 2 R[1-)] ()()
         >    > 2 R[1-(] ()(
         >    > 2 L[1-)] ())
         >    >    > 2 X[())] ())
         >    > 2 R[1-)] ())
         > 2 R[0-)] ()
         2 R[0-(] (
         2 L[0-)] )
         > 2 X[)] )
         2 R[0-)] )
    */

    /**
     n =3
  >      3 L[0-(] (
         > 3 L[1-(] ((
         >    > 3 L[2-(] (((
         >    >    > 3 L[3-(] ((((
         >    >    >    > 3 X[((((] ((((
         >    >    > 3 R[3-(] ((((
         >    >    > 3 L[0-)] ((()
         >    >    >    > 3 L[3-(] ((()(
         >    >    >    >    > 3 X[((()(] ((()(
         >    >    >    > 3 R[3-(] ((()(
         >    >    >    > 3 L[1-)] ((())
         >    >    >    >    > 3 L[3-(] ((())(
         >    >    >    >    >    > 3 X[((())(] ((())(
         >    >    >    >    > 3 R[3-(] ((())(
         >    >    >    >    > 3 L[2-)] ((()))
         >    >    >    >    >    > 3 OUT[((()))] ((()))           *** Valid Combination: ((()))***
         >    >    >    >    > 3 R[2-)] ((()))
         >    >    >    > 3 R[1-)] ((())
         >    >    > 3 R[0-)] ((()
         >    > 3 R[2-(] (((
         >    > 3 L[0-)] (()
         >    >    > 3 L[2-(] (()(
         >    >    >    > 3 L[3-(] (()((
         >    >    >    >    > 3 X[(()((] (()((
         >    >    >    > 3 R[3-(] (()((
         >    >    >    > 3 L[1-)] (()()
         >    >    >    >    > 3 L[3-(] (()()(
         >    >    >    >    >    > 3 X[(()()(] (()()(
         >    >    >    >    > 3 R[3-(] (()()(
         >    >    >    >    > 3 L[2-)] (()())
         >    >    >    >    >    > 3 OUT[(()())] (()())         *** Valid Combination: (()())***
         >    >    >    >    > 3 R[2-)] (()())
         >    >    >    > 3 R[1-)] (()()
         >    >    > 3 R[2-(] (()(
         >    >    > 3 L[1-)] (())
         >    >    >    > 3 L[2-(] (())(
         >    >    >    >    > 3 L[3-(] (())((
         >    >    >    >    >    > 3 X[(())((] (())((
         >    >    >    >    > 3 R[3-(] (())((
         >    >    >    >    > 3 L[2-)] (())()
         >    >    >    >    >    > 3 OUT[(())()] (())()          *** Valid Combination: (())()***
         >    >    >    >    > 3 R[2-)] (())()
         >    >    >    > 3 R[2-(] (())(
         >    >    >    > 3 L[2-)] (()))
         >    >    >    >    > 3 X[(()))] (()))
         >    >    >    > 3 R[2-)] (()))
         >    >    > 3 R[1-)] (())
         >    > 3 R[0-)] (()
         > 3 R[1-(] ((

   >     > 3 L[0-)] ()
         >    > 3 L[1-(] ()(
         >    >    > 3 L[2-(] ()((
         >    >    >    > 3 L[3-(] ()(((
         >    >    >    >    > 3 X[()(((] ()(((
         >    >    >    > 3 R[3-(] ()(((
         >    >    >    > 3 L[1-)] ()(()
         >    >    >    >    > 3 L[3-(] ()(()(
         >    >    >    >    >    > 3 X[()(()(] ()(()(
         >    >    >    >    > 3 R[3-(] ()(()(
         >    >    >    >    > 3 L[2-)] ()(())
         >    >    >    >    >    > 3 OUT[()(())] ()(())           *** Valid Combination: ()(())***
         >    >    >    >    > 3 R[2-)] ()(())
         >    >    >    > 3 R[1-)] ()(()
         >    >    > 3 R[2-(] ()((
         >    >    > 3 L[1-)] ()()
         >    >    >    > 3 L[2-(] ()()(
         >    >    >    >    > 3 L[3-(] ()()((
         >    >    >    >    >    > 3 X[()()((] ()()((
         >    >    >    >    > 3 R[3-(] ()()((
         >    >    >    >    > 3 L[2-)] ()()()
         >    >    >    >    >    > 3 OUT[()()()] ()()()           *** Valid Combination: ()()()***
         >    >    >    >    > 3 R[2-)] ()()()
         >    >    >    > 3 R[2-(] ()()(
         >    >    >    > 3 L[2-)] ()())
         >    >    >    >    > 3 X[()())] ()())
         >    >    >    > 3 R[2-)] ()())
         >    >    > 3 R[1-)] ()()
         >    > 3 R[1-(] ()(
         >    > 3 L[1-)] ())
         >    >    > 3 X[())] ())
         >    > 3 R[1-)] ())
         > 3 R[0-)] ()
         3 R[0-(] (
         3 L[0-)] )
         > 3 X[)] )
         3 R[0-)] )
     **/
    // Runtime - exponential - O(2^n) [O(2^2n)]
    public void dfs(int n, int left, int right, StringBuilder chosen, List<String> result, int ind){
        String indent = IndentUtil.getIndent(ind);
        // When we have a total of n valid left and n valid right parenthesis - we add the choice to our result
        if( left == n && right == n ){
            result.add(chosen.toString());
            System.out.println("*** Valid Combination: " +chosen +"***");
            System.out.println(indent +n +" OUT["+chosen +"] " +chosen);
            return;
        }

        // *** right > left - will prevent adding right parenthesis before the left  - x())
        //     left  > n    - will ensure we do not add more than n left braces      - ((((
        //     right > n    - will ensure we do not add more than n right braces     - ((())))
        if( right > left || left > n || right >n ){
            System.out.println(indent +n +" X["+chosen +"] " +chosen);
            return;
        }

        chosen.append("(");
        System.out.println(indent +n +" L[" +left +"-"+"("+"] " +chosen);
        dfs(n, left+1, right, chosen, result, ind+1);
        System.out.println(indent +n +" R[" +left +"-"+"("+"] " +chosen);
        chosen.deleteCharAt(chosen.length()-1);   // Going downhill - we remove the parenthesis that was added uphill

        chosen.append(")");
        System.out.println(indent +n +" L[" +right +"-"+")"+"] " +chosen);
        dfs(n, left, right+1, chosen, result, ind+1);
        System.out.println(indent +n +" R[" +right +"-"+")"+"] " +chosen);
        chosen.deleteCharAt(chosen.length()-1);   // Doing downhill - we remove the parenthesis that was added uphill

    }

    public static void main(String[] args) {
        GenerateParenthesis obj = new GenerateParenthesis();
        obj.generateParenthesis(2);
    }
}
