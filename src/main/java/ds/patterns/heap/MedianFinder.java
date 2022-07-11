package ds.patterns.heap;

import java.util.*;

    /**
     295. Find Median from Data Stream
     The median is the middle value in an ordered integer list.
     If the size of the list is even, there is no middle value and the median is the mean of the two middle values.

     For example, for arr = [2,3,4], the median is 3.
     For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
     Implement the MedianFinder class:

     MedianFinder() initializes the MedianFinder object.
     void addNum(int num) adds the integer num from the data stream to the data structure.
     double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.


     Example 1:

     Input
     ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
     [[], [1], [2], [], [3], []]
     Output
     [null, null, null, 1.5, null, 2.0]

     Explanation
     MedianFinder medianFinder = new MedianFinder();
     medianFinder.addNum(1);    // arr = [1]
     medianFinder.addNum(2);    // arr = [1, 2]
     medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
     medianFinder.addNum(3);    // arr[1, 2, 3]
     medianFinder.findMedian(); // return 2.0

     */
public class MedianFinder {

    public static class MedianFinderApproach1 {
        List<Integer> list;

        public MedianFinderApproach1() {
            this.list = new ArrayList<>();
        }

        public void addNum(int num) {
            list.add(num);
            if (list.size() > 1) {
                // Collections.sort(list);      // We do not need to sort the whole array.
                sort(list, list.size()-1);  // Insertion sort should do the job
            }
        }

        public void sort(List<Integer> list, int i) {
            //for (int i = 1; i < list.size(); i++){
            for (int j = i; j > 0; j--) {
                if (less(list.get(j), list.get(j-1)))
                    exch(list, j, j - 1);
                else
                    break;
            }
            //}
        }

        private boolean less(Integer integer1, Integer integer2) {
            return integer1 < integer2;
        }

        private void exch(List<Integer> list, int j, int i) {
            Integer tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }

        public double findMedian() {
            int currentSize = list.size();
            if (currentSize % 2 != 0) {
                int index = currentSize / 2; // integer divide 3/2 = 1.5 (1)
                return list.get(index);
            } else {
                int index1 = currentSize / 2;
                double median = list.get(index1 - 1) + list.get(index1);
                return median / 2;
            }
        }
    }



    public static class MedianFinderApproach2 {

        Queue<Integer> maxHeap;
        Queue<Integer> minHeap;

        class MinElementComparator implements Comparator<Integer> {
            public int compare(Integer int1, Integer int2){
                return int1.compareTo(int2);    // Ascending Order
            }
        }

        class MaxElementComparator implements Comparator<Integer> {
            public int compare(Integer int1, Integer int2){
                return int2.compareTo(int1);    // Descending Order
            }
        }

        public MedianFinderApproach2() {
            maxHeap = new PriorityQueue<>(new MaxElementComparator());
            minHeap = new PriorityQueue<>(new MinElementComparator());
        }
            // Even: [MaxHeap] 1 2 3   |  7 8 9 [MinHeap]
            // Odd:  [MaxHeap] 1 2 3 4 |  7 8 9 [MinHeap]
        public void addNum(int num) {
            int maxHeapMaxValue=0;
            if (maxHeap.size()>0)
                maxHeapMaxValue= maxHeap.peek();

            int minHeapMinValue=0;
            if (minHeap.size()>0)
                minHeapMinValue = minHeap.peek();

            if ( (num < maxHeapMaxValue) || (num >= maxHeapMaxValue && num < minHeapMinValue) ){
                maxHeap.add(num);
            }else if (num >= minHeapMinValue){
                minHeap.add(num);
            }

            int maxHeapSize = maxHeap.size();
            int minHeapSize = minHeap.size();

            if (maxHeapSize > minHeapSize+1){
                minHeap.add(maxHeap.poll());
            }

            if (minHeapSize > maxHeapSize){
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            int maxHeapSize = maxHeap.size();
            int minHeapSize = minHeap.size();

            if (maxHeapSize == minHeapSize){
                int num1 = maxHeap.peek();
                int num2 = minHeap.peek();
                double sum = num1 + num2;
                System.out.println("Find - Even: "+ " "+sum/2);
                return sum/2;
            }else { //if (maxHeapSize == minHeapSize+1){
                int num1 = maxHeap.peek();
                System.out.println("Find - Odd: "+ " "+num1);
                return num1;
            }
        }
    }

    public static void main(String[] args) {
        MedianFinderApproach1 medianFinder = new MedianFinderApproach1();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }


    /*
    public static void main(String[] args) {
        //Your MedianFinder object will be instantiated and called as such:
        MedianFinderApproach1 obj = new MedianFinderApproach1();
        obj.addNum(1);    // arr = [1]
        obj.addNum(2);    // arr = [1, 2]
        obj.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
        obj.addNum(3);    // arr[1, 2, 3]
        obj.findMedian(); // return 2.0
    }
    */
}
