package io.dev.v1.linkedlist.arc;

public class LinkedListReverse4 {

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
			System.out.println("Old Last: " +oldLast.item + ", - New Last "+ last.item);			
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
	
	public void traverseForward(){
		Node current = first;		
		Node next = null;		
		while (current != null){
			System.out.println(current.item);			
			next = current.next;
			current = next;						
		}
	}

	public void reverseList(){
		Node current, previous, next;
		current = first;
		previous = null;
		
		while (current != null){			
			if (previous != null)	last = current;
			if (current.next == null) first = current;			
			System.out.println("Current Item: " +current.item);
			
			next = current.next;
			current.next = previous;
			
			previous = current;
			current = next;	
		}
		
	}
	
	
	
	public static void main(String[] args) {
		LinkedListReverse4 linkedList = new LinkedListReverse4();
		linkedList.push("2");
		linkedList.push("3");
		linkedList.push("4");
		linkedList.push("5");
		linkedList.push("6");
		
		System.out.println();
		linkedList.traverseForward();
		
		System.out.println();
		linkedList.reverseList();
		System.out.println();
		linkedList.traverseForward();

		System.out.println();
		linkedList.reverseList();
		System.out.println();
		linkedList.traverseForward();

	}
}