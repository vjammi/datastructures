package dev.vjammi.ds.v2.array;

public class ProductOfArrayExceptSelf {

    /**
     238. Product of Array Except Self
     Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
     The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
     You must write an algorithm that runs in O(n) time and without using the division operation.

     Approach 1: BruteForce
         For every index of the array, traverse the array backwards and traverse the array forwards
         to find the product of the elements to the left of the index and to the right of the index
         Runtime: O(n^2)

     Approach 2: Traverse the array twice - once forwards and once backwards.
         Traverse from front to back - To find the product at each index to its left
         Traverse from back to front - to find the product at each index to its right
         Runtime: O(n+n+n) = O(3n) = O(n)
     */
    public int[] productExceptSelf(int[] nums) {

        int prefixProduct = 1;
        int[] prefixProductArr = new int[nums.length];
        for (int i=0; i<nums.length; i++){
            if (i==0)
                prefixProduct = prefixProduct * 1;
            else
                prefixProduct = prefixProduct * nums[i-1];

            prefixProductArr[i] = prefixProduct;
        }

        int suffixProduct = 1;
        int[] suffixProductArr = new int[nums.length];
        for (int i=nums.length-1; i>=0; i--){
            if (i==nums.length-1)
                suffixProduct = suffixProduct * 1;
            else
                suffixProduct = suffixProduct * nums[i+1];

            suffixProductArr[i] = suffixProduct;
        }

        int answer = 1;
        int[] answerArr = new int[nums.length];
        for (int i=0; i<nums.length; i++){

            if (i==0)
                answerArr[i] = 1 * suffixProductArr[i] ;
            else if (i==nums.length-1)
                answerArr[i] = prefixProductArr[i] * 1 ;
            else
                answerArr[i] = prefixProductArr[i] * suffixProductArr[i] ;
        }

        return answerArr;
    }

    public int[] productExceptSelf_BruteForce(int[] nums) {
        int[] answer = new int[nums.length];
        for (int i=0; i<nums.length; i++){
            int productExceptSelf = 1;
            for (int j=0; j<nums.length; j++){
                if (j != i){
                    productExceptSelf = productExceptSelf * nums[j];
                }
            }
            answer[i] = productExceptSelf;
        }
        return answer;
    }

}
