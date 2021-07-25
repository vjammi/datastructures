# Depth First Search
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
         [] -  b  -  e
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
    // Graph DFS Implementation    
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
        String digit = String.valueOf(digits.charAt(level));
        String characters = map.get(digit);
        for(char character: characters.toCharArray()){
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

    // Valid Combination for n = 3  [((())) , (()()), (())(), ()(()), ()()()] 
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
        if (n==0) return combinations;
        dfs(n, combinations, new StringBuilder(""), 0, 0);
        return combinations;        
    }  

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

### 39	Combination Sum
### 40	Combination Sum II
### 216	Combination Sum III
### 377	Combination Sum IV
### 78	Subsets
### 90  Subsets II        

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
    
### Number of Enclaves
### Supported Regions
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