## Trees Pattern #1: Path based Tree Problems.
Problems related to Path finding. Given a particular tree, find a path that optimizes some criteria.

#### Path Sum II
Given the root of a binary tree and an integer targetSum, 
return all root-to-leaf paths where each path's sum equals targetSum.
A leaf is a listNode with no children.
    
    Example 1 
                1
             2      5
          3     4
    Input = [1, 2, 5, 3, 4, null, null, null, null]  targetSum: 6
    Output: [[1,2,3]]
    
    Example 2
                    5
                 4      8
              11     13     4
            7    2       5      1
    Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
    Output: [[5,4,11,2],[5,8,4,5]]
    
Intuition

                   /   1
                 /  2     5
                  3   4
                  
    [1,2,3,null,null] // Since we hit a null to the left of 3, check the sum. 
                      // We need to backtrack, we pop the current listNode [3] and explore its parents right child.
    [1,2,4,null,null] // Once we hit the a null again to the left and right we check the sum.
                      // We pop 2 and 4 and explore the right side of root listNode 1. 
    [1,5,null,null]   // Once we hit the a null again to the left and right we check the sum.
                    
Solution               
                                        
    class Solution {
        private int targetSum;
    
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            Stack<Integer> stack = new Stack();
            List<List<Integer>> result = new ArrayList<>();
            this.targetSum = targetSum;
    
            if (root == null)
                return result;
    
            pathSum(root, 0, stack, result);
    
            return result;
        }
    
        public void pathSum(TreeNode listNode, int sum, Stack<Integer> stack, List<List<Integer>> result) {
    
            if (listNode == null)
                return;
    
            // Push listNode into the stack
            stack.push(listNode.val);
    
            // Add the listNode value to the sum
            sum = sum + listNode.val;
            
            // Check if the the current listNode is a leaf listNode. l & r nodes are null. 
            // if yes check if the sum so far is the target we are looking for
            if (listNode.left == null && listNode.right==null && sum == targetSum){
                result.add(new ArrayList(stack));
            }
    
            pathSum(listNode.left, sum, stack, result);
            pathSum(listNode.right, sum, stack, result);
                
            stack.pop(); // To backtrack we pop the listNode out of the stack            
            sum = sum - listNode.val;
        }
    }

#### Sum Root to Leaf Numbers
#### Most Frequent SubTree Sum
        
#### LC 298 Binary Tree Longest Consecutive Sequence
Given the root of a binary tree, return the length of the longest consecutive sequence path.
The path refers to any sequence of nodes from some starting listNode to any listNode in the tree along the parent-child 
connections. The longest consecutive path needs to be from parent to child (cannot be the reverse).
Need to find the path of longest consecutive numbers.

    Example 1
                    1
                        3
                     2     4
                              5
    Input: root = [1,null,3,2,4,null,null,null,5]   Output: 3
    Explanation: Longest consecutive sequence path is 3-4-5, so return 3.

    Example 2:
                           2
                            \
                             3
                            /
                           2
                          /
                         1
    Input: root = [2,null,3,2,null,1] Output: 2
    Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

Pattern
    This pattern extremely common. 
    Here in this problem we have to pass intermediate solution/values back up the tree, up the recursion call stack. 
    In other cases you might be asked to delete the listNode, which in this case you might need to pass the modified listNode up the call stack
    Regardless, all of them follow this recursive traversal pattern.
    You need to determine what you need to do within the single step of the problem.

Solution
    If we are at a certain listNode in the tree, we can compute the longest consecutive path of the listNode by calculating 
    the longest consecutive path of the left and right child and returning those values we will be able to compute for the current listNode.
    We then repeat the same by bubbling it up the call stack.
    In the above example, we check if 3 is consecutive to the left and right and then we add 1 if true. 
        
    public class Solution{
        
        private int result = 0;
        
        public int longestConsecutive(TreeNode root){
            if (root == null)
                return 0;
    
            longestSequence(root);
            return result;
        }
    
        private int longestSequence(TreeNode listNode){
            // If the current listNode is null then the path length is 0
            if (listNode == null)
                return 0;
    
            int left = longestSequence(listNode.left);
            int right = longestSequence(listNode.right);
    
            //*** Now do something with the previous left and right nodes return values ***
    
            int max = 1; // LongestConsecutive path at the current listNode
    
            // Checking if the value of the current listNode one more than its right child or one less than its right child. If yes then they can form  a consecutive sequence.
            // We keep the longest we get - either from teh left or from teh right child.
            if (listNode.left == null || listNode.left.val == listNode.val + 1) {
                max = Math.max(left+1, max); // max at teh current listNode
            }
            if (listNode.right == null || listNode.right.val == listNode.val + 1)
                max = Math.max(right+1, max); // max at the current listNode
    
            // If the current listNode cannot form a consecutive path with either children.
            // Then the Longest Consecutive path including the current listNode is 1.
            result = Math.max(result, max); // max within the tree as a whole
    
            return max;
        }        
    }
        

#### LC124 Binary Tree Maximum Path Sum
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 
A listNode can only appear in the sequence at most once. Note that the path does not need to pass through the root.
The path sum of a path is the sum of the listNode's values in the path.
Given the root of a binary tree, return the maximum path sum of any path.

Remember that in your recursive function, you almost always have to do something with the current listNode's value.
Combining or modifying it w3ith the solution from its children. 
You need to know how to combine those intermediate solutions into the current solution and you need to pass it back up 
the recursive tree stack, and the cycle repeats itself.    

    Input: root = [1,2,3]
                    1
                2       3
    Output: 6
    Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
    
    Input: root = [-10,9,20,null,null,15,7]
    Output: 42
    Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.            
    
Intuition
                
                 -10
                                
        9                   20      //return currentNode + its left segment or it right seg, which ever is positive
    
                   15(Left)      7(Right)    

Solution    
    First we get the max sum paths from left and right subtrees
    If either of them are negative, then we are better off not using them at all, in other words we either take a 0 or a positive sum.
    Next we find the max path sum that can be formed including our current listNode.
        
    class Solution {
        private int max = Integer.MIN_VALUE;     
        
        public int maxPathSum(TreeNode root) {
            search(root);
            return max;
        }
    
        private int search(TreeNode listNode){
            // We need to setup the base case. if the current listNode is null then the max path sum at this point is 0
            if (listNode == null)
                return 0;
    
            int leftOutput = search(listNode.left);
            int rightOutput = search(listNode.right);
    
            // If there is a negative value coming from the subtree, we pick 0 instead, we zero it out.
            // We only want to take positive sums from the subtrees
            int left = Math.max(0, leftOutput);
            int right = Math.max(0, rightOutput);
    
            // Max path sum that could be formed including the current listNode adding the left and right to the current listNode val.
            // If this new value is best Max path sum of the entire tree so far then we take it. If not we use the one we have so far.
            // Note that global max variable is not returned but is used to compute the Max Path Sum at each step
            max = Math.max(max, listNode.val + left+ right);
            
            // Because the we need to decide which segment we what to return to the parent. because we cannot send the whole.
            // because it might be using a structure that is forking but the parent needs to us a straight/linear continuous structure.
            // Since we need to maintain a continuous structure, we need to add the current listNode.
            // We then choose add the left or the right segment of the subtree. we cannot add both segments.
            int returnVal = Math.max(left, right) + listNode.val;    
            return returnVal;
        }
    }