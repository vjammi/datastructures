package ds.patterns.subs.subarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAllSubArrays {

    public List<int[]> print(int[] nums){

        List<int[]> subarrays = new ArrayList();
        for (int i=0; i<nums.length; i++){
            for (int j=i; j<nums.length; j++){

                System.out.print(nums[i] +"-" +nums[j] +" : ");
                int l=0; int[] subarray = new int[j-i+1];
                for (int k=i; k<=j; k++){
                    subarray[l++] = nums[k];
                    System.out.print(" " +nums[k]);
                }
                subarrays.add(subarray);
                System.out.println();
            }
        }

        for (int[] subarray: subarrays) {
            Arrays.stream(subarray).forEach(System.out::print);
            System.out.println();
        }

        return subarrays;
    }

    public static void main(String[] args) {
        ListAllSubArrays obj = new ListAllSubArrays();
        obj.print(new int[]{1,2,3,4,5});
    }

}
