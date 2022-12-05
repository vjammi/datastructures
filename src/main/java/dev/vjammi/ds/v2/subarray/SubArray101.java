package dev.vjammi.ds.v2.subarray;

public class SubArray101 {

    /**
     [1 2 3 4 5]
     [1, 1] = 1 - 0
     [1, 2] = 3 - 0
     [1, 3] = 6 - 0
     [1, 4] = 10 - 0
     [1, 5] = 15 - 0

     [2, 2] = 2 - 0
     [2, 3] = 5 - 0
     [2, 4] = 9 - 0
     [2, 5] = 14 - 0

     [3, 3] = 3 - 0
     [3, 4] = 7 - 0
     [3, 5] = 12 - 0

     [4, 4] = 4 - 0
     [4, 5] = 9 - 0

     [5, 5] = 5 - 0
     */

    public void subarray(int[] nums) {
        for (int i=0; i<nums.length; i++){
            for (int j=i; j<nums.length; j++){  // here j == i - if a single element is equal to the sum
                System.out.println(+nums[i] +", " +nums[j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};//{2,1,-3,4,-1,2,1,-5,4,-10,100};
        SubArray101 obj = new SubArray101();
        obj.subarray(nums);
    }
}
