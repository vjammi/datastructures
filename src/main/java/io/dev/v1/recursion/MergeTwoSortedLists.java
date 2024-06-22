package io.dev.v1.recursion;

public class MergeTwoSortedLists {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null && l2 == null){
            return null;
        }

        ListNode l3Head = new ListNode(0, null);
        ListNode l3Tail = l3Head;

        while(l1 !=null || l2 !=null){

            if (l1 !=null && l2 !=null){

                if (l1.val == l2.val){
                    ListNode l1NewNode = new ListNode(l1.val, null);
                    l3Tail.next =  l1NewNode;
                    l3Tail = l3Tail.next;
                    l1 = l1.next;

                    ListNode l2NewNode = new ListNode(l2.val, null);
                    l3Tail.next =  l2NewNode;
                    l3Tail = l3Tail.next;
                    l2 = l2.next;

                }else if (l1.val < l2.val){
                    ListNode newNode = new ListNode(l1.val, null);
                    l3Tail.next =  newNode;
                    l3Tail = l3Tail.next;
                    l1 = l1.next;
                }else{
                    ListNode newNode = new ListNode(l2.val, null);
                    l3Tail.next =  newNode;
                    l3Tail = l3Tail.next;
                    l2 = l2.next;
                }
            }else if (l1 == null &&  l2 != null){
                ListNode newNode = new ListNode(l2.val, null);
                l3Tail.next =  newNode;
                l3Tail = l3Tail.next;
                l2 = l2.next;
            }else if (l1 != null && l2 == null){
                ListNode newNode = new ListNode(l1.val, null);
                l3Tail.next =  newNode;
                l3Tail = l3Tail.next;
                l1 = l1.next;
            }
        }

        return  l3Head.next;
    }

}
