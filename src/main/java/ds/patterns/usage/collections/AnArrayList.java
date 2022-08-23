package ds.patterns.usage.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AnArrayList {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(100);
        List syncList = Collections.synchronizedList(list);

        synchronized (syncList) {
            Iterator iterator = syncList.iterator(); // Must be in synchronized block
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

    /**
        Returns a synchronized (thread-safe) list backed by the specified list. In order to guarantee serial access, it is critical that all access to the backing list is accomplished through the returned list.
        It is imperative that the user manually synchronize on the returned list when traversing it via Iterator, Spliterator or Stream:

        List list = Collections.synchronizedList(new ArrayList());
            ...
        synchronized (list) {
            Iterator i = list.iterator(); // Must be in synchronized block
            while (i.hasNext())
                foo(i.next());
        }

        Failure to follow this advice may result in non-deterministic behavior.
                The returned list will be serializable if the specified list is serializable.
                Params: list – the list to be "wrapped" in a synchronized list.
                Returns: a synchronized view of the specified list.*/
     }


}
