package dev.vjammi.ds.v2.dp;

/*
Problem:
	Climbing Stairs
	You are climbing a stair case. It takes n steps to reach to the top.
	Each time you can either climb 1 or 2 steps.
	In how many distinct ways can you climb to the top?

Framework for Solving DP Problems:

	1. Define the objective function
	   f(i) is the number of distinct ways/steps to reach the i-th stair.

	    To identify the objective function, we should read the description carefully.
	        f(i) is the number of distinct ways/steps to reach the i-th stair.
	    Sometimes it could have few more states/variables. In certain cases you objective function could be f(i,j) or  f(i,j,k)
	    Example of objective function are
	        - If we were to be looking at the cost of a product, we would be looking to minimize the cost.
	        - If we were to be looking for some profit, we would be looking to maximize the cost.
	        - In this case the objective function f(i) is the distinct number of ways to reach to a certain (ith) stair.
	          Either way its a minimization problem.

	2. Identify Base Cases
	   Break the problem down into smaller sub-problems and identify the base cases.
	   Dynamic problem is about breaking the problem into smaller sub problems and we need base cases to identify the boundaries of the problem

            f(0) = 1    // When we are on the ground, no stair cases. Meaning we do not have to do anything to reach the top.
            f(1) = 1    // to the first stair, there is only 1 way to get to the stair

       we only need 2 base cases here f(0) f(1)

            f(2) = 2    // 2 ways - 1 1 steps or 1 2 step

	3. Recurrence relation - Write down a recurrence relation for the optimized objective function
		    f(n) = f(n-1) + f(n-2)

        Allows you to transition from one sub problem to the other sub problem
	    How do we determine the recurrence relation?
	    Sometimes you could spot the pattern by solving the problem for few input values.
	        i.e. for f(2), f(3), f(4)

	    Lets say we were to be at the top of the stairs 3rd stair.
	    We could ask ourselves how did we get here, when we were allowed to make either 1 or 2 steps only?
	    So we could have gotten to the 3rd stair either from the second (n-1) or the first stair (n-2). That's it.
	    There is no other way. We need to add both these together. So the equation would become
	        f(n) = f(n-1) + f(n-2)
        This is called combinatorics. This is called the rule of sum or addition principle.
        Its a counting principle.
        The idea is if we had
            A ways to do something and
            B ways of doing something else.
            and if we cannot do both things at the same time.
            Then there are A + B ways to choose one of the actions.
         So in our case we can always take either one or two steps, which is why we can apply the rule of sum.
         We have the 2 disjoint sets and we apply the union operation. Comes from teh set theory.
            f(n) = f(n-1) + f(n-2)

            	  _ (n)     3
	            _|(n-1)     2
	          _| (n-2)      1

	4. What's the order of execution?
		bottom-up (since we are relying on the values from the 2 previous sub problemsvalue)

		Order of computation/execution
		In this case we have n-1 and n-2, we have a sorted order here. we need to calculate the smaller n values first.
		Sometimes the order could be different also.

        In some cases you could be setting future values based on what you already have.
             f(n+1) = f(n) + 1
        But in our case its bottom up, because we are relying on the values from the previous sub problems.

	5. Where to look for the answer?
		f(n)

		Location of the answer. This means where are we looking for the answer. In this case we are looking within f(n)
		Sometime the answer could reside in f(0)
*/
public class ClimbingStairs {

    // Time Complexity  - O(n) because we need to go over all teh stairs.
    // Space Complexity - O(n) because we are allocating an array of size n+1 elements. There could however be ways to optimize the space.
    private int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2; i<=n; i++){
               dp[i] = dp[i-1] + dp[i-2];
            // dp[2] = dp[2-1] + dp[2-2]; which is  dp[2] = dp[1] + dp[0];
            // dp[3] = dp[3-1] + dp[3-2]; which is  dp[3] = dp[2] + dp[1];
            // dp[4] = dp[4-1] + dp[4-2]; which is  dp[4] = dp[3] + dp[2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        ClimbingStairs obj = new ClimbingStairs();
        int n = 3;   // 3 distinct ways to get to the top   [1, 1, 2, 3]
        //int n = 5; // 8 distinct ways to get to the top   [1, 1, 2, 3, 5, 8]
        System.out.println("climbStairs of " +n +" " +obj.climbStairs(n));
    }



}

