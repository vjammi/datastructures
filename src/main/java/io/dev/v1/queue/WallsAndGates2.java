package io.dev.v1.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 You are given an m x n grid rooms initialized with these three possible values.
 -1 A wall or an obstacle.
 0 A gate.
 INF Infinity means an empty room.

 We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

 Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

 Input: rooms = [[2147483647,-1        ,0         ,2147483647],
 [2147483647,2147483647,2147483647,-1        ],
 [2147483647,-1        ,2147483647,-1        ],
 [0         ,-1        ,2147483647,2147483647]]
 Output: [[3,-1,0,1 ],
 [2,2 ,1,-1],
 [1,-1,2,-1],
 [0,-1,3,4 ]]

 Constraints:
 m == rooms.length
 n == rooms[i].length
 1 <= m, n <= 250
 rooms[i][j] is -1, 0, or 231 - 1.

 */

public class WallsAndGates2 {
    int GATE  =  0;
    int WALL  = -1;
    int EMPTY = -9; //2147483647

    class Cell{
        int x;
        int y;
        Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

//    List<int[]> DIRECTIONS = Arrays.asList(
//            new int[] { 1,  0},
//            new int[] {-1,  0},
//            new int[] { 0,  1},
//            new int[] { 0, -1}
//    );
//    int[] directionInX = new int[]{ 1,-1, 0, 0}; // S, N, E, W
//    int[] directionInY = new int[]{ 0, 0, 1,-1}; // S, N, E, W

    int[] directionInX = new int[]{ 0,-1, 0, 1}; // W, N, E, S
    int[] directionInY = new int[]{-1, 0, 1, 0}; // W, N, E, S

    public void wallsAndGates(int[][] rooms) {

        if (rooms.length == 0)
            return;

        printMatrix(rooms);
        Queue<Cell> queue = new LinkedList<Cell>();

        for(int rows = 0; rows < rooms.length; rows++) {
            for(int cols = 0; cols < rooms[0].length; cols++) {
                if (rooms[rows][cols] == GATE) {
                    System.out.println("" + rooms[rows][cols]);
                    queue.add(new Cell(rows, cols));
                }
            }
        }

        printMatrix(rooms);

        while (!queue.isEmpty()){
            Cell cell = queue.poll();
            System.out.println("Cell " + cell.x +" " +cell.y);

            //for (int[] direction : DIRECTIONS) {
            //  int xx = cell.x + direction[0];
            //  int yy = cell.y + direction[1];

            //  if (xx < 0 || xx >= rooms.length  || yy < 0 || yy >= rooms[0].length || rooms[xx][yy] != EMPTY){
            //     continue;
            //  }
            //  rooms[xx][yy] = rooms[cell.x][cell.y] + 1;
            //  queue.add(new Cell(xx, yy));
            //}

            for (int i=0; i<directionInX.length; i++){
                int xx = cell.x + directionInX[i];
                int yy = cell.y + directionInY[i];

                if (xx >= 0 && xx < rooms.length && yy >= 0 && yy < rooms[0].length &&
                        rooms[xx][yy] != WALL && rooms[xx][yy] != GATE){
                    if (rooms[xx][yy] == EMPTY) {
                        queue.add(new Cell(xx, yy));
                        rooms[xx][yy] = rooms[cell.x][cell.y] + 1;
                    }
                }
            }
        }
        printMatrix(rooms);
    }

    private void printMatrix(int[][] rooms) {
        for (int row = 0; row < rooms.length; row++) {
            for (int col = 0; col < rooms[row].length; col++) {
                System.out.print(rooms[row][col] +", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        WallsAndGates2 wg = new WallsAndGates2();
        int[][] rooms1 = new int[][]{{-9,-1, 0,-9},
                {-9,-9,-9,-1},
                {-9,-1,-9,-1},
                { 0,-1,-9,-9}};

        int[][] rooms2 = new int[][]{{-9,-1, 0,-9},
                {-9,-9,-9,-9},
                {-9,-9,-9,-9},
                { 0,-1,-9,-9}};

        wg.wallsAndGates(rooms1);
    }
}
