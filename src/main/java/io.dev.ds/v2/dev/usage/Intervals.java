package dev.vjammi.ds.v2.dev.usage;

import java.util.*;

public class Intervals {

    private static class IntegerComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer a, Integer b){
            return Integer.valueOf(a).compareTo(Integer.valueOf(b));
        }
    }

    public void sortArrayOfNumbers(int[] nums){
        Arrays.sort(nums);
        System.out.println(nums);
    }

    public void sortArrayOfNumbers(Integer[] nums){
        Arrays.sort(nums);
        System.out.println(nums);
    }

    public void sortArrayOfIntegers(List<Integer> list){
        Collections.sort(list);
        Collections.sort(list, new IntegerComparator());
        System.out.println(list);
    }



    private static class StringComparator implements Comparator<String>{
        public int compare(String a, String b) {
            //return (b + a).compareTo(a + b);
            return Character.valueOf(a.charAt(0)).compareTo(b.charAt(0));
        }
    }

    private String sortStringTest(String string){
        System.out.println(string);
        char[] chars = string.toCharArray();        // method on input string to create a char array for input string.
        Arrays.sort(chars);                         // method to sort char array.
        String sortedString = new String(chars);    // Use String class constructor to create a sorted string from char array. String is immutable in java, hence in third step we have to create a new string.
        System.out.println(sortedString);
        return sortedString;
    }

    private void sortStringArraysTest1() {
        String[] strArr = {"4", "81", "92", "12"};
        Arrays.sort(strArr, new StringComparator()); // (a, b) -> (b + a).compareTo(a + b)
        for (String str: strArr)
            System.out.print(str +" ");
    }

    private void sortStringArraysTest2() {
        String[] strArr = {"DEF", "JKL", "ABC", "GHI"};
        Arrays.sort(strArr);
        for (String str: strArr)
            System.out.print(str +" ");
    }




    class IntervalComparator implements Comparator<int[]>{
        public int compare(int[] a, int[] b){
            return a[0]-b[0];
        }
    }

    class IntArrComparator implements Comparator<int[]>{
        public int compare(int[] arr1, int[] arr2){
            return Integer.valueOf(arr1[0]).compareTo(arr2[0]);
        }
    }

    private void sortIntArraysTest() {
        int[][] intArrays = new int[][]{{5, 4}, {1,4}, {8,1}, {9,2}, {2,2}};
        //Arrays.sort(intArrays, new IntArrComparator());
        Arrays.sort(intArrays, new IntervalComparator());
        System.out.println(intArrays);
        for (int[] arr: intArrays)
            System.out.println(arr[0] +", "+arr[1] +"   ");
    }



    private class ListComparator implements Comparator<Interval>{
        public int compare(Interval interval1, Interval interval2){
            return Integer.valueOf(interval1.a).compareTo(interval2.a);
        }
    }

    class Interval {
        int a;
        int b;
        public Interval(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    private void sortListTest() {
        List<Interval> list = new ArrayList();
        list.add(new Interval(5,10) );
        list.add(new Interval(4,7) );
        list.add(new Interval(1,7) );
        list.add(new Interval(3,5) );
        Collections.sort(list, new ListComparator());
        System.out.println(list);
    }

    public static void main(String[] args) {
        Intervals obj = new Intervals();

        obj.sortStringTest("algorithm");

        obj.sortStringArraysTest1();
        obj.sortStringArraysTest2();

        //obj.sortIntArraysTest();
        //obj.sortStringArraysTest();
        //obj.sortListTest();
        //obj.sortArrayOfNumbers(new int[]{6,5, 7,4, 9});
        //obj.sortArrayOfNumbers(new Integer[]{6,5, 7,4, 9});
    }

}
