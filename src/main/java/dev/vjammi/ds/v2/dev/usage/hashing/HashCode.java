package dev.vjammi.ds.v2.dev.usage.hashing;

public class HashCode {

    private static final int M = 5;

    public static int hashCode(String a){
        int hash = 17; // ??? Should it start off with a prime number?
        for (int i = 0; i < a.length(); i++) {
            hash = (int) a.charAt(i) + (31 * hash); // Use the ascii value of the character at teh ith position of the array (a[])
            //hash =  (int) a[i] + (31 * hash);
            System.out.println("Intermediate: " +hash);
        }
        System.out.println("Hash: " +hash);
        return hash;
    }

    private static int hash(String key) {
        int hashCode = hashCode(key);
        int hashIndex = (hashCode & 0x7fffffff) % M;
        return hashIndex;
    }

    // 0 is not prime. Primes are positive integers.
    // 0 is divisible by anything beside itself, and prime is divisible only by itself and 1 - so 0 is not a prime number, 0 and 1 are foundation on counting system or number system.
    // Zero is neither prime nor composite. Since any number times zero equals zero, there are an infinite number of factors for a product of zero. ... (A prime number has exactly two factors, so one can't be prime.)
    public static void main(String[] args) {
        System.out.println("0 " +0%2);
        System.out.println("1 " +1%2);
        System.out.println("2 " +2%2);
        System.out.println("3 " +3%2);
        System.out.println("4 " +4%2);
        System.out.println("5 " +5%2);

        System.out.println(HashCode.hash("Java"));
    }

}

