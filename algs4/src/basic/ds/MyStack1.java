package basic.ds;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class MyStack1 {
    public static void main(String[] args) {
        Stack<Integer> stack;
        stack = new Stack<Integer>();

        int start = 0;
        while (! StdIn.isEmpty()) {
            stack.push(StdIn.readInt());
            start++;
            StdOut.println("i : " + stack);
        }
        StdOut.println(stack);
        StdOut.println();
        for (int i : stack) {
            StdOut.println(i);
        }
    }
}
