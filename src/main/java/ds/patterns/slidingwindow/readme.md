## Sliding Window

### Overview
Used within an iterable / sequential data structures such as an array or String.
Problems can be solved naively using quadratic time complexity, but sliding window reduces the time complexity to linear time.
You have a window over a section of an array, you slide the window from start to the end. As you do that, you change the size of the window.

### Type of Problems
1. Minimum Size SubArray Sum
2. Longest SubArray without Repeating Characters
3. Longest Substring with At Most Two Distinct Characters
4. Longest Repeating Character
5. Find All Anagrams in a String

### Sliding Window Technique
This technique shows how a nested for loop in some problems can be converted to a single for loop to reduce the time complexity.
We can take a problem to illustrate the technique.
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

#### Return maximum sum in a sub-array of size k - Using nested loop - O((n-k)*k).  
```    
    //    int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20}, n = 9, k = 4
    //                 0  1  2  3   4  5  6  7  8
    //                                 |--------|
    //           i     ->              ^
    //           j     ->                       ^

    // Runtime O((n-k)*k)
    public int maxSumNestedLoop(int arr[], int n, int k) {
        int maxSum = Integer.MIN_VALUE;
        // O(n-k) - This loop runs for n-k iterations
        for (int i=0; i<n-k+1; i++) {
            int sum = 0;
            // O(k) - This loop of k iterations run n-k times.
            // Hence overall time complexity is O((n-k)*k)
            for (int j=i; j<i+k; j++) {
                sum = sum + arr[j];
            }
            maxSum = Math.max(sum, maxSum);
            System.out.println("MaxSum:" +maxSum +" @ Window i: " +i +" j:" +(i+k));
        }
        return maxSum;
    }
```
#### Solution using Sliding Window - O(n)

The time complexity of this solution is O(n) because each element is visited at most twice.
In the worst case scenario, all elements will be visited once by the start pointer and another time by the end pointer.
The space complexity would be O(1) because the solution does not create new data structures.
```
    // Return maximum sum in a sub-array of size k.
    //    int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20}, n = 9, k = 4
    //                 0  1  2  3   4  5  6  7  8
    //                                 |--------|
    //           i                              ^
    //           j                     ^
    // Runtime O(n)
    private int maxSum = 0;
    public int maxSumSlidingWindow(int arr[], int n, int k) {
        if (n<k)
            System.out.println("invalid input");

        int sum = 0;
        int windowSize = 0;
        int j = 0;
        // O(N) - This runs for only n iterations and in each iteration the window (while loop) is shifted by 1 index only.
        for (int i=0; i<n; i++){ // when i is n the for loop ends
            sum = sum + arr[i];
            windowSize++;

            // When window equals to k, we enter into the while [i.e. i=3 & j=0]
            while(windowSize >= k){
                maxSum = Math.max(sum, maxSum);
                sum = sum - arr[j];
                windowSize--;
                j++;
            }
            if (j > n-k+1)
                break;
        }
        return maxSum;
    }
```
## Sliding Window Vs Two Pointers
In sliding window typically we use all the elements within the window for the problem (for eg - sum of all elements in the window).
In a two pointer technique we compare the value at the both pointers instead of taking the elements between the pointers.
Compare the approach with SubarrayMaxSum and SubArrayMaxProduct  

## 209 Minimum Size Subarray Sum
 Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous
 subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no
 such subarray, return 0 instead.
```
    /**
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
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0)
            return  0;

        int minLength = Integer.MAX_VALUE;
        int sum=0;
        int end=0;
        for (int start=0; start<nums.length; start++){

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
```

## Longest SubArray without Repeating Characters
Given a string s, find the length of the longest substring without repeating characters.
```
    /**
         Input: s = "abcabcbb"
         Output: 3
         Explanation: The answer is "abc", with the length of 3.
        
         // Window size changes overtime
         Naive Approach - Nested for loops - O(N2) time
         s = abcad
             ^ ^
             s e
        
         Other problems
             Longest Substring with At Most Two Distinct Characters
             Longest Repeating Character

             Input: s = "abcabcbb"
                        "tmmzuxt"
                    i          ^
                    j      ^
            Output: 3
    
    */
    public int lengthOfLongestSubstringFirstAfterGap_PraiseTheLord(String s) {

        Set<Character> set = new HashSet<>();
        int longest=0;
        int j=0;
        for(int i=0; i<s.length(); i++){
            if (!set.contains(s.charAt(i))) {
                longest = Math.max(longest, (i-j)+1);
                set.add(s.charAt(i));
            }else{
                while( set.contains(s.charAt(i)) && j<=i && j<s.length() ){
                    set.remove(s.charAt(j));
                    j++;
                }
                longest = Math.max(longest, (i-j)+1);
                set.add(s.charAt(i));
            }
            System.out.println(j +" - " +i +" - "+longest);
        }
        return longest;
    }
    
    public int lengthOfLongestSubstring(String s) {

        int longest = 0;
        int longestSubStrLength = 0;
        int j=0;
        Set<Character> set = new HashSet<>();
        for (int i=0; i< s.length(); i++){

            Character c = s.charAt(i);

            if (!set.contains(c)){
                set.add(c);
                longestSubStrLength++; // 3
                longest = Math.max(longest, longestSubStrLength);
            }else{
                // Increment j until it reaches the duplicate char
                // In the meantime you will remove the chars you will encounter.
                while(s.charAt(j) != c) {
                    set.remove(s.charAt(j));
                    j++;
                    longestSubStrLength--;
                }

                // Once j is at char c, we remove from the set, increment j and decrement length
                set.remove(s.charAt(j));
                j++;
                longestSubStrLength--;

                // Now add new char to the set, increment longestSubStrLength and get max length
                set.add(c);
                longestSubStrLength++;
                longest = Math.max(longest, longestSubStrLength);
            }

        }

        return longest;
    }
```

## Find All Anagrams in a String
Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
```
    // Window size changes overtime
    // Create a static window of size 3 and move it across s
    //               0123456789
    //          s = cbaebabacd
    //          i=    ^
    //          j=  ^
    //          p = abc

    public List<Integer> findAnagrams(String s, String p) {

        int[] charSetForP = new int[26];
        for (int i=0; i<p.length(); i++){
            int ch = p.charAt(i);
            charSetForP[ch-97] = charSetForP[ch-97] + 1;
        }

        List<Integer> listOfIndices = new ArrayList<>();
        int anagramSize = p.length();
        int windowSize = 0;
        int[] windowChars = new int[26];
        int j=0;

        for (int i=0; i<s.length(); i++){
            int ch = s.charAt(i);
            windowChars[ch-97] = windowChars[ch-97] + 1;
            windowSize++;

            while(windowSize == anagramSize){
                if (isAnagram(windowChars, charSetForP))
                    listOfIndices.add(j);

                int charAtJ = s.charAt(j);
                windowChars[charAtJ-97] = windowChars[charAtJ-97] - 1;
                j++;
                windowSize--;
            }
        }
        return listOfIndices;
    }

    private boolean isAnagram(int[] windowChars, int[] charSetForP){
        for (int i=0; i< windowChars.length;  i++){
            if (windowChars[i] != charSetForP[i])
                return false;
        }
        return true;
    }
```

##  Longest Substring with At Most Two Distinct Characters
```

```

##  Longest Repeating Character
```

```

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



References:
https://medium.com/outco/how-to-solve-sliding-window-problems-28d67601a66
https://zengruiwang.medium.com/sliding-window-technique-360d840d5740
https://www.geeksforgeeks.org/two-pointers-technique/
https://stackoverflow.com/questions/64078162/is-two-pointer-problem-same-as-sliding-window