package ds.linkedlist;

public class FindIntercestion {


    private void testFindIntercestion() {
        Node list1 = new Node(3);
        list1.addNode(9).addNode(4).addNode(6).addNode(7).addNode(2).addNode(1);
        list1.printChain();
        System.out.println();

        Node list2 = new Node(10);
        list2.addNode(11).addNode(5).addNode(10).addNode(7).addNode(2).addNode(8);
        list2.printChain();
        System.out.println();
        
        checkIfListIntersect(list1, list2);
    }

    private void checkIfListIntersect(Node node1, Node node2) {

        Node current2 = node2;
        while(node1 != null) {
            while (node2 != null) {

                if (node1.key == node2.key) {
                    System.out.print(node1.key + "-" + node2.key + ", ");
                    if (node1.next != null && node2.next != null && node1.next.key == node2.next.key) {
                        node1 = node1.next;
                    }
                }
                node2 = node2.next; // increment node2
            }
            node1 = node1.next; // increment node1
            node2 = current2; // reset node2 to its head
        }

    }


    public static void main(String args[]){
        FindIntercestion obj = new FindIntercestion();
        obj.testFindIntercestion();
    }

    private void println(String str) {
        System.out.println(str);
    }

    private void print(String str) {
        System.out.print(str +" ");
    }
}

