package ds.patterns.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
    46 Permutations  https://leetcode.com/problems/permutations/
    Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
    Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
/**
 //              INPUT|CHOICE
 //                ABC|

 //               0-BC|A
 //                0-C|AB
 //                  _|ABC
 //                0-B|AC
 //                  _|ACB

 //               1-AC|B
 //                     --------------------------------------------------------
                                    0       1       2   BASE
 //                                         0-4x
 //
 //                                                 4x
 //                                0-4      1-5     5x
 //                                                 6   [456]
 //                                                 4x
 //                                         2-6     5   [465]
 //                                                 6x
 //
                                                    4x
 //                                         0-4     5x
                                                    6
 //                    456         1-5      1-5x
                                                    4
 //                                         2-6     5x
 //                                                 6x

                                                    4x
 //                                         0-4     5
                                                    6x

                                                    4
 //                                2-6      1-5     5x
 //                                                 6x

 //                                         2-6x
 //                     --------------------------------------------------------


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
 //                     --------------------------------------------------------
 */

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (nums.length ==  0)
            return result;

        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs1(nums, path, result, visited);

        // List<Integer> chosen = new ArrayList();
        // backtrack1(nums, chosen, result);

        // List<Integer> input = new ArrayList();
        // List<Integer> chosen = new ArrayList();
        // for (int num: nums)
        //     input.add(num);
        // backtrack2(input, chosen, result);

        return result;
    }

    public void dfs1(int[] nums, List<Integer> path, List<List<Integer>> result, boolean[] visited){
        if (path.size() == nums.length){
            result.add(new ArrayList(path));
            return;
        }

        for (int i=0;i<nums.length; i++){
            // if(visited[i]){
            //     continue;
            // }

            // OR

            if(!visited[i]){

                path.add(nums[i]);
                visited[i] = true;

                dfs1(nums, path, result, visited);

                path.remove(path.size()-1);
                visited[i] = false;
            }
        }
    }

    private void backtrack1(int[] nums, List<Integer> chosen, List<List<Integer>> result){
        if (chosen.size() == nums.length){
            result.add(new ArrayList(chosen)); System.out.println(new ArrayList(chosen));
            return;
        }

        for (int i=0; i< nums.length; i++){
            int choice = nums[i];

            if(chosen.contains(choice))
                continue;

            chosen.add(choice);
            backtrack1(nums, chosen, result);
            chosen.remove(chosen.size()-1);
        }

    }

    // Permuting 4 elements is - Picking/Choosing 1 element and permuting the other 3
    private void backtrack2(List<Integer> input, List<Integer> chosen, List<List<Integer>> result){
        if (input.size() == 0){
            result.add(new ArrayList(chosen)); System.out.println(new ArrayList(chosen));
            return;
        }

        for (int i=0; i< input.size(); i++){
            int choice = input.get(i);

            chosen.add(choice);
            input.remove(i);

            backtrack2(input, chosen, result);

            chosen.remove(chosen.size()-1);
            input.add(i, choice);
        }

    }

    public static void main(String[] args) {
        new Permutations().permute(new int[]{4, 5, 6});
    }

}
