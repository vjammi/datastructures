package ds.patterns.dfs;

import ds.util.IndentUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
     // https://leetcode.com/problems/combination-sum/solution/
     39. Combination Sum
     Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
     The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
     It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

         Example 1:
         Input: candidates = [2,3,6,7], target = 7
         Output: [[2,2,3],[7]]
         Explanation:
         2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
         7 is a candidate, and 7 = 7.
         These are the only two combinations.

     Approach 1: Backtracking
     Intuition
     As a reminder, backtracking is a general algorithm for finding all (or some) solutions to some computational problems. The idea is that it incrementally builds candidates to the solutions, and abandons a candidate ("backtrack") as soon as it determines that this candidate cannot lead to a final solution.
     Specifically, to our problem, we could incrementally build the combination, and once we find the current combination is not valid, we backtrack and try another option.
     To demonstrate the idea, we showcase how it works with a concrete example in the following graph:
     exploration tree

     For the given list of candidates [3, 4, 5] and a target sum 8, we start off from empty combination [] as indicated as the root node in the above graph.
     Each node represents an action we take at a step, and within the node we also indicate the combination we build sofar.
     From top to down, at each level we descend, we add one more element into the current combination.
     The nodes marked in blue are the ones that could sum up to the target value, i.e. they are the desired combination solutions.
     The nodes marked in red are the ones that exceed the target value. Since all the candidates are positive value, there is no way we could bring the sum down to the target value, if we explore further.
     At any instant, we can only be at one of the nodes. When we backtrack, we are moving from a node to its parent node.
     An important detail on choosing the next number for the combination is that we select the candidates in order, where the total candidates are treated as a list. Once a candidate is added into the current combination, we will not look back to all the previous candidates in the next explorations.

     To demonstrate the idea, let us zoom in a node (as we shown in the above graph) to see how we can choose the next numbers.
     When we are at the node of [4], the precedent candidates are [3], and the candidates followed are [4, 5].
     We don't add the precedent numbers into the current node, since they would have been explored in the nodes in the left part of the subtree, i.e. the node of [3].
     Even though we have already the element 4 in the current combination, we are giving the element another chance in the next exploration, since the combination can contain duplicate numbers.
     As a result, we would branch out in two directions, by adding the element 4 and 5 respectively into the current combination.

 */
public class CombinationSum {

