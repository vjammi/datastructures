package ds.patterns.subs.subarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 0
 * 0-0 :  1
 * 0-1 :  1 2
 * 0-2 :  1 2 3
 * 0-3 :  1 2 3 4
 * 0-4 :  1 2 3 4 5
 *
 * 1
 * 1-1 :  2
 * 1-2 :  2 3
 * 1-3 :  2 3 4
 * 1-4 :  2 3 4 5
 *
 * 2
 * 2-2 :  3
 * 2-3 :  3 4
 * 2-4 :  3 4 5
 *
 * 3
 * 3-3 :  4
 * 3-4 :  4 5
 *
 * 4
 * 4-4 :  5
 *
 *
 * */
public class ListAllSubArrays {

    public List<int[]> print(int[] nums){

        List<int[]> subarrays = new ArrayList();
        for (int i=0; i<nums.length; i++){
            System.out.println(i);

            for (int j=i; j<nums.length; j++){
                System.out.print(i +"-" +j +" : ");

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