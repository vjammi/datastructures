package runtime;

public class Ex_05 {
	public static int f(int n) {
		System.out.println(n);
		if (n <= 0) {
			return 1;
		}
		// return f(n - 1) + f(n - 1);
		int k = f(n - 1) + f(n - 1);
		return k;
	}
	
	public static void main(String[] args) {
		int k = f(2);
		System.out.println("result: " +k);
	}		
}
