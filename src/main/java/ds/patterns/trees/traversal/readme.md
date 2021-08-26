## Trees Pattern #2: Binary Tree Traversal Problems.
Unlike the previous tree patterns (given a particular tree, find a path that optimizes some criteria), we are not looking here for an optimal solution of any sort. 
Here we will be exploring all the nodes within a tree, generally in some unique way other than preorder, inorder and postorder traversal. 

![Binary Tree Traversals](img/binarytree.jpg "Binary Tree Traversals").

## 102. Binary Tree Level Order Traversal
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

Solution
Creates a list of lists (result list) by creating a list for each level and adding that list to the result list.
  
    public class BinaryTreeLevelOrderTraversal {
        class TreeNode {... }
    
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> lists = new ArrayList<>();
            traversal(root, lists, 0);
            return lists;
        }
    
        public void traversal(TreeNode node, List<List<Integer>> lists, int level){
            if (node == null)
                return;
    
            if (level == lists.size()){  // For instance, initially at level 0 the size of the result list will be 0. Same with other levels.
                System.out.println(node.val +" -- " +level);
                List<Integer> list = new ArrayList<>();
                list.add(node.val);
                lists.add(list);
            }else{ // else - if a list for that level has already been created, then just retrieve the list by level and add the node value to the list
                lists.get(level).add(node.val);
                System.out.println(node.val +" - " +level);
            }
    
            traversal(node.left,  lists, level+1);
            traversal(node.right, lists, level+1);
        }
    }

### Find the largest value in each Tree Row 
### Binary Tree Zigzag Level Order Traversal

    private void zigzagLevelOrderTraversal(TreeNode node, List<List<Integer>> lists, int level) {
        if(node == null) return;

        if(lists.size() <= level){
            List<Integer> newLevel = new LinkedList<>();
            lists.add(newLevel);
        }

        List<Integer> list  = lists.get(level);
        if(level % 2 == 0) {
            list.add(node.val);
        }else {
            // Inserts the the element at the position 0, in the list. 
            // Shifts the element currently at that position if any to the right for the zigZag
            list.add(0, node.val);
        }

        zigzagLevelOrderTraversal(node.left, lists, level + 1);
        zigzagLevelOrderTraversal(node.right, lists, level + 1);
    }

   
## 116. Populating Next Right Pointers in Each Node
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. 
The binary tree has the following definition:

        struct Node {
          int val;
          Node *left;
          Node *right;
          Node *next;
        }

Populate each next pointer to point to its next right node. If there is no next right node, 
the next pointer should be set to NULL. Initially, all next pointers are set to NULL.

Solution
    We will use a level order traversal. At any point we are going down and across/laterally. 
    We do not need to recurse or backtrack. 
    At any point we are connecting the nodes at its next level, children's level
    We will assign the next pointers to the 
            1. left node 
            2. right node
            
                             1
     
                       2     >      3
     
                4   >   5     >    6   >    7
     
             8 > 9 > 10 > 11  > 12 > 13 > 14 > 15            

Solution

    public class PopulatingNextRightPointersInEachNode {
        class Node {...}
    
        public Node connect(Node root) {
            if (root == null)
                return root;
    
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
    
            while(!queue.isEmpty()){
                Node node = queue.poll();
    
                // Assigning next pointer to a left node
                if (node.left !=null && node.right !=null ){
                    node.left.next = node.right;
                }else if(node.left != null && node.right == null){
                    Node nextNode = node.next;
                    if (nextNode == null){
                        node.left.next = null;
                    }else{
                        while(nextNode !=null ){
                            if (nextNode.left != null){
                                node.left.next = nextNode.left;
                                break;
                            }else if (nextNode.right != null){
                                node.left.next = nextNode.right;
                                break;
                            }else{
                                nextNode = nextNode.next;
                            }
                            node.left.next = null;
                        }
                    }
                }
    
                // Assigning next pointer to a right node
                if(node.right != null ){
                    Node nextNode = node.next;
                    if (nextNode == null){
                        node.right.next = null;
                    }else{
                        while(nextNode !=null ){
                            if (nextNode.left != null){
                                node.right.next = nextNode.left;
                                break;
                            }else if (nextNode.right != null){
                                node.right.next = nextNode.right;
                                break;
                            }else{
                                nextNode = nextNode.next;
                            }
                            node.right.next = null;
                        }
                    }
                }
    
                if (node.left != null){
                    queue.add(node.left);
                }
                if (node.right != null){
                    queue.add(node.right);
                }
    
            }
            return root;
        }
    }

