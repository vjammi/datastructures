## Binary Tree
A tree where each parent node can have at most 2 children either 0,1,2 children only
each node is either a leaf node with no children or is a node with 2 children
if a node is a leaf node its left and right children are null

A binary tree is made up of a finite set of elements called nodes. This set either is empty or consists of a node called the root together with two binary trees,
called the left and right subtrees, which are disjoint from each other and from the root. (Disjoint means that they have no nodes in common.)
The roots of these subtrees are children of the root. There is an edge from a node to each of its children, and a node is said to be the parent of its children.

If n1,n2,...,nk is a sequence of nodes in the tree such that ni is the parent of ni+1 for 1≤i<k, then this sequence is called a path from n1 to nk.
The length of the path is k−1. If there is a path from node R to node M, then R is an ancestor of M, and M is a descendant of R.
Thus, all nodes in the tree are descendants of the root of the tree, while the root is the ancestor of all nodes. The depth of a node M in the tree is the length of
the path from the root of the tree to M. The height of a tree is the depth of the deepest node in the tree. All nodes of depth d are at level d in the tree.
The root is the only node at level 0, and its depth is 0. A leaf node is any node that has two empty children. An internal node is any node that has at least one non-empty child.
Reference: https://opendsa-server.cs.vt.edu/ODSA/Books/CS3/html/BinaryTree.html

### Depth, Height, Level in a Binary Tree
- The depth of a node M in the tree is the length of the path from the root of the tree to M.
- The height of a tree is the depth of the deepest node in the tree.
- All nodes of depth d are at level d in the tree.
- The root is the only node at level 0, and its depth is 0.
- A leaf node is any node that has two empty children.
- An internal node is any node that has at least one non-empty child.

### Binary Tree Classifications
1. Full Binary Tree
Each node in a full binary tree is either
(1) an internal node with exactly two non-empty children or
(2) a leaf.
```
             1
       2          3
    4     5
       6     7
Figure (a)
```
2. Complete Binary Tree
A complete binary tree has a restricted shape obtained by starting at the root and filling the tree by levels from left to right.
In the complete binary tree of height d, all levels except possibly level d are completely full.
The bottom level has its nodes filled in from the left side.
In other words every level except the bottom level is completely full and all the nodes within the bottom level nodes are filled from left to right.

```
                    1
            2               3
        4       5       6       7
      8   9   10  11  12
Figure (b)
```
3. Perfect Binary Tree
A Binary tree is a Perfect Binary Tree in which all the internal nodes have two children and all leaf nodes are at the same level.
```
               18
           /       \
         15         30
        /  \        /  \
      40    50    100   40

               18
           /       \
         15         30
Figure (c)
```

### Differences between full and complete binary trees
There is no particular relationship between these two tree shapes; that is, the tree of Figure (a) is full but not complete while the tree of Figure (b) is complete but not full.
- The heap data structure is an example of a complete binary tree.
- while, the Huffman coding tree is an example of a full binary tree.

While these definitions for full and complete binary tree are the ones most commonly used, they are not universal.
Because the common meaning of the words "full" and "complete" are quite similar, there is little that you can do to distinguish between them other than to memorize the definitions.

Note: Here is a memory aid that you might find useful:
"Complete" is a wider word than "full", and complete binary trees tend to be wider than full binary trees because each level of a complete binary tree is as wide as possible.

## Relationship between Level, Height & Nodes in a Binary Tree

#### How can we calculate the min and max number of nodes in a Binary Tree, when the height of a tree is given?
    Level 0     2^0 = 1                     1
    Level 1     2^1 = 2             2               3
    Level 2     2^2 = 4         4       5       6       7

    =  2^0 + 2^1 + 2^2 + 2^3 + 2^4 ... 2^h = (2^(h+1))-1
Therefore, the min and max number of nodes for a binary tree for a given height is
    Min number of nodes = h + 1         [For a skewed Binary Tree]
    Max number of nodes = (2^ (h+1))-1  [For a complete and full Binary Tree]

    For example, when h = 2
        (2^2+1)-1 = (2^3)-1 = 8-1 = 7

#### How can we calculate the min and max height of a tree for a given number of nodes?
If it a full binary tree
    (2^(h+1)) - 1 = n (Number of Nodes)
    (2^(h+1))     = n + 1
    log(2^(h+1))  = log(n + 1)
    log2(n+1)-1   = h

Therefore, the height of a binary tree, when given the number of nodes is
        Height = log2(n+1)-1
    Min Height = Floor(log2(n))
    Max height = n-1

    For example, when n = 7
        h = log2 (7+1) - 1
            log2 (8) - 1
            log2 (8) - 1
            3 - 1
        h = 2

Reference: https://www.geeksforgeeks.org/relationship-number-nodes-height-binary-tree/

Note: Height of a binary tree is the max depth of a tree or the longest path to the left node.

#### Takeaway
- We like to keep the height of the tree as mimimal as we can
- If it is a perfect binary tree then the runtime to reach a node will be the height of the binary tree.
- Greater the height, greater the time needed. So we try to keep a tree balanced so that the height stays minimal

