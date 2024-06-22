package io.dev.v1.recursion;

import java.util.HashMap;

//Fibonacci Series using Recursion
// https://www.mathsisfun.com/numbers/fibonacci-sequence.html
class Fibonacci {
    static int fib(int n)  {
        if (n <= 1)
            return n;

        //   Fn = Fn-1 + Fn-2
        //   x9	= x9-1 + x9-2
        int fn = fib(n-1) + fib(n-2);
        System.out.print(fn + ", ");
        return fn;
    }

    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();

    private int fibb(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int result;
        if (n < 2) {
            result = n;
        } else {
            result = fibb(n-1) + fib(n-2);
        }
        // keep the result in cache.
        cache.put(n, result);
        return result;
    }

    public static void main (String[] args){
        int n = 5;
        int fib = fib(n);
        System.out.println("\n Final Result: " + fib);
    }
}
