package ds.patterns.dfs;

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
}
