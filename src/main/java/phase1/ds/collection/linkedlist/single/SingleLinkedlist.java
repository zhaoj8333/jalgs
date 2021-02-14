package phase1.ds.collection.linkedlist.single;

import java.util.Iterator;

/**
 * @author allen
 */
public class SingleLinkedlist<E> implements Iterable<E> {
    public Node<E> first;
    public int size;

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(first);
    }

    public static class ListIterator<E> implements Iterator<E> {
        Node<E> curr;

        public ListIterator(SingleLinkedlist.Node<E> first) {
            curr = first;
        }

        @Override
        public boolean hasNext() {
            return curr.next == null;
        }

        @Override
        public E next() {
            Node<E> next = curr;
            next = next.next;

            return next.item;
        }
    }

    public static class Node<E> {
        public E item;
        public Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + item + '}';
        }
    }

    public int size() {
        return size;
    }

    public boolean add(E item) {
        add(item, size);
        return true;
    }

    public boolean add(E item, int index) {
        if (index == 0) {
            first = new Node<>(item, first);
        } else {
            Node<E> prev = node(index - 1);
            Node<E> next = prev.next;
            prev.next = new Node<>(item, next);
        }

        size++;
        return true;
    }

    public E remove(int index) {
        checkIndex(index);
        if (index == size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curr = first;
        if (index == 0) {
            first = first.next;
            return curr.item;
        }
        Node<E> prev = node(index - 1);
        curr = prev.next;
        prev.next = curr.next;
        if (index == size - 1) {
            prev.next = null;
        }
        size--;
        return curr.item;
    }

    public Node<E> get(int index) {
        checkIndex(index);

        return node(index);
    }

    public Node<E> node(int index) {
        checkIndex(index);
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("越界");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> node = first;
        while (node != null) {
            sb.append(node.item.toString());
            if (node.next != null) {
                sb.append(", ");
            }
            node = node.next;
        }
        sb.append(" ]");

        return sb.toString();
    }
}
