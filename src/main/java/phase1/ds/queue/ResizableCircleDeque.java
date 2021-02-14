package phase1.ds.queue;

/**
 * @author allen
 */
@SuppressWarnings("unchecked")
public class ResizableCircleDeque<E> {
    private int size;
    private int front;
    private E[] arr;

    public ResizableCircleDeque() {
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

    public void enQueueFront(E ele, boolean resize) {
        if (resize) {
            ensureCapacity(size + 1);
        }
        arr[index(size)] = ele;
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
            newArr[i] = arr[index(i)];
        }

        front = 0;
        arr = newArr;
    }

    private int index(int i) {
        return (front + i) % arr.length;
    }

    public E deQueueFront() {
        E ele = arr[front];
        arr[front] = null;
        front = index(1);
        size--;

        return ele;
    }

    public void enQueueRear(E ele, boolean resize) {
        if (resize) {
            ensureCapacity(size + 1);
        }
        arr[index(size)] = ele;
        size++;
    }

    public void deQueueRear(E ele, boolean resize) {

    }

    public E front() {
        return arr[front];
    }

    public E rear() {
        return arr[(front + size) % size - 1];
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
        ResizableCircleDeque<Integer> raq = new ResizableCircleDeque<>();
        System.out.println(raq);
    }
}
