# Binary Trees

## Binary Tree Traversals
```
                                 16

                     10                      22

                8         12            20         24

             6     9   11    13     19     21   23    25

           N   7
```

```
        PreOrder           
           
                N/+
               /
            A /___ B
        
        InOrder       
              N/+
              /\
             /  \
            A    B
        
        PostOrder       
               N/+
                 \
            A ____\ B
        
```
```
 preorderTraversalList  [+AB] [16, 10, 8, 6, 7, 9, 12, 11, 13, 22, 20, 19, 21, 24, 23, 25]
 inOrderTraversalList   [A+B] [6, 7, 8, 9, 10, 11, 12, 13, 16, 19, 20, 21, 22, 23, 24, 25]
 postOrderTraversalList [AB+] [7, 6, 9, 8, 11, 13, 12, 10, 19, 21, 20, 23, 25, 24, 22, 16]
```

### Preorder Traversal
```
    // preorder +AB
    public void preorder(TreeNode node){
       if(node == null)
           return;

       System.out.println(node.val); //  + [Print node]
       preorder(node.left);          //  A [Traverse left of the node]
       preorder(node.right)          //  B [Traverse right of the node]
    }
```

```
    public List<Integer> preorderTraversal(TreeNode node) {
        List<Integer> preOrderedList = new ArrayList<>();
        if (node == null)
            return preOrderedList;

        // Create an empty stack and push root to it
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);

        // Note that right child is pushed first so that left child can be processed first
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
#### Use of PreOrder Traversal
- Preorder traversal is used to create a copy of the tree.
- Preorder traversal is also used to get prefix expression on an expression tree [http://en.wikipedia.org/wiki/Polish_notation]

Polish/Prefix, Common Infix & Reverse Polish/Postfix Notations
  - Polish notation [Prefix / PreOrder]
    - Polish notation or Polish prefix notation or simply Prefix notation is a mathematical notation in which operators precede their operands
    - The expression for adding the numbers 1 and 2 is written in Polish notation as + 1 2 (prefix), rather than as 1 + 2 (infix).
    - For instance, the expression that would be written in conventional infix notation as ```(5 − 6) × 7``` can be written in Polish notation as ```× (− 5 6) 7```
    - ??? We can handle the expression using a stack. Each time when we meet two operands, we pop the two operands from the stack, calculate the result and push the result back into the stack.
      ```      
                          *
                  -             7
              5       6
      ```
    
  - Infix notation
    - Common Infix notation is where operators are placed between operands
    
  - Reverse Polish Notation [PostFix / PostOrder]
    - Reverse Polish notation, in which operators follow their operands.
    - The expression ((2+1) * 3) can be written in Reverse Polish notation as 2 1 + 3 * 
      ```      
                          *
                  +             3
              2       1
      ``` 

### Inorder Traversal
```
    // inorder A+B
    public void inorder(TreeNode node){
       if(node == null)
           return;

       inorder(node.left);           //  A [Traverse left of the node]
       System.out.println(node.val); //  + [Print node]
       inorder(node.right)           //  B [Traverse right of the node]
    }
```
```
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inOrderedList = new ArrayList<>();

        Stack<TreeNode> stack = new Stack();        // We are not adding the node to the stack, since we need to traverse to the leftmost node first
        TreeNode current = root;                    // *** We add the node to the current to traverse to the leftmost node before printing the node.

        // A+B (Left Node Right)
        while (current != null || !stack.empty()) {            
            // When current != null - Push current element into stack & update current pointer
            // When the left of Node (L/A) is null, traverse the right Node.
            // For the right node, traverse again its left side until its left is null
            if (current != null) {                                
                stack.push(current);                // *** We push the element to the stack, to be processed when current is null                                 
                current = current.left;             // A                            
            }else{ // While iterating to the left of a node, when current becomes null, we process the topmost element in the stack
                // At this point, we pop the top most element from the stack, and Print it
                TreeNode poppedNode = stack.pop();  // +              
                inOrderedList.add(poppedNode.val);  
                // and then assign its right node to the current, to traverse its left subtree
                current = poppedNode.right;         // B
            }
        }
        return inOrderedList;
    }
```
#### Use of InOrder Traversal
- In a binary search tree, we can retrieve all the data in sorted order using in-order traversal.

### Postorder Traversal

Recursive
```
    // postorder AB+
    public void postorder(TreeNode node){
       if(node == null)
           return;

       postorder(node.left);           //  A [Traverse left of the node]
       postorder(node.right)           //  B [Traverse right of the node]
       System.out.println(node.val);   //  + [Print node]
    }
