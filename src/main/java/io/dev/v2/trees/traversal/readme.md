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
```
    public class BinaryTreeLevelOrderTraversal {
        class TreeNode {... }
    
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> lists = new ArrayList<>();
            traversal(root, lists, 0);
            return lists;
        }

        /*
            level       list size
            0           1
            1           2
            2           3
        */
        public void traversal(TreeNode listNode, List<List<Integer>> lists, int level){
            if (listNode == null)
                return;
    
            if (lists.size() <= level){             // For instance, initially at level 0 the size of the result list will be 0.
                System.out.println(listNode.val +" -- " +level);
                List<Integer> list = new ArrayList<>();
                list.add(listNode.val);
                lists.add(list);
            }else{ // else [level < levels.size()] if a list for that level has already been created, then just retrieve the list by level and add the listNode value to the list
                lists.get(level).add(listNode.val);
                System.out.println(listNode.val +" - " +level);
            }
    
            traversal(listNode.left,  lists, level+1);
            traversal(listNode.right, lists, level+1);
        }
    }
```
### 515. Find the largest value in each Tree Row
```
class LargestValueInEachTreeRow{

    public List<Integer> largestValues(TreeNode root) {
        if (root == null)
            return new ArrayList<Integer>();

        //return bfs(root);

        List<Integer> result = new ArrayList<>();
        dfs(root, result, 0);
        return result;
    }

    /*
        level       listSize
        0           1
        1           2
        2           3
    */
    private void dfs(TreeNode listNode, List<Integer> result, int level){
        if (listNode == null)
            return;

        if (result.size() <= level){                    // Level not created so far. Add new level to the list.
            result.add(listNode.val);                       // Add the listNode val - first val
        }else{                                          // Adding the already created level
            Integer largestVal = Math.max(result.get(level), listNode.val);

            // Can use either
            // result.remove(level); result.add(level, largestVal);    // remove and add val at index
            // or
            result.set(level, largestVal);                             // Set val at index
        }

        dfs(listNode.left, result, level+1);
        dfs(listNode.right, result, level+1);

    }

    private List<Integer> bfs(TreeNode root){
        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int largestVal = Integer.MIN_VALUE;

            int levelSize = queue.size();
            for (int i=0; i<levelSize; i++){
                TreeNode listNode = queue.poll();

                largestVal = Math.max(listNode.val, largestVal);

                if (listNode.left!=null)
                    queue.offer(listNode.left);

                if(listNode.right!=null)
                    queue.offer(listNode.right);

            }
            result.add(largestVal);
        }
        return result;
    }

}

```

### 103. Binary Tree Zigzag Level Order Traversal
```
    private void zigzagLevelOrderTraversal(TreeNode listNode, List<List<Integer>> lists, int level) {
        if(listNode == null) return;

        if(lists.size() <= level){
            List<Integer> newLevel = new LinkedList<>();
            lists.add(newLevel);
        }

        List<Integer> list  = lists.get(level);
        if(level % 2 == 0) {
            list.add(listNode.val);
        }else {
            // Inserts the the element at the position 0, in the list. 
            // Shifts the element currently at that position if any to the right for the zigZag
            list.add(0, listNode.val);
        }

        zigzagLevelOrderTraversal(listNode.left, lists, level + 1);
        zigzagLevelOrderTraversal(listNode.right, lists, level + 1);
    }
```

## 116. Populating Next Right Pointers in Each Node
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. 
The binary tree has the following definition:

        struct Node {
          int val;
          Node *left;
          Node *right;
          Node *next;
        }

Populate each next pointer to point to its next right listNode. If there is no next right listNode, 
the next pointer should be set to NULL. Initially, all next pointers are set to NULL.

