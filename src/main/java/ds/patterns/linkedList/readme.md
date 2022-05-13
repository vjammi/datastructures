# Linked List
- Insertion and Deletion is O(N). Fetching a specific node is O(N)
- Linked Lists are all about modify the pointers in a systematic way.
- If you are reordering elements of a list, you take thee nodes and change their next pointer points to.
- if deleting a node, we modify the previous nodes next pointer to point to the next of the node to be deleted.
- We use the dummy nodes for convenience.

## LC19 Remove Nth Node from End of List
- Add a dummy node to the start of the list with dummy.next pointing to the head. 
  Note: ??? Dummy nodes are pointers, not new nodes 
- Setup two pointers - Runner and Walker
- Remove the Nth Node

The idea is we setup the runner and walker pointers with the runner setup n steps ahead of the walker. They will be 
a distance of N nodes apart. Now we move the runner and walker one step at a time until the runner reaches the end. 
At a certain point, the runner will reach the end (r==null), and the walker will be at the Nth node from the end.
     
        N=2
        dummy   1   2    3   4   5   NULL
        w r
        dummy   1   2    3   4   5   NULL
        w                r                Note: Advance the runner n+1 steps ahead of the walker or walker needs to be n+1 steps behind runner
        dummy   1   2    3   4   5   NULL
                         w           r    Note: Advance the runner and walker one step at a time until the runner reaches the end - runner is null
        dummy   1   2    3   4   5   NULL
                         w           r    
        dummy   1   2    3    >   5   NULL
                         w   4 > null r         
                               
Note: Deleting the nth node will require the walker to the n+1 nodes behind the runner.
       
Iterative Solution                         

        public ListNode removeNthFromEnd(ListNode head, int n) {
            
            if (head == null)
                return head;
            
            // Setup the dummy node to point to the head of the list
            ListNode dummy = new ListNode(0) ;        
            dummy.next = head;
            
            // Setup the walker and runner to start at the dummy node
            ListNode walker = dummy;
            ListNode runner = dummy;
            
            // Advance the runner so that the dist between runner ans walker is n+1
            int i=0;
            while(runner != null && i < n+1){
                runner =  runner.next;
                i++;
            }        
            
            // Now advance the runner and walker one step at a time until the runner reaches the end - runner is null
            while(runner != null){
                walker = walker.next;
                runner= runner.next;            
            }
            
            // Delete the Nth node from the end. 
            ListNode next = walker.next; // Save the next node temporarily so that its next could be set to null
            walker.next = walker.next.next;
            next.next = null;        
            
            // return dummy.next
            return dummy.next;
        }
        
Recursive Solution

        public ListNode removeNthFromEnd_recursively(ListNode head, int n) {
            if (head == null)
                return head;
    
            // Setup the dummy node to point to the head of the list
            ListNode dummy = new ListNode(0) ;
            dummy.next = head;
    
            removeNthNode(dummy, n);
    
            return dummy.next;
        }
    
        private int removeNthNode(ListNode node, int n) {
            if (node == null)
                return 0;
    
            int level = removeNthNode(node.next, n) + 1;
            
            // Nth node from the end - Determined on the way back of the recursion stack.
            // dummy   1   2   3   4   5   NULL         n=2, so remove node 4
            //                 ^
            //                 3   2    1    0
            //                n+1  n    1    0
            if (level == n+1){                
                // Note the same iterative delete logic - deleting Nth node from the end.
                ListNode next = node.next; // Save the next node temporarily so that its next could be set to null
                node.next = node.next.next;
                next.next = null;
            }
    
            return level;
        }        

##  LC24 Swap Nodes in Pairs
    Need 3 swaps to swap each pair and to maintain the LinkedList structure.
