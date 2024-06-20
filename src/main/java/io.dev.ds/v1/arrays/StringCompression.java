package dev.vjammi.ds.v1.arrays;

public class StringCompression {

    private void testCompress() {
        String compressed1 = compress("AABCCCCCAAA");
        System.out.println("Compressed String1: " + compressed1);
        String compressed2 = compress("AABCCCCCA");
        System.out.println("Compressed String2: " + compressed2);
        String compressed3 = compress("ABCDEFGHIJK");
        System.out.println("Compressed String3: " + compressed3);
    }

    private String compress(String originalString) {
        //char[] charArray = new char[str.length()];
        String compressedString = "";
        int j =0;
        int charCount = 0;
        char lastChar = '\u0000';

        for(int i = 0; i<originalString.length(); i++){
            char currChar = originalString.charAt(i);
            if (currChar!= lastChar){
                if (lastChar != '\u0000'){
                    compressedString = compressedString + charCount; j++;
                    charCount = 0;
                }
                compressedString = compressedString + currChar; j++;
                lastChar = currChar;
            }
            charCount++;
        }
        compressedString = compressedString + charCount; j++;
        return compressedString.length() < originalString.length() ? compressedString : originalString;
    }

    public static void main (String[] agrs){
        StringCompression stringCompression = new StringCompression();
        stringCompression.testCompress();
    }
}

