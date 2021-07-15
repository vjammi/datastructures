N-Array (Generic Trees)
Generic trees are a collection of nodes where each node is a data structure that consists of records and a list of references to its children(duplicate references are not allowed).
Unlike the linked list, each node stores the address of multiple nodes. Every node stores address of its children and the very first node’s address will be stored in a separate pointer called root.
The Generic trees are the N-ary trees which have the following properties:
1. Many children at every node.
2. The number of nodes for each node is not known in advance.

Node Definition
```
    class Node {
        public int val;
        public List<Node> children;
        public Node() {  }
        public Node(int val) { this.val = val;  }
        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
```
NArray Tree Traversal Examples
```
    Input 1: root = [1,null,3,2,4,null,5,6]
            1
        3   2   4
      5   6
    Output 1: [1,3,5,6,2,4]

    Input 2: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
             1
    2     3       4   5
        6   7   8   9   10
            11  12  13
            14
    Output 2: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
```

Preorder Traversal
```
    public List<Integer> preorder(Node root) {
        List<Integer> preorderList = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node node = stack.pop();
            if (node != null){
                System.out.println(node.val);
                preorderList.add(node.val);
                List<Node> children = node.children;
                // Notice that we are adding items into the stack backwards.
                // This is similar to we adding node.right first and then node.left for preorder Binary Trees iterative traversals
                for (int i=children.size() -1; i>=0; i--){
                    Node child =  children.get(i);
                    if (child != null) stack.push(child);
                }
            }
        }
        return preorderList;
    }
```
Postorder Traversal
```
    public List<Integer> postorder(Node root) {
        List<Integer> postorderList = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node node = stack.pop();

            if (node!=null) {
                List<Node> children = node.children;
                for(int i=0; i<children.size(); i++){
                    stack.push(children.get(i));
                }
                postorderList.add(0, node.val); // Could also use a Stack to store the elements
            }
        }

        System.out.println(postorderList);
        return postorderList;
    }
```
LevelOrder Traversal
```
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levelOrderList = new ArrayList<>();
        if (root == null)
            return levelOrderList;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();

            List<Integer> list = new ArrayList<>();
            while(size > 0){
                Node node = queue.poll();
                if(node != null){
                    list.add(node.val);
                    List<Node> children = node.children;
                    for (Node child: children){
                        if (child!=null) queue.offer(child);
                    }
                }
                size--;
            }
            levelOrderList.add(list);

        }

        System.out.println(levelOrderList);
        return levelOrderList;
    }
```