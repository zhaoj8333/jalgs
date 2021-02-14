package phase1.ds.queue;

/**
 * 队列：有序列表，FIFO
 * 可以用 数组或链表 实现
 * @author allen
 */
public class CircularArrayQueue {

    public static void main(String[] args) {
        // 队列有效数据最多3
        CircularArrayQueue arrayQueue = new CircularArrayQueue(4);
        for (int i = 0; i < 5; i++) {
            arrayQueue.add(i);
        }
        arrayQueue.show();
    }

    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    /**
     * 队列中有效的元素个数为：
     *     (rear + maxSize - front) % maxSize
     */
    public CircularArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr   = new int[maxSize];
    }

    /**
     * 队列满的条件： (rear + 1) % maxSize = front
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 队列为空的条件： rear == front
     */
    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int n) {
        if (isFull()) {
            System.out.println("队列满");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
        // 防止越界
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        int tmp = arr[front];
        front = (front + 1) % maxSize;
        // 防止越界
        return tmp;
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列空");
            return;
        }
        // 从front开始遍历，
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    private int size() {
        return (rear + maxSize - front) % maxSize;
    }

    public int have() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }

        return arr[front];
    }
}


