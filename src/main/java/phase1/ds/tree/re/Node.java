package phase1.ds.tree.re;

import java.util.Objects;

public class Node<E> {
    public E element;
    public Node<E> left;
    public Node<E> right;
    public Node<E> parent;

    public Node(E element) {
        this.element = element;
    }

    public Node(E element, Node<E> parent) {
        this.element = element;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return element.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<?> node = (Node<?>) o;
        return element.equals(node.element);
    }

    @Override
    public int hashCode() {
        return element.hashCode();
    }

    public boolean isLeaf() {
        return Objects.isNull(left) && Objects.isNull(right);
    }

    public boolean hasOneChild() {
        return (Objects.nonNull(left) && Objects.isNull(right)) || (Objects.isNull(left) && Objects.nonNull(right));
    }

    public boolean hasTwoChildren() {
        return Objects.nonNull(left) && Objects.nonNull(right);
    }

    public boolean isLeft() {
        return this.parent != null && this.equals(this.parent.left);
    }

    public boolean isRight() {
        return this.parent != null && this.equals(this.parent.right);
    }
}