package ds.patterns.backtracking;

/**
    79. Word Search
    Given an m x n grid of characters board and a string word, return true if word exists in the grid.
    The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are
    horizontally or vertically neighboring. The same letter cell may not be used more than once.

    Input: board = [["A","B","C","E"],
                    ["S","F","C","S"],
                    ["A","D","E","E"]],
            word = "ABCCED"
    Output: true

    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
    Output: true
*/
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board.length == 0)
            return false;


        int m = board.length;
        int n = board[0].length;

        char[][] visited = new char[m][n];


        for (int i=0; i<board.length; i++){
            for (int j=0; j<board[0].length; j++){
                char ch = board[i][j];
                if (ch == word.charAt(0) ) {
                    boolean wordMatchFound = dfs(i, j, board, visited, word, 0);
                    if (wordMatchFound)
                        return true;
                }
            }
        }
        return false;
    }


    /*
                   r-1, c
         r,c-1      r, c    r, c+1
                   r+1, c
    */

    //              N, S, E, W
    int[] rows  = {-1, 1, 0, 0};
    int[] cols  = { 0, 0, 1,-1};

    private boolean dfs(int row, int col, char[][] board, char[][] visited, String word, int index){
        if (index == word.length() )
            return true;

        if ( row < 0 || col < 0 || row >= board.length || col >= board[0].length || visited[row][col] == 1 || board[row][col] != word.charAt(index) )
            return false;


        visited[row][col] = 1; // visit
        boolean north = dfs(row-1, col,   board, visited, word, index+1);
        boolean south = dfs(row+1, col,   board, visited, word, index+1);
        boolean east  = dfs(row,   col+1, board, visited, word, index+1);
        boolean west  = dfs(row,   col-1, board, visited, word, index+1);
        visited[row][col] = 0; // unvisit

        if (north || south || east || west)
            return true;

        return false;
    }

}
