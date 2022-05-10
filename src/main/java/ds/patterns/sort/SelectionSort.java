package ds.patterns.sort;

/**
 * Created by Vijay Jammi on 06/19/2018.
 */
public class SelectionSort{

    int[] a = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};

    public void sort(){
        printArray();
        for(int i=0; i<a.length; i++){

            int min = i;
            // In the ith iteration we look for the smallest remaining entry to the right of i, starting j=i+1
            for(int j=i+1; j < a.length; j++){
                if ( a[j] < a[min]){
                    min = j;
                }
            }
            exch(i, min); // Swap the smallest element to the right of i, with the ith element
            printArray();
            // Remember that the the last element swaps it with itself at the end.
        }
        printArray();
    }

    private void printArray() {
        System.out.println();
        for (int k=0; k<a.length; k++){
            System.out.print(a[k]+", ");
        }
    }

    private void exch(int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args){
        SelectionSort selSort = new SelectionSort();
        selSort.sort();
    }
}
