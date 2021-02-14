package phase1.ds.stack.bak;

import java.util.Iterator;

public class LinkListStack<Item> implements Iterable<Item> {
    private Node first;

    private int size;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return null == first;
        //                || size == 0
    }

    public int size() {
        return size;
    }

    public void push(Item item) {
        Node oldFirst = this.first;
        this.first = new Node();
        this.first.item = item;
        this.first.next = oldFirst;
        size++;
    }

    public Item pop() {
        Item item = this.first.item;
        this.first = this.first.next;
        size--;
        return item;
    }

    public Item peek() {
        return null;
    }

    public void display() {
        Node current = this.first;
        while (current != null) {
            System.out.print(current.item + "  ");
            current = current.next;
        }
        System.out.println();
    }

    public void displayByIterator() {
        Iterator<Item> itr = iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {
        StackIterator() {
            f = first;
        }

        private Node f;

        public boolean hasNext() {
            return this.f != null;
        }

        public Item next() {
            Item ret = null;
            if (f != null) {
                ret = f.item;
            }
            this.f = f.next;
            return ret;
        }

        public void remove() {}
    }
}
