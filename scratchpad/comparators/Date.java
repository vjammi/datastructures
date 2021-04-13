package scratchpad.comparators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date implements Comparable
 */
public class Date implements Comparable<Date>{
    int mm;
    int dd;
    int yy;

    public Date(int mm, int dd, int yy){
        this.mm = mm;
        this.dd = dd;
        this.yy = yy;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param that the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Date that) {
        if (this.yy < that.yy) return -1;
        if (this.yy > that.yy) return 1;

        if (this.mm < that.mm) return -1;
        if (this.mm > that.mm) return 1;

        if (this.dd < that.dd) return -1;
        if (this.dd > that.dd) return 1;

        return 0;
    }

    public String getDate(){
        return mm + dd +"";
    }

    public static void main (String args[]){

        List<Date> dates = new ArrayList<Date>();

        Date date1 = new Date(11, 18, 2018);
        dates.add(date1);
        Date date2 = new Date(10, 12, 2017);
        dates.add(date2);
        Date date3 = new Date(12, 21, 2016);
        dates.add(date3);
        Date date4 = new Date(7, 16, 2014);
        dates.add(date4);
        Date date5 = new Date(9, 30, 2018);
        dates.add(date5);
        Date date6 = new Date(2, 21, 2017);
        dates.add(date6);

        printList(dates, "Before");
        Collections.sort(dates);
        printList(dates, "After");

    }

    private static void printList(List<Date> dates, String title) {
        System.out.println(title);
        for(Date date: dates){
            System.out.println(date.mm + "." + date.dd +"."+date.yy);
        }
    }


}
