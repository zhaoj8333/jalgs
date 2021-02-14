package phase1.ds.queue.bak;

public class MyQueue {
    private long[] arr;
    private int ele;
    private int front;
    private int end;

    public MyQueue() {
        arr = new long[10];
        ele = 0;
        front = 0;
        end = -1;
    }

    public MyQueue(int maxSize) {
        arr = new long[maxSize];
        ele = 0;
        front = 0;
        end = -1;
    }

    /**
     * @param val
     *
     * 添加： 队尾
     */
    public void insert(int val) {
        arr[++end] = val;
        this.ele++;
    }

    /**
     * 删除： 队头
     */
    public long remove() {
        this.ele--;
        return arr[this.front++];
    }

    /**
     * @return
     *
     * 查看： 从队头
     */
    public long peek() {
        return arr[this.front];
    }

    public boolean isEmpty() {
        return this.ele == 0;
    }

    public boolean isFull() {
        return this.ele == this.arr.length;
    }
}
