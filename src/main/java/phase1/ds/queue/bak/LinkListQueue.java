package phase1.ds.queue.bak;

import java.util.Iterator;

public class LinkListQueue<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;
    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return this.first == null;
//        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(Item item) {
        Node oldLast = this.last;
        this.last = new Node();
        this.last.item = item;
        this.last.next = null;
        if (isEmpty()) {
            this.first = this.last;
        } else {
            oldLast.next = this.last;
        }
        this.size++;
    }

    public Item dequeue() {
        Item item = this.first.item;
        this.first = this.first.next;
        if (isEmpty()) {
            last = null;
        }
        size--;
        return item;
    }

    public void display() {

    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
