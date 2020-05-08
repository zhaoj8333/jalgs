package ds.stack.bak;

public class MyStack {

    public long[] arr;
    public int top;

    public MyStack() {
        arr = new long[5];
        top = -1;
    }

    public MyStack(int maxSize) {
        arr = new long[maxSize];
        top = -1;
    }

    public void push(int val) {
        arr[++top] = val;
    }

    public long pop() {
        return arr[top--];
    }

    public long peek() {
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

}
