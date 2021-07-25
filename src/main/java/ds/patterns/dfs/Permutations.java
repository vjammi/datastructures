package ds.patterns.dfs;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    /* Permutations of abcd */
    public void permute(List<String> input){
        ArrayList<String> chosen = new ArrayList<>();
        permute(input, chosen);
    }
    // Permuting 4 elements is - Picking 1 and permuting the other 3
    public void permute(List<String> input, List<String> chosen){
        // 4. Base Case
        //    The chosenList is full and the inputList is empty??? that is how we know we are done choosing elements. So the input list being empty would be the base case.
        if (input.isEmpty()){
            System.out.println(chosen);
            return;
        }

        // For each choice out of a list of choices
        //     say {a,b,c,d}, we need to try all of the them as first, then we need to try all of them as second, then third and so on...
        for (int i=0;i<input.size();i++) {

            //  1.  choose
            //  When we choose, put our choice into the chosen list and pull/remove the chosen element out of the input list [of choices???].
            String s = input.get(i);    //      a
            chosen.add(s);              //      { }->{a}
            input.remove(i);            //      {a,b,c,d}->{  b,c,d}

            //  2.  explore
            //  The permute recursive function call is going to lead to a tree of calls and all the sub calls are going to come back at later point.
            permute(input, chosen);

            //  3.  un-choose
            //  Undo the choose when the sub calls come back, after they  have finished processing. say the first letter "a"
            //  This would mean I have finished processing all the letters that might possibly have started with the first letter "a", the second and so on...???
            //  At this point I un-choose the letter "a", by removing the letter I have put in the chosen list, so that I can advance by choosing the the next set of letters - b, c, d - the one which is next.
            //  Note:
            //  Oftentimes un-choose is the mirror code of choose and we are undoing something
            chosen.remove(chosen.size()-1); // Remove the last last element that was added
            input.add(i, s); // Add/Put the earlier chosen element back into the input list.
        }
    }

    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length == 0)
            return permutations;

        // N DisConnected Components
        for (int num : nums) {
            List<Integer> path = new ArrayList<>();
            path.add(num);
            dfs1(nums, path, permutations);
        }
        return permutations;
    }
    public void dfs1(int[] nums, List<Integer> path, List<List<Integer>> permutations) {
        if (path.size() == nums.length) {
            permutations.add(new ArrayList<>(path)); // *** Add a copy of the list(path) the result, not the path itself.
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //System.out.println(nums[i]+"\n");
            if (!path.contains(nums[i])) {
                path.add(nums[i]);
                dfs1(nums, path, permutations);
                path.remove(path.indexOf(nums[i])); // *** Need to pass the index of the element, instead of the element itself
            }
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length == 0)
            return permutations;

        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs2(nums, path, permutations, visited);

        return permutations;
    }
    public void dfs2(int[] nums, List<Integer> path, List<List<Integer>> permutations, boolean[] visited) {
        if (path.size() == nums.length) {
            permutations.add(new ArrayList<>(path)); // *** Deep copy
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                path.add(nums[i]);
                visited[i] = true;
                dfs2(nums, path, permutations, visited);
                path.remove(path.size() - 1);  // *** remove
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Permutations obj = new Permutations();
        //System.out.println(obj.permute(new int[]{1,2,3}));
        //System.out.println(obj.permute2(new int[]{1, 2, 3}));

        char[] chars = {'A', 'B', 'C'};
        List<String> resultList = new ArrayList<>();
        obj.permute1(chars, 0, chars.length - 1, resultList);
        System.out.println("ResultList: "+resultList);
        // ABC, ACB, BAC, BCA, CBA, CAB
    }

    private void permute1(char[] chars, int startIndex, int endIndex, List<String> resultList) {
        if (startIndex == endIndex) {
            resultList.add(String.valueOf(chars)); //System.out.println(">"+str);
            return;
        }

        for (int i = startIndex; i <= endIndex; i++) {
            swap(chars, startIndex, i); // swap
            System.out.println(">"+String.valueOf(chars));

            permute1(chars, startIndex + 1, endIndex, resultList);

            swap(chars, startIndex, i); // swap back
            System.out.println("<" +String.valueOf(chars));
        }
    }

    public void swap(char[] chars, int i, int j) {
        //char[] charArray = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        //return String.valueOf(chars);
    }
}
