package ds.patterns.dfs;

import java.util.*;

public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        if (nums.length ==  0)
            return permutations;

        Set<List<Integer>> unique = new HashSet<List<Integer>>();
        boolean[] visited = new boolean[nums.length];

        // N DisConnected Components
        for (int i=0; i<nums.length; i++){
            List<Integer> path = new ArrayList<>();
            dfs(nums, path, unique, visited);
        }

        for (List list : unique){
            permutations.add(list);
        }
        return permutations;
    }

    public void dfs(int[] nums, List<Integer> path, Set<List<Integer>> unique, boolean[] visited){
        if (path.size() == nums.length){
            unique.add(new ArrayList(path)); // *** Deep copy of the list(path), not the path itself.
            return;
        }

        for (int i=0;i<nums.length; i++){
            if(!visited[i]){
                path.add(nums[i]);
                visited[i] = true;
                dfs(nums, path, unique, visited);
                path.remove(path.size()-1);
                visited[i] = false;
            }

        }
    }


    public static void main(String[] args) {
        PermutationsII obj = new PermutationsII();
        //List<List<Integer>> permute = obj.permuteUnique(new int[]{1, 1, 2});
        List<List<Integer>> permute = obj.permuteUnique(new int[]{0,1,0,0,9});
        for (List list :permute)
            System.out.println(list);
    }
}
