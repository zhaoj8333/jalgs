package basic.math;

import edu.princeton.cs.algs4.StdOut;

public class IntCache {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Integer i1 = 3;
        Integer i2 = 3;
        if (i1 == i2) {
            // ==
            StdOut.println("i1 == i2");
        } else {
            StdOut.println("i1 != i2");
        }

        Integer i3 = 300;
        Integer i4 = 300;
        if (i3 == i4) {
            StdOut.println("i3 == i4");
        } else {
            // !=
            StdOut.println("i3 != i4");
        }
        StdOut.println();
        StdOut.println(i3.equals(i4));
    }
}
