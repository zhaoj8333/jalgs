package ds.stack;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Iterator;

public class ChangeableCapacityStackTest {
    public static void main(String[] args) {
        ChangeableCapacityStack<String> fc = new ChangeableCapacityStack(2);
        // System.out.println(fc);
        fc.push("1");
        fc.push("2");
        fc.push("3");
        fc.push("4");
        fc.push("5");
        fc.push("6");
        fc.push("7");
        fc.push("8");
        fc.push("9");
        StdOut.println("top:  " + fc.top);
        StdOut.println("size: " + fc.size());
        StdOut.println("full: " + fc.isFull());

        Iterator<String> itr = fc.iterator();
        /*
        while (itr.hasNext()) {
            StdOut.println(itr.next());
        }
        */

        StdOut.println("=======================");
        while (!fc.isEmpty()) {
            StdOut.println("arr: " + Arrays.toString(fc.arr));
            fc.pop();
        }

    }
}
