package ds.patterns.z.misc;



/**
 * Distance to each point from the origin
 * Pythagorean Theorem - a^{2}+b^{2}=c^{2},
 *
 * As pointed out in the introduction, if c denotes the length of the hypotenuse and a and b denote the lengths
 * of the other two sides, the Pythagorean theorem can be expressed as the Pythagorean equation:
 *
 * { a^{2}+b^{2}=c^{2}.} {a^{2}+b^{2}=c^{2}.}
 * If the length of both a and b are known, then c can be calculated as
 *
 * {\displaystyle c={\sqrt {a^{2}+b^{2}}}.} {\displaystyle c={\sqrt {a^{2}+b^{2}}}.}
 *
 * Distance between (0, 0) to (-2,4) = Sqrt[ (-2)^2 + (4)^2 ] = Sqrt(4 + 16) = Sqrt(20)
 *
 * Option 1: Sort the array in an ascending order and get the top K elements - O(nlogn)
 * Option 2: Selection Sort - O(nk)
 * Option 3: Using a Min Binary Heap - O(k+(n-k)logk)
 *          Create  - O(k)
 *          Replace - O(log k)
 *          Print all - O(k)
 *
 *
 *
 * **/

public class KClosestPointsToTheOrigin {
}
