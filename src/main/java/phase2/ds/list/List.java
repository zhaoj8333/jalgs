package phase2.ds.list;

public interface List<E> {
    int size();
    boolean isEmpty();
    boolean contains(E elem);
    void add(E elem);
    void addFirst(E elem);
    void addLast(E elem);
    E remove(E elem);
    E removeFirst();
    E get(int index);
    E first();
    E last();
    void set(int index, E newElem);
    int indexOf(E elem);
    E[] toArray();
}
