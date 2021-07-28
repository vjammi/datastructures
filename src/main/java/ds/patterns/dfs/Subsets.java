package ds.patterns.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
     78. Subsets    https://leetcode.com/problems/subsets/
     Given an integer array nums of unique elements, return all possible subsets (the power set).
     The solution set must not contain duplicate subsets. Return the solution in any order.
     Example 1:
     Input: nums = [1,2,3]
     Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
public class Subsets {

    // Method to generate a string of 2N blanks
    public String get_indent(int N) {
        String S = new String("");
        for (int i = 0; i < N; i++)
            S = S + "   > ";
        return S;
    }

    public List<List<Integer>> subsets(int[] nums){
        List<Integer> chosen = new ArrayList<>();
        List<Integer> input = new ArrayList<>();
        for (int i=0;i<nums.length; i++)
            input.add(nums[i]);

        List<List<Integer>> result = new ArrayList();
        //subsets1(input, chosen, result, 0);
        subsets2(input, result, 0);
        System.out.println(result);
        return result;
    }

    private void subsets1(List<Integer> input,  List<Integer> chosen, List<List<Integer>> result, int n){
        String indent = get_indent(n);
        if (input.isEmpty()){
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
        subsets1(input, chosen, result, n+1);

        //  3. Choose again & 4. Further Explore
        chosen.add(s); System.out.println(indent +" + " +s +" "+input +chosen);             // Include the first element from the input
        subsets1(input, chosen, result,  n+1);

        //  3. UnChoose
        input.add(0, s);
        System.out.println(indent +" " +input +chosen);                  // input add the first element from the input
        chosen.remove(chosen.size()-1);
    }

    private void subsets2(List<Integer> input, List<List<Integer>> result, int n){
        String indent = get_indent(n);
        if (input.isEmpty()){
            result.add(new ArrayList<Integer>());
            System.out.println(indent +" Input " +input +" Result "+result);
            return;
        }

        Integer s = input.get(0);
        input.remove(0);

        subsets2(input, result, n);
        int size = result.size();
        for (int i=0; i<size; i++){ // We only want to iterate thru the initial size the result
            List<Integer> list = new ArrayList<Integer>(result.get(i));
            list.add(s);
            result.add(list);
        }
        input.add(0, s);
        System.out.println(indent +" Input: " +input +" s: " +s + " Result: "+result);
    }

    public List<String> powerSet(String input) {
        List<String> result = new ArrayList<>();
        String chosen = "";
        powerset(input, chosen, result, 0, 1);
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
    private void powerset(String input, String chosen, List<String> result, int index, int n) {
        String indent = get_indent(n);
        if(index >= input.length()) {
            result.add(chosen); System.out.println(indent +" Result \"" +chosen +"\"");
            return;
        }
        System.out.println(indent +" -(L:" +input.charAt(index) +") "+chosen);
        powerset(input, chosen, result, index + 1, n+1);
        chosen = chosen + input.charAt(index);
        System.out.println(indent +" +(R:" +input.charAt(index) +") "+chosen);
        powerset(input, chosen, result, index + 1, n+1);
    }


    /**
           Combinations: n = 3 k = 2

             > [A, B, C] L(0-A) []
             >    > [A, B, C] L(1-B) []
             >    >    > [A, B, C] L(2-C) []
             >    >    > [A, B, C] R(2-C) []
             >    > [A, B, C] R(1-B) []
             >    >    > [A, B, C] L(2-C) [B]
             >    >    > [A, B, C] R(2-C) [B]
             >    >    >    > [A, B, C] OUT  [B, C]
             > [A, B, C] R(0-A) []
             >    > [A, B, C] L(1-B) [A]
             >    >    > [A, B, C] L(2-C) [A]
             >    >    > [A, B, C] R(2-C) [A]
             >    >    >    > [A, B, C] OUT  [A, C]
             >    > [A, B, C] R(1-B) [A]
             >    >    > [A, B, C] L(2-C) [A, B]
             >    >    >    > [A, B, C] OUT  [A, B]
             >    >    > [A, B, C] R(2-C) [A, B]
             [[B, C], [A, C], [A, B]]
    * */
    public List<List<String>> combination(List<String> input, int k) {
        List<List<String>> result = new ArrayList<>();
        Stack<String> chosen = new Stack<>();
        combination(input, chosen, result, 0, k, 1);
        System.out.println(result);
        return result;
    }
    private void combination(List<String> input, Stack<String> chosen, List<List<String>> result, int index, int k, int indentCount) {
        String indent = get_indent(indentCount);
        if(index == input.size()) {
            if (chosen.size() == k){
                result.add(new ArrayList(chosen)); System.out.println(indent +input +" OUT(" +index +")" +" " +chosen);
            }else{ System.out.println(indent +input +" X(" +index +")" +" " +chosen);  }
            return;
        }

        System.out.println(indent +input +" L(" +index +"-"+input.get(index)+") " +chosen);
        combination(input, chosen, result, index+1, k, indentCount +1);

        System.out.println(indent +input +" R(" +index +"-"+input.get(index)+") " +chosen);
        chosen.push(input.get(index));
        combination(input, chosen, result, index+1, k, indentCount +1);
        chosen.pop();
    }


    public static void main(String[] args) {
        Subsets obj = new Subsets();

        //int[] nums = {1,2,3};
        //obj.subsets(nums);
        //obj.powerSet("123");

        List<String> list = Arrays.asList("A", "B", "C");
        obj.combination(list, 2);
    }
}
