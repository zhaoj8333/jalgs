package phase1.practice.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author allen
 */
public class InsertionSort extends Sort {

    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int curr = i;
            int temp = arr[curr];
            while (curr > 0 && temp < arr[curr - 1]) {
                arr[curr] = arr[curr - 1];
                // swap(arr, curr, curr - 1);
                curr--;
            }
            arr[curr] = temp;
        }
    }

    public static void main(String[] args) {
        int num = 20;
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = new Random().nextInt(num);
        }
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
