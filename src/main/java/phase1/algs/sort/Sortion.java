package phase1.algs.sort;

import java.util.Arrays;

/**
 * @author allen
 */
@SuppressWarnings("all")
public class Sortion {
    public static boolean less(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;  // <= 可能导致不稳定排序
    }

    public static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i]  = arr[j];
        arr[j]  = tmp;
    }

    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void show(Comparable[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
