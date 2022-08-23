package ds.patterns.usage.collections;

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
        obj.sortTreeMapByValue();
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

    public void sortTreeMapByValue() {
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

}
