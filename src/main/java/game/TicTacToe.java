package game;

import java.util.Scanner;

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
                System.out.println("Input Received " +i +" exiting game...");
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
