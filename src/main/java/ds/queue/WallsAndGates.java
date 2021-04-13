package ds.queue;
import java.util.*;
import java.util.Queue;

public class WallsAndGates {

    private int EMPTY = -9;
    private int GATE  = 0;

    int[] directionA = new int[]{-1, 1, 0, 0};
    int[] directionB = new int[]{ 0, 0, 1, -1};

    private Queue<Cell> queue;

    class Cell{
        int x;
        int y;
        public Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public void wallsAndGates(int[][] rooms) {
        printMatrix(rooms);

        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        queue = new LinkedList<Cell>();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) {
                    queue.offer(new Cell(row, col));
                }
            }
        }
        printMatrix(rooms);

        while (!queue.isEmpty()) {
            printQueue(queue);
            Cell point = queue.poll();
            int row = point.x;
            int col = point.y;
            for(int i=0; i<directionA.length; i++) {
            //for (Pair direction : directions) {
                int r = row + directionA[i];
                int c = col + directionB[i];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                        continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                queue.add(new Cell(r, c));
            //}
            }
            printMatrix(rooms, row, col);
        }
        printMatrix(rooms);
    }

    private void printMatrix(int[][] rooms, int x, int y) {
        System.out.println("After Processing Cell (" +x +"," +y +")");
        printMatrix(rooms);
    }

    private void printQueue(Queue<Cell> q) {
        System.out.print("Processing Queue ");
        for (Iterator<Cell> it = q.iterator(); it.hasNext(); ) {
            Cell cell = it.next();
            System.out.print("("+cell.x +"," +cell.y+") ");
        }
        System.out.println("");
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
        WallsAndGates wg = new WallsAndGates();
        //        int[][] rooms = new int[][]{ {-9,-1, 0,-9},
        //                                     {-9,-9,-9,-1},
        //                                     {-9,-1,-9,-1},
        //                                     { 0,-1,-9,-9}};
        int[][] rooms = new int[][]{    {-9,-1, 0,-9},
                                        {-9,-9,-9,-9},
                                        {-9,-9,-9,-9},
                                        { 0,-1,-9,-9}};

        wg.wallsAndGates(rooms);
    }
}

//            class Pair<L, R>{
//                L l;
//                R r;
//                public Pair(L x ,R y){
//                    this.l = x;
//                    this.r = y;
//                }
//            }
//        private List<Pair<Integer,Integer>> directions;
//        directions =  new ArrayList<Pair<Integer,Integer>>();
//        directions.add(new Pair(-1,0)); // N
//        directions.add(new Pair(1,  0)); // S
//        directions.add(new Pair( 0,  1)); // E
//        directions.add(new Pair(0, -1));  // W
//        for (Pair direction : directions) {
//          int r = row + Integer.intValue(direction.l);
//          int c = col + Integer.intValue(direction.r);
//        }