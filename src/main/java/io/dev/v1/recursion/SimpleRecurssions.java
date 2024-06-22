package io.dev.v1.recursion;

public class SimpleRecurssions {
    // Consider the following code.
    public int foo(int n) {
        if (n <= 0)
            return 1;
        else
            return foo(n-1) ; //+ foo(n-3);
    }

    public static int f(int x, int y) {
        if (y==0)
            return 0;
        else
            return x + f(x, y-1);
    }

    public static int GCD(int a, int b) {
       if  (b == 0)
           return a;
       if (a == 0 || a == b)
           return b;
       else if (a > b)
           return GCD(a%b , b);
       else
           return GCD(a, b%a);
    }

    // The first 21 Fibonacci numbers Fn are
    // F0	F1	F2	F3	F4	F5	F6	F7	F8	F9	F10	F11	F12	F13	F14	F15	F16	F17	 F18  F19  F20
    // 0	1	1	2	3	5	8	13	21	34	55	89	144	233	377	610	987	1597 2584 4181 6765
    public int fib(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int val = fib(n - 2) + fib(n - 1);
            System.out.print(", " +val);
            return val;
        }
    }


    private void testFoo() {
        //System.out.println(foo(3));
        //System.out.println(f(3, 2));
        System.out.println( "\n" +fib(20));
    }

    public static void main(String[] args) {
        SimpleRecurssions recur = new SimpleRecurssions();
        recur.testFoo();
    }
}