```

Iterative
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
#### Use of Postorder
- PostOrder traversal is used to delete the tree. When we delete nodes in a tree, deletion process will be in post-order. 
  That is to say, when we delete a node, we will delete its left child and its right child before you delete the node itself.

- Post-order is widely used to get mathematical postfix expression of an expression tree [http://en.wikipedia.org/wiki/Reverse_Polish_notation]
- For Instance, the expression written ```3 − 4 + 5``` in conventional notation would be written as ``` 3 4 − 5 + ```in reverse Polish notation
- Note: 
  - It is easier to write a program to parse a post-order expression. 
  - We can easily figure out the original expression using the inorder traversal.
  - However, it is not easy for a program to handle this expression since we have to check the priorities of operations.
  - If we handle this tree in postorder, we can easily handle the expression using a stack. 
  - Each time when we meet an operator, we can just pop 2 elements from the stack, calculate the result and push the result back into the stack.

References: 
https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/992/
https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
```
                            +

                    *               5

                4       -

                    7       2
                    
        PostOrder:  [Null, Null, 4, 7, 2, -, *, 5, +]
        Visualizing the expression is easier using InOrder 
            4 * (7-2) + 5
        Writing a program to parse the expression is easier via Post Order. 
        Each time we meet an operator, we can just pop 2 elements from the stack, 
        calculate the result and push the result back into the stack.
                [ [4, [7, 2, -], *], 5, +] 
        
