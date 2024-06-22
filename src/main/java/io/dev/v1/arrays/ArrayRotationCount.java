package io.dev.v1.arrays;

/**
 * Created by Vijay Jammi on 06/08/2018.
 */
public class ArrayRotationCount {


    private int findSortedArrayRotationCount(int[] a) {
        int low = 0;
        int high = a.length -1;

        while(low <= high) {

            int mid = (low + high) / 2;
            int prev = mid - 1;
            int next = mid + 1;

            // Segment already sorted
            // Element at lower index is >= element at the higher index
            // Return the lowest element
            if (a[low] <= a[high]) {
                return low;
            }

            // Pivot Property Check
            // Get the middle index and check if it is the pivot
            // Special property of pivot is previous and next element in the array are greater.
            // 18 and 5 are greater than 2. none other will have this prop
            if (a[mid] <= a[next] && a[mid] <= a[prev]) {
                return mid;
            }

            // If the right segment of the array is sorted then look into the left side of the array.
            // and if the left segment of the array is sorted, then look into its right.
            else if (a[mid] <= a[high]) {
                high = mid - 1;
            }else if (a[mid] >= a[low]) {
                low = mid + 1;
            }

        }
        return -1;
    }

    public static void main (String[] args){
        ArrayRotationCount arrayRotationCount = new ArrayRotationCount();
        int[] array = {11,12,15,18,19,2,5,6,8};
        System.out.println(arrayRotationCount.findSortedArrayRotationCount(array));
    }
}
