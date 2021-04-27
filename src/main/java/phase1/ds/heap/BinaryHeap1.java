package phase1.ds.heap;

import phase1.ds.tree.printer.BinaryTreeInfo;
import phase1.ds.tree.printer.BinaryTrees;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 *  MaxPQ
 */
public class BinaryHeap1<E> implements BinaryTreeInfo {
    /**
     * 堆： heap
     *     树状的数据结构
     *
     * 二叉堆的逻辑结构就是一棵完全二叉树，也叫完全二叉堆
     *
     * 使用数组实现二叉堆
     *
     * 索引i的规律：
     *     > 如果i = 0， 则为根节点
     *     > 如果i > 0，它的父节点索引为floor((i-1)/2)
     *     > 如果2i+1 <= n-1，左子节点索引为 2i+1
     *     > 如果2i+1  > n-1，没有左子节点
     *     > 如果2i+2 <= n-1，右子节点索引为2i+2
     */
    private E[] elements;
    private Comparator<E> comparator;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap1(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap1(E[] elements, Comparator<E> comparator) {
        this.comparator = comparator;
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity  = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            System.arraycopy(elements, 0, this.elements, 0, elements.length);
            heapify();
        }
    }

    public BinaryHeap1(E[] elements) {
        this(elements, null);
    }

    public BinaryHeap1() {
        this(null, null);
    }

    private int compare(E e1, E e2) {
//        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>)e1).compareTo(e2);
        return comparator != null ? comparator.compare(e2, e1) : ((Comparable<E>)e2).compareTo(e1);
    }

    private int getParentIndex(int i) {
        return (int)Math.floor((i - 1) >> 1);
    }

    private int getLeftChildIndex(int i) {
        return (i << 1) + 1;
    }

    private int getRightChildIndex(int i) {
        return (i << 1) + 2;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
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

    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        shiftUp(size - 1);
    }

    private void shiftUp(int index) {
        E e = elements[index];
        while (index > 0) {
            int pIndex = getParentIndex(index);
            E p = elements[pIndex];
            if (compare(e, p) <= 0) {
                break;
            }
            elements[index] = p;
            /*
            E tmp = elements[index];
            elements[index]  = elements[pIndex];
            elements[pIndex] = tmp;
             */
            index = pIndex;
        }
        elements[index] = e;
    }

    public E get() {
        emptyCheck();
        return elements[0];
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("could not be null");
        }
    }

    public E remove() {
        // 删除堆顶元素
        emptyCheck();
        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        shiftDown(0);
        return root;
    }

    private void shiftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        // 第一个叶子节点的索引 = 非叶子节点数量
        /*第一个叶子节点的索引*/
        while (index < half) {
            // 默认为左子节点的索引
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightIndex = childIndex + 1;
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
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

    public E replace(E element) {
        // 删除堆顶元素，同时插入新元素
        elementNotNullCheck(element);
        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            shiftDown(0);
        }
        return root;
    }

    /**
     * 批量建堆：
     *
     * 1. 自上而下的上滤: 除根节点外，都进行上滤，先建大堆，后建小堆，本质上就是添加
     *
     * 2. 自下而上的下滤: 除叶子节点外，其他元素进行下滤，本质上是左右先建堆，最后进行合并变成更大堆
     */
    /*
        1. 就是挨个添加
        for (int i = 1; i < size; i++) {
            shiftUp(i);
        }
        // 复杂度： O(n*log(n))
        所有节点的深度之和：
            叶子节点：有n/2个，且每个节点深度都是O(logn)级别
            O(logn)足以利用排序对数组进行全排序了
     */
    /*
        2. 从最后一个非叶子节点开始
        第一个叶子节点为 size >> 1
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            shiftDown(i);
        }
        // 复杂度： O(n)
        所有节点的高度之和：
            节点总个数n，树高h，则 n = 2^h - 1
            所有节点树高之和： H(n) = 2^0*(h-0) + 2^1*(h-1) + 2^2*(h-2) + ... + 2^(h-1)*[h-(h-1)]
     */
    public void heapify() {
        /*
        for (int i = 1; i < size; i++) {
            shiftUp(i);
        }
         */
        for (int i = ((size >> 1) - 1); i >= 0; i--) {
            // System.out.println(elements[i]);
            shiftDown(i);
        }
    }

    /**
     * topk问题
     *  从n个整数中，找出最大的前k个数（k远远小于n）
     *
     *  1. 快排： O(n*logn)
     *  2. 二叉堆解决，O(n*logk)
     */
    private static void topK() {
        int k = 5;
        int num = 20;
//        Integer[] arr = new Integer[num];
//        for (int i = 0; i < num; i++) {
//            arr[i] = new Random().nextInt(num);
//        }
//        System.out.println(Arrays.toString(arr));
        BinaryHeap1<Integer> h = new BinaryHeap1<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
//                return o2 - o1;
            }
        });
//        System.out.println(Arrays.toString(h.elements));
        /*
        for (int i = 0; i < arr.length; i++) {
            if (h.size() < k) {
                h.add(arr[i]);
            } else if (arr[i] > h.get()) {
                h.replace(arr[i]);
            }
        }
         */
        for (int i = 0; i < num; i++) {
            if (h.size() < k) {
                h.add(i);
            } else {
                if (i > h.get()) {
                    h.replace(i);
                }
            }
        }

        BinaryTrees.println(h);

    }

    public static void main(String[] args) {
//        testAdd();
//
//        testHeapify();
        topK();
    }

    private static void testHeapify() {
        int num = 12;

        Integer[] arr = new Integer[num];
        for (int i = 0; i < num; i++) {
            arr[i] = new Random().nextInt(num);
        }

        arr = new Integer[]{88, 44, 53, 41, 16, 6, 70, 18, 85, 98, 81, 23, 36, 43, 37};
        System.out.println(Arrays.toString(arr));
        BinaryHeap1<Integer> bp = new BinaryHeap1<>(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        BinaryTrees.println(bp);
        System.out.println();
        arr[0] = 10;
        arr[1] = 20;
        BinaryTrees.println(bp);
    }

    private static void testAdd() {
        BinaryHeap1<Integer> binaryHeap = new BinaryHeap1<>();
        for (int i = 0; i < 15; i++) {
            binaryHeap.add(i);
    //            System.out.println("【 " + i + " 】");
    //            System.out.println(Arrays.toString(binaryHeap.elements));
    //            BinaryTrees.println(binaryHeap);
    //            System.out.println("--------------------------");
        }
        System.out.println(Arrays.toString(binaryHeap.elements));
        BinaryTrees.println(binaryHeap);
        System.out.println("------------------------");
        binaryHeap.remove();
        System.out.println(Arrays.toString(binaryHeap.elements));
        BinaryTrees.println(binaryHeap);
        System.out.println("====================================");
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
}
