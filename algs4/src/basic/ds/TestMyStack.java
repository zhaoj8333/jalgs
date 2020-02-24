package basic.ds;

import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class TestMyStack {
    public static void main(String[] args) {
        MyStack s = new MyStack(5);

        while (!StdIn.isEmpty() && !s.isFull()) {
            s.push(StdIn.readInt());
            System.out.println(Arrays.toString(s.arr));
            System.out.println("top: " + s.top);
        }

        System.out.println(s.isEmpty());
        System.out.println(s.isFull());

        System.out.println(s.peek());
        System.out.println();


//        while (!s.isEmpty()) {
//            System.out.println(s.pop() + ", ");
//        }
//        System.out.println(s.isEmpty());
//        System.out.println(s.isFull());
    }
}
