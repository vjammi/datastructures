package io.dev.v2.backtracking;

import java.util.*;

/**
    46 Permutations  https://leetcode.com/problems/permutations/
    Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
    Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class PermutationsCombinations {


    public List<List<Integer>> subsets(int[] nums, int k){
        List<Integer> chosen = new ArrayList<>();
        List<Integer> input = new ArrayList<>();
        for (int i=0;i<nums.length; i++)
            input.add(nums[i]);

        List<List<Integer>> result = new ArrayList();
        //subsets1(input, chosen, result, 1, k);
        //subsets2(input, result, 0);

        combinations(nums, 2, 4);
        System.out.println(result);
        return result;
    }

    private void helper(List<int[]> combinations, int[] data, int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            helper(combinations, data, start + 1, end, index + 1);
            helper(combinations, data, start + 1, end, index);
        }
    }

    private void combinations(int[] nums, int k, int n){
        if (k==n){
            //nums[k] = 0; nums[k] = 1;
            System.out.println(nums[0]);
            return;
        }

        nums[k] = 0;
        combinations(nums, k+1, n);
        nums[k] = 1;
        combinations(nums, k+1, n);
    }

    private void subsets1(List<Integer> input,  List<Integer> chosen, List<List<Integer>> result, int n, int k){
        String indent = ""; //get_indent(n);
        //if (input.isEmpty()){
        if (chosen.size() == k ){
            // 4. base case
            result.add(new ArrayList(chosen));
            System.out.println(indent +" Result " +input +chosen);
            return;
        }

        //  Note:
        //  For a backtracking problem it could be tricky to figure what the choices are?
        //  What unit of work each function call is going to need to take care ?
        // Recursive case - for each possible choice we need to [for each element in the input list]
        Integer s = input.get(0);
        //  1. Choose  2. Explore
        //System.out.println(indent +" " +input +" " +s +" " +chosen);     // input add the first element from the input
        input.remove(0); System.out.println(indent +" - " +s +" "+input +chosen);      // Exclude the first element from the input
        subsets1(input, chosen, result, n+1, k);

        //  3. Choose again & 4. Further Explore
        chosen.add(s); System.out.println(indent +" + " +s +" "+input +chosen);             // Include the first element from the input
        subsets1(input, chosen, result,  n+1, k);

        //  3. UnChoose
        input.add(0, s);
        System.out.println(indent +" " +input +chosen);                  // input add the first element from the input
        chosen.remove(chosen.size()-1);
    }


    /* Permutations of abcd */
    public void permute(List<String> input, int k){
        List<String> chosen = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        permute(input, chosen, result, 0, k);
        System.out.println(result);
    }
    // Permuting 4 elements is - Picking 1 and permuting the other 3
    public void permute(List<String> input, List<String> chosen, List<List<String>> result, int start, int k){
        // 4. Base Case
        //    The chosenList is full and the inputList is empty??? that is how we know we are done choosing elements. So the input list being empty would be the base case.
        //if (input.isEmpty()){
        if (chosen.size()==k){
        //if (k == 0){
            System.out.println(chosen);
            result.add(new ArrayList(chosen));
            return;
        }

        // For each choice out of a list of choices
        //     say {a,b,c,d}, we need to try all of the them as first, then we need to try all of them as second, then third and so on...
        for (int i=0;i<input.size();i++) {

            //  1.  choose
            //  When we choose, put our choice into the chosen list and pull/remove the chosen element out of the input list [of choices???].
            String s = input.get(i);    //      a
            chosen.add(s);              //      { }->{a}
            //input.remove(i);            //      {a,b,c,d}->{  b,c,d}

            //  2.  explore
            //  The permute recursive function call is going to lead to a tree of calls and all the sub calls are going to come back at later point.
            permute(input, chosen, result, start, k);

            //  3.  un-choose
            //  Undo the choose when the sub calls come back, after they  have finished processing. say the first letter "a"
            //  This would mean I have finished processing all the letters that might possibly have started with the first letter "a", the second and so on...???
            //  At this point I un-choose the letter "a", by removing the letter I have put in the chosen list, so that I can advance by choosing the the next set of letters - b, c, d - the one which is next.
            //  Note:
            //  Oftentimes un-choose is the mirror code of choose and we are undoing something
            chosen.remove(chosen.size()-1); // Remove the last last element that was added
            //input.add(i, s); // Add/Put the earlier chosen element back into the input list.
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
                path.remove((Integer) nums[i]); // *** Need to pass the index of the element, instead of the element itself
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
        PermutationsCombinations obj = new PermutationsCombinations();
        //System.out.println(obj.permute(new int[]{1,2,3}));
        //System.out.println(obj.permute2(new int[]{1, 2, 3}));

        //        char[] chars = {'A', 'B', 'C'};
        //        List<String> resultList = new ArrayList<>();
        //        obj.permute1(chars, 0, chars.length - 1, resultList);
        //        System.out.println("ResultList: "+resultList);
        //        // ABC, ACB, BAC, BCA, CBA, CAB

        //String[] sts = {"1", "2", "3", "4"};
        //List<String> list = new ArrayList(Arrays.asList(sts));
        //obj.permute(list, 2);

        int[] nums = {1,2,3,4};
        obj.subsets(nums, 2);
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
