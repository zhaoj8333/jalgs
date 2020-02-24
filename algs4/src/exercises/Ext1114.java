package exercises;

import edu.princeton.cs.algs4.StdOut;

public class Ext1114 {
    public static void main(String[] args) {
//        lg1(1000);
//        lg2(1000);
        lg3(1000);
    }

    public static void lg1(int n) {
        int i = 0;
        for (i = 1; i < n; i *= 2) {

        }

        StdOut.println(i / 2);
    }

    public static void lg2(int n) {
        int i = 1;
        while ((i *= 2) < n) {
//            StdOut.println(i);
        }

        StdOut.println(i / 2);
    }

    public static void lg3(int n) {
        int max = 1;
        for (int i = 0; max < n; i++) {
            max *= 2;
        }
        StdOut.println(max / 2);
    }
}
