package ds.z.collections;

import java.util.ArrayList;
import java.util.List;

public class VariousJavaCollectionsClasses {

    public static void main(String[] args) {

        //  1. Java ArrayList
        List<Integer> chosen = new ArrayList();
        chosen.add(1);
        chosen.add(2);
        chosen.add(0, 3);
        chosen.remove(0); //
        chosen.remove(chosen.size()-1);
        chosen.set(0, 2);
        chosen.stream().forEach(System.out::print);

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