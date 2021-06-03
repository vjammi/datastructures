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
    
            if (level == lists.size()){  // Ugly way of checking a list for that level has already been created
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

    public class BinaryTree_ConstructFromPreOrderInOrder {
    
        private int currentPreOrderIndex = 0;
        private int[] preorder;
        private int[] inorder;
        private Map<Integer, Integer> inOrderMap = new HashMap<>();

        //int[] preorder = {16,10,8,6,7,9,12,11,13,22,20,19,21,24,23,25};
        //int[] inorder  = {6,7,8,9,10,11,12,13,16,19,20,21,22,23,24,25};    
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            this.inorder = inorder;
    
            for(int i = 0 ; i < inorder.length; i++){
                inOrderMap.put(inorder[i], i);
            }
    
            int leftIndex = 0; int rightIndex = preorder.length - 1;
            return buildTree(leftIndex, rightIndex);
        }
    
        //int[] preorder = {16,10,8,6,7,9,12,11,13,22,20,19,21,24,23,25};
        //int[] inorder  = {6,7,8,9,10,11,12,13,16,19,20,21,22,23,24,25};
        private TreeNode buildTree(int leftIndex, int rightIndex) {
    
            // Break the recursion by returning null [when right index crosses the left index. The same condition holds good, while traversing both the left and right side of an element in the InOrder array].
            // This is similar to a return, while building a tree, when we encounter node == null while traversing either the left or the right nodes
            if (rightIndex < leftIndex){
                // At some point when we hit the root of the leftmost child - with its null left and right pointers.
                //      Left index will continue to be 0 but right pointer will now become 0-1 = -1.
                //      This is when we return. Right index (0-1) becomes smaller than the left index (0).
                // Similarly, at some point when we hit the root of the rightmost child - with null left and right pointers.
                //      Left and right index point the same node. Left index will continue to be incremented by 1,
                //      thereby crossing the length of the array. However, the right index will continue to be at n-1.
                //      This is when we return again. Right index (n-1) becomes smaller than the left index (currentInd + 1 = n)
                // Eitehr ways in both cases right index becomes smaller than the left index, causing the return.
                return null;
            }
    
            // Base Case [+] of +AB
            int currentVal = preorder[currentPreOrderIndex];
            int currentInx = inOrderMap.get(currentVal);
            TreeNode currentNode = new TreeNode(inorder[currentInx]);
            currentPreOrderIndex++;
    
            // A/Left of +AB
            TreeNode left = buildTree(leftIndex, currentInx - 1);  // Returned left node
            currentNode.left = left; // On your way back, add the returned node to the left of the current node
    
            // B/Right of +AB
            TreeNode right = buildTree(currentInx + 1, rightIndex); // Returned right node
            currentNode.right = right; // On your way back, add the returned node to the right of the current node
    
            // Return the current node to be added to the left or the right side of the parent node.
            return currentNode;
        }
        
    }