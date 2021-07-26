package ds.patterns.dfs;

/**
     https://leetcode.com/problems/combination-sum-iii/solution/
     216. Combination Sum III
     Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
     Only numbers 1 through 9 are used.
     Each number is used at most once.
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
}
