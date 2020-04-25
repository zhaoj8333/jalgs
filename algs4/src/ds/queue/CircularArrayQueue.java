package ds.queue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

/**
 * 队列：有序列表，FIFO
 * 可以用 数组或链表 实现
 */
public class CircularArrayQueue {

    public static void main(String[] args) {
        // 队列有效数据最多3
        CircularArrayQueue arrayQueue = new CircularArrayQueue(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            StdOut.println("s(show): 显示");
            StdOut.println("e(exit): 退出");
            StdOut.println("a(add) : 添加");
            StdOut.println("g(get):  取数据");
            StdOut.println("h(have): 查看队列头数据");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.show();
                    break;
                case 'a':
                    StdOut.println("请输入一个数");
                    int val = scanner.nextInt();
                    arrayQueue.add(val);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.get();
                        System.out.printf("取出的数据为： %d\n", res);
                    } catch (Exception e) {
                        StdOut.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.have();
                        System.out.printf("队列头数据为： %d\n", res);
                    } catch (Exception e) {
                        StdOut.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
            StdOut.println("程序退出");
        }

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
            StdOut.println("队列满");
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
            StdOut.println("队列空");
            return;
        }
        // 从front开始遍历，
        for (int i = front; i < front + size(); i++) {
            StdOut.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
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


