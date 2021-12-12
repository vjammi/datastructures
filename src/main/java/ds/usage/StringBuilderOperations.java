package ds.usage;

public class StringBuilderOperations {

    public void reverseStringRecursive(String input){
        reverse(input, 0);
        System.out.println("result " + result);
    }

    private StringBuilder result = new StringBuilder();
    public void reverse(String input, int i){
        if (i == input.length())
            return;
        result.insert(0,input.charAt(i));  // Insert/Prefix at offset 0
        reverse(input, i+1);
        result.append(input.charAt(i));         // Append/Suffix at offset i/n

    }

    // push and pop in string builder
    public void traversal(String input, int i){
        if (i == input.length()-1)
                return;

        result.insert(0,input.charAt(i));  // Insert/Prefix at offset 0
        result.append(input.charAt(i));
        traversal(input, i+1);
        result.deleteCharAt(result.length()-1);
    }

}
