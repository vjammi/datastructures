package ds.util;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class PrintUtil {

    public class Node{
        String val;
        Node left;
        Node right;

    }

    // https://stackoverflow.com/questions/1649027/how-do-i-print-out-a-tree-structure
    private void print(Node root, String prefix) {
        if (root == null) {
            System.out.println(prefix + "+- <null>");
            return;
        }

        System.out.println(prefix + "+- " + root);
        print(root.left, prefix + "|  ");
        print(root.right, prefix + "|  ");
    }

    // Print All Paths
    // https://algorithms.tutorialhorizon.com/print-all-paths-from-top-left-to-bottom-right-in-two-dimensional-array/printallpaths/
}
