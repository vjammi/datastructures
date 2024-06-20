package dev.vjammi.ds.v1.stack;

public class DFSUsingImplicitExplicitStack {
    /*
     * Recursion template 1 - implicit stack
     * Return true if there is a path from cur to target.
     * In most cases, we can also use DFS when using BFS. But there is an important difference: the traversal order.
     * Different from BFS, the nodes you visit earlier might not be the nodes which are closer to the root node.
     * As a result, the first path you found in DFS might not be the shortest path.
     * Here is a recursion template of DFS that shows how the stack helps with this process.
     */

    //    boolean DFS(Node cur, Node target, Set<Node> visited) {
    //        //if cur is target;
    //        return true;
    //
    //        for (next : each neighbor of cur) {
    //            if (next is not in visited) {
    //                add next to visted;
    //                //if DFS(next, target, visited) == true;
    //                    return true;
    //            }
    //        }
    //        return false;
    //    }

    /*
     * Recursion template 2 - explicit stack
     * Return true if there is a path from cur to target.
     * The advantage of the recursion solution is that it is easier to implement.
     * However, there is a huge disadvantage: if the depth of recursion is too high, you will suffer from stack overflow.
     * In that case, you might want to use BFS instead or implement DFS using an explicit stack.
     * Here is a template using an explicit stack
     */
    //    boolean DFS(int root, int target) {
    //        Set<Node> visited;
    //        Stack<Node> stack;
    //        add root to stack;
    //        while (s is not empty) {
    //            Node cur = the top element in stack;
    //            remove the cur from the stack;
    //            return true if cur is target;
    //            for (Node next : the neighbors of cur) {
    //                if (next is not in visited) {
    //                    add next to visited;
    //                    add next to stack;
    //                }
    //            }
    //        }
    //        return false;
    //    }
}
