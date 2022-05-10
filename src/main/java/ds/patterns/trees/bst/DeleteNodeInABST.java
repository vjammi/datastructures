package ds.patterns.trees.bst;

// Case 1 Node is a leaf node
// Case 2 Left of the node to delete is not null.
//        Point the parent to the left child and the
// Case 3 Left is null and Right of the Node to delete is not null.
//        Traverse the right subtree to find the smallest.
//        For this we will traverse the left most node on the right side [right.left...left]
//        Once we find it, we swap the left most of the right with the node to delete
// O(n)   = O(Height) = log(N) where N = Number of Nodes
public class DeleteNodeInABST {


}
