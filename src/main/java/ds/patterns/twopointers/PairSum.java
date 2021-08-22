package ds.patterns.twopointers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PairSum {

    /**
                  0  1  2  3  4  5   6
         arr[] = {3, 5, 9, 2, 8, 10, 11} target = 17
         arr[] = {2, 3, 5, 8, 9, 10, 11}
         i =                         ^
         j =                     ^
     */
    private boolean isPairSumNaive1(int[] arr, int target) {
        Arrays.sort(arr);

        int length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i != j) {               // We are looking for distinct pairs, not pair an element by itself. So we will skip if i == j
                    int sum = arr[i] + arr[j];
                    if (sum == target)      // pair found
                        return true;
                    else if (sum > target)  // Since the array is sorted, we stop further iterating the jth index and start the next ith index
                        break;
                    else
                       continue;            // Continue looking for the pair
                }
            }
        }
        return false;                       // No pair found with given sum.
    }

    /**
     *            0  1  2  3  4  5   6  7
         arr[] = {3, 5, 9, 2, 8, 10, 11} target = 17
         arr[] = {2, 3, 5, 8, 9, 10, 11}
             i =                     ^
             j =                        ^
    */
    private boolean isPairSumNaive2(int[] arr, int target) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            // Note: When i=6, j=6+1=7, which is beyond the length of the array - so will never enter the inner for loop for j=7
            for (int j = i+1; j < arr.length; j++) { // Optimization: j=i+1
                int sum = arr[i] + arr[j];
                if (sum == target)      // pair found
                    return true;
                else if (sum > target)  // Optimization: Since the array is sorted, we break for that ith iteration and start the next ith iteration.
                    break;
                // else continue;       // Continue looking for the pair
            }
        }
        return false; // No pair found
    }

    /**
     Two pointer technique based solution to find if there is a pair in arr[0...n-1] with a given sum.
     arr[] = {3, 5, 9, 2, 8, 10, 11} target = 17
     arr[] = {2, 3, 5, 8, 9, 10, 11}
     i =  ^
     j =           ^
     */
    public boolean isPairSumUsingTwoPointers(int[] arr, int target) {
        Arrays.sort(arr);
        int i = 0; int j = arr.length-1;
        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == target) { // If we find a pair
                return true;
            }else if (sum < target) { // Optimization: If sum is less than the target, we move towards higher values (i++)
                i++;
            }else if (sum > target){ // Optimization: If sum is grater than the target, we move towards lower values (j--)
                j--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PairSum obj = new PairSum();

        int arr[] = {3, 5, 9, 2, 8, 10, 11}; // unsorted
        int target = 25; //17;

        System.out.println(obj.isPairSumNaive1(arr, target));
        System.out.println(obj.isPairSumNaive2(arr, target));
        System.out.println(obj.isPairSumUsingTwoPointers(arr, target));
    }
}