4  
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

        if (levelOrderTraversalRecursiveList.size() == level) { // For instance, initially at level 0 the size of the result list will be 0. Same with other levels.
            List<Integer> list = new ArrayList<>();             // Since we do not have a list for this level yet (1st in above), we will then create a new list for the this level [level index 0 == index 0 of the list]. 
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

## Binary Tree
- A binary tree is made of nodes, where each node contains a "left" reference, a "right" reference, and a data element.
- The topmost node in the tree is called the root.
- Every node (excluding root) in a tree is connected by a directed edge from exactly one other node. This node is called a parent. 
- On the other hand, each node can be connected to arbitrary number of nodes, called children.
- Nodes with no children are called leaves, or external nodes.
- Nodes which are not leaves are called internal nodes. 
- Nodes with the same parent are called siblings.

### Height and Depth  
```
                                          Height [from a Node the deepest Leaf]           Depth [From Root to the X Node]
                   18                       2                                                   0             
                /     \                             
             15         30                  1                                                   1
            /  \        /  \                        
          40    50    100   40              0                                                   2                
```
- Height of a node
  - The height of a node is the number of edges from the node to the deepest leaf.
- Height of a tree
  - The height of a tree is a height of the root.


- Depth of a node
    - The depth of a node is the number of edges from the root to the node.
    - Depth is in relation to nodes 

### Binary Tree Classifications
    
#### Full Binary Tree [zero or two children]
A full binary tree is a binary tree in which each node has exactly ```zero or two children```.
```
                 1
           2          3
        4     5
           6     7
   
          Figure (a)
```

#### Complete Binary Tree [Completely filled from left to right]
A complete binary tree is a binary tree, which is completely filled, with the possible exception of the bottom level, which is ```filled from left to right```. 
The Heap data structure is a good example of a complete binary tree.
A complete binary tree provides the best possible ratio between the number of nodes and the height.
- The height h of a complete binary tree with N nodes is at most O(log N). 
- We can easily prove this by counting nodes on each level, starting with the root, assuming that each level has the maximum number of nodes:
        n = 1 + 2 + 4 + ... + 2^(h-1) + 2^(h)
    which is
        n = 2^(h+1)-1
- Solving this with respect to h, we obtain
    h = O(log n)
```
                    1
            2               3
        4       5       6       7
      8   9   10  11  12
                Figure (b)
```

#### Perfect Binary Tree [Completely Full Binary Tree: Each level contains max num. of nodes]
A binary tree where each level contains the maximum number of nodes. i.e., every level is completely* full* of nodes [full and complete binary tree].
- Number of nodes of a Perfect Binary Tree at depth (d) is 2^d
- Total number of nodes of a Perfect Binary Tree of height h is 
   n = 2^(h+1)-1 
- Total number of nodes of a Perfect Binary Tree of height h=2 is 
   n = 2^(2+1)-1 = 7 
```
                                          Height   Depth
                   18                       2        0             
                /     \                             
             15         30                  1        1
            /  \        /  \                        
          40    50    100   40              0        2
                Figure (c)
```
##### Property 1: The number of nodes of a perfect binary tree at depth (d) is 2^d nodes
- There is only 1 node (= the root node) at depth 0 
     2^0 = 1
- In a perfect binary tree, every node has 2 children nodes
```
                                                                                Depth  Nodes at level
                                1                                                 0     2^0 
                      2                    3                                      1     2^1 
                4           5         6          7                                2     2^2 
             8       9   10    11   12   13   14    15                            3     2^3 
```
- So:
```
         Depth (d)   Number of Nodes at depth    Num of child nodes
         -----------------------------------------------------------------------
            0        2^0=1                       2 (each node has 2 children)                               1
            1        2^1=2                       4 (each node has 2 children)                     2                    3
            2        2^2=4                       8 (each node has 2 children)               4           5         6          7
            3        2^2=8                       16                                      8       9   10    11   12   13   14    15
```
- i.e. 
  - The number of nodes doubles every time the depth increases by 1.
- Therefore, 
  - nodes at depth d = 2^d

##### Property 2: The number of nodes of a perfect binary tree of height (h) is 2^(h+1)-1
- Total number of nodes in a perfect binary tree of height h
    Number of nodes = 2^0 + 2^1 + ... 2^h = 2^(h+1) − 1
```
                                                                          Height   Depth  Number of Nodes at level/depth
                                1                                           3       0       2^0
                      2                    3                                2       1       2^1
                4           5         6          7                          1       2       2^2
             8       9   10    11   12   13   14    15                      0       3       2^3
```

```
      S =     1 + 2 + 2^2 + 2^3 + ... + 2^h   
    2xS =         2 + 2^2 + 2^3 + ... + 2^h + 2^(h+1)   - (subtract)
    ------------------------------------------------------------    
       2xS - S = 2^(h+1) - 1  
    <==>     S = 2^(h+1) - 1
```
##### Property 3: Number of leaf nodes in a perfect binary tree of height h is 2^(h)
- Number of leaf nodes in a perfect binary tree of height h = 2^h
- Number of nodes at depth d in a perfect binary tree = 2^d
- All the leaf nodes in a perfect binary tree of height h has a depth equal to h:
- Number of nodes at depth h in a perfect binary tree = 2^h
- Therefore, Number of leaf nodes in a perfect binary tree of height h = 2^h

##### Property 4: Number of internal nodes in a perfect binary tree of height h is 2^(h)−1
- Number of nodes in a perfect binary tree of height h = 2^(h+1)−1     (see Property 2)
- Number of leaf nodes in a perfect binary tree of height h = 2^h      (see Property 3)
- The other nodes are internal nodes (i.e., with at least 1 child node)
- So the number of internal nodes in a perfect binary tree of height h is
  - [Total number of nodes] - [Number of nodes at height h] 
  - [(2^(h+1)−1)] − [2^h = 2^(h) − 1]

### Other properties of a Binary Tree
1. Number of nodes in a complete binary tree is between 
    (2^(h)−1)+1 [internal nodes + 1] 
    and
    2^(h+1)-1  [Completely full / Perfect Binary Tree]
2. Number of nodes in a perfect binary tree 2^(h+1)-1
3. Min height of a binary tree is log2(n+1)-1 or floor( (log2(n+1) )

REFERENCE: 
https://www.andrew.cmu.edu/course/15-121/lectures/Trees/trees.html
http://www.mathcs.emory.edu/~cheung/Courses/171/Syllabus/9-BinTree/bin-tree.html
https://opendsa-server.cs.vt.edu/ODSA/Books/CS3/html/BinaryTree.html

https://www.geeksforgeeks.org/relationship-number-nodes-height-binary-tree/
https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/992/
https://www.educative.io/edpresso/what-is-the-difference-between-the-height-and-depth-of-a-tree


## Balanced  Binary Tree
Difference between the left and right subtree is not more than k.
        | leftHeight - rightHeight | = 1
The absolute difference between the height of the left and right subtree must not more than K.
K in this case of a balanced binary tree is 1. 
For example, if we remove the node 5 from the below tree, it becomes unbalanced.
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