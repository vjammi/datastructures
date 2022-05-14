package ds.patterns.binarysearch;

/**
 * ### 2 Given a sorted array of integers, find the number of times the array has been rotated.
 *             0   1   2   3   4   5   6   7   8   9
 *     nums = [15, 16, 17, 18, 19, 20, 21, 12, 13, 14]
 *                             ^
 *                                     ^
 *             --------------------------  ^   ------
 *  An important property to note is that
 *     	The pivot element is lesser than its left and right
 *
 *     	For example here is a normal sorted array
 *			[	 /	]
 *			[  / 	]
 *			[/		]
 *
 * 		But here is the same array rotated.
 *  		[  /   ]
 *  		[/	/  ]
 *
 *      To find the number of times it has been sorted, we find the peak/pivot where the pivot element is lesser than its left and right
 * */
public class NumberOfTimesArrayHasBeenRotated {

	// runtime O(n)
	private int timesArrayHasBeenRotated_NaiveLinearSearch(int[] a) {
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

	public int timesArrayHasBeenRotated_BinarySearchRecursive(int[] a, int low, int high) {
		if (low > high)
			return 0;

		int mid = low + (high - low) / 2;

		if (a[mid] < a[mid-1] && a[mid] < a[mid+1]) {
			return mid;
		} else if (a[low] < a[mid]) {
			timesArrayHasBeenRotated_BinarySearchRecursive(a, mid + 1, high);
		} else  if (a[mid] < a[high]) {
			timesArrayHasBeenRotated_BinarySearchRecursive(a, low, mid - 1);
		}else {
			System.out.println("????");
		}

		return -1;
	}

	// O(log(n)) - Number of times the array has been rotated ? It turns out that it is the index of the element. In other words we need to find the index of the min element in the array.
	private int timesArrayHasBeenRotated_BinarySearchIterative(int[] a) {
		int low = 0 ;
		int size = a.length;
		int high = size -1;

		// Case 1 -- Array is already sorted, so return the index min element in the array, or just 0
		if (a[low] <= a[high]){
			return low; // return low or 0 times
		}

		// Array has been rotated - so lets find the number of times it has been rotated
		while (low <= high){
			int mid = (low+high)/2;
			// ??? int nxt = (mid+1)%size;	int prev = ((mid+size)-1)%size;
			int next = mid + 1;
			int previous = mid - 1;

			if (a[mid] <= a[next] && a[mid] <= a[previous]){ // Case 2 -- segment no sorted. find mid/pivot element. for pivot element both left and right  return mid
				return mid;
			}else if (a[mid] >= a[low]){  // Case 3 -- Left half array is sorted, so drop Left half from the search space
				low = mid+1;
			}else if (a[mid] <= a[high]){ // Case 4-- right half array is sorted, so drop right half from the search space
				high =  mid-1;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		NumberOfTimesArrayHasBeenRotated obj = new NumberOfTimesArrayHasBeenRotated();
		int arr1[] = {11, 12, 15, 18,  22, 25, 9, 10};

		//		int minIndex1 = obj.timesArrayHasBeenRotated_NaiveLinearSearch(arr1);
		//		System.out.println("Number of times array has been rotated [Naive] - Min Index: " +minIndex1 +" Min Index Value: " + arr1[minIndex1]);
		//
		//		int minIndex2 = obj.timesArrayHasBeenRotated_BinarySearchIterative(arr1);
		//		System.out.println("Number of times array has been rotated [BinarySearch] - Min Index: " +minIndex2 +" Min Index Value: " + arr1[minIndex2]);
		//
		//		int arr2[] = {9, 10, 11, 12, 15, 18,  22, 25};
		//		int minIndex3 = obj.timesArrayHasBeenRotated_BinarySearchIterative(arr2);
		//		System.out.println("Number of times array has been rotated [BinarySearch] - Min Index: " +minIndex3 +" Min Index Value: " + arr2[minIndex3]);

		int times = obj.timesArrayHasBeenRotated_BinarySearchRecursive(arr1, 0, arr1.length-1);
		System.out.println("Times an array has been rotated: " +times);

	}

}
