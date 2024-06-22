package io.dev.v2.strings;


/**
 * String Matching Algorithms
 *
 *  Naive Algorithm
 *      Ref: https://youtu.be/V5-7GzOfADQ?t=49
 *
 *                 0 1 2 3 4 5
 *      string1 =  a b c d e f
 *      string2 =  d e f
 *
 *      [pattern]  d e f
 *                     d e f
 *                       d e f
 *                       ^   ^
 *      Maintain i and j pointers on the 2 strings, move the pointers as long as there is a match
 *      If the pattern is not fully found, reset i to the next start index (0>1) and then again try to match the pattern
 *      Runtime: O(m x n)
 *
 *  KMP [Knuth-Morris-Pratt] String Matching Algorithm
 *      Ref: https://youtu.be/V5-7GzOfADQ?t=474
 *
 *      Pattern matching by summing up the ascii values o the individual chars to compute a hash [rolling hash]. If pattern not matched shift by 1 index.
 *      you compute the hash of the pattern only once. For the string to be matched, you compute the hash, when the hash does not match, shift the window,
 *      adjust your hash by subtracting the ascii of the j and move j to the next index, and i to the next index. Now add the ascii of the newer ith index
 *
 *      Runtime
 *          O(mxn)    worst case     [By taking a good hash function to reducing the chances spurious hits]
 *          O(n-m+1) average case   [With no spurious hits]
 *
 *                 0 1 2 3 4 5
 *      string1 =  a b c d e f
 *      string2 =  d e f
 *      [pattern]    d e f
 *                     d e f
 *                       d e f
 *                       ^   ^
 *
 *   Rabin-Karp's suggested Hash Function
 *       For base 10
 *           [(int)d * 10^2] + [(int)e * 10^1] + [(int)f * 10^0]
 *           [100 * 10^2]    + [101 * 10^1]    + [102 * 10^0]
 *           Math.pow(10, 2)         // Params: a – the base. b – the exponent.
 *
 *       For english alphabets we can take the base as 26
 *          Math.pow(26, 2)         // Params: a – the base. b – the exponent.
 *
 *       If Lower/Uppercase and special chars are included then we should consider total ascii codes of 127
 *          Math.pow(127, 2)        // Params: a – the base. b – the exponent.
 *
 *      Now since these values can get big we can perform mod on these large values to get the values within a certain range only
 *         For 32 bytes we can mod by 2^31, 1 left for signed bit
 *
 *      If your generated hash is too large you can just mod it with your keyspace max to make it fit.
 *          myhash = hash64bitvalue % 10^16     // for a key length of 16 digits
 *
 *          A 32-bit hash,
 *              might be small in some scenarios - has a range of +/ 2 billion,
 *          A 64-bit hash,
 *              will give numeric values that could be large (+/ 4 quintillion, or 19 digits).
 *
 *       Reference
 *          https://stackoverflow.com/questions/5566273/good-hash-function-32-bit-too-small-64-bit-too-large
 *          Rabin-Karp String Matching Algorithm
 *          https://youtu.be/qQ8vS2btsxI
 *          https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
 * */
public class StringMatching {

    public static void main(String[] args) {
        System.out.println(Math.pow(10, 2)); // Params: a – the base. b – the exponent.
    }
}
