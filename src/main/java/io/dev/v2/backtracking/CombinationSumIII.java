package io.dev.v2.backtracking;

import io.dev.v2.z.misc.IndentUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
     https://leetcode.com/problems/combination-sum-iii/solution/
     216. Combination Sum III
     Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
        - Only numbers 1 through 9 are used.
        - Each number is used at most once.
     Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

     Example 1:
     Input: k = 3, n = 7
     Output: [[1,2,4]]
     Explanation:
     1 + 2 + 4 = 7
     There are no other valid combinations.

     Approach 1: Backtracking
     Intuition
     The problem asks us to come up with some fixed-length combinations that meet certain conditions.
     To solve the problem, it would be beneficial to build a combination by hand.
     If we represent the combination as an array, we then could fill the array one element at a time.
     For example, given the input k=3k=3 and n=9n=9, i.e. the size of the combination is 3, and the sum of the digits in the combination should be 9. Here are a few steps that we could do:
     1). We could pick a digit for the first element in the combination. Initially, the list of candidates is [1, 2, 3, 4, 5, 6, 7, 8. 9] for any element in the combination, as stated in the problem. Let us pick 1 as the first element. The current combination is [1].
     first element
     2). Now that we picked the first element, we have two more elements to fill in the final combination. Before we proceed, let us review the conditions that we should fullfil for the next steps.
     Since we've already picked the digit 1, we should exclude the digit from the original candidate list for the remaining elements, in order to ensure that the combination does not contain any duplicate digits, as required in the problem.
     In addition, the sum of the remaining two elements should be 9 - 1 = 89−1=8.
     3). Based on the above conditions, for the second element, we could have several choices. Let us pick the digit 2, which is not a duplicate of the first element, plus it does not exceed the desired sum (i.e. 88) neither. The combination now becomes [1, 2].
     second element
     4). Now for the third element, with all the constraints, it leaves us no choice but to pick the digit 6 as the final element in the combination of [1, 2, 6].
     third element
     5). As we mentioned before, for the second element, we could have several choices. For instance, we could have picked the digit 3, instead of the digit 2. Eventually, it could lead us to another solution as [1, 3, 5].
     6). As one can see, for each element in the combination, we could revisit our choices, and try out other possibilities to see if it leads to a valid solution.
     If you have followed the above steps, it should become evident that backtracking would be the technique that we could use to come up an algorithm for this problem.
     Indeed, we could resort to backtracking, where we try to fill the combination one element at a step. Each choice we make at certain step might lead us to a final solution. If not, we simply revisit the choice and try out other choices, i.e. backtrack.
 */
