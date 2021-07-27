package ds.patterns.dfs;

import java.util.ArrayList;
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



    public static void main(String[] args) {
        Combinations obj = new Combinations();
        int[] nums = {1,2,3};
        obj.combine(4,2);
    }
}
