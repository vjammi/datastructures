package dev.vjammi.ds.v1.recursion;

public class RecursiveSum {

    public int sum1(int n){
        if (n<=0)
            return 0;
        return n + sum1(n-1);
    }

    public int sum2(int n){
        if (n<=0)
            return 1;
        return sum2(n-1) + sum2(n-1);
    }

    public static void main(String[] args) {
        RecursiveSum obj = new RecursiveSum();

        int sum1 = obj.sum1(4);
        System.out.println("Sum1 "+sum1);

        int sum2 = obj.sum2(4);
        System.out.println("Sum2 " +sum2);
    }
}
