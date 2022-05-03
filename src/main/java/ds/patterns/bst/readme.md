# Binary Search Tree
A binary search tree (BST), a special form of a binary tree, satisfies the binary search property:
1. The value in each node must be greater than (or equal to) any values stored in its left subtree.
2. The value in each node must be less than (or equal to) any values stored in its right subtree.

Like a normal binary tree, we can traverse a BST in preorder, inorder, postorder or level-order.
However, inorder traversal in BST will return the elements in ascending order.
Therefore, the inorder traversal is the most frequent used traversal method of a BST.

## Operations within a BST
BSTs support three main operations: search, insertion and deletion. According to the property of BST, for each node:
1. return the node if the target value is equal to the value of the node;
2. continue searching in the left subtree if the target value is less than the value of the node;
3. continue searching in the right subtree if the target value is larger than the value of the node.

### Search
Time complexity and space complexity of the search operation in a BST whose height is h.
- Within the recursion solution, in the worse case, the depth of our recursion is equal to the height of the tree.
- Therefore, the time complexity of the recursion solution is O(h).
- And taking system stack into consideration, the space complexity should be O(h) in the worst case as well.


### Inorder Successor in a BST
To find the inorder successor in a BST, we can obviously do the inorder traversal to find the inorder successor in a BST.
But we could apply other properties of the BST to find out a better way.
