package ds.patterns.subarray;

/**
    nums = {9   7   8   4   6   5   2   3 }
      i  =  ^
      j  =          ^
    min=7
    max=9


     nums = {9   2   7   5   6   23   24   22 }
     i  =            ^
     j  =                    ^
     minVal=5       minIndex=2-1        j- (i-1) or (j-i)+1
     maxVal=7       maxIndex=4


    An important property - Applies to only distinct elements [no duplicates]
        If elements are contiguous then the diff of the (index of 7 and index of 9) + 1
        will be equal to the difference of the index of the elements (maxIndex - minIndex) + 1 itself.
        Diff values [7-(5-1)]  ==   diff of indexes  [4-(2-1)]
    If does not contain distinct elements [duplicates]

 **/
public class LargestSubarrayWithContiguousElements {






}
