package dev.vjammi.ds.v2.backtracking;

import java.util.*;

/*
 * References
 * https://youtu.be/-KbRfGR4Y-c
 * https://youtu.be/D6ptfiKVMYQ?t=528
 * https://iitd-plos.github.io/col100/lec/exhaustive_search.html
 * */
public class Backtracking101 {

    int calls = 0;

    public void printAllBinary(int digits){
        //printAllBinaryBacktrack(digits, "");
        printAllBinaryBacktrack(digits, new ArrayList<String>(), 0);
        //printAllBinaryHelper(digits, "");
    }
    /**
     Typically we have 1 recursive call at each level. But notice the multiple recursive calls at each level - for 0 and 1. similar to left and right for tree
     This is because we are exploring all of the options. Here we are going to choose 0 first and then 1. We are going to exhaustively search the space of binary numbers.
     If we want to exhaistively explore a space, your function call will often make multiple successor calls - one for eeach possible choice it could make.
     The predecessor function call does some work, and pass the output to the current function call.

        > 2  L(0) []
        >     > 1  L(0) [0]
        >     >    > 0 OUT  [0, 0]
        >     > 1  R(1) [0]
        >     >    > 0 OUT  [0, 1]
        >  2  R(1) []
        >     > 1  L(0) [1]
        >     >    > 0 OUT  [1, 0]
        >     > 1  R(1) [1]
        >     >    > 0 OUT  [1, 1]

        >  3  L(0) []
             > 2  L(0) [0]
             >    > 1  L(0) [0, 0]
             >    >    > 0 OUT  [0, 0, 0]
             >    > 1  R(1) [0, 0]
             >    >    > 0 OUT  [0, 0, 1]
             > 2  R(1) [0]
             >    > 1  L(0) [0, 1]
             >    >    > 0 OUT  [0, 1, 0]
             >    > 1  R(1) [0, 1]
             >    >    > 0 OUT  [0, 1, 1]
        >  3  R(1) []
             > 2  L(0) [1]
             >    > 1  L(0) [1, 0]
             >    >    > 0 OUT  [1, 0, 0]
             >    > 1  R(1) [1, 0]
             >    >    > 0 OUT  [1, 0, 1]
             > 2  R(1) [1]
             >    > 1  L(0) [1, 1]
             >    >    > 0 OUT  [1, 1, 0]
             >    > 1  R(1) [1, 1]
             >    >    > 0 OUT  [1, 1, 1]

     */
    public void printAllBinaryBacktrack(int digits, List<String> output, int n){
        String indent = get_indent(n);
        // The recursive call enters into one of the blocks at anytime - if + return or the implicit else block we stop the recursive call from going beyond the if block here by using a return
        if (digits == 0){
            System.out.println(indent +digits +" OUT " +" " +output);
            //System.out.println(output);
            return;
        }
        System.out.println(indent +digits +" "+" L(" +0 +") " +output);
        output.add("0"); // Make a selection and later backtrack
        printAllBinaryBacktrack(digits-1, output, n+1);
        output.remove(output.size()-1); // Notice the backtracking

        System.out.println(indent +digits +" "+" R(" +1 +") " +output);
        output.add("1"); // Make a selection and later backtrack
        printAllBinaryBacktrack(digits-1, output, n+1);
        output.remove(output.size()-1); // Notice the backtracking
    }
    public void printAllBinaryBacktrack(int digits, String output){
        // The recursive call enters into one of the blocks at anytime - if + return or the implicit else block we stop the recursive call from going beyond the if block here by using a return
        if (digits == 0){
            System.out.println(output);
            return;
        }

        printAllBinaryBacktrack(digits-1, output +"0");
        printAllBinaryBacktrack(digits-1, output +"1");
    }
    public void backtrackAlternateRepresentation(int digits, List<String> output){
        // The recursive call enters into one of the blocks at anytime - if or else [explicit]
        if (digits == 0){
            System.out.println(output);
            //return; //no more needed because of if/else blocks
        }else {
            output.add("0");                      // Make a selection and later backtrack
            backtrackAlternateRepresentation(digits - 1, output);
            output.remove(output.size() - 1); // Notice the backtracking

            output.add("1");                    // Make a selection and later backtrack
            backtrackAlternateRepresentation(digits - 1, output);
            output.remove(output.size() - 1); // Notice the backtracking
        }
    }
    public void printAllBinaryHelper(int digits, String soFar) {
        if (digits == 0) {
            System.out.println(soFar); // cout << soFar << endl;
            return;
        }

        soFar = soFar + "0";
        printAllBinaryHelper(digits - 1, soFar);
        soFar = soFar.substring(0, soFar.length() - 1);

        soFar = soFar + "1";
        printAllBinaryHelper(digits - 1, soFar);
        soFar = soFar.substring(0, soFar.length() - 1);
    }


