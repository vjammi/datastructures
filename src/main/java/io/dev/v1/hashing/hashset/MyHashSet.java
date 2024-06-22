package io.dev.v1.hashing.hashset;

/*
    Design a HashSet without using any built-in hash table libraries.

    Implement MyHashSet class:
        void add(key) Inserts the value key into the HashSet.
        bool contains(key) Returns whether the value key exists in the HashSet or not.
        void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.

    Example 1:
        Input
        ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
        [[], [1], [2], [1], [3], [2], [2], [2], [2]]
        Output
        [null, null, null, true, false, null, true, null, false]

    Explanation
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // return True
        myHashSet.contains(3); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // return True
        myHashSet.remove(2);   // set = [1]
        myHashSet.contains(2); // return False, (already removed)
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class MyHashSetImpl {

    // Associative Array [0 - 9]
    private final int capacity;
    private final Set<Integer>[] values;

    public MyHashSetImpl() {
        this.capacity = 10;
        values = new TreeSet[capacity];
    }

    public void add(int key) {
        int index = key % capacity;
        if(values[index] == null){
            Set<Integer> set = new TreeSet();
            values[index] = set;
        }else{
            Set<Integer> set = values[index];
            set.add(key);
        }
    }

    public void remove(int key) {
        int index = key % capacity;
        if(values[index] == null){
            Set<Integer> set = new TreeSet();
            values[index] = set;
        }else{
            Set<Integer> set = values[index];
            set.add(key);
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = key % capacity;
        if(values[index] != null){
            Set<Integer> set = values[index];
            return set.contains(key);
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        MyHashSetImpl myHashSetImpl = new MyHashSetImpl();
        myHashSetImpl.add(1);           // set = [1]
        myHashSetImpl.add(2);           // set = [1, 2]
        myHashSetImpl.contains(1);      // return True
        myHashSetImpl.contains(3);      // return False, (not found)
        myHashSetImpl.add(2);           // set = [1, 2]
        myHashSetImpl.contains(2);      // return True
        myHashSetImpl.remove(2);    // set = [1]
        myHashSetImpl.contains(2);      // return False, (already removed)
    }

}

class MyHashSetSol {
    private final int MAX_LEN = 100000; // the amount of buckets
    private final List<Integer>[] set;      // hash set implemented by array

    /** Initialize your data structure here. */
    public MyHashSetSol() {
        set = (List<Integer>[])new ArrayList[MAX_LEN];
    }

    /** Returns the corresponding bucket index. */
    private int getIndex(int key) {
        return key % MAX_LEN;
    }

    /** Search the key in a specific bucket. Returns -1 if the key does not existed. */
    private int getPos(int key, int index) {
        // Each bucket contains a list.
        List<Integer> temp = set[index];
        if (temp == null) {
            return -1;
        }
        // Iterate all the elements in the bucket to find the target key.
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i) == key) {
                return i;
            }
        }
        return -1;
    }

    public void add(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos < 0) {
            // Add new key if key does not exist.
            if (set[index] == null) {
                set[index] = new ArrayList<Integer>();
            }
            set[index].add(key);
        }
    }

    public void remove(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos >= 0) {
            // Remove the key if key exists.
            set[index].remove(pos);
        }
    }

    /** Returns true if this set did not already contain the specified element */
    public boolean contains(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        return pos >= 0;
    }

    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */

    public static void main(String[] args) {
        MyHashSetSol myHashSet = new MyHashSetSol();
        myHashSet.add(1);           // set = [1]
        myHashSet.add(2);           // set = [1, 2]
        myHashSet.contains(1);      // return True
        myHashSet.contains(3);      // return False, (not found)
        myHashSet.add(2);           // set = [1, 2]
        myHashSet.contains(2);      // return True
        myHashSet.remove(2);    // set = [1]
        myHashSet.contains(2);      // return False, (already removed)
    }


}


