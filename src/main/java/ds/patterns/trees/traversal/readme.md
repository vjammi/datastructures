## Trees Pattern #1: Binary Tree Traversal Problems.
Unlike the previous tree patterns (given a particular tree, find a path that optimizes some criteria), we are not looking here for an optimal solution of any sort. 
Here we will be exploring all the nodes within a tree, generally in some unique way other than preorder, inorder and postorder traversal. 

![Binary Tree Traversals](/img/binarytree.jpg "Binary Tree Traversals").

#### 102. Binary Tree Level Order Traversal
   Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
   Example 1:
   Input: root = [3,9,20,null,null,15,7]
   Output: [[3],[9,20],[15,7]]

##### Find the largest value in each Tree Row 
##### Binary Tree Zigzag Level Order Traversal
   
#### 116. Populating Next Right Pointers in Each Node
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. 
The binary tree has the following definition:

        struct Node {
          int val;
          Node *left;
          Node *right;
          Node *next;
        }

Populate each next pointer to point to its next right node. If there is no next right node, 
the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.

Solution
    We will need top use a level order traversal.
    At any point we are going down and across/laterally. We do not need to recurse or backtrack. 
    At any point we are connecting the nodes at its next level, children's level
    We will assign the next pointers to the 
            1. left node 
            2. right node
                             1
     
                       2     >      3
     
                4   >   5     >    6   >    7
     
             8 > 9 > 10 > 11  > 12 > 13 > 14 > 15            

#### 105. Construct Binary Tree from Preorder and Inorder Traversal
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
Example 1:
   Input: 
            preorder = [3,9,20,15,7],       
            inorder  = [9,3,15,20,7]
   Output: 
            [3,9,20,null,null,15,7]
                
                        3
                     9        20
                          15      7
Solution
   
              1
            2   3

PreOrder [1-2-3]: In a Preorder, you perform something on the current node first, before exploring its left and right nodes ???
Inorder  [2-1-3]: In a InOrder, you perform something on the left node first, then the parent node and then on its right node ???.