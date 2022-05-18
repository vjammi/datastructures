package ds.usage.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 * objects = {ArrayList@673}  size = 2
 * <p>
 * 0 = {ArrayList@674}  size = 3
 * 0 = {Integer@681} 1
 * 1 = {Integer@682} 2
 * 2 = {Integer@683} 3
 * <p>
 * 1 = {ArrayList@675}  size = 2
 * 0 = {ArrayList@676}  size = 3
 * 0 = {Integer@687} 21
 * 1 = {Integer@688} 22
 * 2 = {Integer@689} 23
 * 1 = {ArrayList@677}  size = 3
 * 0 = {Integer@688} 22
 * 1 = {Integer@689} 23
 * 2 = {Integer@691} 24
 * ---------------------------------
 * <p>
 * list1 = {ArrayList@674}  size = 3
 * 0 = {Integer@681} 1
 * 1 = {Integer@682} 2
 * 2 = {Integer@683} 3
 * <p>
 * list2 = {ArrayList@675}  size = 2
 * 0 = {ArrayList@676}  size = 3
 * 0 = {Integer@687} 21
 * 1 = {Integer@688} 22
 * 2 = {Integer@689} 23
 * 1 = {ArrayList@677}  size = 3
 * 0 = {Integer@688} 22
 * 1 = {Integer@689} 23
 * 2 = {Integer@691} 24
 * <p>
 * list21 = {ArrayList@676}  size = 3
 * 0 = {Integer@687} 21
 * 1 = {Integer@688} 22
 * 2 = {Integer@689} 23
 * list22 = {ArrayList@677}  size = 3
 * 0 = {Integer@688} 22
 * 1 = {Integer@689} 23
 * 2 = {Integer@691} 24
 **/
public class NestedList<T> implements Iterable<T> {

    private List<T> nestedList;

    public NestedList(List<T> nestedList) {
        this.nestedList = nestedList;
    }

    public Iterator<T> iterator() {
        return (Iterator<T>) new NestedListIterator<T>(nestedList, 0);
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
            return nestedList((List<T>) nestedList, position++);
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

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
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

        NestedList<Integer> nestedList = new NestedList(objects);
        Iterator iterator = nestedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            break;
        }
    }
}
