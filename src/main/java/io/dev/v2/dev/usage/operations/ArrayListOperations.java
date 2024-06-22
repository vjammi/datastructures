package io.dev.v2.dev.usage.operations;

import java.util.ArrayList;
import java.util.List;

public class ArrayListOperations {

    public static void main(String[] args) {
        List<Integer> result = new ArrayList<>();

        result.add(0, 100);
        result.add(1, 25);
        result.add(2, 300);

        // Can use either
        result.remove(1);
        result.add(1, 210);    // remove and add val at index
        // or
        result.set(2, 200);    // Set val at index

        System.out.println(result);
    }

    private void exch(List<Integer> list, int j, int i) {
        Integer tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
