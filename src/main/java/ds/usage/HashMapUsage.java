package ds.usage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapUsage {

    public static void main(String[] args) {
        int[] nums = {11,11,11,22,22,22,33,33,44,55,55,55,55,55,55};

        System.out.println("Build a Map of key frequencies from an input array...");
        Map<Integer, Integer> map = new HashMap();
        for (int num: nums) {
            if (map.containsKey(num))
                map.put(num, map.get(num) + 1);
            else
                map.put(num, 1);
        }

        System.out.println("Iterating over KeySet...");
        Set<Integer> keySet = map.keySet();
        for (Integer key: keySet){
            System.out.println(key + " " +map.get(key));
        }

        System.out.println("Iterating over EntrySet...");
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry: entrySet){
            System.out.println(entry.getKey() + " " +entry.getValue());
        }

    }
}
