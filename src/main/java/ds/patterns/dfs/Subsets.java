package ds.patterns.dfs;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    // Method to generate a string of 2N blanks
    public String get_indent(int N) {
        String S = new String("");
        for (int i = 0; i < N; i++)
            S = S + " > ";
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
        return result;
    }

    private void subsets1(List<Integer> input,  List<Integer> chosen, List<List<Integer>> result, int n){
        String indent = get_indent(n);
        if (input.isEmpty()){
            // 4. base case
            result.add(new ArrayList(chosen));
            System.out.println(indent +" . " +input +chosen);
            return;
        }

        //  Note:
        //  For a backtracking problem it could be tricky to figure what the choices are?
        //  What unit of work each function call is going to need to take care ?

        // Recursive case - for each possible choice we need to [for each element in the input list]

        Integer s = input.get(0);

        //  1. Choose  2. Explore
        System.out.println(indent +" " +input +chosen);     // input add the first element from the input
        input.remove(0);
        System.out.println(indent +" - " +s);               // Exclude the first element from the input
        subsets1(input, chosen, result, n+1);

        System.out.println(indent +" " +input +chosen);
        //  3. Choose again & 4. Further Explore
        chosen.add(s);
        System.out.println(indent +" + " +s);               // Include the first element from the input
        subsets1(input, chosen, result,  n+1);

        //  3. UnChoose
        input.add(0, s);
        System.out.println(indent +" " +input +chosen);     // input add the first element from the input
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

    public static void main(String[] args) {
        Subsets obj = new Subsets();
        int[] nums = {1,2,3};
        obj.subsets(nums);
    }
}
