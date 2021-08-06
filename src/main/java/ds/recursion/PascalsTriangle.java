package ds.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalsTriangle {

    /*
    0    1
    1    1   1
    2    1   2   1
    3    1   3   3   1
    4    1   4   6   4   1
    5    1   5   10  10  5   1
    */

    public List<Integer> getRow(int rowIndex) {

        Integer[] previousArray = new Integer[1];
        previousArray[0] = 1;
        System.out.println(Arrays.asList(previousArray));

        Integer[] currentArray = new Integer[2];
        currentArray[0] = 1; currentArray[1] = 1;
        System.out.println(Arrays.asList(currentArray));

        if (rowIndex == 0) {
            return Arrays.asList(previousArray);
        }
        if (rowIndex == 1) {
            return Arrays.asList(currentArray);
        }

        previousArray = new Integer[2];
        previousArray = currentArray;
        for (int i = 2; i < rowIndex+1; i++){

            currentArray = new Integer[i+1];
            currentArray[0] = 1;
            currentArray[i] = 1;

            for (int j=1; j<i; j++){
                currentArray[j] = previousArray[j-1] + previousArray[j];
            }
            System.out.println(Arrays.asList(currentArray));

            if (rowIndex == i){
                return Arrays.asList(currentArray);
            }else{
                previousArray = new Integer[i+1];
                previousArray = currentArray;
            }
        }
        return null;
    }

    public List<Integer> getRowNotMySolution(int rowIndex) {
        List<Integer> result = new ArrayList<>();

        for (int i=0; i<rowIndex+1; ++i){
            result.add(1);
        }
        System.out.println(result);

        for (int i = 1; i < rowIndex; ++i){
            for (int j = i; j > 0; --j){
                result.set(j, result.get(j) + result.get(j-1));
            }
            System.out.println(result);
        }

        return result;
    }


}
