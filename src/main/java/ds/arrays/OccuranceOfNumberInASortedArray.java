package ds.arrays;
/**
 * Created by Vijay Jammi on 06/15/2017.
 */
public class OccuranceOfNumberInASortedArray {

	public int search(int[] a, int x, int n) {

		int low = 0;
		int high = n - 1;
		int result = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (a[mid] == x) {
				result = mid;
				System.out.println("position of  x " + " is " + result);
				return result;
			} else if (x < a[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return result;
	}

	public int searchFirst(int[] a, int x, int n) {

		int low = 0;
		int high = n - 1;
		int result = -1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (a[mid] == x) {
				result = mid;
				high = mid - 1; // keep searching to the left / lower indexes
			} else if (x < a[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return result;
	}

	public int searchLast(int[] a, int x, int n) {
		int low = 0;
		int high = n - 1;
		int result = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (a[mid] == x) {
				result = mid;
				//System.out.println("position of  x " + " is " + result);
				low = mid + 1; // keep searching to the right / higher indexes
			} else if (x < a[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return result;
	}

	public int searchNumberRecurssively(int[] a, int x, int low, int high) {
		//int low = 0;
		//int high = a.length - 1;
		int result = -1;
		if (low <= high){
			int mid = (high + low ) / 2;
			if (a[mid] == x ){
				System.out.println("X found @ position " +mid + " Length: "+ a.length);
				result = mid;
				return result;
			}else if ( a[mid] <= x ){
				System.out.println("a[mid] < x ");
				low = a[mid] + 1;
				searchNumberRecurssively(a, x, low, high);
			}else if ( a[mid] >= x ){
				System.out.println("a[mid] > x ");
				high = a[mid] - 1;
				searchNumberRecurssively(a, x, low, high);
			}			
		}		
		return result;
	}

	
	// >= / <= or > / < ???
	public int searchNumberIteratively(int[] a, int x, int low, int high) {
		//int low = 0;
		//int high = a.length - 1;
		int result = -1;
		while (low <= high){
			int mid = (high + low ) /2;
			if (a[mid] == x ){
				System.out.println("X found @ position " +mid + " Length: "+ a.length);
				result = mid;
				break;
			}else if ( a[mid] <= x ){
				System.out.println("a[mid] < x ");
				low = a[mid] + 1;
			}else if ( a[mid] >= x ){
				System.out.println("a[mid] > x ");
				high = a[mid] - 1;
			}			
		}		
		return result;
	}
	

	public static void main(String args[]) {
		OccuranceOfNumberInASortedArray binarySearch = new OccuranceOfNumberInASortedArray();
		int a[] = { 0, 1, 1, 1, 2, 2, 3, 4, 5, 5, 5, 4, 5, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9 };
		
		//int firstOccurance = binarySearch.searchFirst(a, 5); 	// returns first occurance
		//int lastOccurance = binarySearch.searchLast(a, 5);   	// returns last occurance	
		//System.out.println("Fisrt Occurance: " +firstOccurance +" Last Occurance: "+lastOccurance + " Number of Occurances: " + (lastOccurance - firstOccurance) );
		
		
		 binarySearch.searchNumberIteratively(a, 8, 0, a.length-1);
		binarySearch.searchNumberRecurssively(a, 8, 0, a.length-1);
		
	}

}
