package io.dev.v1.hashing.hashmap;

import java.util.HashMap;
import java.util.Map;

public class ElementsAppearTwiceExceptOne {

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap();

        for (int num: nums){
            if (map.containsKey(num))
                map.put(num, map.get(num)+1);
            else
                map.put(num, 1);
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            Integer key   = entry.getKey();
            Integer value = entry.getValue();
            if (value == 1)
                return key;
        }

        return -1;
    }

    public static void main(String[] args) {
        ElementsAppearTwiceExceptOne exceptOne = new ElementsAppearTwiceExceptOne();
        int[] nums = {1, 1, 2, 2, 3, 3, 4};
        System.out.println(exceptOne.singleNumber(nums));

    }
}
