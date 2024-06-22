package io.dev.v2.dev.usage.datatypes;

public class StringBuilderUsage {

    private void stringBuilderUsage() {
        StringBuilder sb = new StringBuilder();
        String chosen = "Some Choice";

        // Append Operations
        for(int i=0; i<chosen.length(); i++)
            sb.append(i);

        sb.insert(sb.length(), 'm');
        sb.reverse();

        // Get/Read char Operations
        for(int i=0; i<sb.length(); i++)
            System.out.println(sb.charAt(i));

        // DFS using String Builder
        sb.append(chosen);
        //dfs (...)
        sb.deleteCharAt(sb.length()-1);
    }

    public static void main(String[] args) {
        StringBuilderUsage obj = new StringBuilderUsage();
        obj.stringBuilderUsage();
    }
}
