package exercises.sort;

import ds.tree.printer.BinaryTreeInfo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

public class HeapSort2<T> extends Sort<T> implements BinaryTreeInfo {

    private int heapSize;
    private T[] heap;
    private Comparator<T> comparator;
    private static final int DEFAULT_CAPACITY = 10;

    public T[] getHeap() {
        return heap;
    }

    public void setHeap(T[] heap) {
        this.heap = heap;
    }

    public HeapSort2(T[] array, Comparator<T> comparator) {
        if (array.length == 0) {
            this.heap = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            int capacity = Math.max(array.length, DEFAULT_CAPACITY);
            this.heap = (T[]) new Object[capacity];
            System.arraycopy(array, 0, this.heap, 0, array.length);
        }
        this.comparator = comparator;
        this.heapSize   = array.length;
    }

    public HeapSort2() {
        this(null, null);
    }

    private int compare(T a, T b) {
        return this.comparator == null ? ((Comparable<T>)a).compareTo(b) : comparator.compare(b, b);
    }

    public void sort() {
        heapify();
        StdOut.println("heapify: " + Arrays.toString(heap));
        BinaryTrees.println(this);

        while (heapSize > 1) {
            swap(heap, 0, --heapSize);
            sinkDown(0);
        }
    }

    private void sinkDown(int index) {
        T element = heap[index];
        int half  = heapSize >> 1;
        // 下滤节点必须有子节点
        while (index < half) {
            int childIndex = (index << 1) + 1;
            int rightIndex = childIndex + 1;
            T child = heap[childIndex];
            if (rightIndex < heapSize && compare(heap[rightIndex], child) > 0) {
                child = heap[childIndex = rightIndex];
            }
            if (compare(element, child) >= 0) {
                break;
            }
            heap[index] = child;
            index = childIndex;
        }
        heap[index] = element;
    }

    private void heapify() {
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            sinkDown(i);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = StdRandom.uniform(100);
        }
        HeapSort2<Integer> hs = new HeapSort2<>(arr, null);
        StdOut.println("before:  " + Arrays.toString(hs.getHeap()));
        hs.sort();
        StdOut.println("after :  " + Arrays.toString(hs.getHeap()));

    }

    @Override
    public Object root() {
        return 0;
    }
    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        int leftIndex = (index << 1) + 1;
        return leftIndex < heapSize ? leftIndex : null;
    }
    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        int rightIndex = (index << 1) + 2;
        return rightIndex < heapSize ? rightIndex : null;
    }
    @Override
    public Object string(Object node) {
        return heap[(Integer) node];
    }
}
