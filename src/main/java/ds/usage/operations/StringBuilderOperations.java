package ds.usage.operations;

public class StringBuilderOperations {

    public void operations(String input){
        traversal(input, 0);
        System.out.println("result " + result);

        reverse1(input, 0);
        System.out.println("result " + result1);

        reverse2(input, 0);
        System.out.println("result " + result2);
    }

    private StringBuilder result1 = new StringBuilder();
    public void reverse1(String input, int i){
        if (i == input.length())
            return;
        result1.insert(0,input.charAt(i));     // Insert/Prefix at offset 0
        reverse1(input, i+1);

    }

    private StringBuilder result2 = new StringBuilder();
    public void reverse2(String input, int i){
        if (i == input.length())
            return;
        reverse2(input, i+1);
        result2.append(input.charAt(i));         // Append/Suffix at offset i/n
    }

    private StringBuilder result = new StringBuilder();
    public void traversal(String input, int i){
        if (i == input.length()-1) {
            result.append(input.charAt(i));
            return;
        }
        result.append(input.charAt(i));
        traversal(input, i+1);
    }

    public static void main(String[] args) {
        StringBuilderOperations obj = new StringBuilderOperations();
        obj.operations("hello");
    }

}
