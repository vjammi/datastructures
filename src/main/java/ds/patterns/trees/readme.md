## Binary Trees
A tree where each parent node can have at most 2 children either 0,1,2 children only
each node is either a leaft node with no children or is a node with 2 children
if a node is a leaf node its left and right children are null

A binary tree can be classfified into 3 types 
1. Strict
Each node has striclty has 2 child nodes or none
```
              1
          2       3
                4   5   
              6   7
```
2. Full/Perfert 
Each node has exactly 2 childen and all leaf nodes are exactly at the same level. They are full or completely filled trees.
```
              1
          2       3
        4   5   6   7
```
3. Complete
Every level except the last level is completely filled and all the nodes within the last level nodes filled from left to right
```
              1
          2       3
        4   5   6   7
      8   9  
       
```
Level

Maximum number of nodes in a Binary Tree?
    Level 0     2^0 = 1                     1
    Level 1     2^1 = 2             2               3
    Level 2     2^2 = 3         4       5       6       7

= 2^0  2^1  2^2  2^3  2^4 ....  2^N = (2^ (height+1))-1
= (2^2+1)-1 = (2^3)-1 = 8-1 = 7 

How can we calculate the height of a tree, when given the number of nodes?
If it a full binary tree, we can use the earlier equation
    (2^(h+1)) - 1 = n (Number of Nodes)
    (2^(h+1))     = n + 1
    log(2^(h+1))     = log(n + 1)
    
    log2 (n+1) - 1   = h
    log2 (7+1) - 1   = h
    log2 (8) - 1   = h
    log2 (8)  - 1   = h
        3 - 1 = h
        2 = h
    (2^ (height+1))-1
    
We would like to keep the height of the tree low,      
If it's a perfect binary tree then the max rumtime to reach a node will be the heigh of the binary tree.
Greater the height grater the time needed. 
We try to keep a tree balanced.

Balanced  Binary Tree
Difference between the left and right subtree is not more than k 
        | leftHeight - rightHeight | = 1
The absolute difference between the height of the left and right subtree must not more than K. in the case of a balanced Binary Tree it 1. K in this case is 1, in a balanced binary tree ???

For example, if we remove the node 5 from teh below tree, it becomes unbalanced.
```
              1
          2       3
        4   5   6   7
      8   9

              1
          2       3
        4       6   7
      8   9  
```
     
#### Properties of a Binary Tree
1. Number of nodes in a full/perfect binary tree 2^(n+1) -1
2. Number of nodes in a complete binary tree are between 2^(n) and 2^(n+1)-1
3. Min height of a binary tree is log2 (n+1)-1 or floor( (log2 (n+1) )
   
#### Binary Tree Traversals
```
                                 |-25
                         |-24
                             |-23
                     |-22
                             |-21
                         |-20
                             |-19
                 |-16
                             |-13
                         |-12
                             |-11
                     |-10
                             |-9
                         |-8
                                 |-7
                             |-6
            preorderTraversalList  [16, 10, 8, 6, 7, 9, 12, 11, 13, 22, 20, 19, 21, 24, 23, 25]
            inOrderTraversalList   [6, 7, 8, 9, 10, 11, 12, 13, 16, 19, 20, 21, 22, 23, 24, 25]
            postOrderTraversalList [7, 6, 9, 8, 11, 13, 12, 10, 19, 21, 20, 23, 25, 24, 22, 16]

```    
#### Binary Tree Iterative Traversal Implementation
```
    public List<Integer> preorderTraversal(TreeNode node) {
        List<Integer> preOrderedList = new ArrayList<>();

        // Base Case
        if (node == null) {
            return preOrderedList;
        }

        // Create an empty stack and push root to it
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        // Pop all items one by one. Do following for every popped item
        // a) print it
        // b) push its right child
        // c) push its left child
        // Note that right child is pushed first so that left is processed first
        while (!stack.empty()) {
            // Pop the top item from stack and print it
            TreeNode current = stack.pop();  //stack.peek();
            preOrderedList.add(current.val);

            // Push right and left children of the popped node to stack
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return preOrderedList;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrderedList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        TreeNode current = root;

        while (current != null || !stack.empty()) {
            // When current != null - Push current element into stack & update current pointer
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                // When current == null - Pop most current element from the stack & update current pointer
                TreeNode poppedNode = stack.pop();
                inOrderedList.add(poppedNode.val);
                current = poppedNode.right;
            }
        }
        return inOrderedList;
    }

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> postOrderList = new ArrayList<>();

        if (root == null)
            return postOrderList;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);        

        while (!stack.isEmpty()) {
            // Pop an item from stack and add it to list at the 0th index
            TreeNode current = stack.pop();

            // Push left and right children of removed item to stack
            if (current.left != null)
                stack.push(current.left);
            if (current.right != null)
                stack.push(current.right);

            // Add the node/element to the 0th index of the array list. which will shift any existing elements to its right
            postOrderList.add(0, current.val); // stack2.push(current); // or could add to a stack
        }

        return postOrderList;
    }

```     
