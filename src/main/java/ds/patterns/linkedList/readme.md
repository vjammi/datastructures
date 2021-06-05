#Linked List
Reference: https://youtu.be/OFr16YdsBEQ?list=PLujIAthk_iiO7r03Rl4pUnjFpdHjdjDwy&t=467

- insertion and deletion at O(N)
- finding s specific node is O(N)
- Add a Dummy nodes
- Linked Lists are a game of references. It all about how you modify these pointers in a systematic way. 
- If you are reordering elements of a list, you take thee nodes and change their next pointer points to. 
- if deleting a node, we modify the previous nodes next pointer to point to the next of the node to be deleted. 
   
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
                         
        public ListNode removeNthFromEnd(ListNode head, int n) {
            
            if (head == null)
                return head;
            
            // Setup the dummy node to point to the head of the list
            ListNode dummy = new ListNode(0) ;        
            dummy.next = head;
            
            // Setup the walker and runner to start at the dummy node
            ListNode walker = dummy;
            ListNode runner = dummy;
            
            // Advance the runner so that the dist between is N+1
            int i=0;
            while(runner!=null & i < n+1){
                runner =  runner.next;
                i++;
            }        
            
            // Now advance the runner and walker one step at a time until the runner reaches the end - runner is null
            while(runner!=null){
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
                         
        

##  LC24 Swap Nodes in Pairs
    Need 3 swaps to swap each pair and to maintain the LinkedList structure.
    Reference: https://youtu.be/OFr16YdsBEQ?list=PLujIAthk_iiO7r03Rl4pUnjFpdHjdjDwy&t=747
        
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

Summary of pointer movements

    dummy   1   2    3   4   5   6  NULL
    ^           ^
    dummy   2   1    3   4   5   6  NULL
                ^        ^
    dummy   2   1    4   3   5   6  NULL
                         ^       ^
Implementation
                     
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
            
            
            // Advance the runner 1 step to point to the last element of the current pair
            runner =  runner.next;
            
            // Now advance the runner and walker 2 steps for processing the next pair            
            walker = runner;               // To advance the walker to take the runner's position            
            int j = 0;
            while(runner!=null && j < 2){  // Advance the runner 2 steps ahead
                runner = runner.next;
                j++;
            }
        }            
        
        return dummy.next;        
    }


#### 2.1 Reverse a Linked List
#### 2.2 Middle of a LinkedList
    
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
                dummy2   5   4   null 
               
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
                 
      We will create 2 new linked lists.
      One of them will hold all the elements less than x and the other will hold all the elements >= x
      We iterate thru the original linked list and at each node we compare the node with x and move* the node to the 
      corresponding list. At the end we take the list with the bigger numbers and attach it to end of the smaller list with smaller numbers
      
      ***Very space effecient. Note that we are moving the nodes to the newer lists, not copying the nodes. 
      The only new nodes we are creating are the 2 new dummy nodes.

## Linked List Cycle II
Cycle Detection
    Basically what we are looking is a loop in the list. We also want to know where the cycle starts.            

                    4
        1   2   3       5
                    6
        ^^
        SF
        The slow pointer and fast pointer start out at the begining
        
                    4
        1   2   3       5
                    6
            ^   ^
            S   F                   
        At each step, the slow pointer moves 1 step ahead, fast pointer moves 2 step ahead.
        
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
                
    An easy solution would be to use an HashSet to store the nodes. If a come to a node that is already in the HashSet then we have a cycle. 
    Since it s a Linked list problem we can try to solve it using pointers.    
    There is an algo called Floyd's cycle finding algo. 