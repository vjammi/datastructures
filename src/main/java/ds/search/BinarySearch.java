package ds.search;

/**
 * Created by Vijay Jammi on 05/31/2018.
 */
public class BinarySearch {

    public BinarySearch(){
    }

    private int search(int[] a, int key, int low, int high) {
        while (low <= high) {
            int mid = (low + high)/2;
            if (a[mid] == key) {
                System.out.println("Found the key [" +key +"] at mid[" + mid +"] Low["+low +"] High["+high+"]");
                return mid;
            } else if (key < a[mid]) {
                high = mid - 1;
            } else if (key > a[mid]) {
                low = mid + 1;
            }
        }
        return -1; // or low???
    }

    private void search_recurssively_print_keyindex(int[] a, int key, int low, int high) {
        if (low > high) {
            return;
        }
        int mid = (low + high)/2;
        if (key < a[mid]) {
            high = mid - 1;
            search_recurssively_print_keyindex(a, key, low, high);
        } else if (key > a[mid]) {
            low = mid + 1;
            search_recurssively_print_keyindex(a, key, low, high);
        }else{
            System.out.println(">>> Key ["+key +"] Found at mid [" + mid +"] Low [" +low +"] High [" +high +"]");
        }
    }

    private int search_recurssively_return_keyindex(int[] a, int key, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high)/2;
        if (key < a[mid]) {
            high = mid - 1;
            return search_recurssively_return_keyindex(a, key, low, high);
        } else if (key > a[mid]) {
            low = mid + 1;
            return search_recurssively_return_keyindex(a, key, low, high);
        }else{
            System.out.println(">>> Key ["+key +"] Found at mid [" + mid +"] Low [" +low +"] High [" +high +"]");
            return mid;
        }
    }

    private int search_practice(int[] a, int key){
        int low = 0;
        int high = a.length - 1;

        while(low <= high){
            int mid = (low + high)/2;

            System.out.println("|_Now searching the array using indexes - Low: "+low +" Mid: "+mid + " High: " +high);

            if (key == a[mid]){
                System.out.println(">>>Key Found. Index: "+mid);
                return mid;
            }else if (key < a[mid]){
                high = mid - 1;
                System.out.println(" |_Updating High. New High: " +high);
            }else if(key > a[mid]){
                low = mid +1;
                System.out.println(" |_Updating Low. New Low: " +low);
            }
        }
        return low;
    }

    public int getIndex(int[] a, int key, int low, int high){
        if (a.length == 0)  return -1;
        return  search(a, key, low, high);
    }

    public static void main(String[] args){
        BinarySearch search = new BinarySearch();

        int[] a = {10,15,17,19,20,31,44,55,77,99, 100, 101,102, 106, 109, 110};
        int low = 0;
        int high = a.length - 1;

        for (int i = 0; i < a.length ; i++) {
             search.search_recurssively_print_keyindex(a, a[i], low, high);
        }

        for (int i = 0; i < a.length ; i++) {
            int index = search.search_recurssively_return_keyindex(a, a[i], low, high);
        }

        for (int i = 0; i < a.length ; i++) {
            int returnedIndex = search.getIndex(a, a[i], low, high);
        }
  }

}


/*
    15/Odd - {10,15,17,19,20,31,44,55,  77,99,100, 101,102,106,109};

    looking for key: 77 Low:0 High:14
    NotFound yet.... Low:0 High:14 Mid: 7
    NotFound yet - Low:8 High:14 Mid: 11
    NotFound yet - Low:8 High:10 Mid: 9
   >Before Returning low - Low:8 High:8
    Retruned Index: 8

    looking for key: 99 Low:0 High:14
    NotFound yet.... Low:0 High:14 Mid: 7
    NotFound yet - Low:8 High:14 Mid: 11
   >Found the key at index mid:9 Low:8 High:10
    Retruned Index: 9

    looking for key: 100 Low:0 High:14
    NotFound yet.... Low:0 High:14 Mid: 7
    NotFound yet - Low:8 High:14 Mid: 11
    NotFound yet.... Low:8 High:10 Mid: 9
   >Before Returning low - Low:10 High:10
    Retruned Index: 10

16/Even - {10,15,17,19,20,31,44,55,77,99, 100, 101,102, 106, 109, 110}

    looking for key: 77 Low:0 High:15
    NotFound yet.... Low:0 High:15 Mid: 7
    NotFound yet - Low:8 High:15 Mid: 11
    NotFound yet - Low:8 High:10 Mid: 9
    Before Returning low - Low:8 High:8
    Retruned Index: 8

    looking for key: 99 Low:0 High:15
    NotFound yet.... Low:0 High:15 Mid: 7
    NotFound yet - Low:8 High:15 Mid: 11
    Found the key at index mid:9 Low:8 High:10
    Retruned Index: 9

    looking for key: 100 Low:0 High:15
    NotFound yet.... Low:0 High:15 Mid: 7
    NotFound yet - Low:8 High:15 Mid: 11
    NotFound yet.... Low:8 High:10 Mid: 9
    Before Returning low - Low:10 High:10
    Retruned Index: 10
*/
