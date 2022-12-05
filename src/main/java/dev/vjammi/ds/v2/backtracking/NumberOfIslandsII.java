package dev.vjammi.ds.v2.backtracking;

import java.util.List;

/**
     305. Number of Islands II
     You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
     We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
     Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
     An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

     Example 1:
     Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
     Output: [1,1,2,3]
     Explanation: Initially, the 2d grid is filled with water.
         - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
         - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
         - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
         - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
 */
public class NumberOfIslandsII {

    /**
     * Reuse the code for Problem 200: Number of Islands, for each addLand operation,
     * just call the numIslands function of Problem 200 to get the number of islands after performing that operation.

     * Time complexity  : O(L×m×n) where L is the number of addLand operations, m is the number of rows and n is the number of columns.
     * Space complexity : O(m×n) for the grid and visited 2D arrays.
    * */
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        return null;
    }

}
