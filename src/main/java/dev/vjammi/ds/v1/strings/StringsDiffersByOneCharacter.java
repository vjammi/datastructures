package dev.vjammi.ds.v1.strings;

/**
 * 1554. Strings Differ by One Character
 * Given a list of strings dict where all the strings are of the same length.
 *
 * Return true if there are 2 strings that only differ by 1 character in the same index, otherwise return false.
 *
 * Example 1:
 * Input: dict = ["abcd","acbd", "aacd"]
 * Output: true
 * Explanation: Strings "abcd" and "aacd" differ only by one character in the index 1.
 *
 * Example 2:
 * Input: dict = ["ab","cd","yz"]
 * Output: false
 *
 * Example 3:
 * Input: dict = ["abcd","cccc","abyd","abab"]
 * Output: true
 *
 */
public class StringsDiffersByOneCharacter {
    /**
     *  Intuition
     *    Iterate over the array of Strings computing the simple ascii hash of each string value, store resultant hashed key to orig string value pairs in a HashMap.
     *    At each step before we insert to new key-value pair into the hashmap, check if there already exists a hash key which differs the current hash by one +/- 1.
     *    If there exists return true.
     *
     *    Input: dict = ["ab","cd","yz"]
     *                   97+98=195  : {ab}
     *                   99+100=199 : {cd}
     *                   121+122=243: {yz}
     */


}
