package ds.patterns.z.game;

import java.util.Scanner;

/**
 * 348. Design Tic-Tac-Toe
 * Medium
 *
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 *     A move is guaranteed to be valid and is placed on an empty block.
 *     Once a winning condition is reached, no more moves are allowed.
 *     A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 *
 * Implement the TicTacToe class:
 *
 *     TicTacToe(int n) Initializes the object the size of the board n.
 *     int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.
 *
 * Example 1:
 * Input
 * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
 * Output
 * [null, 0, 0, 0, 0, 0, 0, 1]
 *
 * Explanation
 * TicTacToe ticTacToe = new TicTacToe(3);
 * Assume that player 1 is "X" and player 2 is "O" in the board.
 * ticTacToe.move(0, 0, 1); // return 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * ticTacToe.move(0, 2, 2); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * ticTacToe.move(2, 2, 1); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * ticTacToe.move(1, 1, 2); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * ticTacToe.move(2, 0, 1); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * ticTacToe.move(1, 0, 2); // return 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 *
 * Constraints:
 *     2 <= n <= 100
 *     player is 1 or 2.
 *     0 <= row, col < n
 *     (row, col) are unique for each different call to move.
 *     At most n2 calls will be made to move.
 *
 * Follow-up: Could you do better than O(n2) per move() operation?
 * */

public class TicTacToe {

    private final int[][] gameState;

    public TicTacToe(int[][] gameState) {
        this.gameState = gameState;
    }

    public static void main(String[] args) {

        drawBoard(3,3);

        Scanner scanner = new Scanner(System.in);

        boolean winner = false; boolean exitGame = false;
        while (!winner || !exitGame){

            // Gather input from the player1
            int i = scanner.nextInt();
            System.out.println("Input Received " +i);
            refreshBoard(3,3, i, 1, 1);

            if (weHaveAWinner() || i == 100){
                System.out.println("We have a winner. Game over...");
                refreshBoard(3,3, i, 1, 1);
                break;
            }

            // Gather a random input from player2 (system)
            int rand = generateRandomIndex(1,10);
            refreshBoard(3,3, rand, 2, 2);

            if (i==-1){
                System.out.println("Input Received " +i +" exiting ds.patterns.z.game...");
            }
        }
    }

    static int generateRandomIndex(int lo, int hi) {
        return lo + (int) (Math.random() * (hi-lo));
    }

    private static boolean weHaveAWinner() {
        return false;
    }

    private static void refreshBoard(int row, int col, int val, int ii, int jj) {
        System.out.println();
        for (int i=0; i<row; i++){
            //System.out.print("------------\n");
            for (int j=0;j<col; j++) {
                if (i==ii && j==jj)
                    System.out.print("| " +val +" ");
                else
                    System.out.print("| " +'X' +" ");
            }
            System.out.println();
        }
    }

    private static void drawBoard(int row, int col) {
        for (int i=0; i<row; i++){
            //System.out.print("------------\n");
            for (int j=0;j<col; j++) {
                System.out.print("| " +'X' +" ");
            }
            System.out.println();
        }
    }

}
