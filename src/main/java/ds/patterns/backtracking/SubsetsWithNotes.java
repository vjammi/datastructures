package ds.patterns.backtracking;

import ds.z.misc.IndentUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
     78. Subsets    https://leetcode.com/problems/subsets/
     Given an integer array nums of unique elements, return all possible subsets (the power set).
     The solution set must not contain duplicate subsets. Return the solution in any order.
     Example 1:
     Input: nums = [1,2,3]
     Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class SubsetsWithNotes {

    public List<List<Integer>> subsets1(int[] nums){
        List<Integer> chosen = new ArrayList<>();
        List<Integer> input = new ArrayList<>();
        for (int i=0;i<nums.length; i++)
            input.add(nums[i]);

        List<List<Integer>> result = new ArrayList();
        subsets1(input, 0, chosen, result, 0);
        System.out.println(result);
        return result;
    }
    // Consistent with the Subsets tree view - No removing chars from the input
    private void subsets1(List<Integer> input, int i, List<Integer> chosen, List<List<Integer>> result, int n){
        String indent = get_indent(n);
        //if (input.isEmpty()){
        if (i == input.size()){
            // 4. base case
            result.add(new ArrayList(chosen));
            System.out.println(indent +" Result " +input +chosen);
            //IndentUtil.showChosenI(indent, input,chosen);
            return;
        }
        //  For a backtracking problem it could be tricky to figure what the choices are?
        //  What unit of work each function call is going to need to take care ?
        //  Recursive case - for each possible choice we need to [for each element in the input list]
        Integer choice = input.get(i);

        //  1. Choose to exclude and explore -
        //     Explore without the choice, by incrementing the index
        //input.remove(0); // No longer needed to remove - to be consistent with the subsets tree view
        System.out.println(indent +" - " +choice +" "+input +chosen);      // Exclude the first element from the input
        subsets1(input, i+1, chosen, result, n+1);

        //  3. Choose to include and further explore & UnChoose -
        //     Explore with the choice, incrementing the index and later un-choose
        chosen.add(choice);
        System.out.println(indent +" + " +choice +" "+input +chosen);             // Include the first element from the input
        subsets1(input, i+1, chosen, result,  n+1);
        chosen.remove(chosen.size()-1);
        //input.add(0, s); // No longer needed to add back - to be consistent with the subsets tree view
        System.out.println(indent +" " +input +chosen);                  // input add the first element from the input
    } // [[], [3], [2], [2, 3], [1], [1, 3], [1, 2], [1, 2, 3]]

    // Representing the problem space as a graph and using enumeration
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        ArrayList<Integer> chosen = new ArrayList<>();
        backtrack(nums, 0, chosen, result, 0);
        System.out.println(result);
        return result;
    }
    /**
         [1, 2, 3] OUT() []

         [1, 2, 3] L(0-1) [1]
         > [1, 2, 3] OUT() [1]
         > [1, 2, 3] L(1-2) [1, 2]
         >    > [1, 2, 3] OUT() [1, 2]
         >    > [1, 2, 3] L(2-3) [1, 2, 3]
         >    >    > [1, 2, 3] OUT() [1, 2, 3]
         >    > [1, 2, 3] R(2-3) [1, 2, 3]
         > [1, 2, 3] R(1-2) [1, 2]
         > [1, 2, 3] L(2-3) [1, 3]
         >    > [1, 2, 3] OUT() [1, 3]
         > [1, 2, 3] R(2-3) [1, 3]
         [1, 2, 3] R(0-1) [1]

         [1, 2, 3] L(1-2) [2]
         > [1, 2, 3] OUT() [2]
         > [1, 2, 3] L(2-3) [2, 3]
         >    > [1, 2, 3] OUT() [2, 3]
         > [1, 2, 3] R(2-3) [2, 3]
         [1, 2, 3] R(1-2) [2]

         [1, 2, 3] L(2-3) [3]
         > [1, 2, 3] OUT() [3]
         [1, 2, 3] R(2-3) [3]

         [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
     * */
    private void backtrack(int[] input, int startIndex, List<Integer> chosen, List<List<Integer>> result, int n){
        String indent = IndentUtil.getIndent(n);
        result.add(new ArrayList<>(chosen));
        IndentUtil.showChosen(indent, input, chosen);

        for(int i = startIndex; i < input.length; i++){
            if(i > startIndex && input[i] == input[i-1]) {
                System.out.println(indent +" "+input +" " +i +" " +input[i] +" " +chosen +" Duplicate Skipping..");
                continue; // skip duplicates
            }
            chosen.add(input[i]);
            IndentUtil.showLeft(indent, input, i, input[i], chosen);
            backtrack(input, i+1, chosen, result, n+1);
            IndentUtil.showRight(indent, input, i, input[i], chosen);
            chosen.remove(chosen.size() - 1);
        }
    }

    // Easy to explain
    public List<List<Integer>> subsets22(int[] nums){
        List<Integer> chosen = new ArrayList<>();
        List<Integer> input = new ArrayList<>();
        for (int i=0;i<nums.length; i++)
            input.add(nums[i]);

        List<List<Integer>> result = new ArrayList();
        //subsets1(input, 0, chosen, result, 0);
        subsets22(input, result, 0);
        System.out.println(result);
        return result;
    }

    private void subsets22(List<Integer> input, List<List<Integer>> result, int n){
        if (input.isEmpty()){
            result.add(new ArrayList<Integer>());
            return;
        }

        Integer s = input.get(0);
        input.remove(0);

        subsets22(input, result, n);
        int size = result.size();
        for (int i=0; i<size; i++){ // We only want to iterate thru the initial size the result
            // result.get(i).add(s); // Does not work ???
            List<Integer> list = new ArrayList<Integer>(result.get(i));
            list.add(s);
            result.add(list);
        }
        input.add(0, s);
    }

    public List<String> powerSet(String input) {
        List<String> result = new ArrayList<>();
        String chosen = "";
        powerset(input, 0, chosen, result, 1);
        System.out.println(result);
        return result;
    }
    /**
     -(L) A
     >  -(L) B
     >    >  -(L) C
     >    >    >  Result ""
     >    >  +(R) C C
     >    >    >  Result "C"
     >  +(R) B B
     >    >  -(L) C B
     >    >    >  Result "B"
     >    >  +(R) C BC
     >    >    >  Result "BC"
     +(R) A A
     >  -(L) B A
     >    >  -(L) C A
     >    >    >  Result "A"
     >    >  +(R) C AC
     >    >    >  Result "AC"
     >  +(R) B AB
     >    >  -(L) C AB
     >    >    >  Result "AB"
     >    >  +(R) C ABC
     >    >    >  Result "ABC"
     [, C, B, BC, A, AC, AB, ABC]
     */
    private void powerset(String input, int i, String chosen, List<String> result, int n) {
        String indent = get_indent(n);
        if(i >= input.length()) {
            result.add(chosen);
            System.out.println(indent +"["+input +"] RET \"" +chosen +"\"");
            return;
        }
        char c = input.charAt(i);
        //input = input.substring(i+1, input.length()-1);
        System.out.println(indent +"["+input +"] -(L:" +c +") ["+chosen+"]");
        powerset(input, i + 1, chosen, result, n+1);
        chosen = chosen + c;
        System.out.println(indent +"["+input +"] +(R:" +c +") ["+chosen+"]");
        powerset(input, i + 1, chosen, result, n+1);
        chosen = chosen.substring(0, chosen.length()-1); //
        //input = c + input;
        System.out.println(indent +"["+input +"] -(R:" +c +") ["+chosen+"]");

    }

    public String get_indent(int N) {
        String S = new String("");
        for (int i = 0; i < N; i++)
            S = S + "   > ";
        return S;
    }

    public static void main(String[] args) {
        SubsetsWithNotes obj = new SubsetsWithNotes();
        int[] nums = {1,1,2};
        //obj.subsets1(nums);
        //obj.subsets2(nums);
        obj.powerSet("catsandog");
    }
}
