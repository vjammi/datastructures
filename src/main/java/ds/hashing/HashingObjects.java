package ds.hashing;

import java.util.Arrays;

/**
 * Created by Vijay Jammi on 06/04/2018.
 */
public class HashingObjects {
    private String who;
    private String when ;
    private double amount;

    public HashingObjects(String who, String when, double amount){
        this.who = who;
        this.when = when;
        this.amount = amount;
    }

    public HashingObjects(){
    }

    public int hashCode() {
        // Starts off with a small prime number
        int hash = 17;
        // x = mx+y or 31x+y rule. Multiply hash so far by 31 & Add the hashcode of the field
        hash = 31*hash + who.hashCode();
        hash = 31*hash + when.hashCode();
        // For primitive types take the wrapper types and compute the hashCode
        hash = 31*hash + ((Double) amount).hashCode();
        return hash; // 31x + y rule to return x
    }

    public int hashCode(char[] a){
        int hash = 0; // ??? Should it start off with a prime number?
        for (int i = 0; i < a.length; i++) {
            //hash = a[i] + (31 * hash); //Original
            hash =  (31 * hash) + (int) a[i]; // Use the ascii value of the character at teh ith position of the array (a[])
            //System.out.println("Hash: " +hash);
            //System.out.println(hash +" = "  +a[i] +" + " +(31 * hash) );
        }
        return hash;
    }

    //The hash value of the empty string is zero.
    public int hashCode1(String s){
        int hash = 0;
        for (int i = 0; i< s.length(); i++){
            char c = s.charAt(i);
            // hash =  + (int) c + (31 * hash);
            hash = (31 * hash) + (int) c; // Use the ascii value of the character
            // System.out.println("Hash: " +hash);
        }
        return hash;
    }

    public int hashCode_ArrayOfObjects(String[] s){
        if (s == null){
            return 0;
        }
        int hash = 1;
        for (String element : s) {
            hash = 31 * hash + ( element == null ? 0 : element.hashCode() );
        }
        return hash;
    }

    /** <p>For any two arrays <tt>a</tt> and <tt>b</tt> such that
     * <tt>Arrays.deepEquals(a, b)</tt>, it is also the case that
     * <tt>Arrays.deepHashCode(a) == Arrays.deepHashCode(b)</tt>.
     */
    private int computeDeepHashCode(String[] strarray) {
        return Arrays.deepHashCode(strarray);
    }

    public static void main (String[] args){
        char[] firstName = {'v','i','j','a','y'};
        char[] lastName = {'j','a','m','m','i'};

        HashingObjects h = new HashingObjects();
        System.out.println("hashCode()  " + h.hashCode(firstName));
        System.out.println("hashCode1() " +h.hashCode1("vijay"));
        //The hash value of the empty string is zero.
        System.out.println("hashCode1() " +h.hashCode1(""));
        //double amt = 149.5;
        //Hashing_Objects ht1 = new Hashing_Objects("Vijay Jammi", "06/05/2018", amt);
        //System.out.println(ht1.hashCode());
        String[] strArr = {"Vijay", "Jammi", "43"};
        System.out.println("Hash Code for an Array of Strings:  " +h.hashCode_ArrayOfObjects(strArr));
        System.out.println("Deep HashCode of an Array of Strings : " +h.computeDeepHashCode(strArr));
    }

}
