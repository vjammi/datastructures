package io.dev.v2.dp;

public class SumOfNNumbers {

    /**
        Problem:
            Find the sum of the first N numbers.
        Objective function:
            f(i) is the sum of the first i elements.
        Recurrence relation:
            f(n) = f(n-1) + n

         // dp[1] = dp[1-1] + 1
         // dp[2] = dp[2-1] + 2
         // dp[3] = dp[3-1] + 2
        we created an array of size n+1 and used it as a cache the sum of the previous sub problem
        We cached the intermediate results in an array
        Result in the last element
        for n = 5 we will create an array of 6 elements
            [0, 1, 3, 6, 10, 15]
        In Dynamic programming, we do not want to re-calculte things
            for n = 5 the sum is 15
            so for n = 6 is n[5] + 6
        This is called memoization. we cache the results of a computation so that we can resuse later.
        We optimize the time complexity by giving up space.
        Its a time / memory tradeoff


     */
    private int sumOfFirst(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        for (int i=1; i<=n; i++){
            dp[i] = dp[i-1] + i;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        SumOfNNumbers obj = new SumOfNNumbers();
        int n = 5;
        System.out.println("sumFirst of " +n +" " +obj.sumOfFirst(n));
    }
}
