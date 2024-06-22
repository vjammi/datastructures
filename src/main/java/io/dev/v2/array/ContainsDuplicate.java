package io.dev.v2.array;

import java.util.HashSet;

public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        boolean result = containsDuplicate(nums);
        System.out.println(result);
    }


    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet();
        for (int i=0; i<nums.length; i++) {
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}
