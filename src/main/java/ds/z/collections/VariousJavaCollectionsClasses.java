package ds.z.collections;

import java.util.ArrayList;
import java.util.List;

public class VariousJavaCollectionsClasses {


    public static void main(String[] args) {
        //  1. Java ArrayList
        List<Integer> arrayList = new ArrayList();
        arrayList.add(1); arrayList.add(2);
        arrayList.remove(1);
        arrayList.set(0, 2);
        arrayList.stream().forEach(System.out::print);
        //    2. Java LinkedList

        //    3. Java HashMap
        //    4. Java TreeMap
        //    5. Java LinkedHashMap

        //    6. Java HashSet
        //    7. Java TreeSet

        //    8. Java Stack
        //    9. Java Queue
        //    10. Java Priority Queue

        //    11. Comparable and Comparator
    }

}
