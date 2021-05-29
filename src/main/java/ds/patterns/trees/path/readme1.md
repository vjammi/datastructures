Trees Pattern #1: Path based Tree Problems.
Problems related to Path finding. Given a particular tree, find a path that optimizes some criteria.

1. Path Sum II
    Given the root of a binary tree and an integer targetSum, 
    return all root-to-leaf paths where each path's sum equals targetSum.
    A leaf is a node with no children.
    
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
                  /      1
                 /   2      5
                  3     4
                    [1,2,3,null, null] // Since we hit a null to the left of 3, check the sum.
                    [1,2,4,null]       // We need to backtrack, we pop the current node [3] and explore its parents right child. 
                                       // Once we hit the a null again to the left and right we check the sum.
                    [1,5,null]         // We pop 2 and 4 and explore the right of the above node. Once we hit the a null again to the left and right we check the sum.
                    
                                        
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
    
        public void pathSum(TreeNode node, int sum, Stack<Integer> stack, List<List<Integer>> result) {
    
            if (node ==null)
                return;
    
            stack.push(node.val); 
            sum = sum + node.val;
            if (node.left == null && node.right==null && sum == targetSum){ 
                result.add(new ArrayList(stack));
            }
    
            pathSum(node.left, sum, stack, result);
            pathSum(node.right, sum, stack, result);
    
            stack.pop(); 
        }
    }

   Other Problems
        Sum Root to Leaf Numbers
        Most Frequent SubTree Sum
        
2. LC 298 Binary Tree Longest Consecutive Sequence

    Given the root of a binary tree, return the length of the longest consecutive sequence path.
    The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child 
    connections. The longest consecutive path needs to be from parent to child (cannot be the reverse).

    Example 1
                    1
                        3
                    2       4
                        5
    Input: root = [1,null,3,2,4,null,null,null,5]
    Output: 3
    Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
    
    Need to find the path of longest consecutive numbers.
    
    Example 2 
                    1
            /    6      3   \
              7     2       4
                        5    /
    Constraints: Path should go from parent to child i.e 1,3,4,5 and 
                 The numbers have to be increasing.
                 consequetive [6,7], [3,4,5]

    Solution
        If we are at a certain node in the tree, we can compute the longest consecutive path of the node by calculating 
        the longest consecutive path of the left and right child and returning those values we will be able to compute for the current node.
        We then repeat the same by bubbling it up the call stack.
        In the above example, we check if 3 is concequetive to the left and right and then we add 1 if true. 
    
    Pattern
        This pattern extremely common. 
        Here in this problem we have to pass intermediate solution/values back up the tree, up the recursion call stack. 
        In other cases you might be asked to delete the node, which in this case you might need to pass the modified node up the call stack
        Regardless, all of them follow this recursive traversal pattern.
        You need to determine what you need to do within the single step of the problem.
        
        
    public class Solution{
        
        private int result = 0;
        public int longestConsecutive(TreeNode root){
            if (root == null)
                return 0;
            
            longestSequence(root);            
            return result; 
        }
    
        private int longestSequence(TreeNode node);
            if (node == null)
                return 0;
                
            int left = longestSequence(node.left);
            int right = longestSequence(node.right);
            
            int max =1;            
            // Do something with the previous left and rioght nodes
            
            return max;
        }        
    }
        

3. LC124 Binary Tree Maximum Path Sum
    A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. 
    A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
    The path sum of a path is the sum of the node's values in the path.
    Given the root of a binary tree, return the maximum path sum of any path.
    Remember that in your recursive function, you almost always have to do something with the current node's value.
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
    
    First we get the max sum paths from left and right subtrees
    If either of them are negative, then we are better off not using them at all, in other words we either take a 0 or a positive sum.
    Next we find the max path sum that can be formed including our current node.
        
        
    class Solution {
        private int max = Integer.MIN_VALUE;     
        
        public int maxPathSum(TreeNode root) {
            search(root)
            return max;            
        }
        
        private int search(TreeNode node){
            if (node == null) // base case
                return 0;
            
            int left = Math.max(0, search(node.left));
            int right = Math.max(0, search(node.right));         
            max = Math.max(max, node.val + left+ right);
            
            return Math.max(left, right) + node,val;        
        }
    }
