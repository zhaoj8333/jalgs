package phase1.ds.graph;

import phase1.ds.heap.Heap;
import phase1.ds.tree.printer.BinaryTreeInfo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * @author allen
 */
public class MinHeap<E> implements BinaryTreeInfo,Heap<E> {
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private Comparator<E> comparator;

    public MinHeap(Collection<E> elements, Comparator<E> comparator) {
        this.comparator = comparator;
        if (elements == null || elements.size() == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.size();
            int capacity = Math.max(size, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            int i = 0;
            for (E element : elements) {
                this.elements[i] = element;
                i++;
            }
            heapify();
        }
    }

    public MinHeap() {
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

    public void addAll(Collection<E> elements) {
        if (elements == null) {
            return;
        }
        for (E element : elements) {
            add(element);
        }
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
}
