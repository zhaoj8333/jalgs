package ds.queue;

import edu.princeton.cs.algs4.StdOut;

/**
 * 队列：有序列表，FIFO
 * 可以用 数组或链表 实现
 */
public class ArrayQueue {
    private int size;
    // 队列头
    private int first = 0;
    // 队列最后一个数据
    private int last  = -1;
    private int[] arr;

    public ArrayQueue(int size) throws Exception {
        if (size < 1) {
            throw new Exception("不能小于1");
        }
        this.size = size;
        this.arr  = new int[size];
    }

    public int getFirst() {
        return this.first;
    }

    public int getLast() {
        return this.last;
    }

    public boolean isFull() {
        return this.first == this.size - 1;
    }

    public boolean isEmpty() {
        return this.last == this.first;
    }

    public void show() {
        for (int i = this.first + 1; i <= this.last; i++) {
            StdOut.printf("%d ", this.arr[i]);
        }
        StdOut.println();
    }

    public void add(int n) throws Exception {
        if (isEmpty()) {
            this.first = -1;
        }
        if (this.isFull()) {
            throw new Exception("队列已满");
        }

        this.arr[++this.last] = n;
    }

    public int get() throws Exception {
        if (isEmpty()) {
            throw new Exception("队列为空");
        }
        int index = ++this.first;
        this.size--;
        return this.arr[index];
    }

    public int peek() {
        int index = this.first;
        return this.arr[++index];
    }
}
