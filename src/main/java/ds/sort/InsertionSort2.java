package ds.sort;

import java.util.Arrays;
import java.util.List;

public class InsertionSort2 {

    // Accepts a List of Comparable
    public static void insertionSort2(int n, List<Comparable> list) {
        for (int i = 1; i < list.size(); i++){
            for (int j=i; j>0; j--){
                if (less(list.get(j), list.get(j-1)))
                    exch(list, j, j-1);
                else
                    break;
            }
            print(list);
        }
    }
    // Accepts an Array of Comparable
    public static void insertionSort2(int n, Comparable[] arr) {
        for (int i = 1; i < arr.length; i++){
            for (int j=i; j>0; j--){
                if (less(arr[j], arr[j-1]))
                    exch(arr, j, j-1);
                else
                    break;
            }
            print(arr);
        }
    }

    private static boolean less(Comparable v, Comparable w) { // v and w could be any comparable - Integer, String etc...
        return v.compareTo(w) < 0;
    }
    //    private static boolean less2(Integer integer1, Integer integer2) {
    //        return integer1 < integer2;
    //    }

    private static void print(List<Comparable> list){
        for ( Object item: list){
            System.out.print(item +" ");
        }
        System.out.println();
    }

    private static void print(Comparable[] arr){
        for ( Object item: arr){
            System.out.print(item +" ");
        }
        System.out.println();
    }

    private static void exch(List<Comparable> list, int j, int i /*j-1*/){
        Comparable tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    private static void exch(Object[] arr, int j, int i /*j-1*/){
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args){
        int n = 6;

        Integer[] intArr = {1, 4, 3, 5, 6, 2};
        List<Comparable> list1 = Arrays.asList(intArr);
        insertionSort2(n, list1);

        Integer[] arr = {1, 4, 3, 5, 6, 2};
        insertionSort2(n, arr);

        String[] strArr = {"h", "g", "d", "c", "a", "b", "e", "f", "i"};
        List<Comparable> list2 = Arrays.asList(strArr);
        insertionSort2(n, list2);

    }
}
