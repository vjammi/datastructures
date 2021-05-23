Linked List
- insertion and deletion at O(N)
- finding s specific node is O(N)
- Add a Dummy nodes
- Linked Lists are a game of references. It all about how you modify these pointers in a systematic way. 
- If you are reordering elements of a list, you take thee nodes and change their next pointer points to. 
- if deleting a node, we modify the previous nodes next pointer to point to the next of the node to be deleted. 
   
1. LC19 Remove Nth Node from End of List
    - Add a dummy node to the start of the list with dummy.next pointing to the head. 
      Note: ??? Dummy nodes are pointers, not new nodes 
    - Setup two pointers - Runner and Walker
    - Remove the Nth Node
    
    The idea is we setup the runner and walker pointers with the runner setup n steps ahead of the walker. They will be 
    a distance of N nodes apart. Now we move the runner and walker one step at a time until the runner reaches the end. 
    At a certain point, the runner will reach the end (r==null), and the walker will be at the Nth node from the end.
        
    Deleting the nth node will require the walker to the n+1 nodes behind the runner.
     
    N=2
    dummy   1   2    3   4   5   6  NULL
    r
    w    
    dummy   1   2    3   4   5   6  NULL
    w           r
    dummy   1   2    3   4   5   6  NULL
                             w      r
    Removing will need w to be n+1 steps behind r    
    dummy   1   2    3   4   5   6  NULL
                         w          r      
        

2. LC24 Swap Nodes in Pairs
    Need 3 swaps to swap each pair and to maintain the LinkedList structure.
    
            1   2    3   4   5   6  NULL
    dummy   1   2    3   4   5   6  NULL
                
    dummy   1   2    3   4   5   6  NULL
     ^          ^   
    dummy   2   1    3   4   5   6  NULL
                ^        ^
    dummy   2   1    4   3   5   6  NULL
                         ^       ^
    dummy   2   1    4   3 ----- > 
                             5 < 6  
                             ---->  NULL
2.1 Reverse a Linked List
2.2 Middle of a LinkedList
    
3. Partition List
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

4. Linked List Cycle II
    Cycle Detection
    Basically what we are looking is a loop in the list. Not only that we also want to know where the cycle starts.            
                    4
        1   2   3       5
                    6
    An easy solution would be to use an HashSet to store the nodes. If a come to a node that is already in the HashSet then we have a cycle. 
    Since it s a Linked list problem we can try to solve it using pointers.    
    There is an algo called Floyd's cycle finding algo. 
    
    
    
5. Closing Thoughts