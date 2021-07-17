package ds.patterns.twopointers;

import java.util.Arrays;

class PairSum {

    // arr[] = {3, 5, 9, 2, 8, 10, 11}; target = 17;
    //          ^  ^
    private boolean isPairSum_naive(int arr[], int target) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)            // as equal i and j means same element
                    continue;
                int sum = arr[i] + arr[j];
                if (sum == target) // pair exists
                    return true;
                if (sum > target)  // as the array is sorted
                    break;
            }
        }
        return false; // No pair found with given sum.
    }

    // Two pointer technique based solution to find if there is a pair in arr[0...n-1] with a given sum.
    // arr[] = {3, 5, 9, 2, 8, 10, 11}; target = 17 [sorted arr]
    //          ^                   ^
    public boolean isPairSum_twoPointers(int arr[], int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int i = 0;          // represents first pointer
        int j = n-1;        // represents second pointer
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == target) { // If we find a pair
                return true;
            }else if (sum < target) { // If sum of elements at current pointers is less, we move towards higher values by doing i++
                i++;
            }else if (sum > target){ // If sum of elements at current pointers is more, we move towards lower values by doing j--
                j--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PairSum obj = new PairSum();

        int arr[] = {3, 5, 9, 2, 8, 10, 11};
        int target = 17;
        System.out.println(obj.isPairSum_naive(arr, target));
        System.out.println(obj.isPairSum_twoPointers(arr, target));
    }
}