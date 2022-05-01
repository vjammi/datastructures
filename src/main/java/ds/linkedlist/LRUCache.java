package ds.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache: https://leetcode.com/problems/lru-cache/
 * K      V
 * 1 -> ListNode(100)
 * 2 -> ListNode(200)
 * 3 -> ListNode(300)
 * 4 -> ListNode(400)
 * 5 -> ListNode(500)
 * 6 -> ListNode(600)
 * <p>
 * head                   tail
 * |                        |
 * 6 >  5 >  4 >  3 >  2 >  1
 * <    <    <    <    <
 * <p>
 * ["LRUCache","put", "put", "get", "put", "get","put", "get", "get", "get"]
 * [ [2],       [1,1], [2,2], [1],  [3,3], [2],  [4,4], [1],   [3],   [4]]
 * <p>
 * ["LRUCache","put","get","put","get","get"]
 * [[1],[2,1],[2],[3,2],[2],[3]]
 * <p>
 * ["LRUCache","put","get","put","get","get"]
 * [[1],[2,1],[2],[3,2],[2],[3]]
 * <p>
 * 12/22 test cases pass
 */
class LRUCache {

    private ListNode head;
    private ListNode tail;
    private Map<Integer, ListNode> map;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap(capacity);
        head = null;
        tail = null;
    }

    class ListNode {
        int key;
        int value;

        ListNode next;
        ListNode prev;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
            prev = null;
        }
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        ListNode node = map.get(key);

        ListNode prevNode = node.prev;
        ListNode nextNode = node.next;

        node.prev = null;
        node.next = null;
        if (prevNode != null && nextNode != null) {
            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            ListNode oldHead = head;
            node.next = oldHead;
            oldHead.prev = node;
            head = node;
        } else {
            if (prevNode == null) {
                node.next = nextNode;
                if (nextNode != null)
                    nextNode.prev = node;
            }
            if (nextNode == null) {
                node.next = prevNode;
                prevNode.prev = node;
                prevNode.next = null;
                if (node == tail)
                    tail = prevNode;
            }
            head = node;
        }

        return node.value;
    }

    public void put(int key, int value) {

        // If cache is not full
        if (map.size() < capacity) {
            if (head == null) {
                ListNode node = new ListNode(key, value);
                head = node;
                tail = node;
                map.put(key, node);
            } else {
                ListNode oldHead = head;
                head = new ListNode(key, value);
                oldHead.prev = head;
                head.next = oldHead;
                head.prev = null;
                map.put(key, head);
            }
        } else {
            // If cache is full - Freeup the cache - remove the last node of the Linked List and remove the item from teh cache
            if (tail != null) {
                int keyToFreeUp = tail.key;
                ListNode prevNode = tail.prev;
                if (prevNode != null)
                    prevNode.next = null;
                tail = prevNode;
                map.remove(keyToFreeUp);
            }

            ListNode oldHead = head;
            head = new ListNode(key, value);
            oldHead.prev = head;
            head.prev = null;
            head.next = oldHead;
            map.put(key, head);
        }
    }

    public static void main(String[] args) {
        // Input        ["LRUCache","put", "put", "get", "put", "get","put", "get", "get", "get"]
        //              [ [2],       [1,1], [2,2], [1],  [3,3], [2],  [4,4], [1],   [3],   [4]]
        // expected     [null,null,null,1,null,-1,null,-1,3,4]
        int capacity = 2;
        LRUCache obj = new LRUCache(capacity);
        obj.put(1, 1);
        obj.put(2, 2);
        int param_1 = obj.get(1);
        obj.put(3, 3);
        int param_2 = obj.get(2);
        obj.put(4, 4);
        int param_3 = obj.get(1);
        int param_4 = obj.get(3);
        int param_5 = obj.get(4);
    }
}


