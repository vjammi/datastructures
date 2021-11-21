package ds.patterns.subarray;

import java.util.ArrayList;
import java.util.List;

public class SubArray {

    public static void main(String[] args) {

        int[] nums = {1,2,3,4};; //{2,1,-3,4,-1,2,1,-5,4,-10,100};
        SubArray obj = new SubArray();
        obj.maxSumContigiousSubArray(nums);
    }

    List<int[]> result = new ArrayList();
    private void maxSumContigiousSubArray(int[] nums) {

        for (int i=0;i<nums.length; i++){
            for (int j=i;j<nums.length; j++){
                addSubArray(nums, i, j);
            }
        }
        for (int[] subArray: result){
            printSubArray(subArray);
        }
    }

    private void printSubArray(int[] subArray) {
        for (int i =0; i<subArray.length; i++){
            System.out.print(subArray[i] +" ");
        }
        System.out.println();
    }

    private void addSubArray(int[] nums, int i, int j) {
        int[] subArray = new int[j-i+1];
        int k=0;
        while (i<=j) {
            subArray[k++] = nums[i++];
        }
        result.add(subArray);
    }

}
