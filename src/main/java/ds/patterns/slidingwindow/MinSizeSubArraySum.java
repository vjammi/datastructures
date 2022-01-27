package ds.patterns.slidingwindow;

    /**
     209. Minimum Size Subarray Sum
         Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous
         subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no
         such subarray, return 0 instead.

         Input: nums = [2 3 1 2 4 3]
                        ^     ^
                        j     i
                            sum=8
         target = 7
         Output: 2
         Explanation: The subarray [4,3] has the minimal length under the problem constraint.

    Solution
        Brute Force
             Naive solution would be to use a nested for loop - o(N2)

        2 Pointers - Window size changes overtime
            Setup 2 pointers - start and end pointers.
            Move the start pointer until a window is found. Then adjust the window by moving the end pointer.
            then we move the start pointer to find next window
    */

public class MinSizeSubArraySum {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0)
            return  0;

        int minLength = Integer.MAX_VALUE;
        int sum=0;
        int end=0;
        for (int start=0;start<nums.length;start++){

            sum = sum + nums[start];
            if (sum >= target)
                minLength = Math.min(minLength, (start-end) + 1);

            while (sum >= target){
                minLength = Math.min(minLength, (start-end) + 1);
                System.out.println("Sum: " + sum +" minLength: " +minLength+ " end: " + end + " start: " + start);
                sum = sum - nums[end];
                end = end+1;
            }
        }

        return (minLength != Integer.MAX_VALUE) ? minLength : 0;
    }

    public static void main(String[] args) {
        MinSizeSubArraySum obj = new MinSizeSubArraySum();
        int target = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(obj.minSubArrayLen(target, nums));
    }

}

