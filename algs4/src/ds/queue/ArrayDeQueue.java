package ds.queue;

import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

/**
 * Dequeue
 *
 * 头尾两端添加删除的队列
 * @author allen
 */
public class ArrayDeQueue<E> {

    private LinkedList<E> list = new LinkedList<>();

    public void enQueueFront(E e) {
        list.add(0, e);
    }

    public void enQueueRear(E e) {
        list.add(list.size(), e);
    }

    public E deQueueFront() {
        return list.remove(0);
    }

    public E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    public E front() {
        return list.get(0);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public static void main(String[] args) {
        ArrayDeQueue<Integer> queue = new ArrayDeQueue<>();
        queue.enQueueFront(11);
        queue.enQueueFront(22);
        queue.enQueueRear(33);
        queue.enQueueRear(44);

        while (!queue.isEmpty()) {
            StdOut.println(queue.deQueueFront());
        }
    }
}
