package dev.vjammi.ds.v1.hashing;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Reference
//   https://stackoverflow.com/questions/36491665/byte-to-integer-and-then-to-string-conversion-in-java
//   https://stackoverflow.com/questions/11380062/what-does-value-0xff-do-in-java
//   https://github.com/in-the-keyhole/khs-blockchain-java-example/blob/78656c515ed33b4d9b28005dc87cf862d29c399e/src/main/java/helpers/SHA256.java#L9
public class CryptographicHash {

    // SHA256 hash generation in Java
    // bytes[i] & 0xff
    //          converts byte to integer of 32 bit length copying the byte to the least significant byte of the integer
    // Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1)
    //      Adding 0x100 (which is 256 decimal) sets the 9th bit to 1, which guarantees the binary number representation of the result has exactly 9-bits.
    //      You could equivalently do & 0x100.
    //      After setting bit 8, the result from the toString() will be 9 chars long (of zeroes and ones).
    //      substring(1) effectively ignores bit 8 and outputs the lower 8 bits
    public String generateSha256Hash(String input) {
        String hash = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] valueBytes = input.getBytes(StandardCharsets.UTF_8);
            byte[] messageDigestBytes = messageDigest.digest(valueBytes);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < messageDigestBytes.length; i++) {
                String substring = Integer.toString((messageDigestBytes[i] & 0xff) + 0x100, 16).substring(1);
                sb.append(substring);
            }
            hash = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }

    public static void main(String[] args) {
        CryptographicHash hashing = new CryptographicHash();
        System.out.println(hashing.generateSha256Hash("Some data to be hashed"));
    }

}