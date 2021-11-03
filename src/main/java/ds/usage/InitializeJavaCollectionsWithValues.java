package ds.usage;

import java.util.*;

public class InitializeJavaCollectionsWithValues {

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
    }
}
