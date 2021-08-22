# Depth First Search - Exhaustive Search and backtracking
DFS search is a way to exhaustively search thru all the nodes of a tree or a graph by searching all the way down one path before coming back to search other paths.

## Print all Binary
Typically we have 1 recursive call at each level. But notice the multiple recursive calls at each level - for 0 and 1. Similar to left and right for tree.
This is because we are exploring all of the options. Here we are going to choose 0 first and then 1. We are going to exhaustively search the space of binary numbers.
If we want to exhaustive explore a space, your function call will often make multiple successor calls - one for eeach possible choice it could make.
The predecessor function call does some work, and pass the output to the current function call.
```
    public void printAllBinary(int digits){
        printAllBinaryBacktrack(digits, "");
        printAllBinaryBacktrack(digits, new ArrayList<String>());
        printAllBinaryHelper(digits, "");
    }
    public void printAllBinaryBacktrack(int digits, List<String> output){
        // The recursive call enters into one of the blocks at anytime - if + return or the implicit else block we stop the recursive call from going beyond the if block here by using a return
        if (digits == 0){
            System.out.println(output);
            return;
        }
        output.add("0");                      // Make a selection and later backtrack
        printAllBinaryBacktrack(digits-1, output);
        output.remove(output.size()-1); // Notice the backtracking

        output.add("1");                    // Make a selection and later backtrack
        printAllBinaryBacktrack(digits-1, output);
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
```

## Print all Decimal
Observation: When the set of digit choices available is large, using a loop to enumerate, results in shorter code (this is okay!)
Note: loop over choices, not decisions.
If the number of choices is variable, will need to use a loop, e.g., chess game.
```
    public void printAllDecimal(int digits){
        printAllDecimal(digits, new ArrayList<Integer>() );
        printAllDecimalHelper(digits,"");
    }
    public void printAllDecimal(int digits, ArrayList<Integer> output){
        if (digits == 0){
            System.out.println(output);
            return;
        }
        for (int i=0; i<10; i++){
            output.add(i);
            printAllDecimal(digits-1, output);
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
```
## Dice Roll Sum
```
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
```

## 17 Letter Combinations of a Phone Number
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
Return the answer in any order. A mapping of digit to letters (just like on the telephone buttons) is given below.
Notice that level 0 does not map to any digits/letters. Can also be said as find all permutations of letters formed from digits
Input: digits = "23"
            2 = abc
            3 = def 
Output: ["ad","ae","af",
         "bd","be","bf",
         "cd","ce","cf"]

Tree Representation of digits [23] to their letters
```
               2       3
          -------------- 
                  -  d
               a  -  e
            /     -  f
                  -  d
         [] -  b  -  e
                  -  f
            \     -  d
               c  -  e
                  -  f
```
Alternate Tree Representation of digits [234] to their letters
```
                                                 []

            level0              a               b             c               2=abc
                                0               1             2
            level1          d   e   f       d   e   f     d     e   f         3=def
                            0   1   2
            level2         ghi ghi ghi     ghi ghi ghi   ghi   ghi ghi        4=ghi
                           012

    BaseCase  level3    [adg adh adi aeg aeh aei ....                       ]

```
Steps          
- Starting with the first digit, we list all the possible letters it can represent.
- Then for each of letters for the first digit, we list out all possible letters of the next digit
- We continue until we have considered all the digits in out input.
- Each of the possible paths is a possible combination we will want in our solution set
- Now if we start an empty string on the top, note that our solution space now looks like a tree.
- We want whatever search algorithm we implement, we need to get to all the endpoints at the bottom

Implementation
```
    // Time complexity be O(3^n), which came from O(3+3²+3³+…+3^n)
    // Space Complexity: O(2x3^N)
    public List<String> letterCombinations(String digits) {
        if (digits ==null || digits.length() == 0)
            return new ArrayList<String>();
        Map<String, String> map = buildNumToCharsMap();
        List<String> result = new ArrayList<>();
        combinations(digits, map, new Stack<String>(), result, 0);
        return result;
    }

    private void combinations(String input, Map<String, String> map, Stack<String> chosen, List<String> result, int level) {
        String indent = get_indent(level);
        if (level == input.length()) { // 0>1>2  when level/2 == digits.length()/2 return and backtrack
            result.add(new String(chosen.toString()));
            System.out.println(indent +input +" OUT(" +level +")" +chosen);
            return;
        }
        String digit = String.valueOf(input.charAt(level));
        String characters = map.get(digit);
        for(char character: characters.toCharArray()){
            System.out.println(indent +input +" L(" +level +"-"+character+") " +chosen);
            chosen.push(String.valueOf(character));
            combinations(input, map, chosen, result,level+1);
            chosen.pop();
        }
    }
```

