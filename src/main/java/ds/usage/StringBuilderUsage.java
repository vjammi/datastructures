package ds.usage;

import java.util.*;

public class StringBuilderUsage {

    private StringBuilder stringBuilderUsage() {
        StringBuilder sb = new StringBuilder();
        String chosen = "Some Choice";

        sb.append(chosen);
        //dfs (...)
        sb.deleteCharAt(sb.length()-1);

        return sb;
    }

    public static void main(String[] args) {
        StringBuilderUsage obj = new StringBuilderUsage();
        obj.stringBuilderUsage();
    }
}
