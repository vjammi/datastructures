package ds.hashing.hashset;

import java.util.*;
  /**
      Note: To find the intersection is a trivial solution using a hash or a set.
      You might want to solve it under these constraints:
          O(n) time and O(1) space (the resulting array of intersections is not taken into consideration).
          You may assume the lists are sorted.
      Cases to take into consideration include:
          Duplicates, Negative values, Single value lists, 0's, and Empty List arguments.
          Other considerations might include sparse arrays [a sparse matrix/array in which most of the elements are zero]
    */
public class IntersectionOfTwoArrays {

    // Solution is to compute the hash of each element, of each array and store the elements in each perform hash partitioning join. Similar to joins in bid datasets.
    public int[] intersection(int[] nums1, int[] nums2) {
        // Need to store the hash value of an element of an array in a list, removing duplicates. An Hashset would be ideal for this.
        Set<Integer> set1 = new HashSet();
        for (int num1: nums1)
            set1.add(num1);
        Set<Integer> set2 = new HashSet();
        for (int num2: nums2)
            set2.add(num2);

        // Build a list of common elements
        List<Integer> resultList = new ArrayList<>();
        Iterator<Integer> iterator1 = set1.iterator();
        while(iterator1.hasNext()){
            Integer num1 = iterator1.next();
            if (set2.contains(num1))
                resultList.add(num1);
        }

        //Get the items in an array to return
        int[] resultArr = new int[resultList.size()];
        for(int i=0; i<resultList.size(); i++){
            resultArr[i] = resultList.get(i);
        }

        return resultArr;
    }

    public static void main(String[] args) {

        IntersectionOfTwoArrays obj = new IntersectionOfTwoArrays();
        int[] nums1 = {9,4,9,8,4};
        int[] result = obj.intersection(nums1, new int[]{4,9,5});
        for (int num: result)
            System.out.print(num +" ");

    }
}

class IntersectionOfTwoArraysII {

    class Pair{
        int hash;
        int value;
        Pair(int hash, int value){
            this.hash = hash;
            this.value = value;
        }
    }
    // Solution is to compute the hash of each element, of each array and store the elements in each
    // perform hash partitioning join. Similar to joins in big datasets.
    public int[] intersect(int[] nums1, int[] nums2) {
        // Need to store the hash value of an element of an array in a map. An HashMap would be ideal for this.
        Map<Integer,List<Pair>> map1 = new HashMap();
        for (int num1: nums1){
            int key = new Integer(num1).hashCode();
            if (!map1.containsKey(key)) {
                List<Pair> listPairs = new ArrayList<>();
                listPairs.add(new Pair(key, num1));
                map1.put(key, listPairs);
            }else{
                List<Pair> listPairs = map1.get(key);
                listPairs.add(new Pair(key, num1));
                map1.put(key, listPairs);
            }
        }

        Map<Integer,List<Pair>> map2 = new HashMap();
        for (int num2: nums2){
            int key = new Integer(num2).hashCode();
            if (!map2.containsKey(key)) {
                List<Pair> listPairs = new ArrayList<>();
                listPairs.add(new Pair(key, num2));
                map2.put(key, listPairs);
            }else{
                List<Pair> listPairs = map2.get(key);
                listPairs.add(new Pair(key, num2));
                map2.put(key, listPairs);
            }
        }

        // Build a list of common Pairs
        List<Pair> listPairs = new ArrayList<>();
        for (Map.Entry<Integer, List<Pair>> entry: map1.entrySet()){
            Integer key = entry.getKey();
            List<Pair> map1List = entry.getValue();
            if (map2.containsKey(key)){
                List<Pair> map2List = map2.get(key);
                // Get the least intersected key/valu pair
                List<Pair> list = map1List.size() < map2List.size() ? map1List:map2List;
                listPairs.addAll(list);
            }
        }

        //Get the items in an array to return
        int[] resultArr = new int[listPairs.size()];
        for(int i=0; i<listPairs.size(); i++){
            Pair pair = listPairs.get(i);
            resultArr[i] = pair.value;
        }
        return resultArr;
    }
    public static void main(String[] args) {

        IntersectionOfTwoArraysII obj = new IntersectionOfTwoArraysII();
        int[] nums1 = {9,4,9,8,4};
        int[] result = obj.intersect(nums1, new int[]{4,9,5});
        for (int num: result)
            System.out.print(num +" ");

    }
}

class IntersectionOfTwoArraysSol {

    // Approach 1: Two Sets
    // The naive approach would be to iterate along the first array nums1 and to check for each value if this value in nums2 or not.
    // If yes - add the value to output.
    // Such an approach would result in a pretty bad O(NxM) time complexity, where N and M are array lengths.
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1)
            set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2)
            set2.add(n);

        set1.retainAll(set2);

        int [] output = new int[set1.size()];
        int idx = 0;

        for (int s : set1)
            output[idx++] = s;
        return output;
    }

    // Approach 2: Two Sets
    // To solve the problem in linear time, let's use the structure set, which provides in/contains operation in O(1) time in average case.
    // The idea is to convert both arrays into sets, and then iterate over the smallest set checking the presence of each element in the larger set.
    // Time complexity of this approach is O(n+m) in the average case.
    public int[] intersection2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) set2.add(n);

        if (set1.size() < set2.size())
            return set_intersection(set1, set2);
        else
            return set_intersection(set2, set1);
    }

    public int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int [] output = new int[set1.size()];
        int idx = 0;

        for (Integer s : set1)
            if (set2.contains(s))
                output[idx++] = s;

        return Arrays.copyOf(output, idx);
    }
}

