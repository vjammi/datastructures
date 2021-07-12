package ds;

public class LinkedList {

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

		System.out.println("reverseList");
		while (current != null) {

			// you currently have access to current node and the to the next
			// node
			if (previous == null)
				last = current;
			if (current.next == null)
				first = current;
			System.out.print(current.item + ",");

			next = current.next; // save the current.next to next
			current.next = previous; // Update current.next to previous to
										// reverse the pointer of the list

			// Move forward
			previous = current; // Set the current to previous
			current = next; // Set next to current
		}

	}

	public void traverseList() {
		// System.out.println("traverseRecurssivelyForward ");
		// traverseRecurssivelyForward(first);

		System.out.println("traverseRecurssivelyReverse ");
		traverseRecurssivelyReverse(first);
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
			System.out.println("Breaking @" + current);
			return;
		}
		System.out.println("Uphill: " + current.item + ", ");
		traverseRecurssivelyReverse(current.next);
		if (current.next != null) {
			Node previous = current.next;
			previous.next = current;			
			current.next = null;
			System.out.println("Downhill2 previous: " + previous.item + " - previous.next: " + previous.next.item + ", ");
			if (previous.next == null) {
				first = previous;
				System.out.println("Downhill: First " + first.item);
			}
		} else {
			System.out.println("Downhill: " + current.item + "-" + null + ", ");
		}

	}

	public static void main(String args[]) {
		LinkedList linkedList = new LinkedList();
		linkedList.push("2");
		linkedList.push("3");
		linkedList.push("4");
		linkedList.push("5");
		linkedList.push("6");

		System.out.println("");
		linkedList.traverseForward();

		/*
		 * System.out.println(""); linkedList.reverseList();
		 * System.out.println(""); linkedList.traverseForward();
		 * 
		 * System.out.println(""); linkedList.reverseList();
		 * 
		 * System.out.println(""); linkedList.traverseForward();
		 */

		linkedList.traverseList();
		System.out.println("");
		linkedList.traverseForward();

	}
}