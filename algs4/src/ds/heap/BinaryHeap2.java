package ds.heap;

import ds.tree.printer.BinaryTreeInfo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Comparator;

/*
    堆： 任意节点的值总是 >= 子节点的值
    最大堆 最大值永远是最顶层的元素
    索引i的规律：
        如果i = 0，则为根节点
        如果 i > 0，父节点为 floor((i - 1) / 2)
        如果 2i+1 <+ n-1, 则左子节点编号为2i+1
        如果 2i+1 > n-1, 则无左子节点
 */
@SuppressWarnings("all")
public class BinaryHeap2<E> implements BinaryTreeInfo {

    private E[] elements;
    private Comparator<E> comparator;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap2(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap2() {
        this(null, null);
    }

    public BinaryHeap2(E[] elements, Comparator<E> comparator) {
        this.comparator = comparator;
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            System.arraycopy(elements, 0, this.elements, 0, elements.length);
            heapify();
        }
    }

    private int compare(E a, E b) {
        return comparator != null ? comparator.compare(b, b) : ((Comparable<E>)a).compareTo(b);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elements, null);
    }

    public void add(E element) {
        nullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        floatUp(size - 1);
    }

    private void floatUp(int index) {
        E child = elements[index];
        while (index > 0) {
            int i = (int) Math.floor((index - 1) >> 1);
            E parent = elements[i];
            if (compare(parent, child) /*>= */ <= 0) {
                break;
            }
            elements[index] = parent;
            index = i;
        }
        elements[index] = child;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }

    private void nullCheck(E ele) {
        if (ele == null) {
            throw new IllegalArgumentException("element can not be null");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) {
            return;
        }
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        if (size >= 0) {
            System.arraycopy(elements, 0, newElements, 0, size);
        }
        elements = newElements;
    }

    public E get() {
        emptyCheck();
        return elements[0];
    }

    public E remove() {
        emptyCheck();
        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        sinkDown(0);
        return root;
    }

    private void sinkDown(int index) {
        // heap是一个完全二叉树
        E element = elements[index];
        // index位置的元素必须有子节点，才能让其下沉，或者说 才能有子节点跟其进行比较
        // 非叶子节点才能进入while循环
        // 第一个叶子节点就是非叶子节点的数量
        int half = size >> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightIndex = childIndex + 1;
            if (rightIndex < size && compare(elements[rightIndex], child) /*>*/ < 0) {
                child = elements[childIndex = rightIndex];
            }
            if (compare(element, child) /*>=*/ <= 0) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = element;
    }

    /**
     * 删除最大元素，同事插入新元素
     *
     * 返回删掉的元素
     */
    public E replace(E element) {
        // E root = remove();
        // add(element);
        nullCheck(element);
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

    /**
     * 批量建堆
     * 1. 自上而下的上滤： 叶子节点也必须上滤  n*logn(所有节点深度值和)
     * 2. 自下而上的下滤： 不考虑叶子节点     0(n)(所有节点高度之和)
     */
    public void heapify() {
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
        return elements[(Integer) node];
    }

    public static void main(String[] args) {
//        test1();
//        test2();
        topK(4);
    }

    // 从n个整数中，找出最大的前k个数（k < n）
    // 全排序 O(n*logn)
    // 二叉堆 O(n*logk)
    private static void topK(int top) {
        int num = 50;
        Integer[] arr = new Integer[num];
        for (int i = 0; i < num; i++) {
            // arr[i] = StdRandom.uniform(num);
            arr[i] = i;
        }
        BinaryHeap2<Integer> bh1 = new BinaryHeap2<>(arr, null);
        BinaryTrees.println(bh1);
        BinaryHeap2<Integer> bh2 = new BinaryHeap2<>(null, null);
//        StdOut.println(Arrays.toString(bh2.elements));
//        BinaryTrees.println(bh2);
        for (int i = 0; i < arr.length; i++) {
            if (bh2.size() < top) {
                // 前k个加到小顶堆
                bh2.add(arr[i]);
            } else {
                // k + 1个，删除堆顶元素，添加该处的数字，扫描完毕后， 堆中剩下的就是最大的前k个数
                // 每次删除最小的
                if (arr[i] > bh2.get()) {
                    bh2.replace(arr[i]);
                }
            }
        }
        BinaryTrees.println(bh2);
    }

    private static void test2() {
        Integer[] arr = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }
        BinaryHeap2<Integer> bh2 = new BinaryHeap2<>(arr, null);
        StdOut.println(Arrays.toString(bh2.elements));
        BinaryTrees.println(bh2);

    }

    private static void test1() {
        // binaryheap2<character> bh2 = new binaryheap2();
        BinaryHeap2<Integer> bh2 = new BinaryHeap2();
        for (int i = 0; i < 15; i++) {
            // bh2.add((char)(i + 65));
            bh2.add((i));
        }
        StdOut.println(Arrays.toString(bh2.elements));
        BinaryTrees.println(bh2);
        bh2.remove();
        StdOut.println(Arrays.toString(bh2.elements));
        BinaryTrees.println(bh2);
        bh2.remove();
        StdOut.println(Arrays.toString(bh2.elements));
        BinaryTrees.println(bh2);
    }
}
