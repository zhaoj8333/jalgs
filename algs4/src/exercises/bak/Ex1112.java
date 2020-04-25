package exercises.bak;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ex1112 {
    public static void main(String[] args) {
        a();
    }

    public static void a() {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = 9 - i;
        }

        StdOut.println(Arrays.toString(a));
        StdOut.println();

        for (int i = 0; i < 10; i++) {
            a[i] = a[a[i]];
        }

        StdOut.println(Arrays.toString(a));
    }
}
