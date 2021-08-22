package ds.patterns.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
    77. Combinations  [https://leetcode.com/problems/combinations/]
    Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
    You may return the answer in any order.
    Example 1:
    Input: n = 4, k = 2
    MyNote: N = [1,2,3,4]
    Output:
    [
    [2,4],
    [3,4],
    [2,3],
    [1,2],
    [1,3],
    [1,4],
    ]
*/
public class Combinations {

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
        combination(input, k, 0, chosen, result, 1);
        System.out.println(result);
        return result;
    }
    private void combination(List<String> input, int k, int index, Stack<String> chosen, List<List<String>> result, int indentCount) {
        String indent = get_indent(indentCount);
        if(index == input.size()) {
            if (chosen.size() == k){
                result.add(new ArrayList(chosen)); System.out.println(indent +input +" OUT(" +index +")" +" " +chosen);
            }else{ System.out.println(indent +input +" X(" +index +")" +" " +chosen);  }
            return;
        }
        // *** This impl is consistent with the Subsets tree view - No removing and adding back of chars from the input
        String choice = input.get(index);

        // Explore without the choice, by incrementing the index
        System.out.println(indent +input +" L(" +index +"-"+ choice +") " +chosen);
        combination(input, k, index+1, chosen, result, indentCount +1);

        // Explore with the choice, incrementing the index and later un-choose
        System.out.println(indent +input +" R(" +index +"-"+ choice +") " +chosen);
        chosen.push(choice);
        combination(input, k, index+1, chosen, result, indentCount +1);
        chosen.pop();
    }



    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> chosen = new Stack();
        List<Integer> input = new ArrayList();
        dfs(1, chosen, result, n, k);
        return result;
    }

    private void dfs(int input, Stack<Integer> chosen, List<List<Integer>> result, int n, int k){
        // base case
        if (input == n+1){
            if (chosen.size() == k)
                result.add(new ArrayList(chosen));
            return;
        }

        dfs(input+1, chosen, result, n, k);
        chosen.push(input);
        dfs(input+1, chosen, result, n, k);
        chosen.pop();
    }

    private void dfs(List<Integer> input, int k,  List<List<Integer>> chosen, List<List<Integer>> result){
        if (input.isEmpty()){
            chosen.add(new ArrayList());
            return;
        }

        Integer nodeValue = input.get(0);
        input.remove(0);

        dfs(input, k, chosen, result);

        int size = chosen.size();
        for (int i=0; i<size; i++){
            List<Integer> list = new ArrayList<Integer>(chosen.get(i));
            list.add(nodeValue);
            chosen.add(list);
            if (list.size() == k){
                result.add(list);
            }
        }

        input.add(0, nodeValue);
    }

    private void dfs(List<Integer> input, Stack<Integer> chosen, List<List<Integer>> result, int k, int index){
        // base case
        if (index == input.size()){
            if (chosen.size() == k)
                result.add(new ArrayList(chosen));
            return;
        }

        dfs(input, chosen, result, k, index+1);
        chosen.push(input.get(index));
        dfs(input, chosen, result, k, index+1);
        chosen.pop();
    }

    public String get_indent(int N) {
        String S = new String("");
        for (int i = 0; i < N; i++)
            S = S + "   > ";
        return S;
    }


    public static void main(String[] args) {
        Combinations obj = new Combinations();

        List<String> list = Arrays.asList("A", "B", "C");
        obj.combination(list, 2);

        int[] nums = {1,2,3};
        obj.combine(4,2);
    }
}
