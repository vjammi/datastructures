## Sliding Window

### Overview
Used within an iterable / sequential data structures such as an array or String.
Problems can be solved naively using quadratic time complexity, but sliding window reduces the time complexity to linear time.
You have a window over a section of an array and you slide the window from start to the end. As you do that, you change the size of the window.

### Type of Problems
1. Minimum Size SubArray Sum
2. Longest SubArray without Repeating Characters
3. Find All Anagrams in a String

### Sliding Window Technique - Details
This technique shows how a nested for loop in some problems can be converted to a single for loop to reduce the time complexity.
We can take a problem to illustrate the technique
Given an array of integers of size n, calculate the max sum of k consecutive elements in the array.
```
Input  : arr[] = {100, 200, 300, 400}
         k = 2
Output : 700
```
Notice how we get maximum sum by adding sub-array {300, 400} of size 2.
```
Input  : arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20}
         k = 4
Output : 39
```
Notice how we get maximum sum by adding sub-array {4, 2, 10, 23} of size 4.

### Solution using nested loop - O(n*k)
```
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
```

### Solution using Sliding Window - O(n)
The time complexity of this solution is O(n) because each element is visited at most twice.
In the worst case scenario, all elements will be visited once by the start pointer and another time by the end pointer.
The space complexity would be O(1) because the solution does not create new data structures.
```
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
```
### Sliding Window Vs Two Pointers
In sliding window typically we use all of the elements within the window for the problem (for eg - sum of all elements in the window).
In a two pointer technique we compare the value at the both pointers instead of taking the elements between the pointers.
Also, there is a variation of two pointers called the fast and slow and pointer.

## 480 Sliding Window Median
The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
    For examples, if arr = [2,3,4], the median is 3.
    For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
Explanation:
```
     Window position                Median
     ---------------                -----
     [1  3  -1] -3  5  3  6  7        1
     1 [3  -1  -3] 5  3  6  7       -1
     1  3 [-1  -3  5] 3  6  7       -1
     1  3  -1 [-3  5  3] 6  7        3
     1  3  -1  -3 [5  3  6] 7        5
     1  3  -1  -3  5 [3  6  7]       6
```
Solution
```
public class SlidingWindowMedian {
    int[] slidingArray;
    int[] sortedSlidingArray;

    public double[] medianSlidingWindow(int[] nums, int windowSize) {

        this.slidingArray = new int[windowSize];
        this.sortedSlidingArray = new int[windowSize];
        List<Double> result = new ArrayList<>();

        int j =0;
        int slidingArrayCounter=0;
        for (int i=0; i< nums.length; i++){
            slidingArray[slidingArrayCounter++] = nums[i];
            while(slidingArrayCounter == windowSize){
                int[] sortedSlidingArray = Arrays.copyOf(slidingArray, windowSize);     // slidingArray
                //int[] sortedSlidingArray = Arrays.copyOfRange(nums, j, i+1);          // if we do not want to maintain a slidingArray
                result.add(calculateMedian(sortedSlidingArray, windowSize));
                removeOldestAndShiftElementsLeft();
                slidingArrayCounter--;
                j++;
            }
        }

        double[] resultArr = new double[result.size()];
        for (int i=0; i< result.size(); i++){
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }

    public double[] medianSlidingWindowOption2(int[] nums, int windowSize) {

        this.slidingArray = new int[windowSize];
        this.sortedSlidingArray = new int[windowSize];
        ArrayList<Double> result = new ArrayList<>();

        int j =0;
        int slidingArrayCounter=0;
        for (int i=0; i< nums.length; i++){
            slidingArrayCounter++;
            while(slidingArrayCounter == windowSize){
                int[] sortedSlidingArray = Arrays.copyOfRange(nums, j, i+1);          // if we do not want to maintain a slidingArray
                result.add(calculateMedian(sortedSlidingArray, windowSize));
                slidingArrayCounter--;
                j++;
            }
        }

        double[] resultArr = new double[result.size()];
        for (int i=0; i< result.size(); i++){
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }

    private double calculateMedian(int[] sortedSlidingArray, int windowSize){
        Arrays.sort(sortedSlidingArray);
        //show(sortedSlidingArray);
        double median = 0.0;
        if (windowSize%2 == 0){ // even
            int index = windowSize/2;
            Long sum = Long.valueOf(sortedSlidingArray[index-1]) + Long.valueOf(sortedSlidingArray[index]);
            median   = Double.valueOf(sum)/2;
            //System.out.println(">even - index-1 " +(index-1) +" index: "+index +" median "+median +" sum"+sum);
        }else{
            int index = windowSize/2;
            median = Double.valueOf(sortedSlidingArray[index]);
            //System.out.println("odd - index" +index +" median "+median);
        }
        return median;
    }

    private void show(int[] arr){
        for (int i=0; i< arr.length; i++)
            System.out.print(arr[i] +" ");
        System.out.println();
    }

    private void removeOldestAndShiftElementsLeft(){
        slidingArray[0] = 0;

        // shift left one position
        for (int i=1; i<slidingArray.length; i++){
            slidingArray[i-1] = slidingArray[i]; // shift left by one position
        }
    }

```
## 23 Merge k Sorted Lists
 You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 Merge all the linked-lists into one sorted linked-list and return it.
 Example 1:
 Input: lists = [[1,4,5],[1,3,4],[2,6]]
 Output: [1,1,2,3,4,4,5,6]
 Explanation: The linked-lists are:
             [1->4->5,
             1->3->4,
             2->6]
 merging them into one sorted list:
            1->1->2->3->4->4->5->6

