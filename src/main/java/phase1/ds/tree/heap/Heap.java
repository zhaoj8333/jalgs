package phase1.ds.tree.heap;

public interface Heap<E> {
    int size();
    boolean isEmpty();
    void add(E element);
    E get();
    E remove();
    E replace(E element);
    void clear();
    void heapify();
}
