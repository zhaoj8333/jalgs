package ds.stack;

public class FixedCapacityStackOfStrings {
    public String[] arr;
    public int top;
    public FixedCapacityStackOfStrings(int cap) {
        this.arr = new String[cap];
        this.top = -1;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull() {
        return this.top == this.arr.length - 1;
    }

    public int size() {
        return this.top;
    }

    public void push(String str) {
        this.arr[++this.top] = str;
    }

    public String pop() {
        String s = this.arr[this.top];
        --this.top;
        return s;
    }

    public String peek() {
        return this.arr[this.top];
    }


}
