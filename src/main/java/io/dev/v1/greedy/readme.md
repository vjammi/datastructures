#### Greedy Algorithms
[Reference: https://www.programiz.com/dsa/greedy-algorithm]

#### Coin Change
Problem: You have to make a change of an amount using the smallest possible number of coins.
Amount: $18
Available coins are
  $5 coin
  $2 coin
  $1 coin
There is no limit to the number of each coin you can use.

Solution
Create an empty solution-set = { }. Available coins are {5, 2, 1}.
We are supposed to find the sum = 18. Let's start with sum = 0.
Always select the coin with the largest value (i.e. 5) until the sum > 18. (When we select the largest value at each step, we hope to reach the destination faster. This concept is called greedy choice property.)
In the first iteration, solution-set = {5} and sum = 5.
In the second iteration, solution-set = {5, 5} and sum = 10.
In the third iteration, solution-set = {5, 5, 5} and sum = 15.
In the fourth iteration, solution-set = {5, 5, 5, 2} and sum = 17. (We cannot select 5 here because if we do so, sum = 20 which is greater than 18. So, we select the 2nd largest item which is 2.)
Similarly, in the fifth iteration, select 1. Now sum = 18 and solution-set = {5, 5, 5, 2, 1}.

#### Dijkstra's Algorithm
https://www.programiz.com/dsa/dijkstra-algorithm



#### Greedy Approach Vs Dynamic Programming
https://www.geeksforgeeks.org/greedy-approach-vs-dynamic-programming/

A Greedy algorithm is an algorithmic paradigm that builds up a solution piece by piece, always choosing the next piece that offers the most obvious and immediate benefit. So the problems where choosing locally optimal also leads to a global solution are best fit for Greedy. 
For example, consider the Fractional Knapsack Problem. The local optimal strategy is to choose the item that has maximum value vs weight ratio. This strategy also leads to global optimal solution because we allowed taking fractions of an item. 

Dynamic programming is mainly an optimization over plain recursion. Wherever we see a recursive solution that has repeated calls for the same inputs, we can optimize it using Dynamic Programming. The idea is to simply store the results of subproblems so that we do not have to re-compute them when needed later. This simple optimization reduces time complexities from exponential to polynomial. For example, if we write a simple recursive solution for Fibonacci Numbers, we get exponential time complexity and if we optimize it by storing solutions of subproblems, time complexity reduces to linear.

*Feasibility* 
In a greedy Algorithm, we make whatever choice seems best at the moment in the hope that it will lead to global optimal solution.	
In Dynamic Programming we make decision at each step considering current problem and solution to previously solved sub problem to calculate optimal solution .

*Optimality* 
In Greedy Method, sometimes there is no such guarantee of getting Optimal Solution.	
It is guaranteed that Dynamic Programming will generate an optimal solution as it generally considers all possible cases and then choose the best.

*Recursion* 
A greedy method follows the problem solving heuristic of making the locally optimal choice at each stage.	
A Dynamic programming is an algorithmic technique which is usually based on a recurrent formula that uses some previously calculated states.

*Memoization* 
It is more efficient in terms of memory as it never look back or revise previous choices	
It requires dp table for memoization and it increases it’s memory complexity.

*Time complexity* 
Greedy methods are generally faster. For example, Dijkstra’s shortest path algorithm takes O(ELogV + VLogV) time.	
Dynamic Programming is generally slower. For example, Bellman Ford algorithm takes O(VE) time.

*Fashion* 
The greedy method computes its solution by making its choices in a serial forward fashion, never looking back or revising previous choices.	
Dynamic programming computes its solution bottom up or top down by synthesizing them from smaller optimal sub solutions.

*Example* 
Greedy - Fractional knapsack 
Dynamic programming - 0/1 knapsack problem