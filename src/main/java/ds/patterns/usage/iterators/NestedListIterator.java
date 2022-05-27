package ds.patterns.usage.iterators;

import java.util.*;

/**
 * 341. Flatten Nested List Iterator
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 *     NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 *     int next() Returns the next integer in the nested list.
 *     boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 *
 * Your code will be tested with the following pseudocode:
 *      initialize iterator with nestedList
 *      res = []
 *          while iterator.hasNext()
 *     append iterator.next() to the end of res
 *     return res
 * If res matches the expected flattened list, then your code will be judged as correct.
 *
 * Example 1:
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 *
 * Example 2:
 * Input: nestedList = [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 *
 * Constraints:
 *     1 <= nestedList.length <= 500
 *     The values of the integers in the nested list is in the range [-106, 106].
 **/
public class NestedListIterator {

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    // Solution #1:
    // Recursively unpacks a nested list in DFS order.
    // Time Limit Exceeded
    class NestedIterator_Naive implements Iterator<Integer> {

        private List<Integer> list;
        private int position = 0;

        public NestedIterator_Naive(List<NestedInteger> nestedList) {
            this.list = new ArrayList<>();
            flattenList(nestedList);
            System.out.println(hasNext() + " " + list.size());
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                return this.list.get(position);
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return this.list.size() > 0;
        }

        // Recursively unpacks a nested list in DFS order.
        // Time Limit Exceeded
        public void flattenList(List<NestedInteger> nestedList) {
            for (NestedInteger obj : nestedList) {
                if (obj.isInteger()) {                           // obj instanceof Integer
                    Integer nextInteger = obj.getInteger();
                    list.add(nextInteger);                      // (int)obj
                    System.out.println(nextInteger);
                } else {                                         // if (!obj instanceof List)
                    flattenList(obj.getList());                 // (List<NestedInteger>) obj
                }
            }
        }
    }

    // Solution #2:
    // If the current element is a list,  into a stack Using a Stack to
    // Time Limit Exceeded
    class NestedIterator_Optimized1 implements Iterator<Integer> {

        private List<Integer> list;
        private int position = 0;

        public NestedIterator_Optimized1(List<NestedInteger> nestedList) {
            this.list = new ArrayList<>();
            flattenList(nestedList);
            System.out.println(hasNext() + " " + list.size());
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                return this.list.get(position);
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return this.list.size() > 0;
        }

        // Recursively unpacks a nested list in DFS order.
        // Time Limit Exceeded
        public void flattenList(List<NestedInteger> nestedList) {
            for (NestedInteger obj : nestedList) {
                if (obj.isInteger()) {                           // obj instanceof Integer
                    Integer nextInteger = obj.getInteger();
                    list.add(nextInteger);                      // (int)obj
                    System.out.println(nextInteger);
                } else {                                         // if (!obj instanceof List)
                    flattenList(obj.getList());                 // (List<NestedInteger>) obj
                }
            }
        }
    }

    // Solution #3:
    // Store stack on the iterator object and progress the algorithm on each call to next() to get the next integer out.
    // if the top of the stack is an integer, then we've already found the next integer.
    // else, if it's a list, then the else is adding the list contents to stack.
    // On the next loop iteration, the same will happen.
    // TODO: Not all test cases pass.
    public class NestedIterator implements Iterator<Integer> {

        private Deque<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new ArrayDeque(nestedList);
        }

        @Override
        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException(); // Adding import

            return stack.removeFirst().getInteger();
        }

        @Override
        public boolean hasNext() {
            if (stack.size() > 0){
                if (!stack.peekFirst().isInteger()){
                    loadList();
                }
                return true;
            }
            return false;
        }

        public void loadList(){

            while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
                List<NestedInteger> subList = stack.removeFirst().getList();
                for (int i=subList.size()-1; i>=0; i--){
                    stack.addFirst(subList.get(i));
                }
            }
        }

    }

    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
}