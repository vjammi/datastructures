package ds.patterns.dfs;

import java.util.*;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length ==  0)
            return permutations;

        // N DisConnected Components
        for (int num: nums){
            List<Integer> path = new ArrayList<>();
            path.add(num);
            dfs(nums, path, permutations);
        }
        return permutations;
    }


    public void dfs(int[] nums, List<Integer> path, List<List<Integer>> permutations){
        if (path.size() == nums.length){
            permutations.add(new ArrayList<>(path)); // *** Add a copy of the list(path) the result, not the path itself.
            return;
        }

        for (int i=0;i<nums.length; i++){
            if (!path.contains(nums[i])){
                path.add(nums[i]);
                dfs(nums, path, permutations);
                path.remove(path.indexOf(nums[i]));
            }
        }
    }

    public static void main(String[] args) {
        Permutations obj = new Permutations();
        System.out.println(obj.permute(new int[]{1,2,3}));
    }
}
