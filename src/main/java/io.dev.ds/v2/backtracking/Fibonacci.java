package dev.vjammi.ds.v2.backtracking;

/**
 *  509. Fibonacci Number
 *      The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 *      F(0) = 0, F(1) = 1
 *      F(n) = F(n - 1) + F(n - 2), for n > 1.
 *      Given n, calculate F(n).
 *
 *      Brute Force
 *          Runtime:
 *              Exponential - O(2^n) nodes within the tree
 *          Memory:
 *              O(N) for the call stack (Not all 2^N are in teh call stack at the same time)
 *
 *      With Memoization
 *          Runtime:
 *              Linear - O(N)
 *          Memory:
 *              O(N) + O(N) = O(N)
 *              O(N) for the call stack
 *              O(N) for the memo table
 *
 *  Reference:
 *      https://youtu.be/P8Xa2BitN3I
 **/
public class Fibonacci {

    int[] memo;
    public void findFib(int n){
        //int result1 = fib(n);
       //System.out.println(result1);

        memo = new int[n+1];
        int result2 = fibWithMemo(n);
        System.out.println(result2);
    }

    public int fibWithMemo(int n){
        if (n <= 0)
          return 0;

        if (n == 1)
          return 1;

        if (memo[n] != 0)
          return memo[n];

        return memo[n] = fibWithMemo(n-1) + fibWithMemo(n-2);
    }

    public int fib(int n){
        if (n<=0)
            return 0;

        if (n == 1)
            return 1;

        return fib(n-1) + fib(n-2);
    }


    public static void main(String[] args) {
        Fibonacci obj = new Fibonacci();
        obj.findFib(100);
    }
}