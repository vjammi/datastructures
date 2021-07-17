package ds.patterns.slidingwindow;

public class MaxSumInSubArray {

    // Return maximum sum in a sub-array of size k.
    //    int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20}, n = 9, k = 4
    //                 0  1  2  3   4  5  6  7  8
    //                                 ^
    //                                 ^
    public int maxSumNestedLoop1(int arr[], int n, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n-k+1; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++) {
                sum = sum + arr[i+j];
            }
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    // Runtime O(n*k)
    public int maxSumNestedLoop(int arr[], int n, int k) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n-k+1; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum = sum + arr[j];
            }
            maxSum = Math.max(sum, maxSum);
            System.out.println("MaxSum:" +maxSum +" @ Window i: " +i +" j:" +(i+k));
        }
        return maxSum;
    }

    // Return maximum sum in a sub-array of size k.
    //    int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20}, n = 9, k = 4
    //                 0  1  2  3   4  5  6  7  8
    //                              ^
    //                    ^

    // Runtime O(n)
    private int maxSum = 0;
    public int maxSumSlidingWindow(int arr[], int n, int k) {
        if (n<k)
            System.out.println("invalid input");

        int sum = 0;
        int windowSize = 0;
        int j = 0;
        for (int i=0; i<n; i++){
            sum = sum + arr[i];
            windowSize++;

            while(windowSize >= k){
                maxSum = Math.max(sum, maxSum);
                System.out.println("MaxSum:" +maxSum +" @ Window j: " +j +" i:" +i);

                sum = sum - arr[j];
                windowSize--;
                j++;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSumInSubArray obj = new MaxSumInSubArray();

        int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20}; int k = 4; int n = arr.length;
        System.out.println(obj.maxSumNestedLoop1(arr, n, k));
        System.out.println(obj.maxSumNestedLoop(arr, n, k));
        System.out.println(obj.maxSumSlidingWindow(arr, n, k));
    }

}