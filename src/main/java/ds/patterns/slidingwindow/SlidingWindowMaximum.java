package ds.patterns.slidingwindow;

public class SlidingWindowMaximum {

    /**
     *   nums = [1, 3,-1,-3, 5, 3, 6, 7]      k = 3
     *           ^     ^
     */
    public int[] maxSlidingWindowNaive(int[] nums, int k) {
        int resultArraySize = nums.length-(k-1);
        int resultCounter = 0;
        int[] result = new int[resultArraySize];
        int windowSize = 0;
        int end   = 0;
        for (int start=0; start<nums.length; start++){
            windowSize++;
            if (windowSize == k){
                int windowMax = nums[start];
                for (int j=start; j>=end; j--){
                    if (nums[j] > windowMax) windowMax = nums[j];
                }
                result[resultCounter++] = windowMax;
                windowSize--;
                end++;
            }
        }
        return result;
    }


    /**
     *  TODO: Approach 2 - Implement using a DoublyLinkedList or a DeQueue
     *      nums = [1, 3,-1,-3, 5, 3, 6, 7]      k = 3
     *              ^     ^
     **/
    public int[] maxSlidingWindow(int[] nums, int k) {
        return new int[]{1,2};
    }

}
