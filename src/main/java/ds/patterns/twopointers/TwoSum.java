package ds.patterns.twopointers;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/two-sum/solution/
// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/solution/
public class TwoSum {

    /**
    Complexity Analysis
    Time complexity: O(n^2). For each element, we try to find its complement by looping through the rest of the array which takes O(n) time.
                             Therefore, the time complexity is O(n^2).
    Space complexity: O(1).  The space required does not depend on the size of the input array, so only constant space is used.
     [2,7,11,15] target=9
     ^
    */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            // Note: for i=last element, j=last+1, which is beyond the length of the array - so will never enter the inner for loop for j=last+1
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        return null; // In case there is no solution, we'll just return null
    }


    /**
     Complexity Analysis
     Time complexity: O(n)  We traverse the list containing n elements only once.
                            Since the hash table reduces the lookup time to O(1) [Each lookup in the table costs only O(1) time],
                            the overall time complexity is O(n).

     Space complexity: O(n) The extra space required depends on the number of items stored in the hash table,
                            which stores at most nn elements.

        [2,7,11,15] target=9
        ^

     Follow-Up
     What if the problem constraints were different and we needed to consider integer overflow when adding numbers[low]numbers[low]numbers[low] and numbers[high]numbers[high]numbers[high]? In that case, to prevent an overflow error, we could cast our numbers from int data type to long data type before adding them together, e.g.: long sum = static_cast<long>(numbers[low]) + numbers[high] for C++. Casting ensures that we will not get the overflow error since the signed long data type supports numbers up to 2^63 - 1. Alternatively, if we cannot use long integers, then we can check if numbers[low] > (1 << 31) - 1 - numbers[high] at the beginning of each iteration. If this condition is true, then numbers[low] + numbers[high] will result in integer overflow, and so we would move the larger index to the left.

     Complexity Analysis
     Time complexity: O(n). The input array is traversed at most once. Thus the time complexity is O(n).
     Space complexity: O(1). We only use additional space to store two indices and the sum, so the space complexity is O(1).

     */
    public int[] isPairSumUsingHashtable(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(arr[i], i);
        }
        return null; // In case there is no solution, we'll just return null
    }

}
