package ds.hashtable;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

class MyHashMapImpl {

    // Should also try this with an associative array.
    private List<Node>[] keyValues;

    private int capacity;

    class Node{
        int key;
        int value;
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }


    /** Initialize your data structure here. */
    public MyHashMapImpl() {
        this.capacity = 5;
        keyValues   = new ArrayList[capacity];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = key % capacity;

        if(keyValues[index] == null){
            List<Node> list = new ArrayList();
            list.add(new Node(key, value));
            keyValues[index] = list;
        }else{
            List<Node> list = keyValues[index];
            boolean keyExists = false;
            for(Node node: list){
                if (node.key == key){
                    int indexx = list.indexOf(node);
                    // Update the value of the key, if key exists in the list and flag keyExists to true
                    list.set(indexx, new Node(node.key, value));
                    keyExists = true;
                }
            }
            // If key does not exist in the list add the key, value pair
            if (keyExists == false)
                list.add(new Node(key, value));

            keyValues[index] = list;
        }
    }

    /** Returns the value to which the specified key is mapped,
     or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = key % capacity;

        if(keyValues[index] != null){
            List<Node> list = keyValues[index];
            for(Node node: list){
                if (node.key == key)
                    return node.value;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = key % capacity;

        if(keyValues[index] != null){
            List<Node> list = keyValues[index];
            List<Node> removedItemList = new ArrayList();
            for(Node node: list){
                if (node.key == key){
                    // To prevent Concurrent Modification Exception do not remove while iterating
                    // We need to wait until after iterating before we remove the elements.
                    // https://www.baeldung.com/java-concurrentmodificationexception
                    //int indexx = list.indexOf(node);
                    //list.remove(indexx);
                }else{
                    // To prevent Concurrent Modification Exception we can skip adding
                    // what we want to remove to a removedItemList as we iterate.
                    removedItemList.add(new Node(node.key, node.value));
                }
            }
            keyValues[index] = removedItemList;
        }
    }

    public static void main(String[] args) {
         /**
         * Your MyHashMap object will be instantiated and called as such:
         */
         MyHashMapImpl obj = new MyHashMapImpl();
         obj.put(1, 1001);
         int param_2 = obj.get(1);
         obj.remove(1);
    }

}

class MyHashMapSol {
    private final int MAX_LEN = 100000;             // the amount of buckets
    private List<Pair<Integer, Integer>>[] map;     // hash map implemented by array

    /** Initialize your data structure here. */
    public MyHashMapSol() {
        map = (List<Pair<Integer, Integer>>[])new ArrayList[MAX_LEN];
    }

    /** Returns the corresponding bucket index. */
    private int getIndex(int key) {
        return key % MAX_LEN;
    }

    /** Search the key in a specific bucket. Returns -1 if the key does not existed. */
    private int getPos(int key, int index) {
        // Each bucket contains a list.
        List<Pair<Integer, Integer>> temp = map[index];
        if (temp == null) {
            return -1;
        }
        // Iterate all the elements in the bucket to find the target key.
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).getKey() == key) {
                return i;
            }
        }
        return -1;
    }

    /** value will always be positive. */
    public void put(int key, int value) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos < 0) {
            // Add new (key, value) pair if key is not existed.
            if (map[index] == null) {
                map[index] = new ArrayList<Pair<Integer, Integer>>();
            }
            map[index].add(new Pair(key, value));
        } else {
            // Update the value if key is existed.
            map[index].set(pos, new Pair(key, value));
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos < 0) {
            return -1;
        } else {
            return map[index].get(pos).getValue();
        }
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getIndex(key);
        int pos = getPos(key, index);
        if (pos >= 0) {
            map[index].remove(pos);
        }
    }

    public static void main(String[] args) {
        /**
         * Your MyHashMap object will be instantiated and called as such:
         */
        MyHashMapSol obj = new MyHashMapSol();
        obj.put(1, 1001);
        int param_2 = obj.get(1);
        obj.remove(1);
    }
}