    /**
                            input/chosen
                            [2367] []

              [2]              [3]           [6]     [7]
              |
              [22]                x[33]  x[36]  x[66]    x[77]
              |
             x[222] [223] x[226] x[227]
              |
             x[2222] x[2223] x[2226] x[2227]
                     1                                    2

             [2, 3, 6, 7] L(0-2) []
             [2, 3, 6, 7] L(0-2) [2]
             [2, 3, 6, 7] L(0-2) [2, 2]
             [2, 3, 6, 7] L(0-2) [2, 2, 2]
             [2, 3, 6, 7] X>T  [2, 2, 2, 2]
             [2, 3, 6, 7] R(0-2) [2, 2, 2]
             > [2, 3, 6, 7] L(1-3) [2, 2, 2]
             > [2, 3, 6, 7] X>T  [2, 2, 2, 3]
             > [2, 3, 6, 7] R(1-3) [2, 2, 2]
             >    > [2, 3, 6, 7] L(2-6) [2, 2, 2]
             >    > [2, 3, 6, 7] X>T  [2, 2, 2, 6]
             >    > [2, 3, 6, 7] R(2-6) [2, 2, 2]
             >    >    > [2, 3, 6, 7] L(3-7) [2, 2, 2]
             >    >    > [2, 3, 6, 7] X>T  [2, 2, 2, 7]
             >    >    > [2, 3, 6, 7] R(3-7) [2, 2, 2]
             >    >    >    > [2, 3, 6, 7] X=L  [2, 2, 2]
             [2, 3, 6, 7] R(0-2) [2, 2]
             > [2, 3, 6, 7] L(1-3) [2, 2]
             > [2, 3, 6, 7] OUT  [2, 2, 3]
             > [2, 3, 6, 7] R(1-3) [2, 2]
             >    > [2, 3, 6, 7] L(2-6) [2, 2]
             >    > [2, 3, 6, 7] X>T  [2, 2, 6]
             >    > [2, 3, 6, 7] R(2-6) [2, 2]
             >    >    > [2, 3, 6, 7] L(3-7) [2, 2]
             >    >    > [2, 3, 6, 7] X>T  [2, 2, 7]
             >    >    > [2, 3, 6, 7] R(3-7) [2, 2]
             >    >    >    > [2, 3, 6, 7] X=L  [2, 2]
             [2, 3, 6, 7] R(0-2) [2]
             > [2, 3, 6, 7] L(1-3) [2]
             > [2, 3, 6, 7] L(1-3) [2, 3]
             > [2, 3, 6, 7] X>T  [2, 3, 3]
             > [2, 3, 6, 7] R(1-3) [2, 3]
             >    > [2, 3, 6, 7] L(2-6) [2, 3]
             >    > [2, 3, 6, 7] X>T  [2, 3, 6]
             >    > [2, 3, 6, 7] R(2-6) [2, 3]
             >    >    > [2, 3, 6, 7] L(3-7) [2, 3]
             >    >    > [2, 3, 6, 7] X>T  [2, 3, 7]
             >    >    > [2, 3, 6, 7] R(3-7) [2, 3]
             >    >    >    > [2, 3, 6, 7] X=L  [2, 3]
             > [2, 3, 6, 7] R(1-3) [2]
             >    > [2, 3, 6, 7] L(2-6) [2]
             >    > [2, 3, 6, 7] X>T  [2, 6]
             >    > [2, 3, 6, 7] R(2-6) [2]
             >    >    > [2, 3, 6, 7] L(3-7) [2]
             >    >    > [2, 3, 6, 7] X>T  [2, 7]
             >    >    > [2, 3, 6, 7] R(3-7) [2]
             >    >    >    > [2, 3, 6, 7] X=L  [2]
             [2, 3, 6, 7] R(0-2) []
             > [2, 3, 6, 7] L(1-3) []
             > [2, 3, 6, 7] L(1-3) [3]
             > [2, 3, 6, 7] L(1-3) [3, 3]
             > [2, 3, 6, 7] X>T  [3, 3, 3]
             > [2, 3, 6, 7] R(1-3) [3, 3]
             >    > [2, 3, 6, 7] L(2-6) [3, 3]
             >    > [2, 3, 6, 7] X>T  [3, 3, 6]
             >    > [2, 3, 6, 7] R(2-6) [3, 3]
             >    >    > [2, 3, 6, 7] L(3-7) [3, 3]
             >    >    > [2, 3, 6, 7] X>T  [3, 3, 7]
             >    >    > [2, 3, 6, 7] R(3-7) [3, 3]
             >    >    >    > [2, 3, 6, 7] X=L  [3, 3]
             > [2, 3, 6, 7] R(1-3) [3]
             >    > [2, 3, 6, 7] L(2-6) [3]
             >    > [2, 3, 6, 7] X>T  [3, 6]
             >    > [2, 3, 6, 7] R(2-6) [3]
             >    >    > [2, 3, 6, 7] L(3-7) [3]
             >    >    > [2, 3, 6, 7] X>T  [3, 7]
             >    >    > [2, 3, 6, 7] R(3-7) [3]
             >    >    >    > [2, 3, 6, 7] X=L  [3]
             > [2, 3, 6, 7] R(1-3) []
             >    > [2, 3, 6, 7] L(2-6) []
             >    > [2, 3, 6, 7] L(2-6) [6]
             >    > [2, 3, 6, 7] X>T  [6, 6]
             >    > [2, 3, 6, 7] R(2-6) [6]
             >    >    > [2, 3, 6, 7] L(3-7) [6]
             >    >    > [2, 3, 6, 7] X>T  [6, 7]
             >    >    > [2, 3, 6, 7] R(3-7) [6]
             >    >    >    > [2, 3, 6, 7] X=L  [6]
             >    > [2, 3, 6, 7] R(2-6) []
             >    >    > [2, 3, 6, 7] L(3-7) []
             >    >    > [2, 3, 6, 7] OUT  [7]
             >    >    > [2, 3, 6, 7] R(3-7) []
             >    >    >    > [2, 3, 6, 7] X=L  []
             [[2, 2, 3], [7]]
    */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Stack<Integer> chosen = new Stack();
        List<List<Integer>> result = new ArrayList();
        dfs(candidates, asList(candidates), chosen, target, result, 0, 0, 0);
        System.out.println(totalCalls +" " + callsThatMadeIt + " "+ result);
        return result;
    }
    int totalCalls;
    int callsThatMadeIt;
    void dfs(int[] candidates, List<Integer> input, Stack<Integer> chosen, int target, List<List<Integer>> result, int sum, int index, int n){
        totalCalls++;
        String indent = get_indent(n);
        if (sum == target ){ // ||
            result.add(new ArrayList(chosen));
            System.out.println(indent + input +" OUT " +" " +chosen);
            return;
        }else if (sum > target ){
            System.out.println(indent + input +" X>T " +" " +chosen);
            return;
        } else
            if (index == candidates.length){  // We do not want the sum of elements greater than the input size - [2, 3, 6, 7] X>T  [2, 2, 2, 2]
            System.out.println(indent + input +" X=L " +" " +chosen);
            return;
        }
        callsThatMadeIt++;

        IndentUtil.showLeftI(indent, input, index, candidates[index], chosen);
        chosen.push(candidates[index]);        // 2  2  2 | 2  2  3
        dfs(candidates, input, chosen, target, result, sum+candidates[index], index, n+1);
        chosen.pop();                          // 2  2
        IndentUtil.showRightI(indent, input, index, candidates[index], chosen);
        dfs(candidates, input, chosen, target, result, sum, index+1, n+1);
        IndentUtil.showRightI(indent, input, index, candidates[index], chosen);

        //    for (int i = index; i < candidates.length; ++i) {
        //        IndentUtil.showLeftI(indent, input, index, candidates[index], chosen);
        //        chosen.push(candidates[i]);
        //        dfs(candidates, input, chosen, target, result, sum + candidates[i], index, n+1);
        //        chosen.pop();
        //    }

    }

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> chosen = new ArrayList<>();
        Arrays.sort(nums);
        IndentUtil.showRoot(IndentUtil.getIndent(0), nums, 0, "R", chosen);
        dfs2(nums, target, 0, chosen, result);
        return result;
    }
    private void dfs2(int[] input, int remain, int start, List<Integer> chosen, List<List<Integer>> result){
        String indent = IndentUtil.getIndent(start);
        if(remain < 0) return;
        else if(remain == 0) result.add(new ArrayList<>(chosen));
        else{
            for(int i = start; i < input.length; i++){
                IndentUtil.showLeft(indent, input, start, input[i], chosen);
                chosen.add(input[i]);
                dfs2(input, remain - input[i], i, chosen, result); // not i + 1 because we can reuse same elements
                chosen.remove(chosen.size() - 1);
                //IndentUtil.showRight(indent, input, start, input[i], chosen);
            }
        }
    }

    private List<Integer>  asList(int[] candidates) {
        List<Integer> input = new ArrayList<>();
        for (int value : candidates) {
            input.add(value);
        }
        return input;
    }

    public static void main(String[] args) {
        CombinationSum obj = new CombinationSum();
        int[] nums = {2,3,6,7};
        obj.combinationSum(nums, 7);
        System.out.println();
        //obj.combinationSum2(nums, 7);
    }

    public String get_indent(int N) {
        String S = new String("");
        for (int i = 0; i < N; i++)
            S = S + "   > ";
        return S;
    }

}
