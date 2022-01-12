package ds.patterns.twopointers;

import java.util.*;

/**
    Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
    Notice that the solution set must not contain duplicate triplets.
     Example 1:
     Input: nums = [-1,0,1,2,-1,-4]
     Output: [[-1,-1,2],[-1,0,1]]
     Example 2:
     Input: nums = []
     Output: []
     Example 3:
     Input: nums = [0]
     Output: []

  */
// https://leetcode.com/problems/3sum/solution/
// https://leetcode.com/problems/3sum-smaller/
    
public class ThreeSum {

    // Runtime: O(n^3)
    public List<List<Integer>> threeSumNaive(int[] nums) {
        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet();
        int size = nums.length;
        for (int i=0; i < size-2; i++){
            for (int j=i+1; j < size-1; j++){
                for (int k=j+1; k <size; k++){
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        System.out.println("Sum = " + sum + " where nums[i] " + nums[i] + " nums[j] " + nums[j] + " nums[k] " + nums[k]);
                        List<Integer> list = new ArrayList();
                        list.add(nums[i]); list.add(nums[j]); list.add(nums[k]);
                        set.add(list);
                    }
                }
            }
        }
        return new ArrayList(set);
    }

    /**
           0 1 2 3  4  5
         [-1 0 1 2 -1 -4]  // We will iterate array from i = 0 to i<size-2; j<size-1; k < size;
         ^ ^         ^
         i j --> <-- k

         Runtime: O(n)
     */

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
            return new ArrayList<>(); // if size of array less than 3

        Arrays.sort(nums); // sort array

        Set<List<Integer>> set = new HashSet<>();
        int size = nums.length;
        for (int i = 0; i< size-2; i++) {   // We iterate array from i=0 to i<size-2 or j=size-3; j<size-1 or j=size-2; k<size or k=size-1;
            int j = i+1;
            int k = size-1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0)
                    set.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                else if(sum > 0)
                    k--;
                else if (sum < 0)
                    j++;
            }
        }
        return new ArrayList<>(set);
    }

    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        int[] nums = {-4,-1,-1, 0,1,2};
        System.out.println(obj.threeSumNaive(nums));
        System.out.println(obj.threeSum(nums));
    }
}
