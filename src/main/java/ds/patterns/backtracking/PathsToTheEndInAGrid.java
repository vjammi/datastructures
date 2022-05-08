package ds.patterns.backtracking;

public class PathsToTheEndInAGrid {


    /**
     *                              paths(start, end)
     *
     *              paths(a, end)                           paths(b, end)
     *
     *      paths(d, end)   paths(c, end)            paths(c, end)   paths(e, end)
     *
     *
     *      Runtime is O(2^M??? or N^2) since m == n its O(N^2)
     *
     *
     *
     *   Notice that we are computing the path paths(c, end) twice above. so we can do memoization
     *
     *   Reference: https://youtu.be/P8Xa2BitN3I?t=351
     *
     **/
    public int countPaths(boolean[][] grid, int i, int j){
        if (!isValidSquare(grid, i, j)) return 0;     // when out of bounds 0 paths to the end
        if (isAtEnd(grid, i, j)) return 1;          // there is only on path at the end
        return countPaths(grid, i, j) + countPaths(grid, i, j); // otherwise number of paths to the end = num of paths from left to the end + num of paths from right to the end
    }

    // With Memoization
    public int countPaths(boolean[][] grid, int row, int col, int[][] memo) {
        if (!isValidSquare(grid, row, col))
            return 0;

        if (isAtEnd(grid, row, col))
            return 1;

        if (memo[row][col] == 0) {
            memo[row][col] = countPaths(grid, row + 1, col, memo) + countPaths(grid, row, col + 1, memo);
        }

        return memo[row][col];
    }


    private boolean isAtEnd(boolean[][] grid, int i, int j) {
        return false;
    }

    private boolean isValidSquare(boolean[][] grid, int i, int j) {
        return false;
    }


    public static void main(String[] args) {

    }
}
