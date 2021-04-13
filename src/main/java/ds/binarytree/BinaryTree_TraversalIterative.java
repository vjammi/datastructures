package ds.binarytree;

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

        TreeNode(int key, TreeNode left, TreeNode right) {
            this.val = key;
            this.left = left;
            this.right = right;
        }
    }

    public void print(String prefix, TreeNode node) {
        if (node != null) {
            print(prefix +"\t", node.right);
            System.out.println ( prefix +(" \t |-") + node.val);
            print( prefix + "\t", node.left);
        }
    }

    public void testPut(BinaryTree_TraversalIterative tree) {
        int[] arr = {1,2,0,3,0,4,0,5};
        //int[] arr = {16, 10, 22, 8, 12, 20, 24, 6, 9, 11, 13, 19, 21, 23, 25, 7};
        //int[] arr = {4,9,2,1,3,6,10,5};
        for(int i =0; i < arr.length; i++){
            if(arr[i] == 0){
                tree.put(arr[i], null);
            }else{
                tree.put(arr[i], String.valueOf(arr[i]));
            }
        }
    }

    public void put(int key, String value){
        root = put(root, key, value);
    }

    public TreeNode put(TreeNode x, int key, String value){
        if (x == null){
            //System.out.println("x == null [x.key: 0  New Key: "  + key);
            return new TreeNode(key);
        }
        if (key < x.val){
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.left
            x.left = put(x.left, key, value);
        }else if(key > x.val){
            // When it gets to the leaf node where (node == null), a new node is created, returned and assigned to x.right
            x.right = put(x.right, key, value);
        }else if (key == x.val){
            //System.out.println("key == x.key [x.key: " + x.key + " New Key: "  + key +"]");
        }
        return x;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();

            if (node != null) {
                preOrderList.add(node.val);
                //System.out.print(", " +node.val);

                // Add the right node into the stack to be processed after  reaching the leaf
                if (node.right != null ) {
                    stack.add(node.right);
                    System.out.print(", " +node.right.val);
                }

                if (node.left != null ) {
                    queue.add(node.left);
                }else{
                    // Add the last item added to the stack into the queue to the processed next,
                    // when there is not more left nodes [RLR] to traverse
                    if (!stack.isEmpty())
                        queue.add(stack.pop());
                }

            }

        }
        System.out.println("\n" +preOrderList.toString());
        return preOrderList;
    }

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> postOrderedList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<TreeNode> resultStack = new Stack<TreeNode>();

        if (root == null){
            return postOrderedList;
        }

        stack.add(root);
        while(!stack.isEmpty()) {

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
        System.out.println(postOrderedList.toString());
        return postOrderedList;
    }

    private TreeNode getPreviouslyVisitedNode(Stack<TreeNode> stack) {
        TreeNode previouslyVisitedNode = new TreeNode(500);
        if (!stack.isEmpty()) {
            previouslyVisitedNode = stack.peek();
        }
        return previouslyVisitedNode;
    }

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> inOrderedList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Stack<TreeNode> visitedStack = new Stack<TreeNode>();

        if (root == null){
            return inOrderedList;
        }

        stack.add(root);
        while(!stack.isEmpty()) {

            TreeNode currentNode = stack.peek();

            if (currentNode.left != null && !hasTheNodeAlreadyBeenVisited(visitedStack, currentNode.left)){
                stack.add(currentNode.left);
                visitedStack.add(currentNode.left);
            } else {
                if (hasTheNodeAlreadyBeenVisited(visitedStack, currentNode.left)){
                    visitedStack.pop();
                }

                TreeNode rightNode = currentNode.right;

                TreeNode poppedLeftNode = stack.pop();
                inOrderedList.add(poppedLeftNode.val);
                System.out.print(", "+poppedLeftNode.val);

                if (rightNode != null) {
                    // Add the right node or subtree to the stack for further  exploration [next]
                    stack.add(rightNode);
                }

            }

        }
        System.out.println(inOrderedList.toString());
        return inOrderedList;
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
        if (current != null ) nextLevelChildCount++;
        while (!queue.isEmpty()){

            List<Integer> children =  new LinkedList<>();
            int count = 0;
            for (int i=0; i< nextLevelChildCount; i++){ //(int)Math.pow(2, level)
                TreeNode node = queue.remove();
                if (node != null) {
                    children.add(node.val);
                    if (node.left  != null) {
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

            System.out.println(level +" - " +children.toString());
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

    public void runSomething() {
        System.out.println(Math.pow(2, 0));
    }

    private boolean hasTheNodeAlreadyBeenVisited(Stack<TreeNode> stack, TreeNode node) {
        if (stack.isEmpty()){
            return false;
        }
        return stack.peek().equals(node);
    }

    public static void main(String[] args) {
        BinaryTree_TraversalIterative tree = new BinaryTree_TraversalIterative();

        tree.testPut(tree);
        tree.print("", tree.root);

        //tree.preorderTraversal(tree.root);
        //tree.print("", tree.root);

        //tree.postorderTraversal(tree.root);
        //tree.print("", tree.root);

        //tree.inorderTraversal(tree.root);
        //tree.print("", tree.root);

        tree.runSomething();
        tree.levelOrder(tree.root);
    }

}
