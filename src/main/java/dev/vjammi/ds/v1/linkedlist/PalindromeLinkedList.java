package dev.vjammi.ds.v1.linkedlist;

/**
    234. Palindrome Linked List
    Given the head of a singly linked list, return true if it is a palindrome.

    Input: head = [1,2,2,1]
    Output: true

    Input: head = [1,2]
    Output: false

 */
public class PalindromeLinkedList {
    // TODO:
    // Approach 1: Copy the Linked List to an Array. Use 2 pointers to determine isPalindrome

    // Approach 2: Traverse recursively. On the way backwards the recursion stack,
    //             compare each node with the node in front of the list, traversing until the middle

    // Approach 3: Using slow and fast pointers determine the middle of the list.
    //             Temporarily, reverse the list pointers for the second half
    //             Now traverse the first and second half to determine isPalindrome
    //             Put the second half back in order.

}
