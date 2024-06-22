package io.dev.v2.dp;

import java.util.ArrayList;
import java.util.List;

// A recursive solution for subset sum problem
public class SubsetSum {

    // Returns true if there is a subset of set[] with sun equal to given sum
    boolean isSubsetSum(int[] set, int n, int sum, List<Integer> chosen){
        // Base Cases
        if(sum == 0) {
            System.out.println("Sum Found " +chosen.toString());
            return true;
        }

        if( n == 0 && sum != 0 ) {
            return false;
        }

        // If last element is greater than sum, then ignore it
        if( set[n-1] > sum)
            return isSubsetSum(set,n-1,sum, chosen);

        /*
            else, check if sum can be obtained by any of the following
            (a) including the last element
            (b) excluding the last element
        */

        // Here the element is not chosen (excluded), sum remains the same and we decrement n to n-1
        boolean sumExclude = isSubsetSum(set, n-1, sum, chosen);

        // Here the element is chosen (included), remainder sum becomes sum-set[n-1] and we also decrement n to n-1
        chosen.add(n);
        boolean sumInclude = isSubsetSum(set, n-1, sum - set[n-1], chosen);
        chosen.remove(chosen.size()-1);

        return sumExclude || sumInclude;
    }

    private int subsetSum(int[] set, int sum, int n) {
        if( isSubsetSum(set,n,sum, new ArrayList<Integer>()) == true )
            System.out.println("Found a subset with given sum");
        else
            System.out.println("No subset with given sum");
        return 0;
    }

    // Driver program to test above function
    public static void main(String[] args) {
        int[] set = {3, 34, 4, 12, 5, 2};
        int sum=9;

        //int n = sizeof(set) / sizeof(set[0]);
        int n = set.length;

        SubsetSum obj = new SubsetSum();
        obj.subsetSum(set, sum, n);
    }

}
