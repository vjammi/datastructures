package ds.arrays;

public class NumberOfTimesArrayHasBeenRotated {
	
	private int linearSearch(int[] a) {
		int min = a[0];
		int minIndex = 0;		
		for (int i=1; i<a.length; i++){
			if (a[i] < min){
				min = a[i];
				minIndex = i;
				System.out.println("MinIndex " +i +" Value: " + a[i]);
			}			
		}		
		return minIndex;
	}
		
	private static int numberOfTimesArrayWasRotated(int[] a) {
		int low = 0 ;
		int high = a.length-1;
		System.out.println("Low: " +low + " High " + high);
		
		while (low <= high){
			
			if (a[low] <= a[high]){	// case1
				return low;
			}
			
			int mid = (low+high)/2;
			int next = (mid+1)% a.length;
			int previous = ((mid + a.length) - 1) % a.length;			
			System.out.println("Previous: " +previous + " Next " + next);
			
			if (a[mid] <= a[previous] && a[mid] <= a[next]){ //Case 2				
				System.out.println("Found the pivot element " + a[mid] +" at position " +mid);
				return mid;
			}else if (a[mid] >= a[low]){ // Case 3 -- Left half array is sorted, so drop Left half from the search space
				System.out.println("Setting low = mid + 1");
				low = mid+1;
			}else if (a[mid] <= a[high]){ // Case 4-- right half array is sorted, so drop right half from the search space
				System.out.println("Setting high =  mid-1");
				high =  mid-1;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		NumberOfTimesArrayHasBeenRotated array = new NumberOfTimesArrayHasBeenRotated();
		
		int a[] = {11, 12, 13, 14,  3, 4, 5, 6, 7, 8, 9,10};
		int minIndex = array.linearSearch(a);
		System.out.println("Number of times array has been rotated: " +minIndex +" Min Index Value: " + a[minIndex]);
		
		int count = array.numberOfTimesArrayWasRotated(a); 
		System.out.println("Number of times array has been rotated: " +count +" Min Index Value: " + a[count]);
		
		int position = array.searchElementInACircularArray(a, 5); 
		System.out.println("Number of times array has been rotated: " +position +" Min Index Value: " + a[position]);

		
	}

	private int searchElementInACircularArray(int[] a, int x) {
		int low = 0;
		int high = a.length;
		int N = a.length;
		while (low <= high){ // there is atleat 1 element in the array
			int mid = low + high / N;
			
			if (a[mid] == x){
				return mid;				
			}else if (a[mid] >= a[low] ){ // left side is sorted 
				//if (x )
				
				
				
			}else if (a[mid] <= a[high]){ // right side is sorted 
				
			}
			
		}
		
		
		return -1;
	}






}
