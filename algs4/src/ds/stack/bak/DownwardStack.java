package ds.stack.bak;

import edu.princeton.cs.algs4.In;

import java.util.Stack;

public class DownwardStack {
    public static void main(String[] args) {
        reverse("file:////home/allen/jalgs/data/tinyG.txt");
    }

    public static void reverse(String name) {
        In in = new In(name);
        Stack<Integer> stack = new Stack<Integer>();

        while (!in.isEmpty()) {
            stack.push(in.readInt());
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
//        for (int i : stack) {
//            System.out.println(i);
//        }
    }
}
