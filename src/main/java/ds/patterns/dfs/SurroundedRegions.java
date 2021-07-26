package ds.patterns.dfs;

/**
    130. Surrounded Regions
    Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
    A region is captured by flipping all 'O's into 'X's in that surrounded region.
    Example 1:
    Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
    Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
    Explanation: Surrounded regions should not be on the border, which means that any 'O' on the border of the board are not
    flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
    Two cells are connected if they are adjacent cells connected horizontally or vertically.
*/
public class SurroundedRegions {

    public void solve(char[][] board) {
        if (board.length == 0) // if board is empty
            return; // Do nothing
        int m = board.length;
        int n = board[0].length;
        char[][] copyOfBoard = new char[m][n];
        // Copy the initial state of the board into the visited board
        for (int i=0; i<m; i++){
            for (int j=0; j<n;j++){
                copyOfBoard[i][j] = board[i][j];
            }
        }

        // Explore the uncaptured regions of the board
        for (int i=0; i<m; i++){
            for (int j=0; j<n;j++){
                if (board[i][j] == 'O'){
                    dfs(i, j, board, copyOfBoard); // Go explore from here
                }
            }
        }

    }

    /*
                               N
                     r-1,c-1  r-1, c    r-1,c+1
        W            r, c-1     r, c    r, c+1       E
                     r+1,c-1  r+1, c    r+11,c+1
                               S
    */
    // N  S  E  W
    int[] x = {-1, 1, 0, 0};
    int[] y = { 0, 0, 1,-1};
    private char dfs(int i, int j, char[][] board, char[][] copyOfBoard){
        if (i<0 || j<0 || i>=board.length || j>=board[0].length)
            return 'R';

        if (board[i][j] == 'X')     // IF a neighbor is X
            return 'X';

        if (copyOfBoard[i][j] == 'V' && board[i][j] == 'O') // The cell we visited previously, while we are on our way exploring further.
            return 'O';

        // For a list of choices - N, S, E, W
        copyOfBoard[i][j] = 'V';
        System.out.println("Visited Cell: "+ i +", " +j);
        int xCount = 0;
        int oCount = 0;
        char north = dfs(i-1, j, board, copyOfBoard);   // N  i+x[i], j+x[j]
        if (north == 'X')  xCount++;
        else if (north == 'O') oCount++;

        char south = dfs(i+1, j, board, copyOfBoard);   // S
        if (south == 'X')  xCount++;
        else if (south == 'O') oCount++;

        char east  = dfs(i,   j+1, board, copyOfBoard); // E
        if (east == 'X')  xCount++;
        else if (east == 'O') oCount++;

        char west  = dfs(i,   j-1, board, copyOfBoard); // W
        if (west == 'X')  xCount++;
        else if (west == 'O') oCount++;

        System.out.println("Counts: " + (xCount+oCount) + " " + board[i][j]);
        if ( oCount == 4 && board[i][j] == 'O'){
            copyOfBoard[i][j] = 'X';
            return 'O';
        }
        if ( (xCount+oCount == 4) && board[i][j] == 'O'){ // && (xCount == 3)???
            board[i][j] = 'X';         //Capture cell
            copyOfBoard[i][j] = 'X';
            System.out.println("Captured: " +board[i][j]);
        }
        else if( (xCount+oCount < 4) && copyOfBoard[i][j] == 'V' ){
            copyOfBoard[i][j] = 'O';   // Reset status
            System.out.println("Reset : " +board[i][j]);
        }
        return board[i][j];
    }

    // TODO: Input [["X","O","X"],["X","O","X"],["X","O","X"]]  Output: [["X","O","X"],["X","O","X"],["X","O","X"]]
    // Accepted:
    // Input [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
    // Output [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
    public static void main(String[] args) {
        SurroundedRegions obj = new SurroundedRegions();
        char[][] board = {{'X','O','X'},{'X','O','X'}, {'X','O','X'}};
        obj.solve(board);
        obj.print(board);
    }

    private void print(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i=0; i<m; i++){
            for (int j=0; j<n;j++){
                System.out.print(board[i][j]+", ");
            }
            System.out.println();
        }
    }

}
