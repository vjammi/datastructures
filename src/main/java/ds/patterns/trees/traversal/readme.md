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
    Inorder  [2-1-3]: In a InOrder, you perform something on the left node first, then the current node and then on its right node ???.

        
                                       16
                 
                           10                      22
                 
                     8          12           20           24
                 
                 6       9   11      13   19     21    23     25
                 
            NULL     7   

              // preorder = [16 10 8 6 7 9 12 11 13 22 20 19 21 24 23 25]
                             ^ -->
              // inorder    [6 7 8 9 10 11 12 13 16 19 20 21 22 23 24 25]
                                                  ^
                          -------------------------------------------------------
                                                 16
                          [6 7 8 9 10 11 12 13]      [19 20 21 22 23 24 25]

                          -------------------------------------------------------
                                                  16
                                    10               [19 20 21 22 23 24 25]
                          [6 7 8 9]    [11 12 13]
                          --------------------------------------------------------
                                                  16
                                    10                [19 20 21 22 23 24 25]
                              8            12
                        [6 7]    [9]  [11]    [13]
                        ---------------------------------------------------------
                                                  16
                                    10                [19 20 21 22 23 24 25]
                              8            12
                        [6]     [9]  [11]    [13]
                           [7]
                        -------------------------------------------------------------------
Note:
This is similar to building a tree from a sorted array. Unlike finding the mid node by using binary search (low+hi)/2 on the sorted array,
here we first find the node to be created by looking at the next node in the preorder sequence. We then look up that node in the
inorder array and build the node node of our binary tree.

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
            // On your way back, add the returned node to the left of the current root node
            currNode.left = buildTree(leftIndex, inorderIndexForCurrNode - 1);  // Return left node
            // On your way back, add the returned node to the right of the current root node
            currNode.right = buildTree(inorderIndexForCurrNode + 1, rightIndex); // Return right node

            // Return the current node to be added to the left or the right side of the parent node.
            return currNode;
        }
        
        class TreeNode{...}
        
    }
```

## 106. Construct Binary Tree from Inorder and Postorder Traversal
Approach: Recursion
How to construct the tree from two traversals: inorder and preorder/postorder/etc
Start from not inorder traversal, usually its preorder or postorder one, and use the traversal picture above to define the strategy to pick the nodes. For example,
for preorder traversal the first value is a root, then its left child, then its right child, etc. For postorder traversal the last value is a root, then its right child,
then its left child, etc. The value picked from preorder/postorder traversal splits the inorder traversal into left and right subtrees. The only information one needs
from inorder - if the current subtree is empty (= return None) or not (= continue to construct the subtree).

#### What is the reason that we have to construct the right sub-tree first and then the left sub-tree?
Post order sequence is left -> right -> root. So since here we are building it backward, it should go from root -> right -> left

Complexity Analysis
Time complexity : O(N)\mathcal{O}(N)O(N). Let us compute the solution with the help of master theorem T(N)=aT(bN)+Θ(Nd)T(N) = aT\left(\frac{b}{N}\right) + \Theta(N^d)T(N)=aT(Nb​)+Θ(Nd). The equation represents dividing the problem up into aaa subproblems of size Nb\frac{N}{b}bN​ in Θ(Nd)\Theta(N^d)Θ(Nd) time. Here one divides the problem in two subproblemes a = 2, the size of each subproblem (to compute left and right subtree) is a half of initial problem b = 2, and all this happens in a constant time d = 0. That means that log⁡b(a)>d\log_b(a) > dlogb​(a)>d and hence we are dealing with case 1 that means O(Nlog⁡b(a))=O(N)\mathcal{O}(N^{\log_b(a)}) = \mathcal{O}(N)O(Nlogb​(a))=O(N) time complexity.
Space complexity : O(N)\mathcal{O}(N)O(N), since we store the entire tree.
Reference: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/

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

```
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length ==0) return null;

        return buildTree(nums, 0, nums.length-1);
    }

    public TreeNode buildTree(int[] nums, int start, int end) {

        if (start>end){
            return null;
        }

        int mid = (start+end)/2;
        TreeNode node = new TreeNode(nums[mid]);

        node.left  = buildTree(nums, start, mid-1);
        node.right = buildTree(nums, mid+1, end);

        return node;
    }
```

## 109. Convert Sorted List to Binary Search Tree                       https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
Intuition
The important condition that we have to adhere to in this problem is that we have to create a height balanced binary search tree using the set of nodes given to us in the form of a linked list. The good thing is that the nodes in the linked list are sorted in ascending order.
As we know, a binary search tree is essentially a rooted binary tree with a very special property or relationship amongst its nodes. For a given node of the binary search tree, it's value must be ≥\ge≥ the value of all the nodes in the left subtree and ≤\le≤ the value of all the nodes in the right subtree. Since a binary tree has a recursive substructure, so does a BST i.e. all the subtrees are binary search trees in themselves.

The main idea in this approach and the next is that the middle element of the given list would form the root of the binary search tree. All the elements to the left of the middle element would form the left subtree recursively. Similarly, all the elements to the right of the middle element will form the right subtree of the binary search tree. This would ensure the height balance required in the resulting binary search tree.

Algorithm
Since we are given a linked list and not an array, we don't really have access to the elements of the list using indexes. We want to know the middle element of the linked list.
We can use the two pointer approach for finding out the middle element of a linked list. Essentially, we have two pointers called slow_ptr and fast_ptr. The slow_ptr moves one node at a time whereas the fast_ptr moves two nodes at a time. By the time the fast_ptr reaches the end of the linked list, the slow_ptr would have reached the middle element of the linked list. For an even sized list, any of the two middle elements can act as the root of the BST.
Once we have the middle element of the linked list, we disconnect the portion of the list to the left of the middle element. The way we do this is by keeping a prev_ptr as well which points to one node before the slow_ptr i.e. prev_ptr.next = slow_ptr. For disconnecting the left portion we simply do prev_ptr.next = None
We only need to pass the head of the linked list to the function that converts it to a height balances BST. So, we recurse on the left half of the linked list by passing the original head of the list and on the right half by passing slow_ptr.next as the head.

```
    private ListNode findMiddleElement(ListNode head) {

        // The pointer used to disconnect the left half from the mid node.
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // Handling the case when slowPtr was equal to head.
        if (prevPtr != null) {
            prevPtr.next = null;
        }

        return slowPtr;
    }

    public TreeNode sortedListToBST(ListNode head) {

        // If the head doesn't exist, then the linked list is empty
        if (head == null) {
            return null;
        }

        // Find the middle element for the list.
        ListNode mid = this.findMiddleElement(head);

        // The mid becomes the root of the BST.
        TreeNode node = new TreeNode(mid.val);

        // Base case when there is just one element in the linked list
        if (head == mid) {
            return node;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        node.left = this.sortedListToBST(head);
        node.right = this.sortedListToBST(mid.next);
        return node;
    }
```
## 98. Validate Binary Search Tree                                      https://leetcode.com/problems/validate-binary-search-tree/


