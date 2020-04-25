package ds.queue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

/**
 * 队列：有序列表，FIFO
 * 可以用 数组或链表 实现
 */
public class ArrayQueue {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
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
                        StdOut.printf("取出的数据为： %d\n" + res);
                    } catch (Exception e) {
                        StdOut.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.have();
                        StdOut.printf("队列头数据为： %d\n", res);
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

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr   = new int[maxSize];
        front = -1;  // 队列头前一个位置
        rear  = -1;  // 队列尾，就是队列最后一个数据
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void add(int n) {
        if (isFull()) {
            StdOut.println("队列满");
            return;
        }

        this.arr[++rear] = n;
    }

    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }

        return arr[++front];
    }

    public void show() {
        if (isEmpty()) {
            StdOut.println("队列空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            StdOut.printf("arr[%d] = %d\n", i, arr[i]);
        }

    }

    public int have() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }

        return arr[front + 1];
    }
}


