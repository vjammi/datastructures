package dev.vjammi.ds.v2.hashtable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 380. Insert Delete GetRandom O(1)
 * Implement the RandomizedSet class:
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when
 * this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 * Solution
 * we have two good candidates with O(1) average insert time:
 *      Hashmap (or Hashset, the implementation is very similar): Java HashMap
 *      Array List: Java ArrayList / Python list
 *
 * Hashmap provides Insert and Delete in average constant time, al though has problems with GetRandom.
 * The idea of GetRandom is to choose a random index and then to retrieve an element with that index.
 * There is no indexes in hashmap, and hence to get true random value, one has first to convert hashmap keys in a list,
 * that would take linear time. The solution here is to build a list of keys aside and to use this list to compute GetRandom in constant time.
 * Array List has indexes and could provide Insert and GetRandom in average constant time, though has problems with Delete.
 * To delete a value at arbitrary index takes linear time. The solution here is to always delete the last value:
 * Swap the element to delete with the last one.
 * Pop the last element out.
 * For that, one has to compute an index of each element in constant time, and hence needs a hashmap which stores element -> its index dictionary.
 * Both ways converge into the same combination of data structures:
 * Hashmap element -> its index.
 * Array List of elements.
 **/
class RandomizedSet {

    Map<Integer, Integer> map;  // val, index           5:1, 10:2, 25:3
    int[] arr;                  // index, val           1:5, 2:10, 3:25
    int index;

    public RandomizedSet() {
        map = new HashMap<>();
        arr = new int[5];
        index = 0;
    }

    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;

        arr[index] = val;
        map.put(val, index);
        index++;
        if (index == arr.length-1)
            resizeArray();
        return true;
    }

    public void resizeArray(){
        int[] arr2 = new int[2*(arr.length-1)];
        for (int i=0; i<arr.length-1; i++){
            arr2[i] = arr[i];
        }
        arr = arr2;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;

        int mappedIndex = map.get(val);
        map.remove(val);
        arr[mappedIndex] = 0;
        arr[mappedIndex] = arr[index-1];
        map.put(arr[index-1], mappedIndex);
        arr[index-1] = 0;
        index--;
        return true;
    }


    public int getRandom() {
        int lo  = 0; int hi = index;
        int key = lo + (int) (Math.random() * (hi-lo));
        //System.out.println("lo: " + lo + " hi: " +hi +" key: " +key);

        Random rand = new Random();
        int key2 = rand.nextInt(index);
        //System.out.println("Index: " +index +" key2: " +key);
        return arr[key2];
    }


    public static void main(String[] args) {
        // ["RandomizedSet","insert","remove","insert","getRandom","remove","insert","getRandom"]
        // [[],             [1],      [2],     [2],    [],          [1],    [2],     []]

        RandomizedSet obj = new RandomizedSet();
        //boolean param_1 = obj.insert(1);
        //boolean param_2 = obj.remove(2);
        //boolean param_3 = obj.insert(2);
        //int     param_4 = obj.getRandom();
        //boolean param_5 = obj.remove(1);
        //boolean param_6 = obj.insert(2);
        //int     param_7 = obj.getRandom();

        //  ["RandomizedSet","insert","insert","remove","insert","remove","getRandom"]
        //  [[],              [0],     [1],     [0],     [2],     [1],     []]

        boolean param_1 = obj.insert(0);        //  [0:0]
        boolean param_2 = obj.insert(1);        //  [0:0, 1:1]
        boolean param_3 = obj.remove(0);        //  [0:1     ]
        boolean param_4 = obj.insert(2);        //  [0:1, 1:2]
        boolean param_5 = obj.remove(1);        //  [0:1]
        int     param_6 = obj.getRandom();          //  1


    }
}