public class CombinationSumIII {
/**
            k=3, n=7, numbers = [1...9] Numbers 1 thru 9 are used and each number is used at most once.

                                                                []

                                    /           \        \     \       \       \       \       \       \
                                  1              2        3     4       5       6       7       8       9
                                /  \           3   4...9
                              /     \        x4   5...9
                            /             Sum=9
                          /          \
                        2            x3   x4   x5   x6   x7   x8   x9
                      /  \
                    /     \
                  /        4     x5   x6   x7  x8   x9
                x3     [1,2,4] sum =7
               /  \
             /     \
          x4        x5   x6   x7   x8   x9
     sum=10         11   12   13   14   15

             [1, 2, 3, 4, 5, 6, 7, 8, 9] L(1-1) [1]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(2-2) [1, 2]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-3) [1, 2, 3]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-4) [1, 2, 3, 4]
             >    >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 3, 4]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-4) [1, 2, 3, 4]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-5) [1, 2, 3, 5]
             >    >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 3, 5]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-5) [1, 2, 3, 5]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-6) [1, 2, 3, 6]
             >    >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 3, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-6) [1, 2, 3, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-7) [1, 2, 3, 7]
             >    >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 3, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-7) [1, 2, 3, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-8) [1, 2, 3, 8]
             >    >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 3, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-8) [1, 2, 3, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-9) [1, 2, 3, 9]
             >    >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 3, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-9) [1, 2, 3, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-3) [1, 2, 3]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-4) [1, 2, 4]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] OUT() [1, 2, 4]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-4) [1, 2, 4]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-5) [1, 2, 5]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-5) [1, 2, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-6) [1, 2, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-6) [1, 2, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-7) [1, 2, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-7) [1, 2, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-8) [1, 2, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-8) [1, 2, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-9) [1, 2, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 2, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-9) [1, 2, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(2-2) [1, 2]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(2-3) [1, 3]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-4) [1, 3, 4]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 3, 4]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-4) [1, 3, 4]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-5) [1, 3, 5]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 3, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-5) [1, 3, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-6) [1, 3, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 3, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-6) [1, 3, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-7) [1, 3, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 3, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-7) [1, 3, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-8) [1, 3, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 3, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-8) [1, 3, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-9) [1, 3, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 3, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-9) [1, 3, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(2-3) [1, 3]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(2-4) [1, 4]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-5) [1, 4, 5]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 4, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-5) [1, 4, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-6) [1, 4, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 4, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-6) [1, 4, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-7) [1, 4, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 4, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-7) [1, 4, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-8) [1, 4, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 4, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-8) [1, 4, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-9) [1, 4, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 4, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-9) [1, 4, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(2-4) [1, 4]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(2-5) [1, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-6) [1, 5, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 5, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-6) [1, 5, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-7) [1, 5, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 5, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-7) [1, 5, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-8) [1, 5, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 5, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-8) [1, 5, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-9) [1, 5, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 5, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-9) [1, 5, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(2-5) [1, 5]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(2-6) [1, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(7-7) [1, 6, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 6, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(7-7) [1, 6, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(7-8) [1, 6, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 6, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(7-8) [1, 6, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(7-9) [1, 6, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 6, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(7-9) [1, 6, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(2-6) [1, 6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(2-7) [1, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(2-7) [1, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(2-8) [1, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(2-8) [1, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(2-9) [1, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [1, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(2-9) [1, 9]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] R(1-1) [1]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] L(1-2) [2]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-3) [2, 3]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-4) [2, 3, 4]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 3, 4]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-4) [2, 3, 4]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-5) [2, 3, 5]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 3, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-5) [2, 3, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-6) [2, 3, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 3, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-6) [2, 3, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-7) [2, 3, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 3, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-7) [2, 3, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-8) [2, 3, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 3, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-8) [2, 3, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-9) [2, 3, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 3, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-9) [2, 3, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-3) [2, 3]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-4) [2, 4]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-5) [2, 4, 5]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 4, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-5) [2, 4, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-6) [2, 4, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 4, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-6) [2, 4, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-7) [2, 4, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 4, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-7) [2, 4, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-8) [2, 4, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 4, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-8) [2, 4, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-9) [2, 4, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 4, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-9) [2, 4, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-4) [2, 4]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-5) [2, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-6) [2, 5, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 5, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-6) [2, 5, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-7) [2, 5, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 5, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-7) [2, 5, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-8) [2, 5, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 5, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-8) [2, 5, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-9) [2, 5, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 5, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-9) [2, 5, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-5) [2, 5]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-6) [2, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-6) [2, 6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-7) [2, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-7) [2, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-8) [2, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-8) [2, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(3-9) [2, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [2, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(3-9) [2, 9]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] R(1-2) [2]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] L(1-3) [3]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-4) [3, 4]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-5) [3, 4, 5]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 4, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-5) [3, 4, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-6) [3, 4, 6]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 4, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-6) [3, 4, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-7) [3, 4, 7]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 4, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-7) [3, 4, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-8) [3, 4, 8]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 4, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-8) [3, 4, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-9) [3, 4, 9]
             >    >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 4, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-9) [3, 4, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-4) [3, 4]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-5) [3, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 5]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-5) [3, 5]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-6) [3, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-6) [3, 6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-7) [3, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-7) [3, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-8) [3, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-8) [3, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(4-9) [3, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [3, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(4-9) [3, 9]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] R(1-3) [3]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] L(1-4) [4]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-5) [4, 5]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [4, 5]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-5) [4, 5]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-6) [4, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [4, 6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-6) [4, 6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-7) [4, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [4, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-7) [4, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-8) [4, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [4, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-8) [4, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(5-9) [4, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [4, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(5-9) [4, 9]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] R(1-4) [4]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] L(1-5) [5]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-6) [5, 6]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [5, 6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-6) [5, 6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-7) [5, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [5, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-7) [5, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-8) [5, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [5, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-8) [5, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(6-9) [5, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [5, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(6-9) [5, 9]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] R(1-5) [5]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] L(1-6) [6]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(7-7) [6, 7]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [6, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(7-7) [6, 7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(7-8) [6, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [6, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(7-8) [6, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(7-9) [6, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [6, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(7-9) [6, 9]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] R(1-6) [6]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] L(1-7) [7]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(8-8) [7, 8]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [7, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(8-8) [7, 8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] L(8-9) [7, 9]
             >    > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [7, 9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] R(8-9) [7, 9]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] R(1-7) [7]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] L(1-8) [8]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [8]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] R(1-8) [8]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] L(1-9) [9]
             > [1, 2, 3, 4, 5, 6, 7, 8, 9] RET() [9]
             [1, 2, 3, 4, 5, 6, 7, 8, 9] R(1-9) [9]
             [[1, 2, 4]]

*/
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> chosen = new Stack<>();
        int[] visited = new int[9];
        //backtrack(1, k, n, 0, chosen, result, visited, 0);
        backtrack2(1, k, n, 0, chosen, result, visited, 0);
        System.out.println(result);
        return result;
    }
    int[] validInput = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    // With Enumeration
    private void backtrack(int index, int k, int n, int sum, Stack<Integer> chosen, List<List<Integer>> result, int[] visited, int nn){
        String indent = IndentUtil.getIndent(nn);
        if (sum == n && chosen.size() == k){
            result.add(new ArrayList(chosen));
            IndentUtil.showChosen(indent, validInput, chosen);
            return;
        }else if(sum > n){      //  1   3   6   10
            IndentUtil.showReturn(indent, validInput, chosen);
            return;             //              RET
        }

        for (int i=index; i<=9; i++){   //  1   2   3                   //visited[j-1] = 1;     //  1   2   3   4
            chosen.push(i);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, i, chosen);
            sum = sum + i;          //  1   3   6
            backtrack(i+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, i, chosen);
            sum = sum - i;          //  1   3
            chosen.pop();           //  1   2                       //visited[j-1] = 0;
        }
    }

    // without the enumeration
    private void backtrack2(int index, int k, int n, int sum, Stack<Integer> chosen, List<List<Integer>> result, int[] visited, int nn){
        String indent = IndentUtil.getIndent(nn);
        if (sum == n && chosen.size() == k){
            for(int choice: chosen){
                if (choice <1 || choice >9)
                    return;
            }
            result.add(new ArrayList(chosen));
            IndentUtil.showReturn(indent, validInput, chosen);
            return;
        }else if(sum > n){      //  1   3   6   10
            IndentUtil.showReturn(indent, validInput, chosen);
            return;             //              RET
        }

            //for (int i=index; i<=9; i++){   //  1   2   3
            //    chosen.push(index);         //  1   2   3   4
            //    IndentUtil.showLeft(indent, validInput, index, index, chosen);
            //    sum = sum + index;          //  1   3   6
            //    backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            //    IndentUtil.showRight(indent, validInput, index, index, chosen);
            //    sum = sum - index;          //  1   3
            //    chosen.pop();               //  1   2
            //}

            chosen.push(index);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, index, chosen);
            sum = sum + index;          //  1   3   6
            backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, index, chosen);
            sum = sum - index;          //  1   3
            chosen.pop();               //  1   2

            index = index + 1;
            chosen.push(index);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, index, chosen);
            sum = sum + index;          //  1   3   6
            backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, index, chosen);
            sum = sum - index;          //  1   3
            chosen.pop();           //  1   2                       //visited[j-1] = 0;

            index = index + 1;
            chosen.push(index);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, index, chosen);
            sum = sum + index;          //  1   3   6
            backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, index, chosen);
            sum = sum - index;          //  1   3
            chosen.pop();           //  1   2                       //visited[j-1] = 0;

            index = index + 1;
            chosen.push(index);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, index, chosen);
            sum = sum + index;          //  1   3   6
            backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, index, chosen);
            sum = sum - index;          //  1   3
            chosen.pop();           //  1   2                       //visited[j-1] = 0;

            index = index + 1;
            chosen.push(index);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, index, chosen);
            sum = sum + index;          //  1   3   6
            backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, index, chosen);
            sum = sum - index;          //  1   3
            chosen.pop();           //  1   2                       //visited[j-1] = 0;

            index = index + 1;
            chosen.push(index);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, index, chosen);
            sum = sum + index;          //  1   3   6
            backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, index, chosen);
            sum = sum - index;          //  1   3
            chosen.pop();           //  1   2                       //visited[j-1] = 0;

            index = index + 1;
            chosen.push(index);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, index, chosen);
            sum = sum + index;          //  1   3   6
            backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, index, chosen);
            sum = sum - index;          //  1   3
            chosen.pop();           //  1   2                       //visited[j-1] = 0;

            index = index + 1;
            chosen.push(index);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, index, chosen);
            sum = sum + index;          //  1   3   6
            backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, index, chosen);
            sum = sum - index;          //  1   3
            chosen.pop();           //  1   2                       //visited[j-1] = 0;

            index = index + 1;
            chosen.push(index);         //  1   2   3   4
            IndentUtil.showLeft(indent, validInput, index, index, chosen);
            sum = sum + index;          //  1   3   6
            backtrack2(index+1, k, n, sum, chosen, result, visited, nn+1); //  1   2   3
            IndentUtil.showRight(indent, validInput, index, index, chosen);
            sum = sum - index;          //  1   3
            chosen.pop();           //  1   2                       //visited[j-1] = 0;
    }

    public static void main(String[] args) {
        CombinationSumIII obj = new CombinationSumIII();
        //obj.combinationSum3(3,7);
        obj.combinationSum3(2,18);
    }

}
