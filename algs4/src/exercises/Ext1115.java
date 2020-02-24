package exercises;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ext1115 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 2, 2, 7, 4, 9};

        histogram1(a, 5);
        histogram2(a, 5);
    }

    public static void histogram1(int[] a, int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i == a[j]) {
                    arr[i] += 1;
                }
            }
        }
        StdOut.println(Arrays.toString(arr));
    }

    public static void histogram2(int[] a, int n) {
        int[] arr = new int[n];
        for (int i = 0; i < a.length; i++) {
            if (a[i] < n) {
                arr[a[i]]++;
            }
        }
        StdOut.println(Arrays.toString(arr));
    }
}
