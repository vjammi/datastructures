package ds.patterns.hashtable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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



