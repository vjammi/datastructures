package ds.usage;

public class CharacterOperations {

    public static void main(String[] args) {
        String s = "RA;c'ec;ar ";

        for (int i=0; i< s.length(); i++) {

            char ch = s.charAt(i);
            Character.toLowerCase(ch);
            boolean isDigit = Character.isDigit(ch);
            boolean isLetter = Character.isLetter(ch);
            boolean isLetterOrDigit = Character.isLetterOrDigit(ch);
            boolean isAlphabetic = Character.isAlphabetic(ch);
            Character character = Character.valueOf(ch);
            Character.isLetterOrDigit(ch);
            char chLower = Character.toLowerCase(ch);
            char chUpper = Character.toUpperCase(ch);
        }

        boolean isCodePoint10LetterOrDigit = Character.isLetterOrDigit(10);
        boolean isCodePoint97LetterOrDigit = Character.isLetterOrDigit(97);
        System.out.println("isCodePoint10LetterOrDigit "+ isCodePoint10LetterOrDigit +" isCodePoint97LetterOrDigit " +isCodePoint97LetterOrDigit);

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
