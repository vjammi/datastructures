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
