package phase1.practice.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author allen
 */
public class HeapSort1<E extends Comparable<E>> extends Sort<E> {
    private E[] arr;
    private int heapSize;
    private Comparator<E> comparator;

    public HeapSort1(E[] arr, Comparator<E> comparator) {
        this.arr = arr;
        this.heapSize = arr.length;
        this.comparator = comparator;
    }

    private int compare(E a, E b) {
        return comparator == null ? a.compareTo(b) : comparator.compare(a, b);
    }

    private void sort() {
        heapSize = arr.length;
        heapify();
        System.out.println(Arrays.toString(arr));
        while (heapSize > 1) {
            swap(arr, 0, --heapSize);
            sinkDown(0);
        }
    }

    private void heapify() {
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            this.sinkDown(i);
        }
    }

    private void sinkDown(int index) {
        E element = arr[index];
        int half  = heapSize >> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            int rightIndex = childIndex + 1;
            E child = arr[childIndex];
            if (rightIndex < heapSize && compare(arr[rightIndex], child) > 0) {
                child = arr[childIndex = rightIndex];
            }
            if (compare(element, child) >= 0) {
                break;
            }
            arr[index] = child;
            index = childIndex;
        }
        arr[index] = element;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(100);
        }
        HeapSort1<Integer> hs = new HeapSort1<>(arr, (o1, o2) -> o2 - o1);

        System.out.println(Arrays.toString(hs.arr));
        hs.sort();
        System.out.println(Arrays.toString(hs.arr));
    }
}