```
            1   2    3   4   5   6  NULL
    dummy   1   2    3   4   5   6  NULL
                                   
    dummy   1   2    3   4   5   6  NULL
     ^          ^   
    
    dummy ----> 2    3   4   5   6  NULL
            1<-- 
            -------->   
    ^           ^                              
    dummy ----> 2    3   4   5   6  NULL
            1<-- 
            -------->   
    ^       ^
    dummy   2   1    3   4   5   6  NULL
    ^           ^
    dummy   2   1    3   4   5   6  NULL
                ^        ^
    ...
    
    dummy   2   1    4   3 ----- > 
                         ^   5 < 6  
                             ---->  NULL
                             ^
```
Summary of pointer movements
```
    dummy   1   2    3   4   5   6  NULL
    ^           ^
    dummy   2   1    3   4   5   6  NULL
                ^        ^
    dummy   2   1    4   3   5   6  NULL
                         ^       ^
```
Iterative Solution

    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return head;
        
        // Setup the dummy node to point to the head of the list
        ListNode dummy = new ListNode(0);        
        dummy.next = head;        
        
        // Setup the walker and runner to start at the dummy node
        ListNode walker = dummy;
        ListNode runner = dummy;
        
        // Advance the runner so that the runner is 2 steps ahead of walker
        int i = 0;
        while(runner!=null && i < 2){
            runner = runner.next;
            i++;
        }
        
        // Now the main logic to swap the nodes
        //    Swap the nodes
        //    Advance the runner one step
        //    Advance the runner and walker 2 steps for processing the next pair
        while(runner!=null){
            // walker 
            ListNode next = walker.next;
            // runner
            
            // Swap the Nodes
            next.next = runner.next; // 1 Point the next to runners next
            walker.next = runner;    // 2 Point the walker's next to runner
            runner.next = next;      // 3 Point the runner's next to next
            
            // After the swap of the nodes, advance the runner 1 step to point to the end of the current pair
            runner =  runner.next;
            
            // For swapping the next pair, advance the runner and walker 2 steps
            walker = runner;               // To advance the walker to take the runner's position            
            int j = 0;
            while(runner!=null && j < 2){  // Advance the runner 2 steps ahead
                runner = runner.next;
                j++;
            }
        }            
        
        return dummy.next;        
    }

Recursive Solution
```
    public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null)
                return head;

            // Setup the dummy node to point to the head of the list
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            swap(dummy);
            return dummy.next;
        }

        private void swap(ListNode node) {
            if (node == null || node.next == null || node.next.next == null) // takes care of odd input such as [1,2,3]
                return;

            ListNode walker = node;
            ListNode runner = node;

            // Advance runner 2 steps not n+1/2+1 steps because n+1 would become null at the end
            // The current node here is the node before the nodes that are to ve swapped.
            // ???            node     >       1     >      2       >    3   >   4   >   null
            // ???     dummy/walker        walkerNext       runner      runnerNext
            // ???              2      >       1     >      3       >      4       >    null
            // ???                      dummy/walker        walkerNext       runner      runnerNext
            for(int i=0; i<2; i++)
                runner = runner.next;

            // Swap nodes
            ListNode walkerNext = walker.next;
            ListNode runnerNext = runner.next;
            walkerNext.next = runnerNext;
            runner.next = walkerNext;
            node.next = runner;
            node = node.next;

            swap(node.next);

        }
```
## 2.1 Reverse a Linked List
Iterative Solution
```

    //                         head
    //               null       1   >   2   >   3   >   4   >   5   >   null
    //                ^         ^       ^
    // 1-reverse     prev <(1) curr    next
    // 2-increment             prev    curr    next
    //                                                          prev    curr
    //                                                          head

    public ListNode reverseList_iterative(ListNode head) {
        if (head == null)
            return head;

        ListNode previous = null; // Important for the first node, that needs to be turned to last
        ListNode current = head;
        while(current!=null){
            // previous
            // current
            ListNode next = current.next;

            current.next = previous; // Turning the direction of next

            previous = current;
            current = next;
        }
        head = previous;   // Set head to the previous 
        return previous;   // Why return previous but not current? Because the current becomes null
    }
```
Recursive Solution        
```
        /*
            #    node    previous   next
            --------------------------------
            4    5                  null
            3    4    <  5
            2    3    <  4          null
            1    2      3           null
            0    1      2           null
            return 1
         */

    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        ListNode tail = reverseListRecurssive(head);
        tail.next = null;
        System.out.println("New Tail val " +tail.val);
        return newHead;
    }
    
    ListNode newHead;
    public ListNode reverseListRecurssive(ListNode node) {
        if (node.next == null){
            newHead = node;               
            return node;
        }        
        
        ListNode previous =  reverseListRecurssive(node.next);                    

        if (previous.next == null)
            newHead = previous;                

         // Change direction
         previous.next = node;
         //current.next = null;  // WE do not do this????

        return node;
    }

```

## 2.2 Middle of a LinkedList
    
