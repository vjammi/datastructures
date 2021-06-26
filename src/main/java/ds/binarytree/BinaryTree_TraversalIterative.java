package ds.binarytree;

import jdk.nashorn.internal.ir.CallNode;

import java.util.*;

public class BinaryTree_TraversalIterative {
    TreeNode root;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int key) {
            this.val = key;
        }
    }


    private TreeNode getPreviouslyVisitedNode(Stack<TreeNode> stack) {
        TreeNode previouslyVisitedNode = new TreeNode(500);
        if (!stack.isEmpty()) {
            previouslyVisitedNode = stack.peek();
        }
        return previouslyVisitedNode;
    }

    /*
    private boolean hasTheNodeAlreadyBeenVisited(Stack<TreeNode> stack, TreeNode node) {
        if (stack.isEmpty()) {
            return false;
        }
        return stack.peek().equals(node);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node != null) {
                preOrderList.add(node.val);
                //System.out.print(", " +node.val);

                // Add the right node into the stack to be processed after  reaching the leaf
                if (node.right != null) {
                    stack.add(node.right);
                    System.out.print(", " + node.right.val);
                }

                if (node.left != null) {
                    queue.add(node.left);
                } else {
                    // Add the last item added to the stack into the queue to the processed next,
                    // when there is not more left nodes [RLR] to traverse
                    if (!stack.isEmpty())
                        queue.add(stack.pop());
                }

            }

        }
        System.out.println("\n" + preOrderList.toString());
        return preOrderList;
    }

    public List<Integer> inorderTraversal1(TreeNode root) {

        List<Integer> inOrderedList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<TreeNode> visitedStack = new Stack<TreeNode>();

        if (root == null) {
            return inOrderedList;
        }

        stack.add(root);
        while (!stack.isEmpty()) {

            TreeNode currentNode = stack.peek();

            if (currentNode.left != null && !hasTheNodeAlreadyBeenVisited(visitedStack, currentNode.left)) {
                stack.add(currentNode.left);
                visitedStack.add(currentNode.left);
            } else {
                if (hasTheNodeAlreadyBeenVisited(visitedStack, currentNode.left)) {
                    visitedStack.pop();
                }

                TreeNode rightNode = currentNode.right;

                TreeNode poppedLeftNode = stack.pop();
                inOrderedList.add(poppedLeftNode.val);
                System.out.print(", " + poppedLeftNode.val);

                if (rightNode != null) {
                    // Add the right node or subtree to the stack for further  exploration [next]
                    stack.add(rightNode);
                }

            }

        }
        return inOrderedList;
    }

    */

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

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> postOrderedList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<TreeNode> resultStack = new Stack<TreeNode>();

        if (root == null) {
            return postOrderedList;
        }

        stack.add(root);
        while (!stack.isEmpty()) {

            TreeNode node = stack.peek();

            // If the exploration of the right subtree is complete then the node is ready to be popped from the working stack to the result stack
            // This is the left node [6, 8, 10...] being added to the result stack. This is our way back from the right subtree
            if (!resultStack.isEmpty() && node.right == resultStack.peek()) {
                TreeNode poppedNode = stack.pop();
                resultStack.add(poppedNode);
                postOrderedList.add(poppedNode.val);
                continue;
            }

            TreeNode previouslyVisitedNode = getPreviouslyVisitedNode(resultStack);

            // While there is a left node to iterate and that left node has not been previously explored.
            if (node.left != null && !node.left.equals(previouslyVisitedNode)) {
                // We add the node to the stack for exploration.
                stack.add(node.left);
            } else { //if (node.left == null || node.left.equals(previouslyVisitedNode)){

                // While there is no more a left node to be iterated [left node is null],
                // We can now explore its right node. [L R N or A B +]
                if (node.right != null) {
                    // Add the right node or subtree to the stack for further  exploration [next]
                    stack.add(node.right);
                } else {
                    // Bingo... When left and right nodes are null, we can add the node to the result.
                    // This right node [7, 9, 12...] that is being added to the result stack
                    TreeNode poppedNode = stack.pop();
                    resultStack.add(poppedNode);
                    postOrderedList.add(poppedNode.val);
                }
            }

        }
        return postOrderedList;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrderList = new ArrayList<>();
        if (root == null)
            return levelOrderList;

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode current = root;
        queue.add(current);

        int level = 0;
        int nextLevelChildCount = 0;
        if (current != null) nextLevelChildCount++;
        while (!queue.isEmpty()) {

            List<Integer> children = new LinkedList<>();
            int count = 0;
            for (int i = 0; i < nextLevelChildCount; i++) { //(int)Math.pow(2, level)
                TreeNode node = queue.remove();
                if (node != null) {
                    children.add(node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                        count++;
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                        count++;
                    }
                }
            }
            nextLevelChildCount = count;

            System.out.println(level + " - " + children.toString());
            levelOrderList.add(children);
            level++;
        }

        System.out.println(levelOrderList.toString());
        return levelOrderList;
    }

    public List<List<Integer>> levelOrderAlternate(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.offer(root);
        }

        TreeNode cur;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> subAns = new LinkedList<Integer>();
            for (int i = 0; i < size; ++i) {        // traverse nodes in the same level
                cur = q.poll();
                subAns.add(cur.val);                // visit the root

                if (cur.left != null) {
                    q.offer(cur.left);              // push left child to queue if it is not null
                }
                if (cur.right != null) {
                    q.offer(cur.right);             // push right child to queue if it is not null
                }
            }
            ans.add(subAns);
        }

        return ans;
    }

    /*
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

    * */

    public static void main(String[] args) {
        BinaryTree_TraversalIterative tree = new BinaryTree_TraversalIterative();

        tree.testPut(tree);
        tree.print("", tree.root);

        List<Integer> preorderTraversalList = tree.preorderTraversal(tree.root);
        System.out.println("preorderTraversalList " + preorderTraversalList);

        List<Integer> inOrderTraversalList = tree.inorderTraversal(tree.root);
        System.out.println("inOrderTraversalList " + inOrderTraversalList);

        List<Integer> postOrderTraversalList = tree.postOrderTraversal(tree.root);
        System.out.println("postOrderTraversalList " + postOrderTraversalList);

        //tree.runSomething();
        //tree.levelOrder(tree.root);
    }

    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix + "\t", node.right);
            System.out.println(prefix + (" \t |-") + node.val);
            print(prefix + "\t", node.left);
        }
    }

    public void testPut(BinaryTree_TraversalIterative tree) {
        //int[] arr = {1,2,0,3,0,4,0,5};
        //int[] arr = {4,9,2,1,3,6,10,5};
        int[] arr = {16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, 7};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                tree.put(arr[i], null);
            } else {
                tree.put(arr[i], String.valueOf(arr[i]));
            }
        }
    }

    public void put(int key, String value) {
        root = put(root, key, value);
    }

    public TreeNode put(TreeNode x, int key, String value) {
        if (x == null) {
            //System.out.println("x == null [x.key: 0  New Key: "  + key);
            return new TreeNode(key);
        }
        if (key < x.val) {
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.left
            x.left = put(x.left, key, value);
        } else if (key > x.val) {
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.right
            x.right = put(x.right, key, value);
        } else if (key == x.val) {
            //System.out.println("key == x.key [x.key: " + x.key + " New Key: "  + key +"]");
        }
        return x;
    }
}
