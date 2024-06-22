package io.dev.v2.design.problems;

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
public class NestedListIterator implements Iterable<Integer>{

    public static void main(String[] args) {
        NestedListIterator obj = new NestedListIterator();
        Iterator<Integer> iterator = obj.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        List<NestedInteger> nestedList = new ArrayList<>();
        return new NestedIterator_RecursivelyFlattenNestedListWhileLoading(nestedList);
    }

    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedInteger> getList();
    }

    /**
     * Approach #1: Recursively unpack the nested list in DFS order.
     *              Time Limit Exceeded
     **/
    class NestedIterator_RecursivelyFlattenNestedListWhileLoading implements Iterator<Integer> {

        private final List<Integer> flattenedList;
        private final int position = 0;

        public NestedIterator_RecursivelyFlattenNestedListWhileLoading(List<NestedInteger> nestedList) {
            this.flattenedList = new ArrayList<>();
            // Recursively flatten/unpack the nested list  in dfs order.
            // Onetime unpacking of the nested list to a flattened list.
            // Time Limit Exceeded
            flattenList(nestedList);
            System.out.println(hasNext() + " " + flattenedList.size());
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                return this.flattenedList.get(position);
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return this.flattenedList.size() > 0;
        }

        // Time Limit Exceeded
        // Recursively unpacks a nested list in DFS order
        public void flattenList(List<NestedInteger> nestedList) {
            for (NestedInteger obj : nestedList) {
                if (obj.isInteger()) {                           // obj instanceof Integer
                    Integer nextInteger = obj.getInteger();
                    flattenedList.add(nextInteger);                      // (int)obj
                    System.out.println(nextInteger);
                } else {                                         // if (!obj instanceof List)
                    flattenList(obj.getList());                 // (List<NestedInteger>) obj
                }
            }
        }
    }

    /**
     * Solution # 2: Optimization - Using a Stack
     * Store stack on the iterator object and progress the algorithm on each call to next() to get the next integer out.
     * if the top of the stack is an integer, then we've already found the next integer.
     * else, if it's a list, then the else is adding the list contents to stack.
     * On the next loop iteration, the same will happen.
     *
     * Solution
     * The downside of Approach 1 is that it creates a new data structure instead of simply iterating over the given one. Instead, we should find a way to step through the integers, one at a time, keeping track of where we're currently up to in nestedList.
     *
     * A better way is to do an iterative depth-first search, based on the following tree traversal algorithm:
     *
     * define function iterativeDepthFirstSearch(nestedList):
     *     result = []
     *
     *     stack = a new Stack
     *     push all items in nestedList onto stack, in reverse order
     *
     *     while stack is not empty:
     *         nestedInteger = pop top of stack
     *         if nestedInteger.isInteger():
     *             append nestedInteger.getInteger() to result
     *         else:
     *             list = nestedInteger.getList()
     *             push all items in list onto stack, in reverse order
     *
     *     return result
     *
     * While we could use this algorithm in the constructor like before, a better way would be to store stack on the iterator object and progress the algorithm on each call to next() to get the next integer out.
     *
     * Notice that if the top of the stack is an integer, then we've already found the next integer. Otherwise, if it's a list, then the else is adding the list contents to stack. On the next loop iteration, the same will happen. We could write an algorithm to get the next integer as follows.
     *
     * stack = a new Stack
     * push all items in nestedList onto stack, in reverse order
     *
     * define function getNextInteger():
     *     while stack is not empty:
     *         nestedInteger = pop top off stack
     *         if nestedInteger.isInteger():
     *             RETURN nestedInteger.getInteger()
     *         else:
     *             list = nestedInteger.getList()
     *             push all items in list onto stack, in reverse order
     *
     * Notice that the stack is shared between calls. This means that getNextInteger() will find an integer and return it, while still preserving the state of the stack. We can then call getNextInteger() again to get the next integer, and so forth.
     *
     * To simplify the code a bit, we can change our loop condition so that it checks if the top of the stack is still a list. The loop body should push the contents of the list onto the stack (in reverse). Eventually, there will be an integer on the top of the stack, OR the stack will be empty. Being able to get the next integer to the top of the stack allows the next() and hasNext() methods to access it.
     *
     * stack = a new Stack
     * push all items in nestedList onto stack, in reverse order
     *
     * define function makeStackTopAnInteger():
     *     while stack is not empty AND the nestedInteger at top of stack is a list:
     *         nestedInteger = pop top off stack
     *         list = nestedInteger.getList()
     *         push all items in list onto stack, in reverse order
     * */
    public class NestedListIterator_TopItemsFromNestedListIntoAStack implements Iterator<Integer> {

        // In Java, the Stack class is considered deprecated. Best practice is to use
        // a Deque instead. We'll use addFirst() for push, and removeFirst() for pop.
        private final Deque<NestedInteger> stack;

        public NestedListIterator_TopItemsFromNestedListIntoAStack(List<NestedInteger> nestedList) {
            stack = new ArrayDeque(nestedList); //// puts them in the reverse order
        }

        @Override
        public Integer next() {
            // throw an exception if there's no elements left.
            if (!hasNext()) throw new NoSuchElementException();

            // hasNext ensures the stack top is now an integer.
            // Pop and return this integer.
            return stack.removeFirst().getInteger();
        }

        @Override
        public boolean hasNext() {
            // While stack is not empty and If there are no integers on the top,
            // then we try to load integer to the top
            loadIntegerToTheTopOfTheStack();
            // If the stack is still empty
            return !stack.isEmpty();
        }

        public void loadIntegerToTheTopOfTheStack() {
            // While there are items remaining on the stack and the front of
            // stack is a list (i.e. not integer), keep unpacking.
            while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
                // Put the NestedIntegers onto the stack in reverse order.
                List<NestedInteger> subList = stack.removeFirst().getList();
                for (int i = subList.size()-1; i >= 0; i--) {
                    stack.addFirst(subList.get(i));
                }
            }
        }

    }
    public class NestedListIterator_TopItemsFromNestedListIntoAStack2 implements Iterator<Integer> {

        // In Java, the Stack class is considered deprecated. Best practice is to use. However, not using a Deque here.
        // We'll use stack.push() instead of stack.addFirst() and stack.pop() instead of stack.removeFirst()
        private final Stack<NestedInteger> stack;

        public NestedListIterator_TopItemsFromNestedListIntoAStack2(List<NestedInteger> nestedList) {
            stack = new Stack<>();
            // Loads the items from the list in the reverse order
            for (int i=nestedList.size()-1; i>=0; i--){
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            // throw an exception if there's no elements left.
            if (!hasNext()) throw new NoSuchElementException();

            // hasNext ensures the stack top is now an integer.
            // Pop and return this integer.
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            // While stack is not empty and If there are no integers on the top,
            // then we try to load integer to the top
            bringAnIntegerToTheTop();
            // If the stack is still empty
            return !stack.isEmpty();
        }

        public void bringAnIntegerToTheTop(){
            // While there are items remaining on the stack and the front of
            // stack is a list (i.e. not integer), keep unpacking.
            while (!stack.isEmpty() && !stack.peek().isInteger()) {
                // Put the NestedIntegers onto the stack in reverse order.
                List<NestedInteger> subList = stack.pop().getList();
                for (int i=subList.size()-1; i>=0; i--){
                    stack.push(subList.get(i));
                }

            }
        }
    }


    /**
     * Approach 3: Two Stacks - Instead of reversing the list to put them onto the stack
     * Reversing the lists to put them onto the stack can be an expensive operation, and it turns out it isn't necessary.
     * Instead of pushing every item of a sub-list onto the stack, we can instead associate an index pointer with each sub-list, that keeps track of how far along that sub-list we are. Adding a new sub-list to the stack now becomes an O(1)O(1)O(1) operation instead of a O(lengthofsublist)O(length of sublist)O(lengthofsublist) one.
     **/

    public class NestedIteratorUsing2Stacks implements Iterator<Integer> {

        private final Deque<List<NestedInteger>> listStack = new ArrayDeque<>();
        private final Deque<Integer> indexStack = new ArrayDeque<>();

        public NestedIteratorUsing2Stacks(List<NestedInteger> nestedList) {
            listStack.addFirst(nestedList);
            indexStack.addFirst(0);
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            int currentPosition = indexStack.removeFirst();
            indexStack.addFirst(currentPosition + 1);
            return listStack.peekFirst().get(currentPosition).getInteger();
        }


        @Override
        public boolean hasNext() {
            makeStackTopAnInteger();
            return !indexStack.isEmpty();
        }


        private void makeStackTopAnInteger() {

            while (!indexStack.isEmpty()) {

                // If the top list is used up, pop it and its index.
                if (indexStack.peekFirst() >= listStack.peekFirst().size()) {
                    indexStack.removeFirst();
                    listStack.removeFirst();
                    continue;
                }

                // Otherwise, if it's already an integer, we don't need to do anything.
                if (listStack.peekFirst().get(indexStack.peekFirst()).isInteger()) {
                    break;
                }

                // Otherwise, it must be a list. We need to update the previous index
                // and then add the new list with an index of 0.
                listStack.addFirst(listStack.peekFirst().get(indexStack.peekFirst()).getList());
                indexStack.addFirst(indexStack.removeFirst() + 1);
                indexStack.addFirst(0);
            }
        }
    }

}