package io.dev.v2.backtracking;

public class PathsToTheEndInAGrid {


    /**
     *   Intuition: If we consider the below 4x4 grid with
     *              Starting Point (0,0)
     *              Ending   Point (3,3)
     *
     *    We navigate from start to the end, using a BFS, picking the left and right cells of a cell.
     *    The search is done breadth wise, until we find the ending point/cell and by not going over the grid [out of bounds]
     *    Starting from cell (0,0), which is the starting point/cell and ending at cell (3,3)
     *
     *    We could also do a DFS here but that might not be the quickest way to get to the end,
     *      since it exhaustively considers each neighboring cell,
     *      does not specify a direction and might lead us in a direction that could be completely off from the target/ending point, which will then need to be backtracked/corrected
     *
     *    However, a breadth first search leads us in the direction of the ending point.
     *
     *                      Col1        Col2        Col3        Col4
     *              Row1    (0,0)       (0,1)       (0,2)       (0,3)
     *              Row2    (1,0)       (1,1)       (1,2)       (1,3)
     *              Row3    (2,0)       (2,1)       (2,2)       (2,3)
     *              Row4    (3,0)       (3,1)       (3,2)       (3,3)
     *
     *                                               (0,0)
     *
     *                              (1,0)                           (0,1)
     *
     *                      (2,0)           (1,1)            (1,1)          (0,2)
     *
     *                (3,0)     (2,1)   (2,1)    (1,2)  (2,1)    (1,2)   (1,2)    (0,3)
     *
     *         (4,0)x (3,1)  (3,1)   (2,2)  (3,1) (2,2) (2,2)(1,3)
     *                      3,2
     *                    3,3 END  (3,2) (2,3)
     *                             (3,3)END (3,3)END
     *
     * Runtime is O(2^M??? or N^2) since m == n its O(N^2)
     *
     * ------------------------------------------------------------------------------------------
     *              Note: ??? Representation does not look right
     *                                 paths(start, end)
     *              paths(a, end)                           paths(b, end)
     *      paths(d, end)   paths(c, end)            paths(c, end)   paths(e, end)
     *   Notice that we are computing the path paths(c, end) twice above. so we can do memoization
     * -------------------------------------------------------------------------------------------
     *   Reference: https://youtu.be/P8Xa2BitN3I?t=351
     *
     **/

    private void countPathsToTheEnd() {

    }

    private void pathToTheEnd() {

    }




    public static void main(String[] args) {
        PathsToTheEndInAGrid obj = new PathsToTheEndInAGrid();
        obj.pathToTheEnd();
        obj.countPathsToTheEnd();
    }



    /*
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
*/


}
