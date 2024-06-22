package io.dev.v2.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
     Option 1: [O(n^2) O(1) space]
     Iterate thru array with two for loops, looking for the target sum

     Option 2: [O(n) time, O(n) space]
     Iterate thru the array storing the elements in a hashmap [num, indexOfNum]
     Check if the current element can form the target sum using the elements stored in the map.

     [2,7,11,15]
     ^
     9

     [3,2,4]
     ^
     6

     k v
     ---
     2 0
     7

 */
public class TwoSum {

    public static void main(String[] args) {
        TwoSum obj = new TwoSum();
        int[] nums = {2, 7, 11, 15};
        obj.twoSum_Naive1(nums, 9);
    }

    /**
        nums = [2, 7, 11, 15], target = 19
                ^         ^
                   ^      ^
                      ^   ^
                 i<len-1  j<len
    */
    public int[] twoSum_Naive1(int[] nums, int target) {
        int len = nums.length;
        int[] answer = new int[2];
        if (len == 0)
            return answer;

        for (int i = 0; i<len-1; i++){
            int num1 = nums[i];
            for (int j=i; j<len; j++){
                int num2 = nums[j];
                if (num1 + num2 == target)
                    return new int[]{i,j};
                System.out.println("j: "+j);
            }
        }
        return answer;
    }

    public int[] twoSum_Naive2(int[] nums, int target) {
        int len = nums.length;

        for (int i = 0; i<len-1; i++){
            for (int j = i+1; j<len; j++){
                if (nums[i] + nums[j] == target)
                    return new int[]{i, j};
            }
        }

        return new int[]{};
    }

    public int[] twoSum_UsingMap1(int[] nums, int target) {
        //int[] answer = new int[2];
        Map<Integer, Integer> map = new HashMap();

        if (nums.length == 0)
            return new int[]{}; //answer;

        for (int i=0; i< nums.length; i++){
            int num = nums[i];
            if (map.containsKey(target-num))
                return new int[]{map.get(target-num), i};
            map.put(num, i);
        }

        return new int[]{};
    }

    public int[] twoSum_UsingMap2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            int complementOfI = target-nums[i];
            if (map.containsKey(complementOfI)){
                return new int[]{i, map.get(complementOfI)};
            }
            //map.put(nums[i], i);
            map.put(Integer.valueOf(nums[i]), Integer.valueOf(i));
        }
        return new int[]{};
    }

}
