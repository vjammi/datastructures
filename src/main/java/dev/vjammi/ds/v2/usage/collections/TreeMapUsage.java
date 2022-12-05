package dev.vjammi.ds.v2.usage.collections;

import java.util.*;

/**
 * A Red-Black tree based {@link NavigableMap} implementation.
 * The map is sorted according to the {@linkplain Comparable natural
 * ordering} of its keys, or by a {@link Comparator} provided at map
 * creation time, depending on which constructor is used.
 */
public class TreeMapUsage {

    public static void main(String[] args) {
        TreeMapUsage obj = new TreeMapUsage();
        //obj.iterateTreeMap();
        //obj.sortTreeMapByValue1();
        obj.sortTreeMapByValue2();
    }

    private static void iterateTreeMap() {
        Map<Integer, String> map = new TreeMap<>();
        map.put(1, "5");
        map.put(2, "6");
        map.put(3, "7");
        map.put(0, "4");
        map.put(-3, "1");
        map.put(-2, "2");
        map.put(-1, "3");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void sortTreeMapByValue1() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "AAA");
        map.put(5, "BBB");
        map.put(3, "CCC");
        map.put(2, "DDD");
        map.put(4, "EEE");

        SortedMap<Integer, String> sortedMap1 = sort1(map);
        SortedMap<Integer, String> sortedMap2 = sort2(map);

        for (Map.Entry<Integer, String> entry : sortedMap1.entrySet()) {
            System.out.print(entry.getKey() + " : " +entry.getValue());
        }

        for (Map.Entry<Integer, String> entry : sortedMap2.entrySet()) {
            System.out.print(entry.getKey() + " : " +entry.getValue());
        }

    }

    // Method for sorting the TreeMap based off its values
    // Method with return type Map and extending comparator class which compares values associated with two keys
    private <K, V extends Comparable<V>> SortedMap<K, V> sort1(Map<K, V> map) {
        class TreeMapValueComparator<K> implements Comparator<K> {
            public int compare(K k1, K k2) {
                int comp = map.get(k1).compareTo(map.get(k2));
                if (comp == 0)  return 1;
                else return comp;
            }
        }

        // SortedMap created using the comparator
        SortedMap<K, V> sortedMap = new TreeMap<K, V>(new TreeMapValueComparator());
        sortedMap.putAll(map);
        return sortedMap;
    }

    private <Integer, String extends Comparable<String>> SortedMap<Integer, String> sort2(Map<Integer, String> map) {

        class TreeMapValueComparator<Integer> implements Comparator<Integer> {
            public int compare(Integer k1, Integer k2) {
                int comp = map.get(k1).compareTo(map.get(k2));
                if (comp == 0)
                    return 1;
                else
                    return comp;
            }
        }

        // SortedMap created using the comparator
        SortedMap<Integer, String> sorted = new TreeMap<Integer, String>(new TreeMapValueComparator());
        sorted.putAll(map);
        return sorted;
    }


    // Reference: https://www.educative.io/courses/collections-in-java/3jEkQnA7jy4
    TreeMap<String, Integer> map;
    public void sortTreeMapByValue2() {
        map = new TreeMap<>();
        map.put("Oracle", 43);
        map.put("Microsoft", 56);
        map.put("Apple", 76);
        map.put("Novartis", 87);
        map.put("Google", 23);
        map.put("Audi", 101);

        TreeMap<String, Integer> sortedMap1 = sortByValues1(map);
        System.out.println(sortedMap1); // {Google=23, Oracle=43, Microsoft=56, Apple=76, Novartis=87, Audi=101}

        TreeMap<String, Integer> sortedMap2 = sortByValues2(map);
        System.out.println(sortedMap2); // {Google=23, Oracle=43, Microsoft=56, Apple=76, Novartis=87, Audi=101}

        //TreeMap<String, Integer> sortedMap3 = sortByValues2(unSortedMap); // Throws an exception if entry is being used. Exception in thread "main" java.lang.ClassCastException: class dev.vj.patterns.usage.collections.TreeMapUsage$2 cannot be cast to class java.util.Map (dev.vj.patterns.usage.collections.TreeMapUsage$2 is in unnamed module of loader 'app'; java.util.Map is in module java.base of loader 'bootstrap')
        //System.out.println(sortedMap3); //
    }

    class ValueComparator implements Comparator<String> {
        // return comparison results of values of two keys
        public int compare(String k1, String k2) {
            int comp = map.get(k1).compareTo(map.get(k2));
            // returning 1 if both the values are the same - if two values are the same, then the TreeMap will consider it as duplicate, and it will not insert the keys in the Map.
            if (comp == 0)
                return 1; // ???
            else
                return comp;
        }
    }


    public TreeMap<String, Integer> sortByValues1(TreeMap<String, Integer> map) {
        TreeMap<String, Integer> mapSortedByValues = new TreeMap<>(new ValueComparator());
        mapSortedByValues.putAll(map);
        return mapSortedByValues;
    }

    public TreeMap<String, Integer> sortByValues2(TreeMap<String, Integer> map) {
        Comparator<String> valueComparator = new Comparator<String>() {
            // return comparison results of values of two keys
            public int compare(String k1, String k2) {
                int comp = TreeMapUsage.this.map.get(k1).compareTo(TreeMapUsage.this.map.get(k2));
                // returning 1 if both the values are the same - if two values are the same, then the TreeMap will consider it as duplicate, and it will not insert the keys in the Map.
                if (comp == 0)
                    return 1; // ???
                else
                    return comp;
            }
        };
        TreeMap<String, Integer> mapSortedByValues = new TreeMap<>(new ValueComparator());
        mapSortedByValues.putAll(map);
        return mapSortedByValues;
    }

    // Exception in thread "main" java.lang.ClassCastException: class dev.vj.patterns.usage.collections.TreeMapUsage$2 cannot be cast to class java.util.Map (dev.vj.patterns.usage.collections.TreeMapUsage$2 is in unnamed module of loader 'app'; java.util.Map is in module java.base of loader 'bootstrap')
    public TreeMap<String, Integer> sortByValues3(TreeMap<String, Integer> map) {
        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            // return comparison results of values of two keys
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                //int comp = map.get(entry1.getKey()).compareTo(map.get(entry2.getKey()));
                int comp = entry1.getValue().compareTo(entry2.getValue());
                // returning 1 if both the values are the same - if two values are the same, then the TreeMap will consider it as duplicate, and it will not insert the keys in the Map.
                if (comp == 0)
                    return 1; // ???
                else
                    return comp;
            }
        };
        // Exception in thread "main" java.lang.ClassCastException: class dev.vj.patterns.usage.collections.TreeMapUsage$2 cannot be cast to class java.util.Map (dev.vj.patterns.usage.collections.TreeMapUsage$2 is in unnamed module of loader 'app'; java.util.Map is in module java.base of loader 'bootstrap')
        // Exception in thread "main" java.lang.ClassCastException: class dev.vj.patterns.usage.collections.TreeMapUsage$2 cannot be cast to class java.util.Map (dev.vj.patterns.usage.collections.TreeMapUsage$2 is in unnamed module of loader 'app'; java.util.Map is in module java.base of loader 'bootstrap')
        TreeMap<String, Integer> mapSortedByValues = new TreeMap<String, Integer>((Map<? extends String, ? extends Integer>) valueComparator);
        mapSortedByValues.putAll(map);
        return mapSortedByValues;
    }

}
