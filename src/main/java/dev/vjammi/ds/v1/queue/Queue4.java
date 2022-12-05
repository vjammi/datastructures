package dev.vjammi.ds.v1.queue;

public class Queue4<Key, Value> {
    private Node<Key, Value> first, last = null;
    int N = 0;

    private class Node<Key, Value>{
        private final Key key;
        private final Value value;
        private Node<Key, Value> next;

        public Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }
    }

    public void enqueue(Key key, Value value){
        if (first == null){
            last = new Node<>(key, value);
            N++;
            first =  last; // first = last
        }else{
            Node oldLast = last;
            last = new Node<>(key, value);
            oldLast.next = last;
            N++;
        }
    }

    public Value dequeue(){
        Value valueToReturn = first.value;
        first = first.next;
        N--;
        return valueToReturn;
    }

    public void removeNode(Key key){
        if (first != null && first.next == null && first.key == key){
            first = null;
            last = null;
            N=0;
        }else {
            Node<Key, Value> current = first;
            if (current != null && current.next != null && current.next.key.equals(key)) {
                Node<Key, Value> newNext = current.next.next;
                current.next = newNext;
                N--;
            }
        }
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public static void main(String[] args){
        Queue4<Integer, String> queue = new Queue4<>();
        System.out.println("isEmpty : " +queue.isEmpty());
        queue.enqueue(100, "Value-100");
        queue.removeNode(100);
        System.out.println("isEmpty : " +queue.isEmpty());

        queue.enqueue(200, "Value-200");
        queue.enqueue(300, "Value-300");
        queue.enqueue(400, "Value-400");
        System.out.println("isEmpty : " +queue.isEmpty());
        queue.removeNode(300);
        System.out.println("Size : " +queue.size());
        String value = queue.dequeue();
        System.out.println("Size : " +queue.size());
        System.out.println("isEmpty : " +queue.isEmpty());
    }

}
