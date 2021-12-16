package ds.usage;

public class SortArrayBySlidingWindowRange {

    public static void main(String[] args) {

        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        //                i
        //            j
        // windowSize = 3

        int j = 0;
        int windowSize = 0;
        for (int i=0; i< arr.length; i++){
            windowSize ++;

            while(windowSize == 3){
                for (int k=j; k<=i; k++){
                    System.out.print(arr[k] +" ");
                }
                sortRange(arr, j, i);
                for (int k=j; k<=i; k++){
                    System.out.print(arr[k] +" ");
                }
                j++;
                windowSize--;
                System.out.println();
            }
        }

        System.out.println("");
        for (int k=0; k<arr.length; k++){
            System.out.print(arr[k] +" ");
        }
    }


    private static void sortRange(int[] arr, int i, int j){
        for (int l=i+1; l<=j; l++){
            for (int k=l; k>0; k--) {
                if (arr[k] < arr[k - 1])
                    exch(arr, k, k-1);
                else
                    break;
            }
        }
    }

    private static void exch(int[] arr, int k, int l) {
        int tmp = arr[k];
        arr[k] = arr[l];
        arr[l] = tmp;
    }
}

