package ds.patterns.dfs;

/**
    You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
    A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary
    of the grid. Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any
    number of moves.
* */
public class NumberOfEnclaves {
    // We flood-fill the land by changing 1 to 0, starting from the boundary of the grid.
    // If we find a 1 we dfs to finding all its connected '1's, and set them to '0'.
    // Finally, we count the remainder land.

    // Runtime: O(n * m), where n and m are the dimensions of the grid.
    // Memory: O(n * m). DFS can enumerate all elements in the worst case, and we need to store each element on the stack for the recursion.

    public int numEnclaves(int[][] grid) {
        int numEnclaves = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        iterateAllSides(grid, rows, cols);

        System.out.println("Top Row");
        for (int c = 0; c < cols; c++) {
            System.out.print(grid[0][c] +", ");
            // dfs(...) // Turn land to water, for all connected cells
        }
        System.out.println("Bottom Row");
        for (int c = 0; c < cols; c++) {
            System.out.print(grid[rows-1][c] +", ");
            // dfs(...) // Turn land to water, for all connected cells
        }
        System.out.println("Left Col");
        for (int r = 0; r < rows; r++) {
            System.out.print(grid[r][0] +", ");
            // dfs(...) // Turn land to water, for all connected cells
        }
        System.out.println("Rightmost Col");
        for (int r = 0; r < rows; r++) {
            System.out.print(grid[r][cols-1] +", ");
            // dfs(...) // Turn land to water, for all connected cells
        }

        // Finally, count the leftover land

        System.out.println();
        return numEnclaves;
    }

    private static void iterateAllSides(int[][] grid, int m, int n) {
        int top = 0, left = 0;
        int bottom = m-1, right = n-1;
        int dir = 1;

        System.out.println("Print Elements in teh order TL>TR, T>B, BL<BR, B>T");
        while (top <= bottom && left <= right && dir <= 4) {

            if (dir == 1) { //TL>TR
                for (int i = left; i < right; i++) {
                    System.out.print(grid[top][i] + " ");
                }
            } else if (dir == 2) { // T>B,
                for (int i = top; i < bottom; i++) {
                    System.out.print(grid[i][right] + " ");
                }
            } else if (dir == 3) { // BL<BR
                for (int i = right; i >= left; i--) {
                    System.out.print(grid[bottom][i] + " ");
                }
            } else if (dir == 4) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(grid[i][left] + " ");
                }
            }
            dir++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        NumberOfEnclaves obj = new NumberOfEnclaves();
        int[][] grid = {{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        //        int[][] grid = {{1,1,1,1},
        //                        {2,2,2,2},
        //                        {3,3,3,3},
        //                        {4,4,4,4}};
        obj.numEnclaves(grid);
        obj.print(grid);
    }

    private void print(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i=0; i<m; i++){
            for (int j=0; j<n;j++){
                System.out.print(grid[i][j]+", ");
            }
            System.out.println();
        }
    }

}
