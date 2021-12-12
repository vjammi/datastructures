package ds.usage;

public class CharacterOperations {

    public static void main(String[] args) {
        String s = "ra;c'ec;ar ";

        for (int i=0; i< s.length(); i++) {
            char ch = s.charAt(i);

            Character.toLowerCase(ch);
            boolean isAlphabetic = Character.isAlphabetic(ch);
            boolean isDigit = Character.isDigit(ch);
            boolean isLetter = Character.isLetter(ch);
            Character character = Character.valueOf(ch);
        }

    }

}
