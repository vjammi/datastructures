package ds.bst;

// Case 1 Node is a leaf node
// Case 2 Left of the node to delete is not null.
//        Point the parent to the left child and the
// Case 3 Left is null and Right of the Node to delete is not null.
//        Traverse the left subtree to find the smallest.
//        For this we will traverse the leftmost node on the right side [right.left...left]
//        Once we find it, we swap the leftmost of the right with teh node to delete
// O(n)   = O(Height) = log(N) where N = Number of Nodes
public class DeleteNodeInABST {


}
