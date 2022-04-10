package ds.patterns.twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PairSum {

    /**
         0  1  2  3  4  5   6  7
         arr[] = {3, 5, 9, 2, 8, 10, 11} target  = 8  // unsorted
         arr[] = {2, 3, 5, 8, 9, 10, 11}  target = 8  // sorted
             i =                 ^
             j =                     ^

        Runtime: O(n^2)
     */
    private boolean isPairSumNaive1(int[] arr, int target) {
        int length = arr.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = i+1; j < length; j++) {  // We are looking for distinct pairs, not pair an element by itself. So i cannot be j to form the pair
                int sum = arr[i] + arr[j];
                if (sum == target) {     // pair found
                    System.out.println(i +" " +j);
                    return true;
                }
            }
        }
        return false;          // No pair found with given sum.
    }

    /**
                  0  1  2  3  4  5   6  7
         arr[] = {3, 5, 9, 2, 8, 10, 11} target  = 8  // unsorted
         arr[] = {2, 3, 5, 8, 9, 10, 11}  target = 8  // sorted
             i =                 ^
             j =                     ^

        Runtime: O(n * n/2) = O(n^2)
    */
    private boolean isPairSumNaive2(int[] arr, int target) {
        // iterate i=0 to n-1, j=i+1 to n
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) { // Optimization: j=i+1
                int sum = arr[i] + arr[j];
                if (sum == target)      // pair found
                    return true;

                if (sum > target) // Optimization: Since the array is sorted, we break iterating the inner loop and start at the next ith pointer
                    break;
                else
                    continue; // continue iterating  the inner loop looking for the pair.
            }
        }
        return false; // No pair found
    }

    /**
         Two pointer technique based solution to find if there is a pair in arr[0...n-1] with a given sum.
         arr[] = {3, 5, 9, 2, 8, 10, 11} target  = 8  // unsorted
         arr[] = {2, 3, 5, 8, 9, 10, 11}  target = 8  // sorted
                  -> ^  ^ <------------
                     i  j

         Runtime = O(n)
     */
    public boolean isPairSumUsingTwoPointers(int[] arr, int target) {
        Arrays.sort(arr);
        int i = 0; int j = arr.length-1;
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == target) { // If we find a pair
                return true;
            }else if (sum < target) { // If sum is less than the target, we move towards higher values (i++)
                i++;
            }else if (sum > target){ // If sum is greater than the target, we move towards lower values (j--)
                j--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PairSum obj = new PairSum();

        int arr[] = {3, 5, 9, 2, 8, 10, 11}; // unsorted
        int target = 17; // 25; //17;

        System.out.println(obj.isPairSumNaive1(arr, target));
        System.out.println(obj.isPairSumNaive2(arr, target));

        System.out.println(obj.isPairSumUsingTwoPointers(arr, target));
    }
}