    /**
     Write a recursive method named printAllDecimal that accepts an integer number of digits and prints all base-10 numbers that have exactly that many digits,
     in ascending order, one per line, with leading zeros in front as needed. For example, the call of printAllDecimal(3); should print:
         000, 001, 002, 003, ... 998, 999
     If the number of digits passed is 0 or negative, print no output. Your method must use recursion, but you can use a single for loop if necessary.
     */
    public void printAllDecimal(int digits){
        printAllDecimal(digits, new ArrayList<Integer>(), 0);
        //printAllDecimalHelper(digits,"");
    }
    /**
     When the set of digit choices available is large, using a loop to enumerate, results in shorter code (this is okay!)
     Note: loop over choices, not decisions.
     If the number of choices is variable, will need to use a loop, e.g., chess dev.vj.patterns.z.game.
     */
    public void printAllDecimal(int digits, ArrayList<Integer> output, int n){
        String indent = get_indent(n);
        if (digits == 0){
            System.out.println(indent +digits +" OUT " +" " +output);
            //System.out.println(output);
            return;
        }
        for (int i=0; i<10; i++){
            System.out.println(indent +digits +" L(" +i +") " +output);
            output.add(i);
            printAllDecimal(digits-1, output, n+1);
            output.remove(output.size()-1);
        }
    }
    public void  printAllDecimalHelper(int digits, String soFar) {
        if (digits == 0) {
            System.out.println(soFar); //cout << soFar << endl;
            return;
        }

        for (int i = 0; i < 10; i++) {
            printAllDecimalHelper(digits - 1, soFar + i);
        }
    }

    /*diceRollSum*/
    public void diceRollSum(int dice, int desiredSum){
        diceRollSum(dice, desiredSum, new ArrayList<Integer>());
        System.out.println(calls);
    }
    public void diceRollSum(int dice, int desiredSum, ArrayList<Integer> chosen){
        calls++;
        // 4 base case
        if (dice == 0){
            if (desiredSum == 0)
                System.out.println(chosen);
            return;
        }

        if (desiredSum >=dice*1 && desiredSum <=dice*6) {//???
            for (int i = 1; i <= 6; i++) {
                // 1 choose
                chosen.add(i);
                // 2 explore
                diceRollSum(dice - 1, desiredSum - i, chosen);
                // 3 un-choose backtrack
                chosen.remove(chosen.size() - 1);
            }
        }
    }


    /**
      Permutations of ABC

         A B C
         0 1 2
               0(A) > [A, B, C] L(0-A) []
                       > [B, C] L(0-B) [A]
                       >    > [C] L(0-C) [A, B]
                       >    >    > [] OUT  [A, B, C]
                       > [B, C] L(1-C) [A]
                       >    > [B] L(0-B) [A, C]
                       >    >    > [] OUT  [A, C, B]

               1(B) > [A, B, C] L(1-B) []
                       > [A, C] L(0-A) [B]
                       >    > [C] L(0-C) [B, A]
                       >    >    > [] OUT  [B, A, C]
                       > [A, C] L(1-C) [B]
                       >    > [A] L(0-A) [B, C]
                       >    >    > [] OUT  [B, C, A]

                2(B) > [A, B, C] L(2-C) []
                       > [A, B] L(0-A) [C]
                       >    > [B] L(0-B) [C, A]
                       >    >    > [] OUT  [C, A, B]
                       > [A, B] L(1-B) [C]
                       >    > [A] L(0-A) [C, B]
                       >    >    > [] OUT  [C, B, A]
    * */
    public void permute(List<String> input){
        List<List<String>> result = new ArrayList<>();
        Stack<String> chosen = new Stack<>();
        permute(input, chosen, result, 0);
        System.out.println(result);
    }
    // Permuting 4 elements is - Picking 1 and permuting the other 3
    public void permute(List<String> input, Stack<String> chosen, List<List<String>> result, int n){
        String indent = get_indent(n);
        // 4. Base Case
        //    The chosenList is full and the inputList is empty??? that is how we know we are done choosing elements. So the input list being empty would be the base case.
        if (input.isEmpty()){
            //System.out.println(chosen);
            System.out.println(indent +input +" OUT " +" " +chosen);
            result.add(new ArrayList<>(chosen));
            return;
        }

        // For each choice out of a list of choices
        //     say {A,B,C}, we need to try all of the them as first, then we need to try all of them as second, then third and so on...
        //          0 1 2
        for (int i=0;i<input.size();i++) {

            //  1.  choose
            //  When we choose, put our choice into the chosen list and pull/remove the chosen element out of the input list [of choices???].
            String s = input.get(i);//       0-A                                 1-B             2-C
            System.out.println(indent +input +" L(" +i +"-"+s+") " +chosen);
            //     i=3          0             1       2     3 (BaseCase)
            //                                    - {A,C}   - {A,C,B}
            chosen.push(s);   // { }     --  {A}
            //                                    - {A,B}   - {A,B,C}

            //                                    - 1 {B}{AC} - {}{A,C,B}
            input.remove(i); // {A,B,C} --  {B,C}      0        Empty
            //                   0 1 2       0 1
            //                                    - 0 {C}{AB} - {}{A,B,C}
            //                                         0        Empty

            //  2.  explore
            //  The permute recursive function call is going to lead to a tree of calls and all the sub calls are going to come back at later point.
            permute(input, chosen, result, n+1);

            //  3.  un-choose
            //  Undo the choose when the sub calls come back, after they  have finished processing. say the first letter "a"
            //  This would mean I have finished processing all the letters that might possibly have started with the first letter "a", the second and so on...???
            //  At this point I un-choose the letter "a", by removing the letter I have put in the chosen list, so that I can advance by choosing the the next set of letters - b, c, d - the one which is next.
            //  Note:
            //  Oftentimes un-choose is the mirror code of choose and we are undoing something
            System.out.println(indent +input +" UC(" +i +"-"+s+") " +chosen);
            //chosen.remove(chosen.size()-1); // Remove the last last element that was added
            chosen.pop(); // Remove the last last element that was added
            input.add(i, s); // Add/Put the earlier chosen element back into the input list.
            System.out.println(indent +input +" UC(" +i +"-"+s+") " +chosen);
        }
    }

