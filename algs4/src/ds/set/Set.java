package ds.set;

/**
 * 集合 没有索引
 *
 * 实现： 动态数组, 链表
 */
public interface Set<E> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E> {
        boolean stop;
        abstract boolean visit(E element);
    }
}
