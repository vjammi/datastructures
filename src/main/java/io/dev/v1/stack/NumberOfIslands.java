package io.dev.v1.stack;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int count = 0;
        int[][] visitedGrid = new int[grid.length][grid[0].length];

        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[0].length; j++){
                if (grid[i][j] == '1' && visitedGrid[i][j] == 0){
                    visitIsland(i, j, grid, visitedGrid);
                    count++;
                    System.out.println("\nVisited " +grid[i][j] +" " +count);
                }
            }
        }
        return count;
    }

    public void visitIsland(int i, int j, char[][] grid, int[][] visitedGrid){
        if (i<0 || i>=grid.length || j<0 || j>=grid[0].length
                || grid[i][j] == '0' ||  visitedGrid[i][j] != 0 ){
            return;
        }

        System.out.print(grid[i][j] +", ");
        visitedGrid[i][j] = 9;
        visitIsland(i-1, j, grid, visitedGrid); // N
        visitIsland(i+1, j, grid, visitedGrid); // S
        visitIsland(i, j-1, grid, visitedGrid); // W
        visitIsland(i, j+1, grid, visitedGrid); // E
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
