package ds.patterns.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
     78. Subsets    https://leetcode.com/problems/subsets/
        Given an integer array nums of unique elements, return all possible subsets (the power set).
        The solution set must not contain duplicate subsets. Return the solution in any order.
     Example 1:
        Input: nums = [1,2,3]
        Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class Subsets {
        /**
                                                             []
                 1
                                             []                                   [1]
                 2
                                     []                [2]                 [1]                  [12]
                 3
                                []       [3]     [2]       [23]      [1]       [13]      [12]         [123]

                 BASE CASE: Add choices to the result when i == nums.length [when len=3, i would be 4]
        */
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> chosen = new ArrayList<>();

        backtrack(nums, 0, chosen, result);

        return result;
    }

    private void backtrack(int[] nums, int i, List<Integer> chosen, List<List<Integer>> result){
        // Add choices to the result when i == nums.length [when len=3, i would be 4]
        if (i == nums.length){
            result.add(new ArrayList(chosen));
            System.out.println(new ArrayList(chosen));
            return;
        }

        int choice = nums[i];
        backtrack(nums, i+1, chosen, result);

        chosen.add(choice);
        backtrack(nums, i+1, chosen, result);
        chosen.remove(chosen.size()-1);

    }

    public static void main(String[] args) {
        Subsets obj = new Subsets();
        int[] nums = {1,1,2};
        obj.subsets(nums);
    }
}
