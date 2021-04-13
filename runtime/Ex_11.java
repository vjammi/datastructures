package runtime;

public class Ex_11 {
	public static void reverse(int[] array) {
	    for (int i = 0; i < array.length / 2; i++) {
	        int other = (array.length - 1) - i;
	        int temp = array[i];
	        array[i] = array[other];
	        array[other] = temp;
	    }
	}
	
	public static void main(String[] args) {
		int[] array = {10, 20, 30, 40, 50, 60, 70, 80, 90};
		for (int x : array) {
			System.out.print(x +",");
		}
		System.out.println();
		reverse(array);
		for (int x : array) {
			System.out.print(x +",");
		}
	}	
}
