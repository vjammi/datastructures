package ds.patterns.usage.compare.comparators;

import java.util.*;
import java.util.Map.Entry;

public class ComparatorUsageInHashMap {

    class CompareByHashMapIntegerKey implements Comparator<Map.Entry<String, List<Integer>>> {
        @Override
        public int compare(Entry<String, List<Integer>> entry1, Entry<String, List<Integer>> entry2) {
            return entry1.getKey().compareTo(entry2.getKey());
        }
    }

    class CompareByHashMapIntegerValue implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            return entry1.getValue().compareTo(entry2.getValue());
        }
    }

    class CompareByListIntegerValue implements Comparator<Integer>{
        @Override
        public int compare(Integer integer1, Integer integer2){
            return integer1.compareTo(integer2);
        }
    }

    private void sortByHashMapKey() {
        Map<String, List<Integer>> map = new LinkedHashMap<>();

        List<Integer> list = new ArrayList<Integer>();
        list.add(10); list.add(9);list.add(8);list.add(7);list.add(6);list.add(5);list.add(4);

        map.put("a", new ArrayList(list)); map.put("b", new ArrayList(list)); map.put("c",new ArrayList(list)); map.put("d", new ArrayList(list));
        map.put("e", new ArrayList(list));  map.put("f", new ArrayList(list));  map.put("g",new ArrayList(list)); map.put("h", new ArrayList(list));
        map.put("i", new ArrayList(list));  map.put("j", new ArrayList(list));  map.put("k", new ArrayList(list)); map.put("l", new ArrayList(list));

        List<Entry<String, List<Integer>>> entries = new ArrayList<Entry<String, List<Integer>>>(map.entrySet());
        Collections.sort(entries, new CompareByHashMapIntegerKey());

        Map<String,  List<Integer>> sortedMap = new LinkedHashMap<>();

        System.out.println("Sorted Lists");
            for (Entry<String, List<Integer>> entry : entries) {
                String key = entry.getKey();
                List<Integer> values = entry.getValue();
                Collections.sort(values, new CompareByListIntegerValue());
                sortedMap.put(key, values);
                System.out.println(key + " " + values);
            }
        System.out.println("Sorted Map ");
            System.out.println(sortedMap);
    }

    private void showByHashMapValue() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("a", 11); map.put("B", 12); map.put("c", 3); map.put("d", 4);
        map.put("e", 5);  map.put("f", 6);  map.put("g", 7); map.put("h", 8);
        map.put("i", 9);  map.put("j", 3);  map.put("k", 2); map.put("l", 1);

        List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(map.entrySet());
        Collections.sort(entries, new CompareByHashMapIntegerValue());

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
            System.out.print(sortedMap.put(entry.getKey(), entry.getValue()) + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ComparatorUsageInHashMap obj = new ComparatorUsageInHashMap();
        obj.sortByHashMapKey();
        obj.showByHashMapValue();
    }
}


