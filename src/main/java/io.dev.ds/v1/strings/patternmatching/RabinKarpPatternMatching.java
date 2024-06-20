package dev.vjammi.ds.v1.strings.patternmatching;


/*
    Rabin Karp Pattern Matching - Use hashcode to check the pattern within strings

    Compute the hash of the pattern we are trying to find - string 1
        Approach 1 - Sum of the ascii char value of each of the chars
                     abc = 97 + 98 + 99

        Drawback, this might result into spurious hits. We can try an newer hash function

        Approach 2 - Sum of the ascii char values times total ascii chars to the power n-1, n-2... 0
                     abc = [97x127^2 + 98x127^1 + 99x127^0] mod 2^32
                     We mod the result to avoid overflow, not to exceed the max value of the data type.
                     So that we get the values within a particular range only
                     but once we apply mod the algo will tend towards spurious hits - O(m*n)
    Using sliding window compute the rolling


**/
public class RabinKarpPatternMatching {
}
