package ds.stack.by;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class StackByArrayList<E> extends ArrayList<E> {
    public static void main(String[] args) {
        /**
         * 其他继承的方法重写即可
         */
        StackByArrayList<Integer> stack = new StackByArrayList<>();
        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        stack.push(55);

        while (!stack.isEmpty()) {
            StdOut.println(stack.pop());
        }
    }

    @Override
    public int size() {
        return super.size();
    }

    public void push(E elem) {
        add(elem);
    }

    public E pop() {
        return remove(size() - 1);
    }

    public E top() {
        return get(size() - 1);
    }
}

