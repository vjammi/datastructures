## Knapsack Problem
Reference: https://en.wikipedia.org/wiki/Knapsack_problem
The knapsack problem is a problem in combinatorial optimization: Given a set of items, each with a weight and a value,
determine the number of each item to include in a collection so that the total weight is less than or equal to a given
limit and the total value is as large as possible. It derives its name from the problem faced by someone who is constrained
by a fixed-size knapsack and must fill it with the most valuable items. The problem often arises in resource allocation
where the decision makers have to choose from a set of non-divisible projects or tasks under a fixed budget or time constraint, respectively.
The name "knapsack problem" refers to the commonplace problem of packing the most valuable or useful items without overloading the luggage.

### Knapsack Problems Types
1. 0/1 Knapsack - included or excluded
2. Bounded      - Can include more than 1 to x number of instances of the same item
3. Unbounded    - Can include 0 or more number of instances of the same item with no limitations.
Now the items we can pick could be fractional or whole.

### Knapsack Problem variations
2. Subset sum problem
3. Partition equal subset sum
4. Count of subsets with sum equal to x
5. Partition a set into 2 subsets such that diff os subset sum is determine
6. Target Sum

- Time Complexity: 2^n [exponential]
- With Memoization we can bring the complexity down to O(N x W)

Stages of DP optimizations
1. Plain Recursive

2. Recursive + Memoization
                    Weight (W)
                0   1   2   3   4   5
                0
    N (Items)   1
                2
                3

3. Top Down & Bottom Up Approach
   Lot of fn calls can cause stack memory to be used up causing overflow.
   Recursive code can however be converted into iterative approach and the table can be filled.
   - Stack vs Heap memory








REFERENCES
https://youtu.be/mGfK-j9gAQA