## Partition List
    Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes 
    greater than or equal to x. You should preserve the original relative order of the nodes in each of the two partitions.
    
    Partition the list at 3
        Input  = 1   5   4   2       
        x      = 3
        Output = 1   2 | 5   4
                   < 3 | >= 3

            dummy1   1   2   null      // <x
            dummy2   5   4   null      // >=x   
    
            dummy1   1   2
                         |
            dummy2       5   4   null
               
            return dummy1.next

        Input: head = [1,4,3,2,5,2], x = 3
            0 1 4 3 2 5 2
            0
            0
            
            0
            0 1 2 2 null   
            0 4 3 5 null
            
            0 1 2 2 
                  |   
                0 4 3 5 null   
        
        Output: [1,2,2,4,3,5]
Solution           
We will create 2 new linked lists.
One of them will hold all the elements less than x and the other will hold all the elements >= x
We iterate thru the original linked list and at each node we compare the node with x and move* the node to the 
corresponding list. At the end we take the list with the bigger numbers and attach it to end of the smaller list with smaller numbers
***Space efficient. Note that we are moving the nodes to the newer lists, not copying the nodes. 
The only new nodes we are creating are the 2 new dummy nodes.


        public ListNode partition(ListNode head, int x) {
    
            if (head == null)
                return head;
    
            ListNode dummy = new ListNode(0);
            dummy.next = head;
    
            ListNode dummy1 = new ListNode(0);
            ListNode dummy2 = new ListNode(0);
    
            ListNode walker = dummy;
            ListNode runner = dummy;
    
            ListNode runner1 = dummy1;
            ListNode runner2 = dummy2;
    
            int counter = 0;
            while(runner!=null && counter < 2){
                runner = runner.next;
                counter++;
            }
    
            // Iterate thru the original list splitting the partitioning the list into 2
            while(walker.next!=null){
    
                // Freeup the node from the list
                ListNode next = walker.next;
                walker.next = runner; // remove walkers next element from the list
                next.next = null;     // dis-associate the node from the list by setting up the next to null
    
                if (next.val < x){
                    runner1.next = next;
                    runner1 = runner1.next;
                }else{ // >= x
                    runner2.next = next;
                    runner2 = runner2.next;
                }
    
                if(runner!=null) {
                    runner = runner.next; // Advance runner by 1 step
                }
    
            }
            runner1.next=dummy2.next;
            return dummy1.next;
        }


## Linked List Cycle II
This basically is a Cycle Detection Problem, we are looking to see if there is a loop in the list. We also want to know where the cycle starts.            

Solution                
Option 1: An easy solution would be to use an HashSet to store the nodes. If we come to a node that is already in the HashSet 
then we have a cycle.     

Option 2: Since it s a Linked list problem, we can try to solve it using pointers. 
          We solve it using the Floyd's cycle finding algorithm. 

                    4
        1   2   3       5
                    6
        ^^
        SF
        The slow pointer and fast pointer start out at the dummy node at the beginning
        
                    4
        1   2   3       5
                    6
            ^   ^
            S   F                   
        *** Now, at each step the slow pointer moves 1 step ahead, while the fast pointer moves 2 steps ahead.
        
                    4
        1   2   3       5
                    6
                        ^ ^  They both meet at 5
                        S F


                    4
        1   2   3       5
                    6
        ^               ^
        S               F
        Reset the the pointers and move them now 1 step at a time.                         

                    4
        1   2   3       5
                    6
               ^^
               SF
        Now they both meet at 3
        
Why does this work?

                        K   
                    /   4    \
                  /  /     \    
        1 -  3  -   3   N   5  |  
        |    m    |   \     /   
                        6
            Distance travelled by fast pointer = M + N*Cf + K
            Distance travelled by slow pointer = M + N*Cs + K
            Since Fast pointer is 2X of slow pointer X
                        X = N (Cf - Cs) 
                        6 =  
Implementation

        public ListNode detectCycle(ListNode head) {
    
            if (head == null)
                return head;
            if (head.next == null)
                return null;
            if (head.next.next==null)
                return null;
    
            ListNode dummy = new ListNode(0);
            dummy.next = head;
    
            ListNode walker = dummy;
            ListNode runner = dummy;
    
            // Detect if there is a cycle and break
            boolean cyle = false;
            while(runner !=null && walker != null){
                walker = walker.next;
                if (runner.next==null) // ***
                    return null; // No cycle
                else
                    runner = runner.next.next;
                if (walker == runner){
                    cyle = true;
                    break;
                }
            }
    
            // If there is a cycle then find the node where there is a cycle using floyd's cycle detection algorithm
            if (cyle){
                walker = dummy;
                // runner stays where it was last
                while(walker != runner)    {
                    walker = walker.next;
                    runner = runner.next;
                }
                return walker;
            }else{
                return null;
            }
        }