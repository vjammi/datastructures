package ds.arrays;

public class TwoDimArrayIterations {
    /**
        Java builds multi-dimensional arrays from many one-dimensional arrays, the so-called
        "arrays of arrays" approach.

        There are a couple of interesting consequences of this:
            Rows may be different sizes.
            Also, each row is an object (an array) that can be used independently.

        2-Dim Arrays - Array of 1-Dim Arrays - Array of Arrays
        int[][] twoDimArray = {
                                 {1, 2, 3, 4, 5},
                                 {6, 7, 8, 9, 10},
                                 {11, 12, 13, 14, 15},
                                 {16, 17, 18, 19, 20},
                                 {21, 22, 23, 24, 25}
                                 };

        3-Dim Arrays - Array of 2-Dim Arrays - Array of Array of Arrays
        int[][][] threeDimArray = {
                                    {
                                     {1, 2, 3},
                                     {4, 5, 6},
                                     {7, 8, 9}
                                    },
                                    {{10, 11, 12},
                                     {13, 14, 15},
                                     {16, 17, 18}
                                    },
                                    {{19, 20, 21},
                                     {22, 23, 24},
                                     {25, 26, 27}
                                    }
                                  };
    */
    public static void main(String[] args) {
        int[][] twoDimArray = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        printElementsOfA2DimArray(twoDimArray);
        printMatrixDiagonally(twoDimArray);
        print2DimArraySpirally(twoDimArray, 5, 5);
        itreateCornersOf2DimArray(twoDimArray, 5, 5);
        //itreateCornersOf2DimArrayPractice(twoDimArray,5,5);
    }
    private static void printElementsOfA2DimArray(int[][] arr) {
        System.out.println("Print all elements of a matrix");
        for (int i = 0; i < arr.length; i++) {
            int[] row = arr[i];
            for (int j = 0; j < row.length /* j < arr[i].length*/; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printMatrixDiagonally(int[][] arr) {
        System.out.println("Print diagnolly from left to right");
        for (int row = 0; row < arr.length; row++) {
            System.out.print(arr[row][row] + " ");
        }
        System.out.println();
        System.out.println("Print diagnolly from right to left");
        int col = arr.length - 1;
        for (int row = 0; row < arr.length; row++) {
            System.out.print(arr[row][col] + " ");
            col--;
        }
        System.out.println();
    }

    private static void print2DimArraySpirally(int[][] matrix, int m, int n) {
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        int dir = 0;

        System.out.println("\nPrint elements of a matrix Sprially");
        while (top <= bottom && left <= right) {

            if (dir == 1) {
                for (int k = left; k <= right; k++) {
                    System.out.print(matrix[top][k] + " ");
                }
                top++;
            } else if (dir == 2) {
                for (int k = top; k <= bottom; k++) {
                    System.out.print(matrix[k][right] + " ");
                }
                right--;
            } else if (dir == 3) {
                for (int k = right; k >= left; k--) {
                    System.out.print(matrix[bottom][k] + " ");
                }
                bottom--;
            } else if (dir == 4) {
                for (int k = bottom; k >= top; k--) {
                    System.out.print(matrix[k][left] + " ");
                }
                left++;
            }
            dir = (dir + 1) % 4;
        }
    }

    private static void itreateCornersOf2DimArray(int[][] arr, int m, int n) {
        int top = 0, left = 0, bottom = m - 1, right = n - 1;
        int dir = 1;

        System.out.println("\nPrint elements -  itreateCornersOf2DimArray");
        while (top <= bottom && left <= right && dir <= 4) {

            if (dir == 1) {
                for (int i = left; i < right; i++) {
                    System.out.print(arr[top][i] + " ");
                }
            } else if (dir == 2) {
                for (int i = top; i < bottom; i++) {
                    System.out.print(arr[i][right] + " ");
                }
            } else if (dir == 3) {
                for (int i = right; i >= left; i--) {
                    System.out.print(arr[bottom][i] + " ");
                }
            } else if (dir == 4) {
                for (int i = bottom; i >= top; i--) {
                    System.out.print(arr[i][left] + " ");
                }
            }
            dir++;
        }
    }

    private static void itreateCornersOf2DimArrayPractice(int[][] arr, int m, int n) {
        int top = 0, left = 0, bottom = m - 1, right = n - 1;
        int dir = 0; // 0,1,2,3

        while (left < right && top < bottom && dir <= 3) { // dir <= 3 && ???
            if (dir == 0) {
                for (int i = left; i < right; i++) {
                    System.out.print(arr[top][i]);
                }
                System.out.println("");
            } else if (dir == 1) {
                for (int i = top; i < bottom; i++) {
                    System.out.print(arr[i][right]);
                }
                System.out.println("");
            } else if (dir == 2) {
                for (int i = right; i > left; i--) {
                    System.out.print(arr[bottom][i]);
                }
                System.out.println("");
            } else if (dir == 3) {
                for (int i = bottom; i > top; i--) {
                    System.out.print(arr[i][left]);
                }
                System.out.println("");
                top++;
                left++;
                right--;
                bottom--;
                dir = 0;
            }
            dir++;
        }

    }



    /**
     int top = 0, left = 0, bottom = m-1, right = n-1;
     int dir = 0;
     System.out.println("\nPrint corner elements of a matrix");
     while (top<=bottom && left<=right && dir<=3){
     if (dir == 0){
     for (int k=left; k<=right; k++){System.out.print(arr[top][k] +" ");}
     }else if (dir == 1){
     for (int k=top; k<=bottom; k++){System.out.print(arr[k][right] +" ");}
     }else if (dir == 2){
     for (int k=right; k>=left; k--){System.out.print(arr[bottom][k] +" ");}
     }else if (dir == 3){
     for (int k=bottom; k>=top; k--){System.out.print(arr[k][left] +" ");}
     }
     dir++;
     }
     */
}

