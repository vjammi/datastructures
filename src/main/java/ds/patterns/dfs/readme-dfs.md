## Depth First Search
DFS search is a way to search thru all the nodes ofg a tree or a graph by searching all the way down one path before coming back to search other paths.

## 1. LC 17 Letter Combinations of a Phone Number
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. 
Return the answer in any order. A mapping of digit to letters (just like on the telephone buttons) is given below. 
Note that 1 does not map to any letters.

Input: digits = "23"
            2 = abc
            3 = def 
Output: ["ad","ae","af",
         "bd","be","bf",
         "cd","ce","cf"]

Tree Representation of digits to their letters
```
               2       3
          -------------- 
                  -  d
               a  -  e
            /     -  f
                  -  d
         "" -  b  -  e
                  -  f
            \     -  d
               c  -  e
                  -  f
```
Steps          
    - Starting with the first digit, we list all the possible letters it can represent. 
    - Then for each of letters for the first digit, we list out all possible letters of the next digit  
    - We continue until we have considered all the digits in out input. 
    - Each of the possible paths is a possible combination we will want in our solution set
    - Now if we start an empty string on the top, note that our solution space now looks like a tree.
    - We want whatever search algorithm we implement, we need to get to all the endpoints at the bottom  

```
    Map<String, String> map;
    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits ==null || digits.length() == 0)
            return new ArrayList<String>();
        map = pupulateNumToCharsMap();
        combinations(0, digits, new Stack<String>());
        return result;
    }
    
    private void combinations(int level, String digits, Stack<String> intermediaryResult) {
        if (level == digits.length()) { // 0>1>2  when level/2 == digits.length()/2 return and backtrack
           String res = "";
            Object[] charArr = new Object[intermediaryResult.size()];
            intermediaryResult.copyInto(charArr);
            for(int i=charArr.length-1; i>=0; i--){
               res =  (String)charArr[i] + res;
            }
            result.add(res);
            return;
        }
        String digit = digits.charAt(level)+"";
        char[] characters = map.get(digit).toCharArray();
        for(char character: characters){
            // 134 - a> d g h i  e g h i  f g h i  b d g h i  e g h i  f g h i  c >d g h i  eg h i f g h i
            //System.out.print(character +" "); //23 - a > d e f  b >d e f  c >d e f
            intermediaryResult.push(String.valueOf(character));
            combinations(level+1, digits, intermediaryResult);
            if (!intermediaryResult.isEmpty())
                intermediaryResult.pop();
        }
        return;
    }

```
### 22 Generate Parentheses
```    
    // Valid Combination for n = 3 
    // [((())) , (()()), (())(), ()(()), ()()()]
    public void dfs(int n, List<String> combinations, StringBuilder current, int left, int right){
        if( left == n && right == n ){
            combinations.add(current.toString());  System.out.println("*** Valid Combination: " +current +"***");
            return;
        }

        //  right>left will prevent adding right parenthesis being added before the left  - )))(((
        //  left > n will ensure we do not add more than n left braces      - ((((
        //  right>left will ensure we do not add more than n right braces   - ((())))
        if( right>left || left > n || right >n ){
            return;
        }
        dfs(n, combinations, current.append("("), left+1, right);
        current.deleteCharAt(current.length()-1);   // Going downhill - we remove the parenthesis that was added uphill
        dfs(n, combinations, current.append(")"), left, right+1);
        current.deleteCharAt(current.length()-1);   // Doing downhill - we remove the parenthesis that was added uphill

        return;
    }
```

### 46 Permutations    
### 39	Combination Sum
### 40	Combination Sum II
### 216	Combination Sum III
### 377	Combination Sum IV
### 78	Subsets
### 90  Subsets II        

## 2. Number of Islands.
    
### Number of Enclaves
### Supported Regions
### Work Search 
### Smallest Rectangle
### Enclosing Black Pixels
 
 
## 3. N-Queens
### Valid Sudoku
    