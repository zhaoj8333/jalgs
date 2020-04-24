package ds.queue.bak;

public class MyLoopQueue {
    public long[] arr;
    public int ele;
    public int front;
    public int end;

    public MyLoopQueue() {
        arr = new long[4];
        ele = 0;
        front = 0;
        end = -1;
    }

    public MyLoopQueue(int maxSize) {
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
        if (this.end == this.arr.length - 1) {
            this.end = -1;
            this.ele --;
        } else {
            this.ele++;
        }
        arr[++end] = val;
    }

    /**
     * 删除： 队头
     */
    public long remove() {
        long val  = arr[this.front++];
        if (front == this.arr.length) { // 最大索引 +１
            this.front = 0;
        }
        this.ele--;
        return val;
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
