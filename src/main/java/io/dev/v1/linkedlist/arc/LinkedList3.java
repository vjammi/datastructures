package io.dev.v1.linkedlist.arc;

public class LinkedList3 {

	Node first;
	Node last;
	int N;

	private class Node {
		String item;
		Node next;
	}

	public void push(String item) {
		if (N == 0) {
			last = new Node();
			last.item = item;
			first = last;
			N++;
		} else {
			Node oldLast = last;
			last = new Node();
			last.item = item;
			oldLast.next = last;
			N++;
			System.out.println("Old Last: " + oldLast.item + ", - New Last " + last.item);
		}
	}

	public String popFront() {
		if (N == 0) {
			return null;
		}
		String item = first.item;
		first = first.next;
		return null;
	}

	public void traverseForward() {
		Node current = first;
		Node next = null;
		System.out.println("traverseForward");
		while (current != null) {
			System.out.print(current.item + ",");
			// Traverse forward
			next = current.next;
			current = next;
		}
	}

	public void reverseList() {
		Node current, previous, next;
		current = first;
		previous = null;

		System.out.println("reverse_practice");
		while (current != null) {
			// set the tail/last
			if (previous == null) {
				last = current;
				System.out.println(" Setting Tail: " + current.item);
			}
			// Set the first/head
			if (current.next == null) {
				first = current;
				System.out.println(" Setting Head: " + current.item);
			}

			// At any point we have references to the current node and to its children node.
			// We will navigate the entire list making use of this logic
			next = current.next; // save the current.children to children
			current.next = previous; // Update current.children to previous to reverseIteratively the pointer of the list

			// move forward
			previous = current; // Set the current to previous
			current = next; // Set children to current
		}

	}

	public void reverseListPractice(){
		Node previous = null;
		Node current = first;
		Node next = null;

		while(current != null){

			if (previous == null){
				last = previous;
				System.out.println("Assigned previous as the last " +previous);
			}

			if (current.next == null){
				first = current;
				System.out.println("Assigned current to first " +current);
			}

			next = current.next;
			current.next = previous;

			previous = current;
			current = next;
		}


	}


	public void traverseRecurssivelyForward(Node current) {
		if (current == null) {
			return;
		}
		System.out.print(current.item);
		traverseRecurssivelyForward(current.next);
	}

	public void traverseRecurssivelyReverse(Node current) {

		if (current == null) {
			System.out.println("Breaking out of uphill @" + current);
			return;
		}

		// climbing uphill
		//System.out.print("Uphill: " + current.item + ", ");
		traverseRecurssivelyReverse(current.next);
		System.out.print("Downhill: " + current.item + ", ");

/*		if (current.children != null) {
			TrieNode previous = current.children;
			previous.children = current;
			current.children = null;
			System.out.print(" Downhill:" + previous.item);
			if (previous.children == null) {
				first = previous;
				//System.out.println("Downhill: First " + first.item);
			}
		} else {
			System.out.println("Downhill: " + current.item + "-" + null + ", ");
		}
*/

	}

	public static void main(String[] args) {
		LinkedList3 linkedList = new LinkedList3();
		linkedList.push("2");
		linkedList.push("3");
		linkedList.push("4");
		linkedList.push("5");
		linkedList.push("6");

		System.out.println();
		//linkedList.reverse_practice();
		linkedList.reverseListPractice();
		linkedList.traverseForward();
		System.out.println();

	}
}
