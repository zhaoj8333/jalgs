package exercises;

import edu.princeton.cs.algs4.StdOut;

public class Ex1118 {
    public static void main(String[] args) {
        int a = mystery(2, 25);
        StdOut.println("2, 25: " + a);
        int b = mystery(3, 11);
        StdOut.println("3, 11: " + a);
        StdOut.println();
        int c = mystery1(2, 25);
        StdOut.println("2, 25: " + a);
        int d = mystery1(3, 11);
        StdOut.println("3, 11: " + a);
    }

    public static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return mystery(a + a, b / 2);
        }
        return mystery(a + a, b / 2) + a;
    }

    public static int mystery1(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b % 2 == 0) {
            return mystery(a * a, b / 2);
        }
        return mystery(a * a, b / 2) * a;
    }
}
