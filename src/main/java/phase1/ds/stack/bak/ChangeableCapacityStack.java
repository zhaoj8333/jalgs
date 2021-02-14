package phase1.ds.stack.bak;

import java.util.Iterator;

public class ChangeableCapacityStack<Item> {
    public Item[] arr;
    public int top;

    public ChangeableCapacityStack(int cap) {
        this.arr = (Item[])new Object[cap];
        this.top = -1;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top == this.arr.length - 1;
    }

    public int size() {
        return this.top + 1;
    }

    public void push(Item item) {
        if (size() == this.arr.length) {
            resize(2 * this.arr.length);
        }
        this.arr[++this.top] = item;
    }

    public Item pop() {
        Item res = this.arr[this.top];
        this.arr[this.top] = null;
        // 避免对象游离
        if (this.top > 0 && this.top == this.arr.length / 4) {
            resize(this.arr.length / 2);
        }
        --this.top;
        return res;
    }

    public void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i <= this.top; i++) {
            temp[i] = this.arr[i];
        }
        this.arr = temp;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = top + 1;
        public boolean hasNext() {
            return i > 0;
        }
        public Item next() {
            return arr[--i];
        }

        public void remove() {
        }
    }

}
