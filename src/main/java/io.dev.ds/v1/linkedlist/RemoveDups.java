package dev.vjammi.ds.v1.linkedlist;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/*
            Core Interfaces in Collections
                  Collection            Map
                /  /    \    \           |
              /   /      \    \          |
            Set List    QueueImplementingIterable Dequeue   SortedMap
         /
        /
     SortedSet

    Collection : Root interface with basic methods like add(), remove(),
                 contains(), isEmpty(), addAll(), ... etc.

     Set : Doesn't allow duplicates. Example implementations of Set
          interface are HashSet (Hashing_Objects based) and TreeSet (balanced
          BST based). Note that TreeSet implements SortedSet.
    List : Can contain duplicates and elements are ordered. Example
           implementations are LinkedList1 (linked list based) and
           ArrayList (dynamic array based)
    QueueImplementingIterable : Typically order elements in FIFO order except exceptions
            like PriorityQueue.
    Deque : Elements can be inserted and removed at both ends. Allows
            both LIFO and FIFO.
    Map : Contains Key value pairs. Doesn't allow duplicates.  Example
          implementation are HashMap and TreeMapTest.
          TreeMapTest implements SortedMap.
    The difference between Set and Map interface is that in Set we
    have only keys, whereas in Map, we have key, value pairs.
*/
public class RemoveDups {

    public static void main(String[] args){
        RemoveDups rd = new RemoveDups();
        rd.testRemoveDuplicates();
    }

    private void testRemoveDuplicates() {
        LinkedList<String> list = new LinkedList<String>();
        list.add("ABC");
        list.add("BBC");
        list.add("BBC");
        list.add("ABC");
        list.add("DBC");
        list.add("EBC");
        //removeDuplicates_Hashtable(list);
        removeDuplicates_With2Pointers(list);
    }

    public void removeDuplicates_Hashtable(LinkedList<String> list)  {
        Set<String> set = new HashSet<String>();
        Iterator<String> listIter = list.iterator();
        while(listIter.hasNext()){
            String str = listIter.next();
            if (!set.contains(str)){
                set.add(str);
            }else{
                println(" Skipping " + str);
            }
        }
        Iterator<String> hsIter = set.iterator();
        while(hsIter.hasNext()){
            String str = hsIter.next();
            println(str);
        }
    }
    // Using current and runner pointers
    public void removeDuplicates_With2Pointers(LinkedList<String> list) {

        //current and runner
        for (int i = 0; i< list.size(); i++){
            print(list.get(i) +" ");
            for (int j = i+1; j<list.size(); j++){
                if(list.get(i) == list.get(j)){
                    print("removed ["+list.get(j) +"] ");
                    list.remove(j);
                }
            }
        }

        for (int i = 0; i< list.size(); i++){
            println(list.get(i) +" ");
        }


    }

    private void println(String str) {
        System.out.println(str);
    }

    private void print(String str) {
        System.out.print(str +" ");
    }
}
