package ds.usage;

public class StringBuilderOperations {

    public void reverseStringRecursive(String input){
        //reverseString1(input, 0);
        //System.out.println("");

        StringBuilder output = new StringBuilder();
        reverseString2(input, 0, output);
        System.out.println("Output1 " + output);
        System.out.println("Output2 " + output);


    }

    private StringBuilder output2 = new StringBuilder();
    public void reverseString2(String input, int i, StringBuilder output){
        if (i == input.length())
            return;

        output.insert(0,input.charAt(i));  // Insert/Prefix at offset 0
        reverseString2(input, i+1, output);
        output2.append(input.charAt(i));         // Append/Suffix at offset i/n

    }
}