```
public class MergeKSortedLists {

    class MinComparator implements Comparator<ListNode>{
        public int compare(ListNode node1, ListNode node2){
            Integer val1 = node1.val;
            Integer val2 = node2.val;
            return val1.compareTo(val2); // Ascending
        }
    }

    class MinIntegerComparator implements Comparator<Integer>{
        public int compare(Integer val1, Integer val2){
            return val2.compareTo(val1); // Ascending
        }
    }

    //         1->4->5>null
    // current          ^
    // next             ^
    // pq      1  4

    //         1->3->4>null
    // current          ^
    // next             ^
    // pq      1  4  1   3  4

    //         2->6>null
    // current       ^
    // next          ^
    // pq      1  4  1  3  4  2  6
    //         1 > 1 > 2 > 3 > 4 > 4 > 5 > 6
    Queue<ListNode> pq;
    public ListNode mergeKLists(ListNode[] lists) {
        pq = new PriorityQueue<ListNode>(new MinComparator());

        for (int i=0;i<lists.length; i++){
            ListNode current = lists[i];
            while(current!=null){
                ListNode next = current.next;  // Next is safe
                current.next = null;           // Free up the current node
                pq.add(current);               // Adding listNode to pq instead of the copy - pq.add(new ListNode(current.val));
                current = next;
            }
            // current == null
        }

        // int i=0;
        // pq      1  4  1  3  4  2  6
        // pq      1  1  2  3  4  4  6
        // node                      ^
        // head    1> 1> 2> 3> 4> 4> 6 > null
        // head    ^
        // tail                      ^
        // i=1
        ListNode tail = null;
        ListNode head = null;
        int i = 0;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            System.out.print(node.val +" > ");
            if (i == 0) {
                head = node;
                tail = node;
                node.next = null;
            } else {
                tail.next = node;
                tail = node;
                node.next = null;
            }
            i++;
        }
        return head;
    }
}
```
References:
https://zengruiwang.medium.com/sliding-window-technique-360d840d5740
https://www.geeksforgeeks.org/two-pointers-technique/
https://stackoverflow.com/questions/64078162/is-two-pointer-problem-same-as-sliding-window