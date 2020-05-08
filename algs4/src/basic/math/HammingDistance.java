package basic.math;

import edu.princeton.cs.algs4.StdOut;

public class HammingDistance {
    public static void main(String[] args) {
        int d = getDistanceOfInts(123, 8341);
        StdOut.println("ints distance: " + d);

    }

    public static int getDistanceOfInts(int x, int y) {
        int xor = x ^ y;
        return Integer.bitCount(xor);
    }

    public static int getDistanceOfStrings(String x, String y) {

        return 0;
    }
}
