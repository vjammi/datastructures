package dev.vjammi.ds.v2.binarysearch;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:
 *      [4,5,6,7,0,1,2] if it was rotated 4 times.
 *      [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * Example 3:
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 *
 **/
public class SearchMinimumInRotatedSortedArray {

    //      [5,6,7,8,1,2,3,4]
    //       0 1 2 3 4 5 6 7
    //       ^             ^

    int min = Integer.MAX_VALUE;
    public int findMin(int[] nums) {
        findMin(nums,0,nums.length-1);
        return min;
    }

    void findMin(int [] arr, int low, int high){
        if(low>high)
            return;

        int mid = (low+high)/2;
        min = Integer.min(min, arr[mid]);

        if(arr[low] <= arr[mid]){
            min = Integer.min(min, arr[low]);
            findMin(arr,mid+1,high);
        }else if(arr[low] >= arr[mid]){
            min = Integer.min(min, arr[mid+1]);
            findMin(arr, low,mid-1);
        }
    }

    public int findMinIterative(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
        }
        return nums[left];
    }



}