## 105. Construct Binary Tree from Preorder and Inorder Traversal
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

        
                                       16
                 
                           10                      22
                 
                     8          12           20           24
                 
                 6       9   11      13   19     21    23     25
                 
            NULL     7   

            // preorder = [16 10 8 6 7 9 12 11 13 22 20 19 21 24 23 25]
                           ^ -->
            // inorder    [6 7 8 9 10 11 12 13 16 19 20 21 22 23 24 25]
                                                ^
    Note: This is similar to building a a tree from a sorted array.
          Unlike finding the mid node by using binary search (low+hi)/2 on the sorted array,
          Here we first find the node to be created by looking at the next node in the preorder sequence.
          We then look up that node in the inorder array and build the node node of our binary tree.

    public class BinaryTree_ConstructFromPreOrderInOrder {
        
        private int nextPreorderIndex = 0;
        private int[] preorder;
        private int[] inorder;
        private Map<Integer, Integer> inOrderMap = new HashMap<>();
    
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;
    
            for(int i = 0 ; i < inorder.length; i++){
                inOrderMap.put(inorder[i], i);
            }
    
            int leftIndex = 0; int rightIndex = preorder.length - 1;
            return buildTree(leftIndex, rightIndex);
        }

        private TreeNode buildTree(int leftIndex, int rightIndex) {
            // Traversing the left side of the tree, the right index becomes negative, causing the left index to be greater then the right
            // Traversing the right side of the tree, the left index index grows and crosses the size of the right index, once again causing the left index to be greater then the right.
            if (leftIndex > rightIndex){
                return null;
            }
    
            // Pick the next node to be built by selecting the next preorder node as the current root and incrementing the preorder index
            // This is similar to building a a tree from a sorted array where we pick the next node by find the mid (low+hi)/2 of the segment of the array.
            int preorderNodeVal = preorder[nextPreorderIndex++]; // preOrderIndex++;
            TreeNode currNode = new TreeNode(preorderNodeVal);

            int inorderIndexForCurrNode = inOrderMap.get(preorderNodeVal); // lookup the index of the nextPreOrderNodeVal within the inorder map.
            TreeNode leftNode = buildTree(leftIndex, inorderIndexForCurrNode - 1);  // Return left node
            TreeNode rightNode = buildTree(inorderIndexForCurrNode + 1, rightIndex); // Return right node
    
            currNode.left = leftNode;   // On your way back, add the returned node to the left of the current root node
            currNode.right = rightNode; // On your way back, add the returned node to the right of the current root node
    
            // Return the current node to be added to the left or the right side of the parent node.
            return currNode;
        }
        
        class TreeNode{...}
        
    }
    
## 106. Construct Binary Tree from Inorder and Postorder Traversal
```
     
                                            16
                      
                                10                      22
                      
                          8          12           20           24
                      
                      6       9   11      13   19     21    23     25
                      
                 NULL     7   
     
                // postorder [7 6 9 8 11 13 12 10 19 20 23 25 24 22 16]
                                                               <--- ^
                // inorder   [6 7 8 9 10 11 12 13 16 19 20 21 22 23 24 25]
```                                                  ^
Implementation
```
     public class BinaryTree_ConstructFromPostOrderInOrder {
        
        private int[] postorder;
        private int[] inorder;
        private int nextPostorderIndex = 0;
        private Map<Integer, Integer> inorderMap = new HashMap<>();
        
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            this.postorder = postorder;
            this.inorder = inorder;
    
            for(int i=0; i< inorder.length; i++){
                inorderMap.put(inorder[i], i);
            }
            nextPostorderIndex = postorder.length - 1;
            return buildTree(0, inorder.length-1);
        }
    
        public TreeNode buildTree(int leftIndex, int rightIndex) {
            // Traversing the left side of the tree, the right index becomes negative, causing the left index to be greater then the right
            // Traversing the right side of the tree, the left index index grows and crosses the size of the right index, once again causing the left index to be greater then the right.
            if (leftIndex > rightIndex){
                return null;
            }
    
            int postOrderNodeVal = postorder[nextPostorderIndex--];
            TreeNode currNode = new TreeNode(postOrderNodeVal);
            int inorderIndexForCurrNode = inorderMap.get(postOrderNodeVal);

            // Note: Because the sequence is post order, we build the right first???
            currNode.right = buildTree(inorderIndexForCurrNode+1, rightIndex);
            currNode.left = buildTree(leftIndex, inorderIndexForCurrNode - 1);

            return currNode;
        }
        
        class TreeNode{...}
    }
```

## 285. Inorder Successor in BST                                        https://leetcode.com/problems/inorder-successor-in-bst/
## 1676. Lowest Common Ancestor of a Binary Tree IV                     https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/
## 314. Binary Tree Vertical Order Traversal                            https://leetcode.com/problems/binary-tree-vertical-order-traversal/
## 431. Encode N-ary Tree to Binary Tree                                https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
## 108. Convert Sorted Array to Binary Search Tree                      https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
## 109. Convert Sorted List to Binary Search Tree                       https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
## 98. Validate Binary Search Tree                                      https://leetcode.com/problems/validate-binary-search-tree/


