package dev.vjammi.ds.v2.binarysearch.arc;

public class SearchInARotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums.length < 3 ){
            if (nums.length == 1){
                if (nums[0] == target)
                    return 0;
                else
                    return -1;
            }
            if (nums.length == 2){
                if (nums[0] == target)
                    return 0;
                else if (nums[1] == target)
                    return 1;
                else
                    return -1;
            }
        }

        if (nums[0] < nums[nums.length-1]){
            return binarySearch(nums, target, 0,nums.length-1);
        }else{
            int pivotIndex = search(nums, 0, nums.length-1) ;
            System.out.println("pivotIndex: " +pivotIndex);

            if (target >= nums[0] && target <= nums[pivotIndex-1])
                return binarySearch(nums, target, 0, pivotIndex-1);
            else if (target >= nums[pivotIndex+1] && target <= nums[nums.length-1])
                return binarySearch(nums,target, pivotIndex+1, nums.length-1);
            else if (target == nums[pivotIndex])
                return pivotIndex;
            else
                return -1;
        }
    }

    //         0  1  2  3  4  5  6   7  8  9
    // nums = [4, 5, 6, 7, 8, 9, 10, 0, 1, 2], target = 0
    //                     ^
    //                           ^
    //
    //
    //
    //
    public int search(int[] nums, int low, int high) {
        if (low > high)
            return -1;

        int mid = low + (high -low) / 2;

        if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1])
            return mid;
        else if (nums[mid] > nums[mid-1])
            return search(nums, mid+1, high);
        else
            return search(nums, low, mid-1);
    }
    public int search2(int[] nums, int low, int high) {
        if (nums[low] < nums[high])
            return 0;

        while (low <= high) {
            int pivot = (low + high) / 2;
            if (nums[pivot] > nums[pivot + 1])
                return pivot + 1;
            else {
                if (nums[pivot] < nums[low])
                    high = pivot - 1;
                else
                    low = pivot + 1;
            }
        }
        return 0;
    }

    private int binarySearch(int[] arr, int key, int low, int high) {
        if (low > high)
            return -1;

        int mid = low + (high-low) / 2;
        if (key == arr[mid]) {
            return mid;
        }else if(key < arr[mid]) {
            high = mid - 1;
            return binarySearch(arr, key, low, high);
        } else { // if (key > arr[mid])
            low = mid + 1;
            return binarySearch(arr, key, low, high);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 8, 9, 10, 0, 1, 2};


    }

}
