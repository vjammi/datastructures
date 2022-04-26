package ds.usage;

public class TreeTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode buildTree() {
        TreeNode root = new TreeNode();
        root.val = 16;

        TreeNode left = new TreeNode();
        root.left = left;
        root.val = 10;

        TreeNode right = new TreeNode();
        root.right = right;
        root.val = 22;
        return root;
    }

    private StringBuilder stringBuilderUsage() {
        StringBuilder sb = new StringBuilder();
        String chosen = "Some Choice";

        sb.append(chosen);
        //dfs (...)
        sb.deleteCharAt(sb.length()-1);

        return sb;
    }

    private void treeUsage() {
        dfs_tree(buildTree(), new StringBuilder(), "");
    }

    private void dfs_tree(TreeNode node, StringBuilder tmpResult, String str) {
        if (node == null)
            return;

        tmpResult.append(node.val);
        str = str + node.val;

        dfs_tree(node.left, tmpResult, str);
        tmpResult.deleteCharAt(tmpResult.length() - 1);
        str = str.substring(0, str.length());

        dfs_tree(node.right, tmpResult, str);
        tmpResult.deleteCharAt(tmpResult.length() - 1);
        str = str.substring(0, str.length());

    }

    private void main() {
        stringBuilderUsage();
        treeUsage();
    }

    public static void main(String[] args) {
        TreeTraversal obj = new TreeTraversal();
        obj.main();
    }
}
