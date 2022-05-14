package ds.patterns.backtracking;

import ds.z.misc.IndentUtil;

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
public class CombinationSumWithNotes {

    public List<List<Integer>> combinationSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList();
        if (nums.length == 0)
            return result;

        List<Integer> chosen = new ArrayList();
        backtrack(nums, 0, target, 0, chosen, result);

        return result;
    }

    private void backtrack(int[] nums, int sum, int target, int start, List<Integer> chosen, List<List<Integer>> result){

        if(sum > target)
            return;

        if (sum == target) {
            result.add(new ArrayList(chosen));
            System.out.println(new ArrayList(chosen));
            return;
        }

        for (int i=start; i< nums.length; i++){
            chosen.add(nums[i]);
            sum = sum + nums[i];
            backtrack(nums, sum, target, i, chosen, result);
            sum = sum - nums[i];
            chosen.remove(chosen.size()-1);
        }
    }

    /**
     [input] [chosen]
     [2367] []      target=7
     ___________________|__________________________
     0 |             1 |               2  |     3 |
     [2]             [3]               [6]      [7]
     ______|______       __|__               _        _
     0 |  1 |    3 |      |     |              |        |
     [22] [23]...[27]  x[33]  x[36]         x[66]    x[77]
     __________|__________
     0 |  1 |     2 |    3 |
     x[222] [223] x[226] x[227]
     __________|____________
     0|   2 |      2 |      3 |
     x[2222] x[2223] x[2226] x[2227]
     */
    /**
     [2, 3, 6, 7] BL(0-2) [2]
     > [2, 3, 6, 7] BL(0-2) [2, 2]
     >    > [2, 3, 6, 7] BL(0-2) [2, 2, 2]
     >    >    > [2, 3, 6, 7] BL(0-2) [2, 2, 2, 2]
     >    >    >    > [2, 3, 6, 7] X>T  [2, 2, 2, 2]
     >    >    > [2, 3, 6, 7] N(0-2) [2, 2, 2]
     >    >    >    > [2, 3, 6, 7] BL(1-3) [2, 2, 2, 3]
     >    >    >    >    > [2, 3, 6, 7] X>T  [2, 2, 2, 3]
     >    >    >    > [2, 3, 6, 7] N(1-3) [2, 2, 2]
     >    >    >    >    > [2, 3, 6, 7] BL(2-6) [2, 2, 2, 6]
     >    >    >    >    >    > [2, 3, 6, 7] X>T  [2, 2, 2, 6]
     >    >    >    >    > [2, 3, 6, 7] N(2-6) [2, 2, 2]
     >    >    >    >    >    > [2, 3, 6, 7] BL(3-7) [2, 2, 2, 7]
     >    >    >    >    >    >    > [2, 3, 6, 7] X>T  [2, 2, 2, 7]
     >    >    >    >    >    > [2, 3, 6, 7] N(3-7) [2, 2, 2]
     >    >    >    >    >    >    > [2, 3, 6, 7] X=L  [2, 2, 2]
     >    >    >    >    >    > [2, 3, 6, 7] AR(3-7) [2, 2, 2]
     >    >    >    >    > [2, 3, 6, 7] AR(2-6) [2, 2, 2]
     >    >    >    > [2, 3, 6, 7] AR(1-3) [2, 2, 2]
     >    >    > [2, 3, 6, 7] AR(0-2) [2, 2, 2]
     >    > [2, 3, 6, 7] N(0-2) [2, 2]
     >    >    > [2, 3, 6, 7] BL(1-3) [2, 2, 3]
     >    >    >    > [2, 3, 6, 7] OUT  [2, 2, 3]
     >    >    > [2, 3, 6, 7] N(1-3) [2, 2]
     >    >    >    > [2, 3, 6, 7] BL(2-6) [2, 2, 6]
     >    >    >    >    > [2, 3, 6, 7] X>T  [2, 2, 6]
     >    >    >    > [2, 3, 6, 7] N(2-6) [2, 2]
     >    >    >    >    > [2, 3, 6, 7] BL(3-7) [2, 2, 7]
     >    >    >    >    >    > [2, 3, 6, 7] X>T  [2, 2, 7]
     >    >    >    >    > [2, 3, 6, 7] N(3-7) [2, 2]
     >    >    >    >    >    > [2, 3, 6, 7] X=L  [2, 2]
     >    >    >    >    > [2, 3, 6, 7] AR(3-7) [2, 2]
     >    >    >    > [2, 3, 6, 7] AR(2-6) [2, 2]
     >    >    > [2, 3, 6, 7] AR(1-3) [2, 2]
     >    > [2, 3, 6, 7] AR(0-2) [2, 2]
     > [2, 3, 6, 7] N(0-2) [2]
     >    > [2, 3, 6, 7] BL(1-3) [2, 3]
     >    >    > [2, 3, 6, 7] BL(1-3) [2, 3, 3]
     >    >    >    > [2, 3, 6, 7] X>T  [2, 3, 3]
     >    >    > [2, 3, 6, 7] N(1-3) [2, 3]
     >    >    >    > [2, 3, 6, 7] BL(2-6) [2, 3, 6]
     >    >    >    >    > [2, 3, 6, 7] X>T  [2, 3, 6]
     >    >    >    > [2, 3, 6, 7] N(2-6) [2, 3]
     >    >    >    >    > [2, 3, 6, 7] BL(3-7) [2, 3, 7]
     >    >    >    >    >    > [2, 3, 6, 7] X>T  [2, 3, 7]
     >    >    >    >    > [2, 3, 6, 7] N(3-7) [2, 3]
     >    >    >    >    >    > [2, 3, 6, 7] X=L  [2, 3]
     >    >    >    >    > [2, 3, 6, 7] AR(3-7) [2, 3]
     >    >    >    > [2, 3, 6, 7] AR(2-6) [2, 3]
     >    >    > [2, 3, 6, 7] AR(1-3) [2, 3]
     >    > [2, 3, 6, 7] N(1-3) [2]
     >    >    > [2, 3, 6, 7] BL(2-6) [2, 6]
     >    >    >    > [2, 3, 6, 7] X>T  [2, 6]
     >    >    > [2, 3, 6, 7] N(2-6) [2]
     >    >    >    > [2, 3, 6, 7] BL(3-7) [2, 7]
     >    >    >    >    > [2, 3, 6, 7] X>T  [2, 7]
     >    >    >    > [2, 3, 6, 7] N(3-7) [2]
     >    >    >    >    > [2, 3, 6, 7] X=L  [2]
     >    >    >    > [2, 3, 6, 7] AR(3-7) [2]
     >    >    > [2, 3, 6, 7] AR(2-6) [2]
     >    > [2, 3, 6, 7] AR(1-3) [2]
     > [2, 3, 6, 7] AR(0-2) [2]
     [2, 3, 6, 7] N(0-2) []
     > [2, 3, 6, 7] BL(1-3) [3]
     >    > [2, 3, 6, 7] BL(1-3) [3, 3]
     >    >    > [2, 3, 6, 7] BL(1-3) [3, 3, 3]
     >    >    >    > [2, 3, 6, 7] X>T  [3, 3, 3]
     >    >    > [2, 3, 6, 7] N(1-3) [3, 3]
     >    >    >    > [2, 3, 6, 7] BL(2-6) [3, 3, 6]
     >    >    >    >    > [2, 3, 6, 7] X>T  [3, 3, 6]
     >    >    >    > [2, 3, 6, 7] N(2-6) [3, 3]
     >    >    >    >    > [2, 3, 6, 7] BL(3-7) [3, 3, 7]
     >    >    >    >    >    > [2, 3, 6, 7] X>T  [3, 3, 7]
     >    >    >    >    > [2, 3, 6, 7] N(3-7) [3, 3]
     >    >    >    >    >    > [2, 3, 6, 7] X=L  [3, 3]
     >    >    >    >    > [2, 3, 6, 7] AR(3-7) [3, 3]
     >    >    >    > [2, 3, 6, 7] AR(2-6) [3, 3]
     >    >    > [2, 3, 6, 7] AR(1-3) [3, 3]
     >    > [2, 3, 6, 7] N(1-3) [3]
     >    >    > [2, 3, 6, 7] BL(2-6) [3, 6]
     >    >    >    > [2, 3, 6, 7] X>T  [3, 6]
     >    >    > [2, 3, 6, 7] N(2-6) [3]
     >    >    >    > [2, 3, 6, 7] BL(3-7) [3, 7]
     >    >    >    >    > [2, 3, 6, 7] X>T  [3, 7]
     >    >    >    > [2, 3, 6, 7] N(3-7) [3]
     >    >    >    >    > [2, 3, 6, 7] X=L  [3]
     >    >    >    > [2, 3, 6, 7] AR(3-7) [3]
     >    >    > [2, 3, 6, 7] AR(2-6) [3]
     >    > [2, 3, 6, 7] AR(1-3) [3]
     > [2, 3, 6, 7] N(1-3) []
     >    > [2, 3, 6, 7] BL(2-6) [6]
     >    >    > [2, 3, 6, 7] BL(2-6) [6, 6]
     >    >    >    > [2, 3, 6, 7] X>T  [6, 6]
     >    >    > [2, 3, 6, 7] N(2-6) [6]
     >    >    >    > [2, 3, 6, 7] BL(3-7) [6, 7]
     >    >    >    >    > [2, 3, 6, 7] X>T  [6, 7]
     >    >    >    > [2, 3, 6, 7] N(3-7) [6]
     >    >    >    >    > [2, 3, 6, 7] X=L  [6]
     >    >    >    > [2, 3, 6, 7] AR(3-7) [6]
     >    >    > [2, 3, 6, 7] AR(2-6) [6]
     >    > [2, 3, 6, 7] N(2-6) []
     >    >    > [2, 3, 6, 7] BL(3-7) [7]
     >    >    >    > [2, 3, 6, 7] OUT  [7]
     >    >    > [2, 3, 6, 7] N(3-7) []
     >    >    >    > [2, 3, 6, 7] X=L  []
     >    >    > [2, 3, 6, 7] AR(3-7) []
     >    > [2, 3, 6, 7] AR(2-6) []
     > [2, 3, 6, 7] AR(1-3) []
     [2, 3, 6, 7] AR(0-2) []
     [[2, 2, 3], [7]]
     */

    int totalCalls;
    int callsThatMadeIt;
    public List<List<Integer>> combinationSumApproach1(int[] candidates, int target) {
        Stack<Integer> chosen = new Stack();
        List<List<Integer>> result = new ArrayList();
        backtrackApproach1(candidates, asList(candidates), chosen, target, result, 0, 0, 0);
        System.out.println(result);
        return result;
    }

    void backtrackApproach1(int[] candidates, List<Integer> input, Stack<Integer> chosen, int target, List<List<Integer>> result, int sum, int index, int n){
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

        //   > [2, 3, 6, 7] BL(0-2) [2, 2]                      // We make an initial choice
        //   >    > [2, 3, 6, 7] BL(0-2) [2, 2, 2]              // We make the same choice again until the base case passes/fails
        //   >    >    > [2, 3, 6, 7] BL(0-2) [2, 2, 2, 2]      // We make the same choice again until the base case passes/fails
        //   >    >    >    > [2, 3, 6, 7] X>T  [2, 2, 2, 2]    // Base case fails. We now backtrack
        //   >    >    > [2, 3, 6, 7] N(0-2) [2, 2, 2]          // Backtrack by popping out the element
        //   >    >    >    > [2, 3, 6, 7] BL(1-3) [2, 2, 2, 3] // We make the next choice by incrementing the index

        // We add the same choice over and over until the base case is reached - either sum is > or == target
        chosen.push(candidates[index]);         // 2  2  2 | 2  2  3
        IndentUtil.showBeforeLeftI(indent, input, index, candidates[index], chosen);
        // We make an initial choice, update the sum. We then make the same choice again and update the sumuntil the base case passes/fails
        sum = sum + candidates[index];
        backtrackApproach1(candidates, input, chosen, target, result, sum, index, n+1);
        chosen.pop();                           // 2  2
        sum = sum - candidates[index];
        IndentUtil.showI(indent, input, index, candidates[index], chosen);

        //Finally when are done trying all variations of the same choice, we can now increment the index and make the next choice
        backtrackApproach1(candidates, input, chosen, target, result, sum, index+1, n+1); // We make the next choice by incrementing the index
        IndentUtil.showAfterRightI(indent, input, index, candidates[index], chosen);
    }

    /**
         [2, 3, 6, 7] L(0-R) []

         [2, 3, 6, 7] L(0-2) [2]
         > [2, 3, 6, 7] L(0-2) [2, 2]
         >    > [2, 3, 6, 7] L(0-2) [2, 2, 2]
         >    >    > [2, 3, 6, 7] L(0-2) [2, 2, 2, 2]
         >    >    >    > [2, 3, 6, 7] RET() [2, 2, 2, 2]
         >    >    > [2, 3, 6, 7] R(0-2) [2, 2, 2, 2]
         >    >    > [2, 3, 6, 7] L(0-3) [2, 2, 2, 3]
         >    >    >    > [2, 3, 6, 7] RET() [2, 2, 2, 3]
         >    >    > [2, 3, 6, 7] R(0-3) [2, 2, 2, 3]
         >    >    > [2, 3, 6, 7] L(0-6) [2, 2, 2, 6]
         >    >    >    > [2, 3, 6, 7] RET() [2, 2, 2, 6]
         >    >    > [2, 3, 6, 7] R(0-6) [2, 2, 2, 6]
         >    >    > [2, 3, 6, 7] L(0-7) [2, 2, 2, 7]
         >    >    >    > [2, 3, 6, 7] RET() [2, 2, 2, 7]
         >    >    > [2, 3, 6, 7] R(0-7) [2, 2, 2, 7]
         >    > [2, 3, 6, 7] R(0-2) [2, 2, 2]
         >    > [2, 3, 6, 7] L(0-3) [2, 2, 3]
         >    >    > [2, 3, 6, 7] OUT() [2, 2, 3]
         >    > [2, 3, 6, 7] R(0-3) [2, 2, 3]
         >    > [2, 3, 6, 7] L(0-6) [2, 2, 6]
         >    >    > [2, 3, 6, 7] RET() [2, 2, 6]
         >    > [2, 3, 6, 7] R(0-6) [2, 2, 6]
         >    > [2, 3, 6, 7] L(0-7) [2, 2, 7]
         >    >    > [2, 3, 6, 7] RET() [2, 2, 7]
         >    > [2, 3, 6, 7] R(0-7) [2, 2, 7]
         > [2, 3, 6, 7] R(0-2) [2, 2]

         > [2, 3, 6, 7] L(0-3) [2, 3]
         >    > [2, 3, 6, 7] L(1-3) [2, 3, 3]
         >    >    > [2, 3, 6, 7] RET() [2, 3, 3]
         >    > [2, 3, 6, 7] R(1-3) [2, 3, 3]
         >    > [2, 3, 6, 7] L(1-6) [2, 3, 6]
         >    >    > [2, 3, 6, 7] RET() [2, 3, 6]
         >    > [2, 3, 6, 7] R(1-6) [2, 3, 6]
         >    > [2, 3, 6, 7] L(1-7) [2, 3, 7]
         >    >    > [2, 3, 6, 7] RET() [2, 3, 7]
         >    > [2, 3, 6, 7] R(1-7) [2, 3, 7]
         > [2, 3, 6, 7] R(0-3) [2, 3]
         > [2, 3, 6, 7] L(0-6) [2, 6]
         >    > [2, 3, 6, 7] RET() [2, 6]
         > [2, 3, 6, 7] R(0-6) [2, 6]
         > [2, 3, 6, 7] L(0-7) [2, 7]
         >    > [2, 3, 6, 7] RET() [2, 7]
         > [2, 3, 6, 7] R(0-7) [2, 7]
         [2, 3, 6, 7] R(0-2) [2]

         [2, 3, 6, 7] L(0-3) [3]
         > [2, 3, 6, 7] L(1-3) [3, 3]
         >    > [2, 3, 6, 7] L(1-3) [3, 3, 3]
         >    >    > [2, 3, 6, 7] RET() [3, 3, 3]
         >    > [2, 3, 6, 7] R(1-3) [3, 3, 3]
         >    > [2, 3, 6, 7] L(1-6) [3, 3, 6]
         >    >    > [2, 3, 6, 7] RET() [3, 3, 6]
         >    > [2, 3, 6, 7] R(1-6) [3, 3, 6]
         >    > [2, 3, 6, 7] L(1-7) [3, 3, 7]
         >    >    > [2, 3, 6, 7] RET() [3, 3, 7]
         >    > [2, 3, 6, 7] R(1-7) [3, 3, 7]
         > [2, 3, 6, 7] R(1-3) [3, 3]
         > [2, 3, 6, 7] L(1-6) [3, 6]
         >    > [2, 3, 6, 7] RET() [3, 6]
         > [2, 3, 6, 7] R(1-6) [3, 6]
         > [2, 3, 6, 7] L(1-7) [3, 7]
         >    > [2, 3, 6, 7] RET() [3, 7]
         > [2, 3, 6, 7] R(1-7) [3, 7]
         [2, 3, 6, 7] R(0-3) [3]

         [2, 3, 6, 7] L(0-6) [6]
         > [2, 3, 6, 7] L(2-6) [6, 6]
         >    > [2, 3, 6, 7] RET() [6, 6]
         > [2, 3, 6, 7] R(2-6) [6, 6]
         > [2, 3, 6, 7] L(2-7) [6, 7]
         >    > [2, 3, 6, 7] RET() [6, 7]
         > [2, 3, 6, 7] R(2-7) [6, 7]
         [2, 3, 6, 7] R(0-6) [6]

         [2, 3, 6, 7] L(0-7) [7]
         > [2, 3, 6, 7] OUT() [7]
         [2, 3, 6, 7] R(0-7) [7]

         [[2, 2, 3], [7]]
     */
    public List<List<Integer>> combinationSumApproach2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> chosen = new ArrayList<>();
        Arrays.sort(nums);
        IndentUtil.showRoot(IndentUtil.getIndent(0), nums, 0, "", chosen);
        backtrackApproach2(nums, target, 0, chosen, result, 0);
        System.out.println(result);
        return result;
    }

    private void backtrackApproach2(int[] input, int sum, int index, List<Integer> chosen, List<List<Integer>> result, int n){
        String indent = IndentUtil.getIndent(n);
        if(sum < 0) {
            IndentUtil.showReturn(indent, input, chosen);
            return;
        }else if(sum == 0) {
            result.add(new ArrayList<>(chosen));
            IndentUtil.showChosen(indent, input, chosen);
            return;
        }

        for(int i = index; i < input.length; i++){
            chosen.add(input[i]);  //IndentUtil.showLeft(indent, input, index, input[i], chosen);
            sum = sum - input[i];
            // not i + 1 because we can reuse same elements
            backtrackApproach2(input, sum, i, chosen, result, n+1); // IndentUtil.showRight(indent, input, index, input[i], chosen);
            sum = sum + input[i];
            chosen.remove(chosen.size() - 1);
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
        CombinationSumWithNotes obj = new CombinationSumWithNotes();

        int[] nums = {2,3,6,7};
        obj.combinationSum(nums, 7);
        obj.combinationSumApproach1(nums, 7);
        System.out.println();
        obj.combinationSumApproach2(nums, 7);
    }

    public String get_indent(int N) {
        String S = new String("");
        for (int i = 0; i < N; i++)
            S = S + "   > ";
        return S;
    }

}
