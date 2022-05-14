
package ds.patterns.array;

import java.util.Arrays;

class Pair{
    int lo;
    int hi;

    public Pair(int i, int j){
        this.lo = i;
        this.hi = j;
    }

    public int getLo() {
        return lo;
    }

    public int getHi() {
        return hi;
    }
}

class PairPlusOne {
    int lo;
    int mid;
    int hi;

    public PairPlusOne(int i, int k, int j){
        this.lo = i;
        this.mid = mid;
        this.hi = j;
    }

    public int getLo() {
        return lo;
    }

    public int getHi() {
        return hi;
    }

    public int getMid() {
        return mid;
    }
}

public class TwoThreeSum {

    /**
        {-5, -2, -1, 0, 1, 3, 4, 6};
         ^    ^                  ^
         i   lo                  hi
    **/

    public PairPlusOne threeSum(int[] a, int sumToMatch){
        Arrays.sort(a);
        for (int i = 0; i<a.length-3; i++){
            int lo = i+1;
            int hi  = a.length -1;
            Pair pair = twoSum(a, lo, hi, sumToMatch - a[i]);
            System.out.println(a[i] +", " +pair.getLo() +", "+ pair.getHi());
            if (pair.getLo() != -1 && pair.getHi() != -1){
                return new PairPlusOne(a[i], pair.getLo(), pair.getHi());
            }
        }
        return new PairPlusOne(-1, -1, -1);
    }

    public Pair twoSum(int[] a, int lo, int hi, int sumToMatch){
        while (lo < hi) {
            int sum = a[lo] + a[hi];
            if (sum == sumToMatch) {
                return new Pair(a[lo], a[hi]);
            } else if (sum < sumToMatch) {
                lo++;
            } else if (sum > sumToMatch) {
                hi--;
            }
        }

        return new Pair(-1, -1);
    }

    /**
        {-5, -2, -1, 0, 1, 3, 4, 6};
         ^                       ^
         lo                      hi
    */
    public Pair twoSum(int[] a, int sumToMatch){
        Arrays.sort(a); // log(n)

        int lo = 0;
        int hi = a.length - 1;

        while (lo < hi) {
            int sum = a[lo] + a[hi];
            if (sum == sumToMatch) {
                return new Pair(a[lo], a[hi]);
            } else if (sum < sumToMatch) {
                lo++;
            } else if (sum > sumToMatch) {
                hi--;
            }
        }
        return new Pair(-1, -1);
    }

    public static void main(String args[]){
        int[] a = {-5, -2, -1, 0, 1, 3, 4, 6};

        TwoThreeSum ts = new TwoThreeSum();
        //Pair pair = ts.twoSum(a, 0);
        //System.out.println(pair.getLo() +", "+ pair.getHi());

        PairPlusOne pairPlusOne = ts.threeSum(a, 8);
        System.out.println(pairPlusOne.getLo() +", "+pairPlusOne.getMid() +", " + pairPlusOne.getHi());

    }


}
