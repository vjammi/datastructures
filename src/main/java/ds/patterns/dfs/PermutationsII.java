package ds.patterns.dfs;

import java.util.*;

public class PermutationsII {

    // Solution using N connected components
    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();

        if (nums.length ==  0)
            return permutations;

        Set<List<Integer>> unique = new HashSet<List<Integer>>();
        boolean[] visited = new boolean[nums.length];

        // N DisConnected Components
        for (int i=0; i<nums.length; i++){
            List<Integer> path = new ArrayList<>();
            dfs1(nums, path, unique, visited);
        }

        for (List list : unique){
            permutations.add(list);
        }
        return permutations;
    }
    public void dfs1(int[] nums, List<Integer> path, Set<List<Integer>> unique, boolean[] visited){
        if (path.size() == nums.length){
            unique.add(new ArrayList(path)); // *** Deep copy of the list(path), not the path itself.
            return;
        }

        for (int i=0;i<nums.length; i++){
            if(!visited[i]){
                path.add(nums[i]);
                visited[i] = true;
                dfs1(nums, path, unique, visited);
                path.remove(path.size()-1);
                visited[i] = false;
            }

        }
    }

    // Solution using a set of Lists.
    public List<List<Integer>> permuteUnique2(int[] nums) {
        Set<List<Integer>> result = new HashSet();
        List<List<Integer>> result2 = new ArrayList();
        List<Integer> input = new ArrayList();

        for (Integer num: nums) input.add(num);
        List<Integer> chosen = new ArrayList<>();
        permuteHelper(input, chosen, result, 0);
        result2.addAll(result);

        return result2;
    }
    // Permuting 4 elements is - Picking 1 and permuting the other 3
    public void permuteHelper(List<Integer> input, List<Integer> chosen, Set<List<Integer>> result, int n){
        // totalCalls++;
        // String indent = IndentUtil.getIndent(n);
        // 4. Base Case
        //    The chosenList is full and the inputList is empty??? that is how we know we are done choosing elements. So the input list being empty would be the base case.
        if (input.isEmpty()){
            //IndentUtil.showChosen(indent, input, chosen);
            result.add(new ArrayList(chosen));
            return;
        }

        // For each choice out of a list of choices
        //     say {a,b,c,d}, we need to try all of the them as first, then we need to try all of them as second, then third and so on...
        for (int i=0;i<input.size();i++) {

            //  1.  choose
            //  When we choose, put our choice into the chosen list and pull/remove the chosen element out of the input list [of choices???].
            Integer choice = input.get(i);       //      a
            //IndentUtil.showLeft(indent, input, i, choice, chosen);
            chosen.add(choice);                 //      { }->{a}
            input.remove(i);                    //      {a,b,c,d}->{  b,c,d}

            //  2.  explore
            //  The permute recursive function call is going to lead to a tree of calls and all the sub calls are going to come back at later point.
            permuteHelper(input, chosen, result, n+1);

            //  3.  un-choose
            //  Undo the choose when the sub calls come back, after they  have finished processing. say the first letter "a"
            //  This would mean I have finished processing all the letters that might possibly have started with the first letter "a", the second and so on...???
            //  At this point I un-choose the letter "a", by removing the letter I have put in the chosen list, so that I can advance by choosing the the next set of letters - b, c, d - the one which is next.
            //  Note:
            //  Oftentimes un-choose is the mirror code of choose and we are undoing something
            chosen.remove(chosen.size()-1); // Remove the last last element that was added
            input.add(i, choice); // Add/Put the earlier chosen element back into the input list.
            //madeItThrough++;
        }
    }

    public static void main(String[] args) {
        PermutationsII obj = new PermutationsII();
        List<List<Integer>> permute1 = obj.permuteUnique1(new int[]{1, 1, 2});
        List<List<Integer>> permute11 = obj.permuteUnique1(new int[]{0,1,0,0,9});
        for (List list :permute1)
            System.out.print(list);

        List<List<Integer>> permute2 = obj.permuteUnique1(new int[]{1, 1, 2});
        List<List<Integer>> permute22 = obj.permuteUnique1(new int[]{0,1,0,0,9});
        for (List list :permute2)
            System.out.print(list);
    }
}
