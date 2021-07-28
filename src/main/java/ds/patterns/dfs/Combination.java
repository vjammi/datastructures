package ds.patterns.dfs;

// Ref: https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
// Java program to print all combination of size r in an array of size n
class Combination {

    // The main function that prints all combinations of size r
    // in arr[] of size n. This function mainly uses combinationUtil()
    static void printCombination(int arr[], int n, int r) {
        // A temporary array to store all combination one by one
        int data[] = new int[r];

        // Print all combination using temporary array 'data[]'
        combination(arr, n, r, 0, data, 0);
    }

    /* arr[]  ---> Input Array
    data[] ---> Temporary array to store current combination
    start & end ---> Staring and Ending indexes in arr[]
    index  ---> Current index in data[]
    r ---> Size of a combination to be printed */
    static void combination(int input[], int n, int r, int index, int chosen[], int i) {
        if (index == r) {
            for (int j = 0; j < r; j++)  System.out.print(chosen[j] + " ");
            System.out.println("");
            return;
        }
        // When no more elements are there to put in data[]
        if (i >= n)
            return;

        // current is included, put next at next location
        chosen[index] = input[i];
        combination(input, n, r, index + 1, chosen, i+1);

        // current is excluded, replace it with next (Note that i+1 is passed, but index is not changed)
        combination(input, n, r, index, chosen, i+1);
    }


    /*Driver function to check for above function*/
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4};
        int r = 2;
        int n = arr.length;
        printCombination(arr, n, r);
    }
}