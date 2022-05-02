package ds.hashing;

public class Hashing_SeperateChaining<Key, Value> {

    private int M ;                   // 5 or 97      Number of chains. A prime number.
    private Node[] arrayOfchains;      // 0-4 or 0-96  Array of chains / list of Nodes

    private static class Node<Key, Value>   {
        private Object key; // Declare key and val of type Object
        private Object val;
        private Node next;

        public Node(Key key, Value val, Node node){
            this.key = key;
            this.val = val;
            this.next = node;
        }
    }

    public Hashing_SeperateChaining(int buckets){
        M = buckets;
        arrayOfchains = new Node[buckets];
    }

    /**
      Hash [hash(key)] To compute the Hash Value we will
          1. Use the the system hashCode to compute the hash of the object
               key.hashCode()
          2. We will then make it positive by
               Ending on the sign bit [ key.hashCode() & 0x7fffffff ]  or
               Taking the absolute of the integer [ Math.abs( key.hashCode() )  ]
          3. Take the Modulus of the above value by M to get a number between 0 and M-1
               (key.hashCode() & 0x7fffffff) % M
               Math.abs( key.hashCode() ) % M

     What does & 0x7fffffff mean?
        The constant 0x7FFFFFFF is a 32-bit integer in hexadecimal with all but the highest bit set.
        Despite the name, this method isn't getting the hashCode, rather looking for which bucket the key should appear in
        for a getBucketIndex set or map. When you use % on negative value, you get a negative value. There are no negative buckets so
        to avoid this you can remove the sign bit (the highest bit) and one way of doing this is to use a mask
        e.g.  x & 0x7FFFFFFF which keeps all the bits except the top one. Another way to do this is to shift the output
        x >>> 1 however this is slower.
        A slightly better approach is to use "take the modulus and apply Math.abs". This uses all the bits of the hashCode
        which might be better.

     */ // Can also use the x = 31x + y rule
    private int getBucketIndex(Key key){
        return getBucketIndex1(key);
        //return getBucketIndex2(key);
        //return getBucketIndex3(key);
    }
    private int getBucketIndex1(Key key){
        return (key.hashCode() & 0x7fffffff) % M; // The constant 0x7FFFFFFF is a 32-bit integer in hexadecimal with all but the highest bit set.
    }
    /**
     *      Returns the absolute value of an {@code int} value.
     *      If the argument is not negative, the argument is returned.
     *      If the argument is negative, the negation of the argument is returned.
     *
     *      public static int abs(int a) {
     *         return (a < 0) ? -a : a;
     *      }
     */
    private int getBucketIndex2(Key key){
        return Math.abs(key.hashCode()) % M;
    }
    public int getBucketIndex3(Key key) {
        return Math.abs(hash(key) % M);
    }
    /**HashMap in java 8 uses this
     */
    static final int hash(Object key) {
        int h;
        return (key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16));
    }

    /**
         Get the mapped bucket index for key.
         Assuming the HashTable is already populated, get the first node of the linked list stored at that bucketIndex
         Then, iterate thru each of the nodes stored within the bucket to determine if the node is present.

         After we iterate thru the list of nodes stored at that index, if we find a no match, we return null - Key not found
         Search [get(key)]: To look for a key in the hash table
         We compute the hash value based on the hash function described above.
         int i = hash(key);
         We pick that number i and go to that list. We will use the standard code for traversing a linked list. Start at the first node,
         as long as x is not null, do x = x.next. If you find the key which is equal to the key you are looking for, then return the value.
         We will have to cast the value because of the generic array creation in java. Otherwise return null.	public Value get(Key key) {
         int i = hash(key);
         for (Node x = st[i]; x != null; x = x.next)
         if (key.equals(x.key)) return (Value) x.val;
         return null;
         }
     */
    public Value get(Key key) {
        int bucketIndex = getBucketIndex(key);
        /**
        Node x = arrayOfchains[bucketIndex];
        while(x != null){
            if (key.equals(x.key)) {
                Key k = (Key) x.key;
                Value v = (Value) x.val;
                System.out.println("Key Found. Returning Value: " + k + " Value: " + v);
                return v;
            }
            x = x.next;
        }
        */
        for (Node x = arrayOfchains[bucketIndex]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                Key k = (Key) x.key;
                Value v = (Value) x.val;
                System.out.println("Key Found. Returning Value: " + k + " Value: " + v);
                return v;
            }
        }
        return null;
    }

    /**
     Insertion/Update [put(key, value)]: We do the same, if we find the node with key equal to the key we are looking for,
     we insert the value and return. Otherwise we make a new Node and put it at the beginning of the linked list.
     Replace st[i] with a new Node that links to the old st[i].

     If key is already present within the chain of nodes, then update it with the newer value. Else insert the new key into the chain
    Update an existing node. Using a for loop, iterate over the linked list stored in arrayOfchains[bucketIndex]
    If the new Key is in one of the nodes stored in the bucket then update the node

    Create a new Node 1006 - Create a new node for 1006 and chain it to the node already in there (bucketIndex.e 1001/null)
    the new node will be appended in front of the existing node (bucketIndex.e 1001 or NULL)
    We then update arrayOfchains[bucketIndex] with the newly created node, which intern chains to the previous node.
    Create a new Node with the key, val and point the children of the new node to the exiting node [NULL or a NON-NULL TrieNode].
    Update the value of the array with the newly created node and rhe new node pointing to the previous node
    For example
      arrayOfchains[0] = 20 > 15> 11> 5 > 0 > NULL
      arrayOfchains[1] = 21 > 16> 11> 6 > 1 > NULL
      arrayOfchains[4] = NULL  // Not true in practice when the other buckets are that full due to uniform hashing.
      but at some point the array value would be NULL

     The new Key was not found in any of the nodes stored in the bucket.
     So lets go ahead an insert this new node in
     the bucket with the node.next pointing to the existing list of nodes in the bucket
     This above one lines is equivalent to
     Node x = new Node(key, val);
     x.key = arrayOfchains[bucketIndex];
     arrayOfchains[bucketIndex] = x;
    */
    public void put(Key key, Value val) {
        int bucketIndex = getBucketIndex(key);
        //Update Value [If Key exists]
        for (Node x = arrayOfchains[bucketIndex]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        // Insert Key [since the key does not exist in the chain]
        Node oldChain = arrayOfchains[bucketIndex];
        Node newChain = new Node(key, val, oldChain);
        arrayOfchains[bucketIndex] = newChain;
    }

    public static void main(String args[]){
        int N = 25;             // Capacity expected
        int buckets = N/5;      // Number of buckets for the buckets
        Hashing_SeperateChaining<Integer, String> table = new Hashing_SeperateChaining<>(buckets);

        int[] a = {1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1011, 1012, 1013,
                   1014, 1015, 1016, 1017, 1018, 1019, 1020, 1021, 1022, 1023, 1024, 1025};

        for (int i = 0 ; i < a.length; i++){
            Integer key = (Integer) a[i];
            String value = (String) ("Val-"+a[i]);
            table.put(key, value);
        }
        System.out.println("Math.abs(..) "+Math.abs(-100001.01));
        table.get(1001);
        table.get(1010);
        table.get(1015);
        table.get(1020);
        table.get(1025);
    }

}
