package dev.vjammi.ds.v2.usage.operations;

public class CharacterOperations {

    public static void main(String[] args) {
        String s = "RA;c'ec;ar ";

        for (int i=0; i< s.length(); i++) {
            char ch = s.charAt(i);
            Character.toLowerCase(ch);
            boolean isDigit = Character.isDigit(ch);
            boolean isLetter = Character.isLetter(ch);
            boolean isLetterOrDigit = Character.isLetterOrDigit(ch);
            boolean isAlphabetic = Character.isAlphabetic(ch); // UPPER/LOWER/TITLE CASE, LETTER_NUMBER
            Character character = Character.valueOf(ch); // Returns a Character instance representing the specified char value.
            Character.isLetterOrDigit(ch);
            char chLower = Character.toLowerCase(ch);
            char chUpper = Character.toUpperCase(ch);
            // See also
            //     isDigit(char),
            //     isJavaIdentifierStart(char),
            //     isJavaLetter(char),
            //     isJavaLetterOrDigit(char),
            //     isLetterOrDigit(char),
            //     isLowerCase(char),
            //     isTitleCase(char),
            //     isUnicodeIdentifierStart(char),
            //     isUpperCase(char)
        }

        //Determines if the specified character (Unicode code point) is a letter or digit.
        //A character is considered to be a letter or digit if either isLetter(codePoint) or isDigit(codePoint) returns true for the character.
        // Check if the unicode value is a letter or a digit
        boolean isCodePoint10LetterOrDigit = Character.isLetterOrDigit(10);
        boolean isCodePoint97LetterOrDigit = Character.isLetterOrDigit(97);
        System.out.println("isCodePoint10LetterOrDigit "+ isCodePoint10LetterOrDigit +" isCodePoint97LetterOrDigit " +isCodePoint97LetterOrDigit);

        // Unicode to char value
        char[] charArr = Character.toChars(97);
        System.out.println("What char is ascii 97? " + charArr[97-97] +" "+ (char) 97 + " " +Character.valueOf((char) 97));

        s = "abcdefghijklmnopqrstuvw";
        char[] chars = s.toCharArray();
        int[] charSet = new int[26];
        for (int i=0; i<s.length(); i++) {
            int ch = s.charAt(i);
            charSet[ch-97] = charSet[ch-97]+1;
        }
        String resultantString = charSet.toString();
        System.out.println(resultantString);
    }



}
