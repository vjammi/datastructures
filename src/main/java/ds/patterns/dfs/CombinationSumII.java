package ds.patterns.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
     40	Combination Sum II  https://leetcode.com/problems/combination-sum-ii/
     Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
     Each number in candidates may only be used once in the combination.
        Note: The solution set must not contain duplicate combinations.

     Example 1:
     Input: candidates = [10,1,2,7,6,1,5], target = 8
     Output:
     [
     [1,1,6],
     [1,2,5],
     [1,7],
     [2,6]
     ]
*/
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> chosen = new Stack<>();

        Arrays.sort(candidates);
        combinationSum2(candidates, target, 0, chosen, result, 0);

        return result;
    }

    public void combinationSum2(int[] candidates, int target, int sum, Stack<Integer> chosen, List<List<Integer>> result, int index){

        if (sum == target){
            result.add(new ArrayList(chosen) );
            System.out.println("OUT "+ chosen);
            return;
        }else if (sum>target){
            System.out.println("Sum>Target");
            return;
        }
        else if (index == candidates.length){
            System.out.println("index==length");
            return;
        }

        for (int i=index;i<candidates.length; i++){
            System.out.println("Choice " +candidates[i] +" Sum " +sum);
            if(i > index && candidates[i] == candidates[i-1]) {
                System.out.println("Skipping " +candidates[i] +" Sum " +sum);
                continue;
            }
            chosen.push(Integer.valueOf(candidates[i]));
            combinationSum2(candidates, target, sum+candidates[i], chosen, result, i+1);
            chosen.pop();
        }
    }

}
