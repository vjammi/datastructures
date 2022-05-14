package ds.usage.collections;
import java.util.Map;
import java.util.TreeMap;

public class SortedMap_TreeMap {

    public static void main(String[] args) {
        /*
         * A Red-Black tree based {@link NavigableMap} implementation.
         * The map is sorted according to the {@linkplain Comparable natural
         * ordering} of its keys, or by a {@link Comparator} provided at map
         * creation time, depending on which constructor is used.
         **/
        Map<Integer, String> map = new TreeMap<Integer, String>();

        map.put(1, "5");
        map.put(2, "6");
        map.put(3, "7");
        map.put(0, "4");
        map.put(-3, "1");
        map.put(-2, "2");
        map.put(-1, "3");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}
