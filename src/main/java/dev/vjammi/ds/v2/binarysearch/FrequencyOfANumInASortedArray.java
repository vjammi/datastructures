package dev.vjammi.ds.v2.binarysearch;


/**
 *  Given an array of integers nums sorted in ascending order, find the frequency of a given target value
 *
 *          int[] nums = new int[]{1, 2, 2, 2, 2, 2, 3, 4, 5, 6, 7, 8};
 *                                 0  1  2  3  4  5  6  7  8  9  10 11
 *         int target = 2;
 *
 *  1. Go from left to right counting the occurrences of the num
 *
 *  2. Two Pointer O(logn): Go from left to right, right to left. find the left and right most occurrence of the number.
 *     The frequency would be (right-left +1)
 *
 *  3. Binary Search O(log(n)): find rightMostIndex, find leftMostIndex, return the difference + 1.
 *      Find the leftMostIndex:
 *          Using binary search find the target value in the array
 *          Once you find the first occurrence of the num, save it as the leftmost index
 *          Reduce the search range by going further left of mid to find the next occurrence of it.
 *          Repeat until low > high
 *      Find the rightMostIndex index:
 *          Using binary search find the target value in the array
 *          Once you find the first occurrence of the num, save it as the rightmost index
 *          Reduce the search range by going further right of mid to find the next occurrence of it.
 *          Repeat until low > high
 *      Return
 *          rightMostIndex - leftMostIndex + 1
 **/

public class FrequencyOfANumInASortedArray {

    int leftMostIndex;
    int rightMostIndex;
    private int findFrequencyOfANumInASortedArray(int[] nums, int targetVal) {
         int low = 0;
         int high =  nums.length;
         findLeftMostIndex(nums, targetVal, low, high);
         findRightMostIndex(nums, targetVal, low, high);
         System.out.println("leftMostIndex: "+leftMostIndex +" rightMostIndex: " +rightMostIndex);
         return rightMostIndex - leftMostIndex +1;
    }

    private void findLeftMostIndex(int[] nums, int targetVal, int low, int high) {
        if (low > high)
            return;

        int mid = low + (high-low)/2;

        if (nums[mid] == targetVal){
            leftMostIndex = mid;
            findLeftMostIndex(nums, targetVal, low, mid-1);
        }else if (targetVal < nums[mid]){
            findLeftMostIndex(nums, targetVal, low, mid-1);
        }else if (targetVal > nums[mid]){
            findLeftMostIndex(nums, targetVal, mid+1, high);
        }
    }

    private void findRightMostIndex(int[] nums, int targetVal, int low, int high) {
        if (low > high)
            return;

        int mid = low + (high-low)/2;
        if (nums[mid] == targetVal){
            rightMostIndex = mid;
            findRightMostIndex(nums, targetVal, mid+1, high);
        }else if (targetVal < nums[mid]){
            findRightMostIndex(nums, targetVal, low, mid-1);
        }else if (targetVal > nums[mid]){
            findRightMostIndex(nums, targetVal, mid+1, high);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,2,2,2,3,4,5,6,7,8};
        int target = 2;
        FrequencyOfANumInASortedArray obj = new FrequencyOfANumInASortedArray();
        obj.findFrequencyOfANumInASortedArray(nums, target);
    }
}
