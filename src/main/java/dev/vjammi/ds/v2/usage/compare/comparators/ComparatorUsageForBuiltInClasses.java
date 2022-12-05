package dev.vjammi.ds.v2.usage.compare.comparators;

import java.text.Collator;
import java.util.*;

/**
 Compares its two arguments for order.
     Returns a             negative integer, zero,   or a positive integer
     as the first argument is less than,     equal to, or greater than the second

     int compare(Object o1, Object o2){
          if first < second
              returns a negative integer
          if first == second
              return a 0
          if first > second
              returns a positive integer
     }

     class CompareStrings implements Comparator<String> {
         int compare(String o1, String o2){
             return o1.compareTo(o2); // Note that the objects passed are comparable
         }
     }
    *** Note: You pass comparable objects to be compared by a Comparator
 */
/**
 * int compare(String o1, String o2){
 *     return o1.compareTo(o2);
 * }
 * Compares its two arguments for order.  Returns a negative integer,
 * zero, or a positive integer as the first argument is less than, equal
 * to, or greater than the second.<p>
 * <p>
 * In the foregoing description, the notation
 * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
 * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
 * <tt>0</tt>, or <tt>1</tt> according to whether the value of
 * <i>expression</i> is negative, zero or positive.<p>
 * <p>
 * The implementor must ensure that <tt>sgn(compare(x, y)) ==
 * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
 * implies that <tt>compare(x, y)</tt> must throw an exception if and only
 * if <tt>compare(y, x)</tt> throws an exception.)<p>
 * <p>
 * The implementor must also ensure that the relation is transitive:
 * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
 * <tt>compare(x, z)&gt;0</tt>.<p>
 * <p>
 * Finally, the implementor must ensure that <tt>compare(x, y)==0</tt>
 * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
 * <tt>z</tt>.<p>
 * <p>
 * It is generally the case, but <i>not</i> strictly required that
 * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
 * any comparator that violates this condition should clearly indicate
 * this fact.  The recommended language is "Note: this comparator
 * imposes orderings that are inconsistent with equals."
 *
 * param o1 the first object to be compared.
 * param o2 the second object to be compared.
 *
 * return a negative integer, zero, or a positive integer as the
 * first argument is less than, equal to, or greater than the second.
 * @throws NullPointerException if an argument is null and this
 *                              comparator does not permit null arguments
 * @throws ClassCastException   if the arguments' types prevent them from
 *                              being compared by this comparator.
 */
public class ComparatorUsageForBuiltInClasses {

    class CompareStrings implements Comparator<String> {
        public int compare(String o1, String o2) { // these object passes as params implement Comparable interface
            return o1.compareTo(o2); // Ascending
        }
    }

    class CompareIntegers implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1); // Descending
        }
    }

    private void testIntegerComparators() {
        List<Integer> list = new ArrayList<>();
        list.add(50000);list.add(400);list.add(300000);list.add(200000);list.add(10);

        Collections.sort(list, new CompareIntegers());

        Iterator<Integer> listIterator = list.iterator();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();
    }

    class CompareByKeyComparator implements Comparator<String>{
        @Override
        public int compare(String first, String second) {
            Collator collator = Collator.getInstance(Locale.getDefault());
            //Collator collator = Collator.getInstance(new Locale("tr", "TR"));
            return collator.compare(first, second);
        }
    }

    public <K extends Comparable, V extends Comparable> Map<K,V> sortByKeys(Map<K,V> map){
        List<K> keys = new LinkedList<K>(map.keySet());
        CompareByKeyComparator compareByKeyComparator = new CompareByKeyComparator();
        Collections.sort(keys, (Comparator<? super K>) compareByKeyComparator);
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for(K key: keys)
            sortedMap.put(key, map.get(key));
        return sortedMap;
    }

    private void testSortByKeys() {
        // Ordering by the natural key
        Map<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(5, "aba");treeMap.put(4, "jkl");treeMap.put(3, "ghi");treeMap.put(2, "def");treeMap.put(11, "abc");
        System.out.println(treeMap);
        for(Map.Entry<Integer, String> entry : treeMap.entrySet()){
            System.out.println(entry.getKey() + " " +entry.getValue());
        }

        // Retains Insertion Order
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(5, "aba");linkedHashMap.put(4, "jkl");linkedHashMap.put(3, "ghi");linkedHashMap.put(2, "def");linkedHashMap.put(11, "abc");
        System.out.println(linkedHashMap);
        for(Map.Entry<Integer, String> entry : linkedHashMap.entrySet()){
            System.out.println(entry.getKey() + " " +entry.getValue());
        }
    }

    public static void main(String[] args) {
        ComparatorUsageForBuiltInClasses obj = new ComparatorUsageForBuiltInClasses();
        //obj.testIntegerComparators();
        obj.testSortByKeys();
    }
}

