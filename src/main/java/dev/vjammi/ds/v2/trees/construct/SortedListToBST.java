package dev.vjammi.ds.v2.trees.construct;

public class SortedListToBST {

    public class ListNode { int val; ListNode next; ListNode(int x) { val = x; } }

    public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }

    public TreeNode sortedListToBST(ListNode head) {

        // If the head doesn't exist, then the linked list is empty
        if (head == null)
            return null;

        // Find the middle element for the list.
        ListNode mid = findMid(head);

        // The mid becomes the root of the BST.
        TreeNode node = new TreeNode(mid.val);

        // Another base case - when there is just one element in the linked list
        // When the node has no left and right siblings (only 1 element left) - head will be the mid.
        // Then, we will no longer need to traverse its left and right subtree
        if (head == mid)
            return node;


        // Recursively form balanced BSTs using the left and right halves of the original list.
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);

        // Return the current node
        return node;
    }

    private ListNode findMid(ListNode head) {

        ListNode prev = null; // The pointer used to disconnect the left half from the mid node.
        ListNode slow = head; // Mid element
        ListNode fast = head;

        // Iterate until fastPr doesn't reach the end of the linked list.
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
}
