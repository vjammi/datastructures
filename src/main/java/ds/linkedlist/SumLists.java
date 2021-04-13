package ds.linkedlist;

public class SumLists {

    public static void main(String args[]){
        SumLists obj = new SumLists();
        obj.testSumLists();
    }

    private void testSumLists() {
        Node head1 = new Node(7);
        head1.addNode(1).addNode(6).addNode(9);

        Node head2 = new Node(5);
        head2.addNode(9).addNode(2).addNode(9).addNode(9);

        int sum = 7+7;
        int times = sum/10;
        int remainder = sum%10;
        Node sumNode = sumNodes(head1, head2);
        println("Expected Sum: " +(99295 + 9617));

    }

    private Node sumNodes(Node head1, Node head2) {

        Node node1 = head1;
        Node node2 = head2;
        Node sumNode = null;
        int tim = 0;

        while (node1 != null || node2 != null){
            if (node1 == null && node2 == null)  break;
            int a = 0;
            int b = 0;
            if (node1!= null)
                a = node1.key;
            if (node2!= null)
                b = node2.key;
            int sum = a + b + tim;
            tim = sum/10;
            int rem = sum%10;

            if (sumNode == null) {
                sumNode = new Node(rem);
            }else {
                sumNode.addNode(rem);
            }

            if (node1 != null) node1 = node1.next;
            if (node2 != null) node2 = node2.next;
            println(" Rem " + rem + " Times " +tim +" " +node1 +" "+ node2);

        }
        sumNode.addNode(tim);

        sumNode.printChain();
        return sumNode;
    }

    private void println(String str) {
        System.out.println(str);
    }

    private void print(String str) {
        System.out.print(str +" ");
    }
}
