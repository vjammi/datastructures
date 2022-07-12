package ds.patterns.binarysearch;

/**
    81. Search in Rotated Sorted Array II
    There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
    Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that
    the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
    For example,
        [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
    Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
    You must decrease the overall operation steps as much as possible.

    Input: nums = [2,5,6,0,0,1,2], target = 0
    Output: true

    Input: nums = [2,5,6,0,0,1,2], target = 3
    Output: false

    Constraints:
        1 <= nums.length <= 5000
        -104 <= nums[i] <= 104
        nums is guaranteed to be rotated at some pivot.
        -104 <= target <= 104

    Follow up: This problem is similar to Search in Rotated Sorted Array, but nums may contain duplicates.
               Would this affect the runtime complexity? How and why?
    */

public class SearchInRotatedSortedArrayII {
    /**
    Solution: This is similar to the problem 33 [Search in Rotated Sorted Array is normal cases] the same solution works for this one too
             nums [2,5,6,0,0,1,2] target = 0

        However in a scenario where, (nums[mid] != target) && (nums[low] == nums[mid] && nums[high] == nums[mid]) we are
        not able to reduce the search space by half. say for example
             [1,0,1,1,1], target = 0
                    or
            [1, 1, 1, 1, 1, 1, 1], target = 2

        In worst case, this might turn into a linear search

             if (nums[mid] == target){
                // ...
             }else if(nums[low] == nums[mid] && nums[high] == nums[mid]) { // Additional logic to handle duplicates
                return search(nums, target, low+1, high-1);
             }else if (nums[low] <= nums[mid]){                             // left side is sorted
                // ...
             }else {                                                        //right side is sorted
                // ...
             }

    Complexity Analysis
         Time complexity : O(N) worst case, O(logN) best case, where NN is the length of the input array.

        Worst case: This happens when all the elements are the same and we search for some different element.
         At each step, we will only be able to reduce the search space by 1 since arr[mid] equals arr[start] and
         it's not possible to decide the relative position of target from arr[mid]. Example: [1, 1, 1, 1, 1, 1, 1], target = 2.

         Best case: This happens when all the elements are distinct. At each step, we will be able to divide our search space
         into half just like a normal binary search.
         This also answers the following follow-up question:

     Would this (having duplicate elements) affect the run-time complexity? How and why?
         As we can see, by having duplicate elements in the array, we often miss the opportunity to apply binary search in certain search spaces.
         Hence, we get O(N) worst case (with duplicates) vs O(logN) best case complexity (without duplicates).

     Space complexity : O(1)
     */
    public boolean search(int[] nums, int target) {
        return search(nums,target,0, nums.length-1);
    }

    public boolean search(int[] nums, int target, int low, int high){
        if (low > high)
            return false;

        int mid = low + (high - low)/2;

        if (nums[mid] == target){
            return true;

        /**
            //    3 3 3 4 5 3 3 3 3 3
            //    0 1 2 3 4 5 6 7 8 9
            //    ^         ^       ^
        */
        }else if(nums[low] == nums[mid] && nums[high] == nums[mid]) { // Additional logic to handle duplicates
            return search(nums, target, low+1, high-1);

        }else if (nums[low] <= nums[mid]){ // left side is sorted -- nums[low] <= nums[mid
            if (target >= nums[low] && target < nums[mid]){
                return search(nums,target,low, mid-1);
            }else{
                return search(nums,target,mid+1, high);
            }

        }else { //if right side is sorted -- nums[high] >= nums[mid]
            if (target > nums[mid] && target <= nums[high]){
                return search(nums,target,mid+1, high);
            }else{
                return search(nums,target,low, mid-1);
            }
        }
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArrayII obj = new SearchInRotatedSortedArrayII();
        System.out.println(obj.search(new int[]{4,5,6,7,0,1,2},0));
    }

}
