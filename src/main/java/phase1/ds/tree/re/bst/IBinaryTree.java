package phase1.ds.tree.re.bst;

public interface IBinaryTree<E extends Comparable<E>> {
    int size();
    boolean isEmpty();
    boolean contains(E element);
    void clear();
    void add(E element);
    void remove(E element);
    void traversal();
    int height();
    boolean isComplete();
    void reverse();

}
