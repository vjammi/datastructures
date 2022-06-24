package ds.patterns.binarysearch;

/**
    33. Search in Rotated Sorted Array
    There is an integer array nums sorted in ascending order (with distinct values).
    Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
    Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
    You must write an algorithm with O(log n) runtime complexity.

    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4

    Input: nums = [4,5,6,7,0,1,2], target = 3
    Output: -1

    Input: nums = [1], target = 0
    Output: -1
*/
public class SearchInRotatedSortedArray {

    /**
        Input: nums = [4,5,6,7,0,1,2], target = 0
        Output: 4

        i       0   1   2   3   4   5   6   7    8    9  10  11
        nums = [33, 44, 55, 66, 76, 86, 96, 106, 1,   5, 11, 22]
                                    ^                         ^
                                                 ^
                                                      ^
        low     high    mid
        0       11      5       0+(11-0)/2
        6       11      8       6+(11-6)/2
        8       11      9       8+(11-8)/2
    */

    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length-1);
    }

    public int search(int[] nums, int target, int low, int high) {
        if (low > high)
            return -1;

        //int mid = low + (high-low)/2;
        int mid = (low + high) >>> 1;

        if (nums[mid] == target){
            return mid;

        }else if (nums[low] <= nums[mid]){ // should the left half be sorted

            // if the target is within - left sorted half go left, else go right
            if (target >= nums[low] && target < nums[mid])
                return search(nums, target, low, mid-1);
            else
                return search(nums, target, mid+1, high);

        }else { // should the right half be sorted if(nums[mid] <= nums[high])

            // if the target is within - right sorted half, go right else go left
            if (target > nums[mid] && target <= nums[high])
                return search(nums, target, mid+1, high);
            else
                return search(nums, target, low, mid-1);
        }

    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray obj = new SearchInRotatedSortedArray();
        System.out.println(obj.search(new int[]{4,5,6,7,0,1,2},0));
    }
}
