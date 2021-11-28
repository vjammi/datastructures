package ds.binarytree;

/**
     <---------Negative Cols---   -------------Positive Cols------->
     -3      -2      -1         0          1      2       3

                                A
                              [0,0]                                Row/Level 0

                     B                     C
                   [1,-1]                [1,1]                     Row/Level 1

             D               E     F              G
          [2,-2]           [2,0] [2,0]          [2,2]              Row/Level 2

     H               I                    J               K
    [3,-3]         [3,-1]                [3,1]          [3,3]      Row/Level 3
*/

import java.util.*;

/**
     987. Vertical Order Traversal of a Binary Tree

     Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
     For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
     The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
     Return the vertical order traversal of the binary tree.

     Input: root = [3,9,20,null,null,15,7]
     Output: [[9],[3,15],[20],[7]]
     Explanation:
     Column -1: Only node 9 is in this column.
     Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
     Column 1: Only node 20 is in this column.
     Column 2: Only node 7 is in this column.

     Input: root = [1,2,3,4,5,6,7]
     Output: [[4],[2],[1,5,6],[3],[7]]
     Explanation:
     Column -2: Only node 4 is in this column.
     Column -1: Only node 2 is in this column.
     Column 0: Nodes 1, 5, and 6 are in this column.
     1 is at the top, so it comes first.
     5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
     Column 1: Only node 3 is in this column.
     Column 2: Only node 7 is in this column.

 */
public class BinaryTree_VerticalOrderTraversal {

    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int x) {
            val = x;
        }
    }

    class MinRowAndValueComparator implements Comparator<Cell> {
        @Override
        public int compare(Cell a,  Cell  b){
            if (a.row.equals(b.row))
                return a.value.compareTo(b.value);  // Same rows - return the smaller value
            else
                return a.row.compareTo(b.row);      // Not same rows - return the smaller row
        }
    }

    class Cell{
        Integer row;
        Integer col;
        Integer value;

        Cell(Integer row, Integer col, Integer value){
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        dfs(root, 0, 0);

        for (Map.Entry<Integer, List<Cell>> entry: map.entrySet()){
            //Integer         key = entry.getKey();
            List<Cell> values = entry.getValue();
            Collections.sort(values, new MinRowAndValueComparator());

            List<Integer> listOfInts = new ArrayList();
            for (int i=0; i< values.size(); i++){
                Cell cell = values.get(i);
                listOfInts.add(cell.value);
            }
            result.add(listOfInts);
        }

        return result;
    }

    //Map of - Col, ListOfCell
    Map<Integer, List<Cell>> map = new TreeMap();

    public void dfs(TreeNode node, int row, int col) {

        if (node == null)
            return;

        if (map.containsKey(col)){
            map.get(col).add(new Cell(row, col, node.val));
        }else{
            List<Cell> list = new ArrayList<>();
            list.add(new Cell(row, col, node.val));
            map.put(col, list);
        }

        dfs(node.left, row+1, col-1);
        dfs(node.right, row+1, col+1);

    }

}
