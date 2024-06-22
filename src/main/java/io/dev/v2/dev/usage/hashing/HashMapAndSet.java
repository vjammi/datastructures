package io.dev.v2.dev.usage.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HashMapAndSet {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,1};
        System.out.println(mapContainsDuplicate(nums));
        System.out.println(setContainsDuplicate(nums));
    }

    public static boolean mapContainsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap();
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(nums[i])){
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static boolean setContainsDuplicate(int[] nums) {
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


