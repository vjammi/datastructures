package ds.patterns.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MedianFinder {

    List<Integer> list;

    public MedianFinder() {
        this.list = new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(num);
        if(list.size() > 1) {
            // Collections.sort(list);      // We do not need to sort the whole array.
            sort(list, list.size() - 1);  // Insertion sort should do the job
        }
    }

    public void sort(List<Integer> list, int i) {
        //for (int i = 1; i < list.size(); i++){
        for (int j=i; j>0; j--){
            if (less(list.get(j), list.get(j-1)))
                exch(list, j, j-1);
            else
                break;
        }
        //}
    }

    private boolean less(Integer integer1, Integer integer2) {
        return integer1 < integer2;
    }

    private void exch(List<Integer> list, int j, int i){
        Integer tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    public double findMedian() {
        int currentSize = list.size();
        if(currentSize%2 != 0){
            int index = currentSize/2;
            return list.get(index);
        }else{
            int index1 = currentSize/2;
            double median = list.get(index1-1) + list.get(index1);
            return median/2;
        }
    }

    public static void main(String[] args) {
        //Your MedianFinder object will be instantiated and called as such:
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);    // arr = [1]
        obj.addNum(2);    // arr = [1, 2]
        System.out.println(obj.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        obj.addNum(3);    // arr[1, 2, 3]
        System.out.println(obj.findMedian()); // return 2.0
    }
}
