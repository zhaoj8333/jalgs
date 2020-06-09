package ds.set;

import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class ListSet<E> implements Set<E> {
    private LinkedList<E> list = new LinkedList<>();

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        if (list.contains(element)) {
            return;
            // 或覆盖
        }
        list.add(element);
    }

    @Override
    public void remove(E element) {
        list.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            visitor.visit(list.get(i));
        }
    }

    public static void main(String[] args) {
        ListSet<Integer> listSet = new ListSet<>();
        listSet.add(10);
        listSet.add(11);
        listSet.add(11);
        listSet.add(12);
        listSet.add(13);

        listSet.traversal(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                StdOut.println(element);
                return true;
            }
        });
    }
}
