package io.dev.v1.queue;

import java.util.LinkedList;
import java.util.Queue;

class MovingAverage {

    private final Queue<Integer> queue;
    private double sum;
    private int filled;
    private final int size;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.queue   = new LinkedList<Integer>();
        this.size    = size;
        this.filled  = 0;
        this.sum = 0;
    }
    /*
    Difference between float and double variable in Java?
    Though both float and double datatype are used to represent floating-point numbers in Java,
    A double data type is more precise than float. A double variable can provide precision up to
    15 to 16 decimal points as compared to float precision of 6 to 7 decimal digits
    */
    public double next(int val) {
        double avg = 0;
        if ( filled < size ){

            queue.offer(val);
            sum = sum + val;
            filled++;
            avg = sum / filled;
            System.out.println(">sum " +sum +" filled " +filled +" val " +val +" avg " +avg);

        }else{
            Integer peekedVal = queue.poll(); //.peek();
            double peekedValue = peekedVal != null ? peekedVal.intValue() : 0;
            sum = sum - peekedValue;
            //queue.poll();
            filled--;

            queue.offer(val);
            sum = sum + val;
            filled++;

            avg = sum / filled;
            System.out.println(">>sum " +sum +" filled " +filled +" avg " +avg);
        }
        return avg;
    }

    public static void main(String[] args) {

         // Your MovingAverage object will be instantiated and called as such:
         MovingAverage obj = new MovingAverage(3);
         double param_1 = obj.next(1);
         double param_2 = obj.next(10);
         double param_3 = obj.next(3);
         double param_4 = obj.next(5);

    }
}