## Balanced  Binary Tree
Difference between the left and right subtree is not more than k.
        | leftHeight - rightHeight | = 1
The absolute difference between the height of the left and right subtree must not more than K.
K in this case of a balanced binary tree is 1. For example, if we remove the node 5 from the below tree, it becomes unbalanced.
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
     
### Properties of a Binary Tree
1. Number of nodes in a full/perfect binary tree 2^(h+1)-1
2. Number of nodes in a complete binary tree is between 2^(h) and 2^(h+1)-1
3. Min height of a binary tree is log2(n+1)-1 or floor( (log2(n+1) )
   

## Binary Tree Traversals
```
                                 16

                     10                      22

                8         12            20         24

             6     9   11    13     19     21   23    25

           N   7

```
```
preorderTraversalList  [16, 10, 8, 6, 7, 9, 12, 11, 13, 22, 20, 19, 21, 24, 23, 25]
inOrderTraversalList   [6, 7, 8, 9, 10, 11, 12, 13, 16, 19, 20, 21, 22, 23, 24, 25]
postOrderTraversalList [7, 6, 9, 8, 11, 13, 12, 10, 19, 21, 20, 23, 25, 24, 22, 16]
```

### Preorder Traversal
```
    public List<Integer> preorderTraversal(TreeNode node) {
        List<Integer> preOrderedList = new ArrayList<>();
        if (node == null)
            return preOrderedList;

        // Create an empty stack and push root to it
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        // Note that right child is pushed first so that left is processed first
        // +AB (Node Left Right)
        while (!stack.empty()) {

            // Pop the top item from stack and add it to the list 
            TreeNode current = stack.pop();  //stack.peek();
            preOrderedList.add(current.val); // +

            // *** [+AB] Push right and left children of the popped node to stack
            if (current.right != null) {
                stack.push(current.right);  // R
            }
            if (current.left != null) {
                stack.push(current.left);   // L
            }
        }
        return preOrderedList;
    }
```
### Inorder Traversal
```
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrderedList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack(); // We are not adding the node to the stack, since we need to traverse to the leftmost node first
        TreeNode current = root; // *** We add the node to the current to traverse to the leftmost node before printing the node.

        // A+B (Left Node Right)
        while (current != null || !stack.empty()) {
            // When current != null - Push current element into stack & update current pointer
            // When the left of Node (L/A) is null, traverse the right Node.
            // For the right node, traverse again its left side until its left is null
            // When left and right are null then ???
            if (current != null) {
                stack.push(current);
                current = current.left;             // A
            } else { // When iterating to the left of a node, when current becomes null, we process the topmost element in the stack.
                // At this point, we pop the top most element from the stack,
                TreeNode poppedNode = stack.pop();
                // and Print it
                inOrderedList.add(poppedNode.val);  // +
                // and then assign its right node to the current, to traverse its left subtree
                current = poppedNode.right;         // B
            }
        }
        return inOrderedList;
    }
```
### Postorder Traversal
```
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> postOrderList = new ArrayList<>();
        if (root == null)
            return postOrderList;

        // Create an empty stack and push root to it
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);        
        // AB+ (Left Right Node)
        while (!stack.isEmpty()) {
            // Pop an item from stack and add it to list at the 0th index
            TreeNode current = stack.pop();

            // Push left and right children of current popped item to stack
            if (current.left != null)
                stack.push(current.left);   // A
            if (current.right != null)
                stack.push(current.right);  // B

            // *** Add the current popped element to the 0th index of the list (could use a stack alternatively)
            // Adding to the 0th index/position of the list, shifts any existing elements to its right
            postOrderList.add(0, current.val); // + ***
        }
        return postOrderList;
    }
```
### Level Order Traversal
```
    private List<List<Integer>> levelOrderTraversalIterative(TreeNode root) {
        List<List<Integer>> levelOrderTraversalLists = new ArrayList();
        List<Integer> levelList = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            levelList = new ArrayList();
            System.out.println("Processing level " +level +" of size " +size);
            while(size > 0){
                TreeNode node = queue.poll();
                levelList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            levelOrderTraversalLists.add(levelList);
            level++;
        }
        return levelOrderTraversalLists;
    }

    List<List<Integer>> levelOrderTraversalRecursiveList =  new ArrayList<>();
    private void levelOrderTraversalRecursive(TreeNode node, int level) {
        if (node == null)
            return;

        if (levelOrderTraversalRecursiveList.size() == level) { // Ugly way of checking a list for that level has already been created
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            levelOrderTraversalRecursiveList.add(list); // Note: list.get(level).add(node.val) will throw IndexOutOfBoundsException: Index: 0, Size: 0
        }else {
            // if a list for that level has already been created, then just retrieve the list by level and add the node value to the list
            levelOrderTraversalRecursiveList.get(level).add(node.val);
        }
        levelOrderTraversalRecursive(node.left, level+1);
        levelOrderTraversalRecursive(node.right, level+1);
    }
```



