package phase1.ds.queue;

import java.util.Arrays;

/**
 * @author allen
 */
@SuppressWarnings("unchecked")
public class ResizableArrayQueue <E> {
    private int size;
    private int front;
    private E[] arr;

    public ResizableArrayQueue() {
        arr = (E[]) new Object[10];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }

    public void enQueue(E ele, boolean resize) {
        if (resize) {
            ensureCapacity(size + 1);
        }
        arr[(front + size) % arr.length] = ele;
        size++;
//        arr[(front + size ++) % arr.length] = ele;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = arr.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newArr = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[(i + front) % arr.length];
        }

        front = 0;
        arr = newArr;
    }

    private int index(int i) {
        return (front + i) % arr.length;
    }

    public E deQueue() {
        E ele = arr[front];
        arr[front] = null;
        front = (front + 1) % arr.length;
        size--;

        return ele;
    }

    public E front() {
        return arr[front];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("capacity: ").append(arr.length).append("\n");
        sb.append("size : ").append(size).append("\n");
        sb.append("front: ").append(front).append("\n");
        for (int i = front; i < (front + size()); i++) {
            sb.append(arr[i % arr.length]);
            if (i != (front + size() - 1)) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ResizableArrayQueue<Integer> raq = new ResizableArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            raq.enQueue(i, false);
        }
        System.out.println(Arrays.toString(raq.arr));
        System.out.println(raq);
        System.out.println();
        for (int i = 0; i < 5; i++) {
            raq.deQueue();
        }
        System.out.println(Arrays.toString(raq.arr));
        System.out.println(raq);
        System.out.println();
        for (int i = 15; i < 23; i++) {
            raq.enQueue(i, true);
        }
        System.out.println(Arrays.toString(raq.arr));
        System.out.println(raq);
    }
}
