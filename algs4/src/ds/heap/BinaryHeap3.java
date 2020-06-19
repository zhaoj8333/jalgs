package ds.heap;

import ds.tree.printer.BinaryTreeInfo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author allen
 */
public class BinaryHeap3<E> implements BinaryTreeInfo,Heap<E> {
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private Comparator<E> comparator;

    public BinaryHeap3(E[] elements, Comparator<E> comparator) {
        this.comparator = comparator;
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(size, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            System.arraycopy(elements, 0, this.elements, 0, size);
            heapify();
        }
    }

    public BinaryHeap3() {
        this(null, null);
    }

    public int compare(E a, E b) {
        return comparator != null ? comparator.compare(a, b) : ((Comparable<E>)a).compareTo(b);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
    }

    @Override
    public void add(E element) {
        elementCheck(element);
        ensureCapacity(size + 1);
        this.elements[size] = element;
        floatUp(size);
        size++;
    }

    private void floatUp(int index) {
        E last = this.elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = this.elements[parentIndex];
            if (compare(parent, last) >= 0) {
                break;
            }
            this.elements[index] = parent;
            index = parentIndex;
        }
        this.elements[index] = last;
    }

    private void sinkDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            int rightIndex = childIndex + 1;
            E child = elements[childIndex];
            if (rightIndex < size && compare(child, elements[rightIndex]) < 0) {
                child = elements[childIndex = rightIndex];
            }
            if (compare(element, child) >= 0) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }

    private void emptyCheck() {
        if (this.elements.length == 0) {
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }

    private void elementCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element can't be null");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = this.elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        if (size > 0) {
            System.arraycopy(elements, 0, newElements, 0, size);
        }
        elements = newElements;
    }

    @Override
    public E get() {
        return this.elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        E root = this.elements[0];
        int lastIndex = --size;
        this.elements[0] = this.elements[lastIndex];
        this.elements[lastIndex] = null;
        sinkDown(0);
        return root;
    }

    /**
     *  替换最大元素
     */
    @Override
    public E replace(E element) {
        elementCheck(element);
        E root = null;
        if (size == 0) {
            elements[size++] = element;
        } else {
            root = elements[0];
            elements[0] = element;
            sinkDown(0);
        }
        return root;
    }

    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            sinkDown(i);
        }
    }

    @Override
    public Object root() {
        return 0;
    }
    @Override
    public Object left(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 1;
        return index >= size ? null : index;
    }
    @Override
    public Object right(Object node) {
        Integer index = (Integer) node;
        index = (index << 1) + 2;
        return index >= size ? null : index;
    }
    @Override
    public Object string(Object node) {
        return this.elements[(Integer) node];
    }

    public static void topK(int top) {
//        BinaryHeap3<Integer> tmp  = new BinaryHeap3<>(arr, null);
//        BinaryTrees.println(tmp);
//        StdOut.println("==================================");

        BinaryHeap3<Integer> hp  = new BinaryHeap3<>(arr, (o1, o2)->o2 - o1);
        BinaryTrees.println(hp);
        StdOut.println("==================================");

        StdOut.println(Arrays.toString(arr));
        BinaryHeap3<Integer> tpk = new BinaryHeap3<>(null, (a, b) -> b - a);
        for (Integer integer : arr) {
            if (tpk.size() < top) {
                tpk.add(integer);
            } else {
                if (integer > tpk.get()) {
//                    StdOut.println("----------------------" + arr[i] + " replace " + tpk.get());
//                    BinaryTrees.println(tpk);
//                    StdOut.println(Arrays.toString(tpk.elements));
                    tpk.replace(integer);
                }
            }
        }
        BinaryTrees.println(tpk);
//        StdOut.println(Arrays.toString(tpk.elements));
    }

    private static Integer[] arr = new Integer[1];

    private static void initArr() {
        int len = 20;
        arr = new Integer[20];
        for (int i = 0; i < len; i++) {
//            arr[i] = StdRandom.uniform(100);
            arr[i] = i;
        }
    }

    public static void main(String[] args) {
        // test1();
        // test2();
        test3();
    }

    private static void test3() {
        initArr();
        int top = 5;
        topK(top);
    }

    private static void test2() {
        initArr();
        BinaryHeap3<Integer> hp = new BinaryHeap3<>(arr, null);
        StdOut.println(Arrays.toString(hp.elements));
        BinaryTrees.println(hp);
    }

    private static void test1() {
        BinaryHeap3<Integer> hp = new BinaryHeap3();
        for (int i = 0; i < 10; i++) {
            hp.add(i);
//            StdOut.println("-----------------------");
//            BinaryTrees.println(hp);
        }
        StdOut.println(Arrays.toString(hp.elements));
        BinaryTrees.println(hp);
        StdOut.println();
        StdOut.println("remove: ");
        hp.remove();
        BinaryTrees.println(hp);
    }
}
