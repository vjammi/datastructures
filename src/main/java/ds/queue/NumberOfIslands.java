package ds.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
        Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
        An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
        You may assume all four edges of the grid are all surrounded by water.

        Constraints:
             m == grid.length
            n == grid[i].length
            1 <= m, n <= 300
            grid[i][j] is '0' or '1'.

        Example 1:
                    Input: grid = [
                      ["1","1","1","1","0"],
                      ["1","1","0","1","0"],
                      ["1","1","0","0","0"],
                      ["0","0","0","0","0"]
                    ]
        Output: 1

        Example 2:
                    Input: grid = [
                      ["1","1","0","0","0"],
                      ["1","1","0","0","0"],
                      ["0","0","1","0","0"],
                      ["0","0","0","1","1"]
                    ]
        Output: 3

     */
public class NumberOfIslands {
    int[][] visitedGrid;
    int[] directionInX = new int[]{ 0,-1, 0, 1}; // W, N, E, S
    int[] directionInY = new int[]{-1, 0, 1, 0}; // W, N, E, S

    private class Cell {
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
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
                System.out.println("Evaluating " + grid[cell.x][cell.y] +" at location " +cell.x +"," +cell.y);
                queue2.add(cell);

                while(!queue2.isEmpty()) {
                    Cell cell2 = queue2.poll();

                    if (grid[cell2.x][cell2.y] == '1' && visitedGrid[cell2.x][cell2.y] == 1) {
                        for (int i = 0; i < directionInX.length; i++) {
                            int row = cell2.x + directionInX[i];
                            int col = cell2.y + directionInY[i];

                            if (row >= 0 && row < grid.length && col >= 0 &&
                                    col < grid[0].length && grid[row][col] != '0') {
                                queue2.add(new Cell(row, col));
                            }
                        }
                        visitedGrid[cell2.x][cell2.y] = 9;
                    }

                }
                //printMatrix(visitedGrid);
                numberOfIslands++;
                System.out.println(">> numberOfIslands " +numberOfIslands);
            }else{
                System.out.println(" > Skipping val " + grid[cell.x][cell.y] +" at location "
                        +cell.x +"," +cell.y);
            }
        }

        return numberOfIslands;
    }

    private void printMatrix(int[][] rooms) {
        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[row].length; col++) {
                System.out.print(rooms[row][col] +", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        NumberOfIslands obj = new NumberOfIslands();
        char[][] grid = new char[][]{   {'1','1','1','1','0'},
                                        {'1','1','0','1','0'},
                                        {'1','1','0','1','0'},
                                        {'0','0','0','0','0'} };
        System.out.println("Number of numberOfIslands " +obj.numIslands(grid));
    }
}
