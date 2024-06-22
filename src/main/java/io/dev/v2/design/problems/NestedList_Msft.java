package io.dev.v2.design.problems;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedList_Msft<T> implements Iterable<T> {

    private final List<T> nestedList;

    public NestedList_Msft(List<T> nestedList) {
        this.nestedList = nestedList;
    }

    public Iterator<T> iterator() {
        return new NestedListIterator<T>(nestedList, 0);
    }

    private class NestedListIterator<T> implements Iterator<T> {
        int position;
        List<T> nestedList;

        public NestedListIterator(List<T> nestedList, int position) {
            this.position = position;
            this.nestedList = nestedList;
        }

        @Override
        public boolean hasNext() {
            return nestedList.size() > 0;
        }

        @Override
        public T next() {
            return nestedList(nestedList, position++);
        }

        private T nestedList(List<T> nestedList, int i) {
            if (i >= nestedList.size())
                return null;

            for (Object object : nestedList) {
                if (object instanceof Integer) {
                    return (T) object;
                } else if (object instanceof java.util.List) {
                    nestedList((List<T>) nestedList.get(i), i++);
                }
            }
            return null;
        }

    }

    public static void main(String[] args) {

        List<Object> objects = new ArrayList<>();

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        objects.add(list1);

        List<List<Integer>> list2 = new ArrayList<>();
        List<Integer> list21 = new ArrayList<>();
        list21.add(21);
        list21.add(22);
        list21.add(23);
        list2.add(list21);
        List<Integer> list22 = new ArrayList<>();
        list22.add(22);
        list22.add(23);
        list22.add(24);
        list2.add(list22);

        objects.add(list2);

        NestedList_Msft<Integer> nestedList = new NestedList_Msft(objects);
        Iterator iterator = nestedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            break;
        }
    }
}
