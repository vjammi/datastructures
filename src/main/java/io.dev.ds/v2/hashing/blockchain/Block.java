package dev.vjammi.ds.v2.hashing.blockchain;

import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

    private String data;
    private String previousHash;
    private long timestamp;
    private String hash;
    private int nonce;

    public String data() {
        return data;
    }

    public String previousHash() {
        return previousHash;
    }

    public long timestamp() {
        return timestamp;
    }

    public String hash() {
        return hash;
    }

    public Block(String data, String previousHash) throws UnsupportedEncodingException {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.nonce = 0;
        this.hash = mineBlock(data, previousHash, timestamp, 4);
    }

    //Increase nonce value until hash target is reached.
    public String mineBlock(String data, String previousHash, long timestamp, int difficulty) throws UnsupportedEncodingException {
        String target = getDifficultyString(difficulty);            //Create a string with difficulty * "0"
        boolean continueMining = true;
        while(continueMining) {
            hash = calculateHash(nonce, data, previousHash, timestamp);
            boolean mined = hash.substring(0, difficulty).equals(target);
            if (mined) {
                System.out.println("\n" +hash.substring(0, difficulty) +" "+ hash);
                break;
            }else {
                if (nonce % 1000000 == 0)
                    System.out.print(".");
                nonce++;
            }
        }
        System.out.println("Block Mined!!! : " + hash);
        return hash;
    }

    private String calculateHash(int nonce, String data, String previousHash, long timestamp) throws UnsupportedEncodingException {
        return generateHash( nonce + data + previousHash + timestamp);
    }

    private String generateHash(String inputData) throws UnsupportedEncodingException {
        StringBuilder hexOutput = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] messageDigestBytes = messageDigest.digest(inputData.getBytes("UTF-8"));

            for (int i = 0; i < messageDigestBytes.length; i++) {
                String hex = Integer.toHexString(messageDigestBytes[i] & 0xff);
                hexOutput.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexOutput.toString();
    }

    //Short hand helper to turn Object into a json string
    public String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"
    public String getDifficultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

}