    /* sublists / subsets / powersets */
    public void sublists(List<String> input){
        List<String> chosen = new ArrayList<>();
        sublists(input, chosen);
    }
    public void sublists(List<String> input, List<String> chosen){
        if (input.isEmpty()){
            // 4. base case
            System.out.println(chosen);
            return;
        }

        // Recursive case - for each possible choice we need to
        // For each element in the input list
        //for(int i=0; i<input.size(); i++) {
            //  1. Choose
            String s = input.get(0);      // Save the first elements, just in case.
            input.remove(0);         // Remove the first element.

            //  2. Explore
            sublists(input, chosen);

            //  3. Further Choose
            chosen.add(s);
            //  4. Further Explore
            sublists(input, chosen);

            //  3. Un choose
            input.add(0, s);
            chosen.remove(chosen.size()-1);

            //  Note:
            //  For a backtracking problem it could be tricky to figure what the choices are?
            //  What unit of work each function call is going to need to take care ?
        //}
        //sublists(input, chosen);
    }


    public void subsets(int[] nums){
        List<List<Integer>> result = new ArrayList();
        List<Integer> input = new ArrayList<>();
        for (int i=0;i<nums.length; i++)
            input.add(nums[i]);
        subsets(input, result, 0);
    }

    private void subsets(List<Integer> input, List<List<Integer>> result, int n){
        String indent = get_indent(n);
        if (input.isEmpty()){
            result.add(new ArrayList<Integer>());
            System.out.println(indent +" Input " +input +" Result "+result);
            return;
        }

        Integer s = input.get(0);
        input.remove(0);

        subsets(input, result, n);
        int size = result.size();
        for (int i=0; i<size; i++){ // We only want to iterate thru the initial size the result
            List<Integer> list = new ArrayList<Integer>(result.get(i));
            list.add(s);
            result.add(list);
        }
        input.add(0, s);
        System.out.println(indent +" Input: " +input +" s: " +s + " Result: "+result);
    }

    private void subsets_backtrack(List<List<Integer>> result, List<Integer> intermediaryResult, int[] nums, int startIndex){
        result.add(new ArrayList<>(intermediaryResult));
        System.out.println(intermediaryResult);
        if (startIndex >= nums.length)
            return;

        int endIndex = nums.length;
        for(int i = startIndex; i < endIndex; i++){
            intermediaryResult.add(nums[i]);
            subsets_backtrack(result, intermediaryResult, nums, i + 1);
            intermediaryResult.remove(intermediaryResult.size() - 1);
        }
    }

    // Method to generate a string of 2N blanks
    public String get_indent(int N) {
        String S = "";
        for (int i = 0; i < N; i++)
            S = S + "   > ";
        return S;
    }

    public static void main(String[] args) {
        Backtracking101 obj = new Backtracking101();
        //int[] nums = {1,2,3};
        //System.out.println(obj.subsets(nums));

        int digits = 2;
        //obj.printAllBinary(digits);
        //obj.printAllDecimal(digits);
        //obj.diceRollSum(4,7);
        List<String> strings = new ArrayList<>(Arrays.asList("A","B","C"));
        obj.permute(strings);
        //List<String> lists = new ArrayList<>(Arrays.asList("Jane","Bob","Matt","Sara"));
        //obj.sublists(lists);
        //int[] nums = {1,2,3,4};
        //obj.subsets(nums);
    }
}
// Permutations of [a, b, c, d]
//    [a, b, c, d]
//    [a, b, d, c]
//    [a, c, b, d]
//    [a, c, d, b]
//    [a, d, b, c]
//    [a, d, c, b]
//    [b, a, c, d]
//    [b, a, d, c]
//    [b, c, a, d]
//    [b, c, d, a]
//    [b, d, a, c]
//    [b, d, c, a]
//    [c, a, b, d]
//    [c, a, d, b]
//    [c, b, a, d]
//    [c, b, d, a]
//    [c, d, a, b]
//    [c, d, b, a]
//    [d, a, b, c]
//    [d, a, c, b]
//    [d, b, a, c]
//    [d, b, c, a]
//    [d, c, a, b]
//    [d, c, b, a]