package ds.arrays;
/*
    0    0    4
    8    6    9
    4    1    1

    4    8    0
    1    6    0
    1    9    4
 */
public class RotateMatrix {

	public static boolean rotate(int[][] matrix) {
		if (matrix.length == 0 || matrix.length != matrix[0].length)
			return false; // Not a square
		int n = matrix.length;
		
		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;
			for(int i = first; i < last; i++) {
				int offset = i - first;
				int top = matrix[first][i]; // save top

				// left -> top
				matrix[first][i] = matrix[last-offset][first]; 			

				// bottom -> left
				matrix[last-offset][first] = matrix[last][last - offset]; 

				// right -> bottom
				matrix[last][last - offset] = matrix[i][last]; 

				// top -> right
				matrix[i][last] = top; // right <- saved top
			}
		}
		return true;
	}
	
	//	public static void main(String[] args) {
	//		int[][] matrix = AssortedMethods.randomMatrix(5, 5, 0, 9);
	//		AssortedMethods.printMatrix(matrix);
	//		rotate(matrix);
	//		System.out.println();
	//		AssortedMethods.printMatrix(matrix);
	//	}

}
