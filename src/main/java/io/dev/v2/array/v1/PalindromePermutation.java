package io.dev.v2.array.v1;

public class PalindromePermutation {

    public static void main(String[] args) {
        //String s = "tactcoapapa";
        String s = "catttac";
        //String s = "cac";
        //String s = "asdfghjkl";
        PalindromePermutation pp = new PalindromePermutation();
        pp.checkPalindromePermutation_solution1(s);
        //pp.checkPalindromePermutation_solution2(s);
    }
    // Using a single integer (as a bit vector)
    public int checkPalindromePermutation_solution3(String s){

        return 0;
    }

    /*  Map each character to a number.
        a -> 0, b -> 1, c -> 2, etc. This is case insensitive.
        Non-letter characters map to -1.
    */
    int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z
                ) {
            return val - a;
        }
        return -1;
    }

    // Check the number of odd counts as we go. toward the end we have our answer
    public int checkPalindromePermutation_solution2(String s) {
        int asscii_a = 'a';
        int asscii_z = 'z';
        int asscii_A = 'A';
        int asscii_Z = 'Z';
        Print.print("a : " + asscii_a + " - z " + asscii_z);
        Print.print("a : " + asscii_A + " - z " + asscii_Z);

        Print.print("a : " + +Character.getNumericValue('a') + " - z " + Character.getNumericValue('z'));
        Print.print("A : " + Character.getNumericValue('A') + " - Z " + Character.getNumericValue('Z'));
        Print.print("Mod Checks:  0%2 " + 0 % 2 + ", 5%2 " + 5 % 2 + ", 6%2 " + 6 % 2 + ", (-5%2) " + (-5 % 2) + ", (-6%2) " + (-6 % 2));

        int charIndAtA = Character.getNumericValue('a');
        int arraySize = (Character.getNumericValue('z') - Character.getNumericValue('a')) + 1;
        Print.print("arraySize: " + arraySize);
        int[] table = new int[arraySize];
        int oddCount = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = s.charAt(i);
            Print.print("Ascii: " + ascii);
            int cnt = table[ascii - asscii_a];
            Print.print("Count: " + cnt);
            if (cnt == 0) { // even
                table[ascii - asscii_a]++;
                oddCount++;
                Print.print(" Odd++: " + ascii + "-" + cnt);
            } else if (cnt % 2 == 0) { // Odd Count
                oddCount--;
                Print.print(" Odd--: " + ascii + "-" + cnt);
            } else {
                Print.print("odd??? : " + ascii + "-" + cnt);
            }
        }
        Print.print("returning Count " + oddCount + "-" + (s.length() - oddCount));
        return oddCount;
    }

    // Checking the number of odd counts at the end
    public boolean checkPalindromePermutation_solution1(String s) {
        Print.print("Mod Checks:  0%2 " + 0 % 2 + ", 5%2 " + 5 % 2 + ", 6%2 " + 6 % 2 + ", (-5%2) " + (-5 % 2) + ", (-6%2) " + (-6 % 2));

        // Dealing with lower case only for now
        int ascii_a = 'a'; // Character.getNumericValue('a')
        int ascii_z = 'z'; // Character.getNumericValue('z')
        Print.print("a : " + ascii_a + " - z " + ascii_z);

        int arraySize = ascii_z - (ascii_a - 1);
        Print.print("arraySize: " + arraySize);
        int[] arr = new int[arraySize];

        for (int i = 0; i < s.length(); i++) {
            int ascii = s.charAt(i);
            arr[ascii - ascii_a]++;
            Print.print("Ascii: " + ascii + " ascii-ascii_a: " + (ascii - ascii_a));
        }

        int oddCount = 0;
        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            if (val % 2 != 0) {
                oddCount++;
            }
        }
        // Checking the number of odd counts at the end
        if (oddCount > 1) {
            Print.print("returning oddCount " + oddCount + " " + false);
            return false;
        } else {
            Print.print("returning oddCount " + oddCount + " " + true);
            return true;
        }
    }
}
