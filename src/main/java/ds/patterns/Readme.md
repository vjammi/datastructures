
### How would you Test your algorithm?
You test every implementation of an algorithm the same way: 
    take an input, 
    calculate by hand your expected output, and 
    compare it to the output the algorithm provides you.

Boundary conditions
Edge Cases
Invalid Arguments
Test by hand

https://www.khanacademy.org/computing/ap-computer-science-principles/algorithms-101/evaluating-algorithms/a/verifying-an-algorithm
Test with various inputs - negative numbers. Does it still work on the list of positive numbers? What about a mixed list of positive and negative numbers? What about fractions? Or irrational numbers?



Verification - This area looks at the process the algorithm uses. In other words, it analyzes the steps the algorithm uses and determines if they are correct.
Validation - This area looks at the outputs of the algorithm and determines whether the results produced are correct.

Time - This area looks at the performance of the algorithm and determines how long it takes to produce its results.
Space - This area looks at the space the algorithm occupies in storage and in memory. 
        This is of particular interest when the target device is resource constrained.
        
        
### Permutations - Here order in which they are arranged matter - so a greater number of permutations
https://www.khanacademy.org/math/precalculus/x9e81a4f98389efdf:prob-comb#x9e81a4f98389efdf:combinatorics-precalc
   
26!
    26 x 26 x 26    = 26 ^ 3
    26 x 25 x 24    = 26!/(26-3)! = 26! / 23!
    24 x 25 x 26    =  

3! & 0!=1
    3 x 2 .... 1
    -   -      -
    3! / (3-3)!
    3! / 0!         
    3! / 1      Note: 0! = 1

Problem on Permutations
1. If there are 6 possible colors [B Y W R O G]. How many 4 color codes can be made, if the codes cannot be repeated. Note, Order here matters.

    B   R   Y   G
    -   -   -   -
    G   R   Y   B
    -   -   -   -
    
    6   5   4   3   = 360 possible colors
    -   -   -   -
2. A club of 9 people want to choose a board of 3 oficers. President, Vice President, Sec. How many ways are there to choose the board from 9 people.
  Note: we will assume that if one person is picked for a President, he cannot be picked for other offices.
  
      9  8  7   =   9 x 8 x 7 = 504 possible ways to pick your board out of 9 people. 
      P  VP S          
      -  -  -
      
Permutation is counting the different ways/arrangement of k things.
Combination is N choose k. If we have a pool of N things. how many combinations of k things are there? n C k
We take the number of permuations - n!/(n-k)! and divide how many  ways to arrang k people.
    n!/(n-k)! / k! (Number of ways to arrange k things in k spots)
    n! / k!(n-k)!
    
*** In permutations we have seen that there are 360 ways of seating 6 people in 4 chairs.
But what if we did not care for who is sitting in which chair.
    
*** In combinations, we would say how many combinations are there to choose four people out of a group of 6 people.  
    6! / 4! x (6-4)! = 6! / 4! x 2! = 15 combinations
    
            6!
       x  -----
    4!    (6-4)! 
   Combi  Permutations        
    
To summarize - There are 360 permutations but 15 combinations for putting 6 people in 4 chairs 
In permutations, order matters, so we see more permutations. 
 However in combinations, order does not matter. Therefore, less number of chairs. 
    
Number of ways to arrange k things in k spots = k!. 4 things in 4 spots - 4!, 3 things in 3 spots - 3!       


### Combinations - Order does not matter. So lesser number of permutations 

A B C D E F
    6   5   4   = 120 permutations
    -   -   -
How many ways can you arrange 3 people? Say A B C
Permutations would be 3 x 2 x 1 = 6
    3 2 1 = 6
    - - -
    ABC BAB CAB
    ACB VBA CBA

Combinations would be   6 C 3 




------------------------------------------------------
Programmatically visualize the decision process to generate permutations and combinations - conceptual understanding
1. Permutations - n!
   https://youtu.be/us0cYQXQpxg      
2. Combinations - 2^N combinations 
   https://youtu.be/NA2Oj9xqaZQ?t=341





------
Reason it thru it so that we can connect it to a formulae.
It is always best to reason it thru.  
Comes out of straight logic
Every time I revisit it after few years, I rethink about it, as opposed to memorizing it or not understanding what is going on.
visual understanding