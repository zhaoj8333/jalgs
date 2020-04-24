package ds.stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FixedCapacityStackOfStringsTest {
    public static void main(String[] args) {
        testStringStack(args);
//        FixedCapacityStackOfStrings strStack = new FixedCapacityStackOfStrings(5);
//        strStack.push("A");
//        strStack.push("B");
//        strStack.push("C");
//        strStack.push("D");
//        strStack.push("E");
//        strStack.push("F");

//        System.out.println(strStack.isFull());
//        System.out.println(strStack.isEmpty());
//        System.out.println(Arrays.toString(strStack.arr));
    }

    public static void testStringStack(String[] args) {
        FixedCapacityStackOfStrings strStack = new FixedCapacityStackOfStrings(10);

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                strStack.push(item);
                StdOut.println(Arrays.toString(strStack.arr));
                StdOut.println("push top: " + strStack.top);
            } else if (!strStack.isEmpty()) {
                StdOut.println(strStack.pop() + "  ");
                StdOut.println("pop top: " + strStack.top);
            }
        }

        StdOut.println("(" + strStack.size() + " left on stack)");
    }
}
