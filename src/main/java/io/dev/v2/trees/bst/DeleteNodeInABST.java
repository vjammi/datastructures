package io.dev.v2.trees.bst;

/**
    //                   11
    //             2           15
    //         1      4    12     25
    //             3    5      20
    //                             22


    // Approach 1: // TODO: Need to verify the logic
    // Case 1 Node is a leaf node
    // Case 2 Left of - the node to delete - is not null.
    //        Point the parent to the left child
    // Case 3 Left is null and Right of the Node to delete is not null.
    //        Traverse the right subtree to find the smallest.
    //        For this we will traverse the left most node on the right side [right.left...left]
    //        Once we find it, we swap the left most of the right with the node to delete
    //        Then we delete the node, because its
    // O(n)   = O(Height) = log(N) where N = Number of Nodes

    // Approach 2: Node to delete is a
    //                  Case 1. Node is a leaf node
    //                  Case 2. Node has 1 Child
    //                  Case 3. Node has 2 children
    // TODO: Need to Revisit based on the above cases
    // Reference: https://youtu.be/j6IHk2cH58I
     // Case 1. Node is a leaf node -  left and right children are null
     //         We delete the child and return null to the parent

     // Case 2: One child is null
     // We point the parent of the node to be deleted to the non-null child
     // To delete 2 we point 1 to 11
     //                   11
     //             2           15
     //         1      4    12     25
     //             3    5      20
     //                             22
     //                   11
     //             1           15
     //                4    12     25
     //             3    5      20
     //                             22

     // Case 3:
     // Delete node with 2 children: 15
     // Find the min of the right subtree of 15
     //                   11
     //             2           15
     //         1      4    12     25
     //             3    5      20
     //                             22

     //  Exchange 15 with 20
     //                   11
     //             2           20
     //         1      4    12     25
     //             3    5      15
     //                             22

     //  Now deleting 15 is case 2 - node with single child
     //                   11
     //             2           20
     //         1      4    12     25
     //             3    5      22

 */


public class DeleteNodeInABST {


}
