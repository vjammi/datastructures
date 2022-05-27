package ds.patterns.usage.collections;

import java.util.*;

public class InitializeDataStructureWithValues {

    public static void main(String[] args) {
        // Initialize a HashMap with Values
        Map<String, String> myMap = new HashMap<String, String>() {
            {
                put("a", "b");
                put("c", "d");
            }
        };

        // Initialize a ArrayList with Values
        ArrayList<String> gfg = new ArrayList<String>() {
            {
                add("a");
                add("b");
                add("c");
            }
        };

        List<String> strings = new ArrayList<>(Arrays.asList("a","b","c"));

        Integer[] integerArray = new Integer[] {1,2,3,4,5,6};
        int[] intArray = new int[] {1,2,3,4,5,6};
        String[] stringArray = new String[]{"cat", "in", "the", "hat"};
    }
}
