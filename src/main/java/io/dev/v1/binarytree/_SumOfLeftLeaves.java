package io.dev.v1.binarytree;

public class _SumOfLeftLeaves {

/*
     - Pass Left and right child indicators from a node
     - If a current node is a left child of its parent && is also a leaf node - then add the value to the sum
     - While traversing the left node of the tree, we pass isLeft == true
     - while traversing rhe right node of the tree we pass the isLeft = false

                                  5
                                L    R
                              3        6
                            L   R     L
            Add to Sum => 2      4  10 <= Add to Sum

       Solution - The logic can be contained before the traversing the left node.
*/


}
