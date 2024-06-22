package io.dev.v1.hashing.hashset;

import java.lang.reflect.Type;
import java.util.*;

public class DuplicatesInHashSet {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int num: nums){
            if (set.contains(num))
                return true;
            else
                set.add(num);
        }
        return false;
    }

    boolean findDuplicates(List<String> keys) {
        // Replace Type with actual type of your key
        Set<String> hashset = new HashSet<>();
        for (String key : keys) {
            if (hashset.contains(key)) {
                return true;
            }
            hashset.add(key);
        }
        return false;
    }
    /*
     * Template for using hash set to find duplicates.
     */
    boolean findDuplicatesTemplate(List<Type> keys) {
        // Replace Type with actual type of your key
        Set<Type> hashset = new HashSet<>();
        for (Type key : keys) {
            if (hashset.contains(key)) {
                return true;
            }
            hashset.add(key);
        }
        return false;
    }

    public static void main(String[] args) {
        DuplicatesInHashSet duplicates = new DuplicatesInHashSet();

        String[] strArr = {"Hello", "there", "Hello"};
        List<String> list = Arrays.asList(strArr);
        System.out.println(duplicates.findDuplicates(list));

        int[] intArr = {1,2,3,4,5};
        System.out.println(duplicates.containsDuplicate(intArr));
    }
}
