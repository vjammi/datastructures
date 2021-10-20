package ds.patterns.dp.problems;

/*
    https://leetcode.com/problems/partition-equal-subset-sum/
    Given a non-empty array nums containing only positive integers,
    find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

    Example 1:
    Input: nums = [1,5,11,5]
    Output: true
    Explanation: The array can be partitioned as [1, 5, 5] and [11].

    Example 2:
    Input: nums = [1,2,3,5]
    Output: false
    Explanation: The array cannot be partitioned into equal sum subsets.

*/

import java.util.ArrayList;
import java.util.List;

public class PartitionEqualSubsetSum {
    // nums {1,2,3,4} =>  {1,4}, {2,3}
    public boolean canPartition(int[] nums) {

        int sum = 0;
        for (int i=0; i<nums.length; i++){
            sum = sum + nums[i];
        }
        ArrayList<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> chosen = new ArrayList<>();
        canPartition(nums, 0, sum / 2, chosen, result);

        if (result.size() == 2 ) {
            int elementCount = result.get(0).size() + result.get(1).size();
            if (elementCount == sum/2){
                return true;
            }else{
                return false;
            }
        }else
            return false;
    }

    public boolean canPartition(int[] nums, int n, int sum, List<Integer> chosen, List<List<Integer>> result) {
        if (sum == 0){ //nums.length == n-1 &&
            System.out.println("True "+chosen.toString());
            result.add(chosen);
            return true;
        }

        if (sum < 0 || n == nums.length){
            //System.out.println("False "+chosen.toString());
            return false;
        }

        boolean canPartitionByExcluding = canPartition(nums, n+1, sum, chosen,  result);

        chosen.add(nums[n]);
        boolean canPartitionByIncluding = canPartition(nums, n+1, sum-nums[n], chosen,  result);
        chosen.remove(chosen.size()-1);

        if (canPartitionByExcluding || canPartitionByIncluding)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum obj =  new PartitionEqualSubsetSum();
        //int[] nums = {1,2,3,4};
        //int[] nums = {1,5,11,5};
        int[] nums = {1, 5, 10, 21, 5};
        obj.canPartition(nums);
    }
}
