package ds.patterns.backtracking;

/**
    200. Number of Islands
    Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
    Example 1:
    Input: grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    Output: 1
*/
public class NumberOfIslands {

    /**
     * Complexity Analysis
     * Time complexity : O(M×N) where M is the number of rows and N is the number of columns.
     * Space complexity : worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×N deep.
     **/
    public int numIslands(char[][] grid) {
        int numOfIslands = 0;
        if (grid.length == 0)
            return numOfIslands;

        int m = grid.length;
        int n = grid[0].length;
        int[][] visitedGrid = new int[m][n]; // Will be all initialized to 0's

        for (int i=0; i<m; i++){
            for (int j=0; j<n;j++){
                if (grid[i][j] == '1' && visitedGrid[i][j] == 0){
                    dfs(i, j, grid, visitedGrid); // Go explore from here
                    numOfIslands++;
                }
            }
        }

        for (int i=0; i<m; i++){
            for (int j=0; j<n;j++){
                System.out.print(visitedGrid[i][j]+", ");
            }
            System.out.println();
        }
        return numOfIslands;
    }

    /*
                               N
                     r-1,c-1  r-1, c    r-1,c+1
        W            r, c-1     r, c    r, c+1       E
                     r+1,c-1  r+1, c    r+1,c+1
                               S
    */
    // N  S  E  W
    int[] x = {-1, 1, 0, 0};
    int[] y = { 0, 0, 1,-1};
    private void dfs(int i, int j, char[][] grid, int[][] visitedGrid){
        if (i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j] == '0' || visitedGrid[i][j] == 9 )
            return;

        // For a list of choices - N, S, E, W
        visitedGrid[i][j] = 9;
        System.out.println("Visited Cell: "+ i +", " +j);
        dfs(i-1, j, grid, visitedGrid);   // N  i+x[i], j+x[j]
        dfs(i+1, j, grid, visitedGrid);   // S
        dfs(i,   j+1, grid, visitedGrid); // E
        dfs(i,   j-1, grid, visitedGrid); // W
    }

}
