package ds.patterns.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
    77. Combinations  [https://leetcode.com/problems/combinations/]
    Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
    You may return the answer in any order.
    Example 1:
    Input: n = 4, k = 2
    MyNote: N = [1,2,3,4]
    Output:
    [
    [2,4],
    [3,4],
    [2,3],
    [1,2],
    [1,3],
    [1,4],
    ]
*/
public class Combinations {
    /**
         Combinations: n = 3 k = 2
         > [A, B, C] L(0-A) []
         >    > [A, B, C] L(1-B) []
         >    >    > [A, B, C] L(2-C) []
         >    >    > [A, B, C] R(2-C) []
         >    > [A, B, C] R(1-B) []
         >    >    > [A, B, C] L(2-C) [B]
         >    >    > [A, B, C] R(2-C) [B]
         >    >    >    > [A, B, C] OUT  [B, C]
         > [A, B, C] R(0-A) []
         >    > [A, B, C] L(1-B) [A]
         >    >    > [A, B, C] L(2-C) [A]
         >    >    > [A, B, C] R(2-C) [A]
         >    >    >    > [A, B, C] OUT  [A, C]
         >    > [A, B, C] R(1-B) [A]
         >    >    > [A, B, C] L(2-C) [A, B]
         >    >    >    > [A, B, C] OUT  [A, B]
         >    >    > [A, B, C] R(2-C) [A, B]
         [[B, C], [A, C], [A, B]]
    */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> chosen = new ArrayList();
        backtrack(n, k, chosen, result);
        return result;
    }
    /*
         //                                                   []
         // 4                      []                                                    [4]
         // 3               []                 [3]                           [4]                      [4 3]
         // 2           []      [2]        [3]        [3 2]            [4]        [4 2]         [4 3]        [4 3 2]
         // 1       []    [1] [2]  [21] [3]   [31] [32]  [321]     [4]   [41] [42]   [421]  [43]  [431]  [432]    [4321]
    */

    public void backtrack(int n, int k, List<Integer> chosen, List<List<Integer>> result){
        if (n == 0){
            if (chosen.size() == k)  result.add(new ArrayList(chosen));
            //System.out.println(new ArrayList(chosen));
            return;
        }

        int choice = n;
        backtrack(n-1, k, chosen, result);

        chosen.add(choice);
        backtrack(n-1, k, chosen, result);
        chosen.remove(chosen.size()-1);
    }

    public static void main(String[] args) {
        Combinations obj = new Combinations();
        obj.combine(4,2);
    }
}