### 22 Generate Parentheses
Recursive tree view of the Generate Parenthesis for n=2, with output [ [(())], [()()] ]
```
                                                     []
                             (                                                    x )
              ((                          ()
        x(((     (()                ()(          x())
             x(()(  (())OUT     x()((  OUT()()

```
Base Case 1 - When we have a total of n valid left and n valid right parenthesis - we add the choice to our result
```
       if ( left == n && right == n )
            we gather the results
```
Base Case 2 - When   right > left - will prevent adding right parenthesis before the left  - x())
Base Case 3 - When   left  > n    - will ensure we do not add more than n left braces      - x((((
              When   right > n    - will ensure we do not add more than n right braces     - x((( ))))
```
       if ( right > left || left > n || right > n )
            we return / backtrack
```

```
    // Valid Combination for n = 3  [((())) , (()()), (())(), ()(()), ()()()] 
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        StringBuilder chosen = new StringBuilder("");
        if (n == 0)
            return result;

        dfs(n, 0, 0, chosen, result, 0);
        return result;
    }

    // Runtime - exponential - O(2^n) [O(2^2n)]
    public void dfs(int n, int left, int right, StringBuilder chosen, List<String> result){
        // When we have a total of n valid left and n valid right parenthesis - we add the choice to our result
        if( left == n && right == n ){
            result.add(chosen.toString());
            System.out.println("*** Valid Combination: " +chosen +"***"); System.out.println(indent +n +" OUT["+chosen +"] " +chosen);
            return;
        }

        // *** right > left - will prevent adding right parenthesis before the left  - )))(((
        //     left > n     - will ensure we do not add more than n left braces      - ((((
        //     right > left - will ensure we do not add more than n right braces   - ((())))
        if( right>left || left > n || right >n ){
            return;
        }

        chosen.append("(");
        dfs(n, left+1, right, chosen, result);
        chosen.deleteCharAt(chosen.length()-1);   // Going downhill - we remove the parenthesis that was added uphill

        chosen.append(")");
        dfs(n, left, right+1, chosen, result);
        chosen.deleteCharAt(chosen.length()-1);   // Doing downhill - we remove the parenthesis that was added uphill

    }
```

### 46 Permutations    

```
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
                      0 [3]    []
            0 [2,3]   -  2  -  3
               1      1 [2]    []  
             /        -  3  -  2
            /
           /     0     
          /      -  1  -  3
      []  -    2 1   
          \      -  3  -  1
           \
            \    -  1  -  2
               3  
                 -  2  -  1

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length ==  0)
            return permutations;

        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs(nums, path, permutations, visited);
        return permutations;
    }

    public void dfs(int[] nums, List<Integer> path, List<List<Integer>> permutations, boolean[] visited){
        if (path.size() == nums.length){            
            permutations.add(new ArrayList<>(path)); // *** Deep copy of the list 
            return;
        }

        for (int i=0;i<nums.length; i++){
            if (!visited[i]){     
                path.add(nums[i]);
                visited[i] = true;
                dfs(nums, path, permutations, visited);
                path.remove(path.size()-1); 
                visited[i] = false;
            }
        }
    }
```
### 47. Permutations II
```
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> result = new HashSet();
        List<List<Integer>> result2 = new ArrayList();
        List<Integer> input = new ArrayList();

        for (Integer num: nums) input.add(num);
        List<Integer> chosen = new ArrayList<>();
        permuteHelper(input, chosen, result, 0);
        result2.addAll(result);

        return result2;
    }

```


##  BackTracking
### Permutations of ABC
Permuting 4 elements is - Picking 1 and permuting the other 3
Permutations of [A B C]
```
	ABC	BC	ABC
			ACB
		AC	BAC
			BCA
		AB  CAB
			CBA

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
```
Implementation
```
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
                //chosen.remove(chosen.size()-1); // Remove the last last element that was added
                chosen.pop(); // Remove the last last element that was added
                input.add(i, s); // Add/Put the earlier chosen element back into the input list.
            }
        }
```
Analyzing the Time Complexity of Permutations
```
    1. void perm(String str){
    2.    perm(str, "");
    3.  }
    4.
    5. void perm(String str, String prefix){
    6.     if(str.length() == 0){
    7.         System.out.println(prefix);
    8.     }
    9.     for(int i = 0; i < str.length(); i++){
    10.        String rem = str.substring(0, i) + str.substring(i + 1);
    11.        perm(rem, prefix + str.charAt(i));
    12.    }
    13. }
```
https://media.geeksforgeeks.org/wp-content/uploads/recInPermutation_1-3.jpg

1. How many times does function perm get called in its base case?
As we can understand from the recursion explained above that for a string of length 3 it is printing 6 permutations which is actually 3!. This is because if it needs to generate permutation, it is needed to pick characters for each slot. If there are 3 characters in our string, in the first slot, there are 3 choices, 2 choices for the next slot (for each of 3 choices earlier, i.e multiplication and not addition) and so on. This tells that there are n! permutations being printed in the base case which is what is shown in the image.

2. How many times does function perm get called before its base case?
Consider that lines 9 through 12 are hit n number of times. Therefore, there will be no more than (n * n!) function calls.

3. How long does each function call take?
Since, each character of string prefix needs to be printed, thus executing line 7 will take O(n) time. Line 10 and line 11 will also take O(n) time combined due to string concatenation, as sum of rem, prefix and str.charAt(i) will always be n. Each function call therefore corresponds to O(n) work.

4. What is the total runtime?
Calling perm O(n * n!) times (as an upper bound) and each call takes O(n) time, the total runtime will not exceed O(n^2 * n!).
Source: Geek for Geeks and CTCI by Gayle Laakmann McDowell


## Combinations of Input = ABC, k = 2
```
        > [A, B, C] L(0-A) []
        >    > [A, B, C] L(1-B) []
        >    >    > [A, B, C] L(2-C) []
        >    >    >    > [A, B, C] X(3) []
        >    >    > [A, B, C] R(2-C) []
        >    >    >    > [A, B, C] X(3) [C]
        >    > [A, B, C] R(1-B) []
        >    >    > [A, B, C] L(2-C) [B]
        >    >    >    > [A, B, C] X(3) [B]
        >    >    > [A, B, C] R(2-C) [B]
        >    >    >    > [A, B, C] OUT(3) [B, C]
        > [A, B, C] R(0-A) []
        >    > [A, B, C] L(1-B) [A]
        >    >    > [A, B, C] L(2-C) [A]
        >    >    >    > [A, B, C] X(3) [A]
        >    >    > [A, B, C] R(2-C) [A]
        >    >    >    > [A, B, C] OUT(3) [A, C]
        >    > [A, B, C] R(1-B) [A]
        >    >    > [A, B, C] L(2-C) [A, B]
        >    >    >    > [A, B, C] OUT(3) [A, B]
        >    >    > [A, B, C] R(2-C) [A, B]
        >    >    >    > [A, B, C] X(3) [A, B, C]
        [[B, C], [A, C], [A, B]]
```
```
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
```
### 77  Combinations            https://leetcode.com/problems/combinations/
```
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> chosen = new Stack();
        List<Integer> input = new ArrayList();
        dfs(1, chosen, result, n, k);
        return result;
    }
    private void dfs(int input, Stack<Integer> chosen, List<List<Integer>> result, int n, int k){
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
```
### 39	Combination Sum         https://leetcode.com/problems/combination-sum/
### 40	Combination Sum II      https://leetcode.com/problems/combination-sum-ii/
### 216	Combination Sum III     https://leetcode.com/problems/combination-sum-iii/
### 377	Combination Sum IV      https://leetcode.com/problems/combination-sum-iv/

### 46 Permutations             https://leetcode.com/problems/permutations/
### 47 Permutations II          https://leetcode.com/problems/permutations-ii/

### 131 Palindrome Partition    https://leetcode.com/problems/palindrome-partitioning/

### 78	Subsets                 https://leetcode.com/problems/subsets/
Option 1
```
    // Time complexity is O(n*2^n) ???
    // Space complexity is O(2^n). ???
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
```
Option 2
```
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
```
Option 3
```
    private void constructSubset(String input, String chosen, List<String> chosenList, int index, int n) {
        String indent = get_indent(n);
        if(index >= input.length()) {
            chosenList.add(chosen); System.out.println(indent +" Result \"" +chosen +"\"");
            return;
        }
        System.out.println(indent +" -(L:" +input.charAt(index) +") "+chosen);
        constructSubset(input, chosen, chosenList, index + 1, n+1);
        chosen = chosen + input.charAt(index);
        System.out.println(indent +" +(R:" +input.charAt(index) +") "+chosen);
        constructSubset(input, chosen, chosenList, index + 1, n+1);
    }
```
```
-----------------------
A    B    C    BaseCase
0    1    2    3
------------------------
> -(L:A)
>    > -(L:B)
>    >    > -(L:C)
>    >    >    >  Result ""
>    >    > +(R:C) C
>    >    >    >  Result "C"
>    > +(R:B) B
>    >    >  -(L:C) B
>    >    >    >  Result "B"
>    >    >  +(R:C) BC
>    >    >    >  Result "BC"
>  +(R:A) A
>    >  -(L:B) A
>    >    >  -(L:C) A
>    >    >    >  Result "A"
>    >    >  +(R:C) AC
>    >    >    >  Result "AC"
>    >  +(R:B) AB
>    >    >  -(L:C) AB
>    >    >    >  Result "AB"
>    >    >  +(R:C) ABC
>    >    >    >  Result "ABC"
[, C, B, BC, A, AC, AB, ABC]

```
### 90. Subsets II              https://leetcode.com/problems/subsets-ii/

## 2. Number of Islands.
```
Input: grid = [["1","1","0","0","0"],
               ["1","1","0","0","0"],
               ["0","0","1","0","0"],
               ["0","0","0","1","1"] ]
Output: 3

DFS
    public int numIslands(char[][] grid) {
        int count = 0; 
        int[][] visitedGrid = new int[grid.length][grid[0].length];
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if (grid[i][j] == '1' && visitedGrid[i][j] == 0){
                    visitIsland(i, j, grid, visitedGrid);
                    count++;
                }
            }
        }
        return count;
    }

    public void visitIsland(int i, int j, char[][] grid, int[][] visitedGrid){
        if (i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j] == '0' ||  visitedGrid[i][j] != 0 )
            return;
        
        visitedGrid[i][j] = 9;
        
        visitIsland(i-1, j, grid, visitedGrid); // N
        visitIsland(i+1, j, grid, visitedGrid); // S
        visitIsland(i, j-1, grid, visitedGrid); // W
        visitIsland(i, j+1, grid, visitedGrid); // E
    }

BFS
    int[] directionInX = new int[]{ 0,-1, 0, 1}; // W, N, E, S
    int[] directionInY = new int[]{-1, 0, 1, 0}; // W, N, E, S
    int[][] visitedGrid;

    private class Cell {
        int x; int y;
        public Cell(int x, int y) {this.x = x; this.y = y; }
    }

    public int numIslands(char[][] grid) {
        Queue<Cell> queue = new LinkedList<>();
        visitedGrid = new int[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if (grid[i][j] == '1' ){
                    queue.add(new Cell(i, j));
                    visitedGrid[i][j] = 1;
                    System.out.println(grid[i][j] +" at location " +i +"," +j);
                }
            }
        }

        int numberOfIslands = 0;
        Queue<Cell> queue2 = new LinkedList<>();
        while(!queue.isEmpty()){
            Cell cell = queue.poll();
            if (grid[cell.x][cell.y] == '1' && visitedGrid[cell.x][cell.y] == 1) {                
                queue2.add(cell);
                while(!queue2.isEmpty()) {
                    Cell cell2 = queue2.poll();
                    if (grid[cell2.x][cell2.y] == '1' && visitedGrid[cell2.x][cell2.y] == 1) {
                        for (int i = 0; i < directionInX.length; i++) {
                            int row = cell2.x + directionInX[i];
                            int col = cell2.y + directionInY[i];
                            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] != '0')
                                queue2.add(new Cell(row, col));
                        }
                        visitedGrid[cell2.x][cell2.y] = 9;
                    }
                }
                numberOfIslands++;                
            }else{ System.out.println(" > Skipping val " + grid[cell.x][cell.y] +" at location "  +cell.x +"," +cell.y); }
        }
        return numberOfIslands;
    }
```
### 200 Number of Islands
### 1020 Number of Enclaves     https://leetcode.com/problems/number-of-enclaves/
### 130 Surrounded Regions
### Work Search 
### Smallest Rectangle
### Enclosing Black Pixels

## 3. N-Queens
### Valid Sudoku


# Clearing Up Exponential Complexity - Difference between ```O(s^8) vs O(8^s).```

### An example of a recursion with O(8^s)?
In the non-linear word search, from any position there are a maximum of 8 choices, so any recursive call can lead to up to 8 more! (One for each direction)

Consider a board that of w width, h height, if we are searching for a word with length s.

1. For ```s=1``` and an ```initial position```, there is no recursion. Either we found the correct letter, or we didn’t.
2. For ```s=2```, and an ```initial position (i, j)```, there are 8 calls. This is 8^1 = 8 calls.
```
     (i−1, j−1)  (i−1, j)  (i−1, j+1)
                \    |    /
                  \  |  /
     (i, j−1) - -  (i,j)  - - (i, j+1)
                /    |    \
               /     |      \
    (i+1, j−1)    (i+1, j)   (i+1, j+1)
```

```
        Direction   (row, col)
        ----------------------
        northwest   (i−1, j−1),
        north       (i−1, j),
        northeast   (i−1, j+1),
        east        (i, j+1),
        southeast   (i+1, j+1),
        south       (i+1, j),
        southwest   (i+1, j−1),
        west        (i, j−1)
```
3. Now consider s=3. For each of the 8 positions from s=2, we can try 8 more positions. So that is ```8×8 = 8^2 = 64 total calls```.

So to determine ```s=i```, we could repeat this approach recursively by looking at the previous step, ```s=i−1```.
Each time we add a step, we are multiplying by another ```8```, because every position from ```s=i−1``` can try ```8 more positions```.

In general, our solution looks like ```8^(s−1) = 8^s ∗ 8^−1```. Since ```8^−1 is just a constant```, so we can say ```O(8^s)```.

- This isn’t the whole picture though. Let’s consider a few cases:
```w×h = 50, 000, s = 2? s = 4? s = 50,000?```
```w×h = 4, s = 2? s = 4? s = 50,000?```

### Now how we would write a recursion of O(s^8)?
```
    int func(int s, int layer){
        if(layer==0){ return 1; }
        int ret = 0;
        //Make s calls
        for(int i=0; i<s; i++){
            ret += func(s,layer-1);
        }
        return ret;
    }
```
1. func(1,8); ```// 1^8 = 1```
2. func(2,8); ```// 2^8 = 256```
3. func(3,8); ```// 3^8 = 6,561```
4. func(4,8); ```// 4^8 = 65,536```



## Permutations & Combinations

### Permutations - How many ways can we award a 1st, 2nd and 3rd place prize among eight contestants? (Gold / Silver / Bronze)?
We use permutations since the order we need to hand out these medals matters.
To order 3 people out of 8, we start with all options (8) then take away one at a time (7, then 6) until we run out of medals.

If we have n items and want to find the number of ways k items can be ordered
    P(n,k)                   = n!/(n-k)!
    Permute(8,3) = 8!/(8-3)! = 8*7*6

### Combinations - How many ways can I give 3 tin cans to 8 people or How many ways can we rearrange 3 people?
Well, in this case, the order we pick people doesn’t matter.
We have 3 choices for the first person, 2 for the second, and only 1 for the last.
So we have 3*2*1 ways to re-arrange 3 people.
If you have N people and you want to know how many arrangements there are for all of them, it’s just N factorial or N!



References
BackTracking
https://youtu.be/wiBPsaJs2yQ
https://iitd-plos.github.io/col100/lec/exhaustive_search.html
http://web.stanford.edu/class/cs106b/
https://medium.com/algorithms-and-leetcode/backtracking-e001561b9f28
https://www.geeksforgeeks.org/time-complexity-permutations-string/

Permutations & Combinations
https://betterexplained.com/articles/easy-permutations-and-combinations/
https://youtu.be/TBnPkKxXPu8


