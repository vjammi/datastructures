package dev.vjammi.ds.v1.hashing.hashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/*
    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    You may assume that each input would have exactly one solution, and you may not use the same element twice.
    You can return the answer in any order.

    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Output: Because nums[0] + nums[1] == 9, we return [0, 1].

    Input: nums = [3,2,4], target = 6
    Output: [1,2]

    Input: nums = [3,3], target = 6
    Output: [0,1]
*/
public class TwoSum {

    /**
     * Using a HashMap - O(N)
     */
    public int[] twoSumUsingMap(int[] nums, int target) {
        Map<Integer, Integer> map = new LinkedHashMap<>();

        for (int i=0; i<nums.length; i++){
            Integer goal =  new Integer(target - nums[i]);
            if (map.containsKey(goal.hashCode())) {
                Integer k = map.get(goal);
                return new int[]{i, k};
            }else{
                Integer element = new Integer(nums[i]);
                map.put(element.hashCode(), i);
            }
        }
        return new int[]{};
    }

    /**
     * Using 2 pointers i and j - O(N^2)
     * A really brute force way would be to search for all possible pairs of numbers but that would be too slow.
     * However, it's best to try out brute force solutions, just for completeness.
     * It is from these brute force solutions that you can come up with optimizations.
     */
    public int[] twoSumNaive(int[] nums, int target) {
        for (int i=0; i<nums.length; i++){
            for (int j=i+1; j<nums.length; j++){
                if (nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {

        TwoSum obj = new TwoSum();
        int[] nums = new int[]{2,7,11,15};
        int   target = 9;
        int[] indexes1 = obj.twoSumNaive(nums, target);
        int[] indexes2 = obj.twoSumUsingMap(nums, target);
        for (int index: indexes2) {
            System.out.print(index +" ");
        }
    }

}
