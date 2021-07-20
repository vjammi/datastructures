package ds.sort;

import java.util.Arrays;
import java.util.List;

public class InsertionSort2 {

    /*
     * Complete the 'insertionSort2' function below.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */
    public static void insertionSort2(int n, List<Integer> list) {
        for (int i=1; i<list.size(); i++){
            for (int j=i; j>0; j--){
                if (list.get(j) < list.get(j-1))
                    exch(list, j, j-1);
                else
                    break;
            }
            print(list);
        }
    }

    private static void print(List<Integer> list){
        for ( Integer item: list){
            System.out.print(item +" ");
        }
        System.out.println();
    }

    private static void exch(List<Integer> list, int j, int i /*j-1*/){
        Integer tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    public static void main(String[] args){
        int n = 6;
        Integer[] arr = {1, 4, 3, 5, 6, 2};
        insertionSort2(n, Arrays.asList(arr));
    }
}
