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
            //System.out.println(nums[i]+"\n");
            if (!path.contains(nums[i])){
                path.add(nums[i]);
                dfs(nums, path, permutations);
                path.remove(path.indexOf(nums[i])); // *** Need to pass the index of the element, instead of the element itself
            }
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length ==  0)
            return permutations;

        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs2(nums, path, permutations, visited);

        return permutations;
    }

    public void dfs2(int[] nums, List<Integer> path, List<List<Integer>> permutations, boolean[] visited){
        if (path.size() == nums.length){
            permutations.add(new ArrayList<>(path)); // *** Deep copy
            return;
        }

        for (int i=0;i<nums.length; i++){
            if (!visited[i]){
                path.add(nums[i]);
                visited[i] = true;
                dfs2(nums, path, permutations, visited);
                path.remove(path.size()- 1);  // *** remove
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Permutations obj = new Permutations();
        //System.out.println(obj.permute(new int[]{1,2,3}));
        System.out.println(obj.permute2(new int[]{1,2,3}));
    }
}
