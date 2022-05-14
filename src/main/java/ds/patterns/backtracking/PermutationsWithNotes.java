package ds.patterns.backtracking;

import ds.z.misc.IndentUtil;
import java.util.ArrayList;
import java.util.List;

/**
    46 Permutations  https://leetcode.com/problems/permutations/
    Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
    Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class PermutationsWithNotes {
    int totalCalls;
    int madeItThrough;
    /**
                 INPUT|CHOICE
                   ABC|
                    BC|A
                    AC|B
                    AB|C


        1. Permute ABC
                                          |ABC
                                    C|AB
                           BC|A
                                    B|AC
                                          |ACB

                                          |BAC
                                    C|BA
                ABC|       AC|B
                                    A|BC
                                          |BCA

                                          |CAB
                                    B|CA
                           AB|C
                                    A|CB
                                          |CBA


        2. Permute ABCD
                                                0-D|ABC     0-_|ABCD
                                   0-CD|AB        0
                                     01         1-C|ABD     0-_|ABDC

                                                0-D|ACB     0-_|ACBD
                           BCD|A   1-BD|AC        0
                           012                  1-B|ACD     0-_|ACDB
                                                  0
                                                2-C|ADB     0-_|ADBC
                                   2-BC|AD
                                                2-B|ADC     0-_|ADCB



                ABCD|      ACD|B
                0123       012




                           ABD|C
                           012



                           ABC|D
                           012
    */

    // Permuting using recursion/dfs - no for loops
    public List<List<String>> permute1(List<String> input){
        List<List<String>> result = new ArrayList<>();
        List<String> chosen = new ArrayList<>();
        //permuteHelper(input, chosen, result, 0);
        permuteHelper2(input, 0);
        System.out.println(result);
        System.out.println(totalCalls +" "+madeItThrough);
        return result;
    }
    // Option 1 Permuting 4 elements is - Picking 1 and permuting the other 3
    public void permuteHelper(List<String> input, List<String> chosen, List<List<String>> result, int n){
        totalCalls++;
        String indent = IndentUtil.getIndent(n);
        // 4. Base Case
        //    The chosenList is full and the inputList is empty??? that is how we know we are done choosing elements. So the input list being empty would be the base case.
        if (input.isEmpty()){
            IndentUtil.showChosen(indent, input, chosen);
            result.add(new ArrayList(chosen));
            return;
        }

        // For each choice out of a list of choices
        //     say {a,b,c,d}, we need to try all of the them as first, then we need to try all of them as second, then third and so on...
        for (int i=0; i<input.size(); i++) {
            // Because we remove the 0th element, we start the loop from 0.
            // If not we would use a startIndex, by pass the startIndex+1 from the permuteHelper

            //  1.  choose
            //  When we choose, put our choice into the chosen list and pull/remove the chosen element out of the input list [of choices???].
            String choice = input.get(i);       //      a

            IndentUtil.showLeft(indent, input, i, choice, chosen);
            input.remove(i);                    //      {a,b,c,d}->{  b,c,d}
            chosen.add(choice);                 //      { }->{a}

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
            madeItThrough++;
        }
    }

    // Option 2 - more intitutive
    public List<List<String>> permuteHelper2(List<String> input, int n){
        String indent = IndentUtil.getIndent(n);
        if (input.isEmpty()){
            List<List<String>> result = new ArrayList<>();
            //IndentUtil.showChosen(indent, input, chosen);
            //result.add(new ArrayList(chosen));
            return result;
        }
        //List<String> chosen, List<List<String>> result
        String choice = input.get(0);                  //      a
        input.remove(0);                          //      {a,b,c,d}->{  b,c,d}
        //chosen.add(choice);                            //      { }->{a}
        List<List<String>> inputWithoutChoice = permuteHelper2(input, n+1);
        //chosen.remove(chosen.size()-1);           // Remove the last last element that was added
        //List<String> list = new ArrayList<>();
        for (int i=0; i<inputWithoutChoice.size(); i++) {
            List<String> list2 = inputWithoutChoice.get(i);
            list2.add(i, choice);
            System.out.println(list2); //IndentUtil.showLeft(indent, input, i, choice, chosen);
            inputWithoutChoice.add(list2);
        }
        input.add(0, choice);                     // Add/Put the earlier chosen element back into the input list.

        return inputWithoutChoice;
    }

    // Hard to reason about. Only works because of the check within the for loop - Representing the problem space as a graph and using enumeration
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result, 0);
        System.out.println(result);
        return result;
    }
    private void backtrack(int[] input, List<Integer> chosen, List<List<Integer>> result, int n){
        String indent = IndentUtil.getIndent(n);
        if(chosen.size() == input.length){
            result.add(new ArrayList<>(chosen));
            IndentUtil.showChosen(indent, input, chosen);
        }

        for(int i = 0; i < input.length; i++){
            if(chosen.contains(input[i])) {
                //System.out.println(indent +" "+IndentUtil.asList(input) +" " +i +" " +input[i] +" " +chosen +" Skipping Duplicate...");
                //continue; // element already exists, skip
            }

            chosen.add(input[i]);
            IndentUtil.showLeft(indent, input, i, input[i], chosen);
            backtrack(input, chosen, result, n+1);
            IndentUtil.showRight(indent, input, i, input[i], chosen);
            chosen.remove(chosen.size() - 1);
        }

    }

    // Solving using Graph - looping thru all nodes and dfs into each unvisited node - As n disconnected components
    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length == 0)
            return permutations;

        // N DisConnected Components
        for (int num : nums) {
            List<Integer> path = new ArrayList<>();
            path.add(num);
            dfs3(nums, path, permutations);
        }
        return permutations;
    }
    public void dfs3(int[] nums, List<Integer> path, List<List<Integer>> permutations) {
        if (path.size() == nums.length) {
            permutations.add(new ArrayList<>(path)); // *** Add a copy of the list(path) the result, not the path itself.
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //System.out.println(nums[i]+"\n");
            if (!path.contains(nums[i])) {
                path.add(nums[i]);
                dfs3(nums, path, permutations);
                path.remove(path.indexOf(nums[i])); // *** Need to pass the index of the element, instead of the element itself
            }
        }
    }

    // DFS / Graph using visited array
    public List<List<Integer>> permute4(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length == 0)
            return permutations;

        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs4(nums, path, permutations, visited);

        return permutations;
    }
    public void dfs4(int[] nums, List<Integer> path, List<List<Integer>> permutations, boolean[] visited) {
        if (path.size() == nums.length) {
            permutations.add(new ArrayList<>(path)); // *** Deep copy
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                path.add(nums[i]);
                visited[i] = true;
                dfs4(nums, path, permutations, visited);
                path.remove(path.size() - 1);  // *** remove
                visited[i] = false;
            }
        }
    }

    // Using Swapping....

    /**
                                         ABC
                               /          |            \
             0 A           A/A           A|B           A\C
                           /              |              \
                        ABC              BAC             CBA
             1 B     B/B  B\C           /   \
                    ABC    ACB       BAC     BCA
             2 C  C/C       B\B     c/c       b\b
                 ABC         ACB   BAC          BCA

    */
    private List<String> permute5(char[] chars) {
        List<String> result = new ArrayList<>();
        permute5(chars, 0, chars.length - 1, result, 0);
        System.out.println("ResultList: "+result);
        return result;
    }
    private void permute5(char[] input, int startIndex, int endIndex, List<String> result, int n) {
        String indent = IndentUtil.getIndent(n);
        if (startIndex == endIndex) {
            String string = String.valueOf(input);
            result.add(string);
            IndentUtil.showChosen(indent,  string, String.valueOf(input));
            return;
        }

        for (int i = startIndex; i <= endIndex; i++) {
            System.out.println(indent +"123" +" L1(" +startIndex +"-" +i +"-"+input[i]+") " +String.valueOf(input));
            swap(input, startIndex, i); // swap
            System.out.println(indent +"123" +" L2(" +startIndex +"-" +i +"-"+input[i]+") " +String.valueOf(input));

            permute5(input, startIndex + 1, endIndex, result, n+1);

            System.out.println(indent +"123" +" R1(" +startIndex +"-" +i +"-"+input[i]+") " +String.valueOf(input));
            swap(input, startIndex, i); // swap back
            System.out.println(indent +"123" +" R2(" +startIndex +"-" +i +"-"+input[i]+") " +String.valueOf(input));
        }
    }
    public void swap(char[] chars, int i, int j) {
        //char[] charArray = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        //return String.valueOf(chars);
    }


    public static void main(String[] args) {
        PermutationsWithNotes obj = new PermutationsWithNotes();
        String[] arr = {"a", "b", "c"};
        obj.permute1(obj.asList(arr));
        //obj.permute2(new int[]{1, 2, 3});
        //char[] array = {'1', '2', '3'};
        //List<String> resultList = obj.permute5(array);
    }

    public List<String> asList(String[] array) {
        List<String> input = new ArrayList<>();
        for (String value : array) {
            input.add(value);
        }
        return input;
    }
}
