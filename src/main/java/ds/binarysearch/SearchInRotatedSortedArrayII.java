package ds.binarysearch;

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
// 274 / 279 test cases passed
public class SearchInRotatedSortedArrayII {


    /**
     0 1 2 3 4 5 6 7 8
     Input: nums = [2,5,6,7,8,0,0,1,2], target = 0 Output: true
     ^   ^
     1 2 3 4 5
     [1,0,1,1,1]  target=0
     ^

     */
    public boolean search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        search(nums,target,low, high);
        return result;
    }


    boolean result = false;
    public void search(int[] nums, int target, int low, int high){
        if (low > high)
            return;

        int mid = low + (high - low)/2;

        if (nums[mid] == target){
            result = true;
            return;
        }else if (nums[low] <= nums[mid]){ // left side is sorted -- nums[low] <= nums[mid
            if (target >= nums[low] && target < nums[mid]){
                search(nums,target,low, mid-1);
            }else{
                search(nums,target,mid+1, high);
            }

        }else { //if right side is sorted -- nums[high] >= nums[mid]
            if (target > nums[mid] && target <= nums[high]){
                search(nums,target,mid+1, high);
            }else{
                search(nums,target,low, mid-1);
            }
        }
    }

}
