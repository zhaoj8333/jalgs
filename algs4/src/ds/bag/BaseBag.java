package ds.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BaseBag<Item> implements Iterable<Item> {

    private int N;          // number of elements
    private Node first;     // beginning of bag

    private class Node {
        private Item item;
        private Node next;
    }

    public BaseBag() {
        first = null;
        N = 0;
    }

    public void add(Item item) {
        Node oldfirst = first; // null

        first = new Node();
        first.item = item;
        first.next = oldfirst;

        N++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current   = current.next;

            return item;
        }
    }
}
