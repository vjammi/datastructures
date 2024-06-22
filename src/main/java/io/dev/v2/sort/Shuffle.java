package io.dev.v2.sort;

public class Shuffle {


    public void shuffleStringArray(String[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = generateRandomIndex(i, n-1); // between i and n-1
            System.out.println("i="+i +", r="+r);
            exch1(a, i, r);
        }
    }

    private int generateRandomIndex(int lo, int hi) {
        return lo + (int) (Math.random() * (hi-lo));
    }

    // Shuffle with a random number between 0 and i-1
    public void shuffleIntArray(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            //int r = generateRandomIndex(0, i-1); // between 0 and i-1
            int r = generateRandomIndex(i, n-1); // between i and n-1
            System.out.print("i="+i +" r="+r +" | ");
            exch2(a, i, r);
        }
    }

    public void exch1(String[] a, int i, int j) {
        String swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public void exch2(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // take as input an array of strings and print them out to standard output
    public void show(String[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private void printArray1(String[] a) {
        System.out.println();
        for (int k=0; k<a.length; k++){
            System.out.print(a[k]+", ");
        }
    }

    private void printArray2(int[] a) {
        System.out.println();
        for (int k=0; k<a.length; k++){
            System.out.print(a[k]+", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Shuffle shuffle = new Shuffle();

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,  12, 13, 14,  15, 16, 17};
        shuffle.printArray2(arr);
        shuffle.shuffleIntArray(arr);
        shuffle.printArray2(arr);
    }
}


    /*
    Random rand = new Random();

    For 0 to 49.
        int value = rand.nextInt(50);

    For 1 to 50
        rand.nextInt((max - min) + 1) + min;
     *//*

    private int generateRandom2(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private int generateRandom3(int max){
        Random r = new Random();
        return r.nextInt(max);
    }
    */