Solution
    We will use a level order traversal. At any point we are going down and across/laterally. 
    We do not need to recurse or backtrack. 
    At any point we are connecting the nodes at its next level, children's level
    We will assign the next pointers to the 
            1. left listNode 
            2. right listNode
            
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
                Node listNode = queue.poll();
    
                // Assigning next pointer to a left listNode
                if (listNode.left !=null && listNode.right !=null ){
                    listNode.left.next = listNode.right;
                }else if(listNode.left != null && listNode.right == null){
                    Node nextNode = listNode.next;
                    if (nextNode == null){
                        listNode.left.next = null;
                    }else{
                        while(nextNode !=null ){
                            if (nextNode.left != null){
                                listNode.left.next = nextNode.left;
                                break;
                            }else if (nextNode.right != null){
                                listNode.left.next = nextNode.right;
                                break;
                            }else{
                                nextNode = nextNode.next;
                            }
                            listNode.left.next = null;
                        }
                    }
                }
    
                // Assigning next pointer to a right listNode
                if(listNode.right != null ){
                    Node nextNode = listNode.next;
                    if (nextNode == null){
                        listNode.right.next = null;
                    }else{
                        while(nextNode !=null ){
                            if (nextNode.left != null){
                                listNode.right.next = nextNode.left;
                                break;
                            }else if (nextNode.right != null){
                                listNode.right.next = nextNode.right;
                                break;
                            }else{
                                nextNode = nextNode.next;
                            }
                            listNode.right.next = null;
                        }
                    }
                }
    
                if (listNode.left != null){
                    queue.add(listNode.left);
                }
                if (listNode.right != null){
                    queue.add(listNode.right);
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
    PreOrder [1-2-3]: In a Preorder, you perform something on the current listNode first, before exploring its left and right nodes ???
    Inorder  [2-1-3]: In a InOrder, you perform something on the left listNode first, then the current listNode and then on its right listNode ???.

        
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
                          preorder = [16 10 8 6 7 9 12 11 13 22 20 19 21 24 23 25]
                                      ^
                                                 16
                          [6 7 8 9 10 11 12 13]      [19 20 21 22 23 24 25]

                          -------------------------------------------------------
                          preorder = [16 10 8 6 7 9 12 11 13 22 20 19 21 24 23 25]
                                         ^
                                                  16
                                    10               [19 20 21 22 23 24 25]
                          [6 7 8 9]    [11 12 13]
                          --------------------------------------------------------
                          preorder = [16 10 8 6 7 9 12 11 13 22 20 19 21 24 23 25]
                                            ^
                                                  16
                                    10                [19 20 21 22 23 24 25]
                              [89]       [11 12 13]
                        [6 7]
                        ---------------------------------------------------------
                          preorder = [16 10 8 6 7 9 12 11 13 22 20 19 21 24 23 25]
                                              ^
                          preorder = [16 10 8 6 7 9 12 11 13 22 20 19 21 24 23 25]
                                                ^
                          preorder = [16 10 8 6 7 9 12 11 13 22 20 19 21 24 23 25]
                                                  ^
                                                  16
                                    10                [19 20 21 22 23 24 25]
                              8       [11 12 13]
                        [6]     [9]
                           [7]
                        -------------------------------------------------------------------
Note:
This is similar to building a tree from a sorted array. Unlike finding the mid listNode by using binary search (low+hi)/2 on the sorted array,
here we first find the listNode to be created by looking at the next listNode in the preorder sequence. We then look up that listNode in the
inorder array and build the listNode listNode of our binary tree.

Reference
https://youtu.be/FBaSrNSf9po?list=PLFj4kIJmwGu2WedpHdv1p_LrLGvwqDvjZ

```
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
    
            // Pick the next listNode to be built by selecting the next preorder listNode as the current root and incrementing the preorder index
            // This is similar to building a a tree from a sorted array where we pick the next listNode by find the mid (low+hi)/2 of the segment of the array.
            int preorderNodeVal = preorder[nextPreorderIndex++]; // preOrderIndex++;
            TreeNode currNode = new TreeNode(preorderNodeVal);

            int inorderIndexForCurrNode = inOrderMap.get(preorderNodeVal); // lookup the index of the nextPreOrderNodeVal within the inorder map.
            currNode.left = buildTree(leftIndex, inorderIndexForCurrNode - 1);  // Return left listNode. On your way back, add the returned listNode to the left of the current root listNode
            currNode.right = buildTree(inorderIndexForCurrNode + 1, rightIndex); // Return right listNode. On your way back, add the returned listNode to the right of the current root listNode

            // Return the current listNode to be added to the left or the right side of the parent listNode.
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
Postorder sequence is left -> right -> listNode. So since here we are building it backward, it should go from listNode, right, left

Complexity Analysis
Time complexity : O(N)\mathcal{O}(N)O(N). Let us compute the solution with the help of master theorem T(N)=aT(bN)+Θ(Nd)T(N) = aT\left(\frac{b}{N}\right) + \Theta(N^d)T(N)=aT(Nb​)+Θ(Nd). The equation represents dividing the problem up into aaa subproblems of size Nb\frac{N}{b}bN​ in Θ(Nd)\Theta(N^d)Θ(Nd) time. Here one divides the problem in two subproblemes a = 2, the size of each subproblem (to compute left and right subtree) is a half of initial problem b = 2, and all this happens in a constant time d = 0. That means that log⁡b(a)>d\log_b(a) > dlogb​(a)>d and hence we are dealing with case 1 that means O(Nlog⁡b(a))=O(N)\mathcal{O}(N^{\log_b(a)}) = \mathcal{O}(N)O(Nlogb​(a))=O(N) time complexity.
Space complexity : O(N)\mathcal{O}(N)O(N), since we store the entire tree.
Reference:
https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/
https://youtu.be/rY9ejIY9Osw?list=PLFj4kIJmwGu2WedpHdv1p_LrLGvwqDvjZ

```
     
                                            16
                      
                                10                      22
                      
                          8          12           20           24
                      
                      6       9   11      13   19     21    23     25
                      
                 NULL     7   
     
                // postorder [7 6 9 8 11 13 12 10 19 20 23 25 24 22 16]
                                                               <--- ^
                // inorder   [6 7 8 9 10 11 12 13 16 19 20 21 22 23 24 25]
                                                  ^
```

#### Implementation
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

## 108. Convert Sorted Array to Binary Search Tree
https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
Implementation
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
        TreeNode listNode = new TreeNode(nums[mid]);

        listNode.left  = buildTree(nums, start, mid-1);
        listNode.right = buildTree(nums, mid+1, end);

        return listNode;
    }
```
## 109 Convert Sorted List to Binary Search Tree
https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

#### Intuition
The important condition that we have to adhere to in this problem is that we have to create
a height balanced binary search tree using the set of nodes given to us in the form of a linked list.
The good thing is that the nodes in the linked list are sorted in ascending order.
As we know, a binary search tree is essentially a rooted binary tree with a very special property or
relationship amongst its nodes. For a given listNode of the binary search tree, its value must be ≥\ge≥
the value of all the nodes in the left subtree and ≤\le≤ the value of all the nodes in the right subtree.
Since a binary tree has a recursive substructure, so does a BST i.e. all the subtrees are binary search trees in themselves.

The main idea in this approach and the next is that the middle element of the given list would form the
root of the binary search tree. All the elements to the left of the middle element would form the left subtree recursively.
Similarly, all the elements to the right of the middle element will form the right subtree of the binary search tree.
This would ensure the height balance required in the resulting binary search tree.

#### Algorithm
Since we are given a linked list and not an array, we dont really have access to the elements of the list using indexes.
We want to know the middle element of the linked list.
We can use the two pointer approach for finding out the middle element of a linked list. Essentially, we have two pointers
called slow_ptr and fast_ptr. The slow_ptr moves one listNode at a time whereas the fast_ptr moves two nodes at a time.
By the time the fast_ptr reaches the end of the linked list, the slow_ptr would have reached the middle element of the linked list.
For an even sized list, any of the two middle elements can act as the root of the BST.
Once we have the middle element of the linked list, we disconnect the portion of the list to the left of the middle element.
The way we do this is by keeping a prev_ptr as well which points to one listNode before the slow_ptr i.e. prev_ptr.next = slow_ptr.
For disconnecting the left portion we simply do prev_ptr.next = None
We only need to pass the head of the linked list to the function that converts it to a height balances BST. So, we recurse on
the left half of the linked list by passing the original head of the list and on the right half by passing slow_ptr.next as the head.

```
    public TreeNode sortedListToBST(ListNode head) {

        // If the head doesnt exist, then the linked list is empty
        if (head == null) {
            return null;
        }

        // Find the middle element for the list.
        ListNode mid = findMid(head);

        // The mid becomes the root of the BST.
        TreeNode listNode = new TreeNode(mid.val);

        // Another base case - when there is just one element in the linked list
        // When the listNode has no left and right siblings (only 1 element left) - head will be the mid.
        // Then, we will no longer need to traverse its left and right subtree
        if (head == mid) {
            return listNode;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        listNode.left = sortedListToBST(head);
        listNode.right = sortedListToBST(mid.next);
        return listNode;
    }

    private ListNode findMid(ListNode head) {

        ListNode prev = null; // The pointer used to disconnect the left half from the mid listNode.
        ListNode slow = head; // Mid element
        ListNode fast = head;

        // Iterate until fastPr doesnt reach the end of the linked list.
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Disconnect mid/slow from its prev
        if (prev != null) {
            prev.next = null;
        }

        return slow;
    }
```
## 98. Validate Binary Search Tree
https://leetcode.com/problems/validate-binary-search-tree/


