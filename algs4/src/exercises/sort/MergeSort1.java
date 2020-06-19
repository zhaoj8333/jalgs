package exercises.sort;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class MergeSort1 {

    private static Integer[] aux;

    public static void merge(Integer[] arr, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        StdOut.println("arr: " + Arrays.toString(arr));
        StdOut.println("aux: " + Arrays.toString(aux));
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {

            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform(100);
        }
        aux = new Integer[20];
        int lo  = 0;
        int hi  = arr.length - 1;
        int mid = (lo + hi) >> 1;
        merge(arr, 0, mid, hi);
    }
}
