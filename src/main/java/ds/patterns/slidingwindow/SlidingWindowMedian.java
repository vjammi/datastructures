package ds.patterns.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
     480. Sliding Window Median
     The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.

     For examples, if arr = [2,3,4], the median is 3.
     For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
     You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

     Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
     Example 1:
     Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
     Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
     Explanation:
     Window position                Median
     ---------------                -----
     [1  3  -1] -3  5  3  6  7        1
     1 [3  -1  -3] 5  3  6  7       -1
     1  3 [-1  -3  5] 3  6  7       -1
     1  3  -1 [-3  5  3] 6  7        3
     1  3  -1  -3 [5  3  6] 7        5
     1  3  -1  -3  5 [3  6  7]       6
 */

public class SlidingWindowMedian {
    int[] slidingArray;
    int[] sortedSlidingArray;

    public double[] medianSlidingWindow(int[] nums, int windowSize) {

        this.slidingArray = new int[windowSize];
        this.sortedSlidingArray = new int[windowSize];
        List<Double> result = new ArrayList<>();

        int j =0;
        int slidingArrayCounter=0;
        for (int i=0; i< nums.length; i++){
            slidingArray[slidingArrayCounter++] = nums[i];
            while(slidingArrayCounter == windowSize){
                int[] sortedSlidingArray = Arrays.copyOf(slidingArray, windowSize);     // slidingArray
                //int[] sortedSlidingArray = Arrays.copyOfRange(nums, j, i+1);          // if we do not want to maintain a slidingArray
                result.add(calculateMedian(sortedSlidingArray, windowSize));
                removeOldestAndShiftElementsLeft();
                slidingArrayCounter--;
                j++;
            }
        }

        double[] resultArr = new double[result.size()];
        for (int i=0; i< result.size(); i++){
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }

    public double[] medianSlidingWindowOption2(int[] nums, int windowSize) {

        this.slidingArray = new int[windowSize];
        this.sortedSlidingArray = new int[windowSize];
        ArrayList<Double> result = new ArrayList<>();

        int j =0;
        int slidingArrayCounter=0;
        for (int i=0; i< nums.length; i++){
            slidingArrayCounter++;
            while(slidingArrayCounter == windowSize){
                int[] sortedSlidingArray = Arrays.copyOfRange(nums, j, i+1);          // if we do not want to maintain a slidingArray
                result.add(calculateMedian(sortedSlidingArray, windowSize));
                slidingArrayCounter--;
                j++;
            }
        }

        double[] resultArr = new double[result.size()];
        for (int i=0; i< result.size(); i++){
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }

    private double calculateMedian(int[] sortedSlidingArray, int windowSize){
        Arrays.sort(sortedSlidingArray);
        //show(sortedSlidingArray);
        double median = 0.0;
        if (windowSize%2 == 0){ // even
            int index = windowSize/2;
            Long sum = Long.valueOf(sortedSlidingArray[index-1]) + Long.valueOf(sortedSlidingArray[index]);
            median   = Double.valueOf(sum)/2;
            //System.out.println(">even - index-1 " +(index-1) +" index: "+index +" median "+median +" sum"+sum);
        }else{
            int index = windowSize/2;
            median = Double.valueOf(sortedSlidingArray[index]);
            //System.out.println("odd - index" +index +" median "+median);
        }
        return median;
    }

    private void show(int[] arr){
        for (int i=0; i< arr.length; i++)
            System.out.print(arr[i] +" ");
        System.out.println();
    }

    private void removeOldestAndShiftElementsLeft(){
        slidingArray[0] = 0;

        // shift left one position
        for (int i=1; i<slidingArray.length; i++){
            slidingArray[i-1] = slidingArray[i]; // shift left by one position
        }
    }

    public static void main(String[] args) {

    }

}