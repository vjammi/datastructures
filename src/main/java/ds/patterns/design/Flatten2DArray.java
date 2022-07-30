package ds.patterns.design;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Flatten2DArray {

    // Constructor will put all the nums into this list.
    private List<Integer> nums; // = new ArrayList<>();

    // Keep track of where the Iterator is up to.
    private int position = 0;

    public Flatten2DArray(int[][] v) {
        nums = new ArrayList<>();
        // We need to iterate over the 2D array, getting all the integers
        // out of it and putting them into nums (a field).
        for (int[] innerArray : v) {
            for (int num : innerArray) {
                nums.add(num);
            }
        }
    }

    public int next() {
        // In Java, we throw a NoSuchElementException when next() is called
        // on an exhausted Iterator.
        if (!hasNext()) throw new NoSuchElementException();
        // Store the number we need to return, as we still need to move position forward.
        int result = nums.get(position);
        // Move the position pointer forward by 1, so that it's ready for
        // the next call to next, and gives a correct hasNext result.
        position++;
        return result;
    }

    public boolean hasNext() {
        // There's nums left as long as position is a valid index of the list.
        return position < nums.size();
    }

}
