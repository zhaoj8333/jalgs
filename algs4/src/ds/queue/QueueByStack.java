package ds.queue;

import java.util.Stack;

public class QueueByStack<E> {
    private Stack<E> inStack = new Stack<E>();
    private Stack<E> outStack = new Stack<E>();

    public QueueByStack() {
    }

    public void push(E elem) {
        inStack.push(elem);
    }

    public E pop() {
        checkOutStack();
        return outStack.pop();
    }

    public E peek() {
        checkOutStack();
        return outStack.peek();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
