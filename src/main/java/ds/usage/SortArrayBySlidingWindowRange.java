package ds.usage;

public class SortArrayBySlidingWindowRange {

    public static void main(String[] args) {

        int[] arr = {10,9,8,7,6,5,4,3,2,1};
        //                j
        //            i
        // currWinSize = 3

        int i = 0;
        int currWinSize = 0;
        for (int j=0; j< arr.length; j++){
            currWinSize ++;

            while(currWinSize == 3){
                for (int k=i; k<=j; k++){
                    System.out.print(arr[k] +" ");
                }
                sortRange(arr, i, j);
                for (int k=i; k<=j; k++){
                    System.out.print(arr[k] +" ");
                }
                i++;
                currWinSize--;
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

