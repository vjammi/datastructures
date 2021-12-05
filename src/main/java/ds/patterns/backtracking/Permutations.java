package ds.patterns.backtracking;

import ds.util.IndentUtil;

import java.util.ArrayList;
import java.util.List;

/**
    46 Permutations  https://leetcode.com/problems/permutations/
    Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
    Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class Permutations {
    //              INPUT|CHOICE
    //                ABC|

    //               0-BC|A
    //                0-C|AB
    //                  _|ABC
    //                0-B|AC
    //                  _|ACB

    //               1-AC|B

    //                                       _|ABC
    //                               0-C|AB                // C inserted back at the 0th index - C
    //                                 0
    //                      0-BC|A                         // B inserted back 0th index resulting in BC, C inserted back at 1st index resulting in BC
    //                        01
    //                               1-B|AC                // B is put back at the 0th index
    //                                 0
    //                                       _|ACB

    //                                       _|BAC
    //                               0-C|BA                // On the way back B is put back at the 0th index
    //                                 0
    //             ABC|     1-AC|B
    //             012        01     1-A|BC
    //                                 0
    //                                       _|BCA

    //                                       _|CAB
    //                               0-B|CA
    //                                 0
    //                      2-AB|C
    //                        01
    //                               1-A|CB
    //                                 0
    //                                       _|CBA

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        if (nums.length == 0)
            return result;

        List<Integer> input = new ArrayList();
        for (int num: nums){
            input.add(num);
        }

        List<Integer> chosen = new ArrayList();
        //backtrack(input, 0, chosen, result);
        backtrack(nums, 0, chosen, result);

        return result;
    }

    // Permuting 4 elements is - Picking/Choosing 1 element and permuting the other 3
    private void backtrack(int[] nums, int index, List<Integer> chosen, List<List<Integer>> result){
        if (chosen.size() == nums.length){
            List<Integer> list = new ArrayList(chosen);
            result.add(list); System.out.println(list);
            return;
        }

        for (int i=0; i< nums.length; i++){
            int choice = nums[i];

            if(chosen.contains(choice)) {
                System.out.println("            "+i +"***" + index+"***"+choice);
                continue;
            }
            System.out.println(i +" " +index+" "+choice);

            chosen.add(choice);
            backtrack(nums, i+1, chosen, result);
            chosen.remove(chosen.size()-1);
        }

    }

    // Permuting 4 elements is - Picking/Choosing 1 element and permuting the other 3
    private void backtrack2(List<Integer> input, int index, List<Integer> chosen, List<List<Integer>> result){
        if (input.size() == 0){
            List<Integer> list = new ArrayList(chosen);
            result.add(list);
            System.out.println(list);
            return;
        }

        for (int i=0; i< input.size(); i++){
            int choice = input.get(i);

            chosen.add(choice);
            input.remove(i);

            backtrack2(input, i+1, chosen, result);

            chosen.remove(chosen.size()-1);
            input.add(i, choice);
        }

    }

    public static void main(String[] args) {
        new Permutations().permute(new int[]{4, 5, 6});
    }

}
