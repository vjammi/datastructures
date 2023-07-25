package dev.vjammi.ds.v2.dev.usage.hashing;

public class Hashing {

    public int hashCode(String s){
        int hash = 0;                     //The hash value of the empty string is zero.
        for (int i = 0; i< s.length(); i++){
            char c = s.charAt(i);
            hash = (31 * hash) + (int) c; // Use the ascii value of the character
        }
        return hash;
    }

    public static void main(String[] args) {
        System.out.println(new Hashing().hashCode(""));
        System.out.println(new Hashing().hashCode("STRING"));
    }

}
