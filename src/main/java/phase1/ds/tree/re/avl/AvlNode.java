package phase1.ds.tree.re.avl;

import phase1.ds.tree.re.Node;

import java.util.Objects;

public class AvlNode<E> extends Node<E> {

    public int height = 1;
    public E element;
    public AvlNode<E> left;
    public AvlNode<E> right;
    public AvlNode<E> parent;

    public AvlNode(E element, AvlNode<E> parent) {
        super(element);
        this.element = element;
        this.parent = parent;
    }

    @Override
    public String toString() {
//        return "(" + element.toString() + "," + this.height + ")";
        return element.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvlNode<?> AvlNode = (AvlNode<?>) o;
        return element.equals(AvlNode.element);
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

    public int balanceFactor() {
        int leftHeight  = Objects.isNull(left)  ? 0 : left.height;
        int rightHeight = Objects.isNull(right) ? 0 : right.height;
        return leftHeight - rightHeight;
    }

    public void updateHeight() {
        int leftHeight  = Objects.isNull(left)  ? 0 : left.height;
        int rightHeight = Objects.isNull(right) ? 0 : right.height;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    public AvlNode<E> tallerChild() {
        int leftHeight = Objects.isNull(left) ? 0 : left.height;
        int rightHeight = Objects.isNull(right) ? 0 : right.height;
        if (leftHeight > rightHeight) {
            return left;
        } else if (leftHeight < rightHeight) {
            return right;
        } else {
            return this.isLeft() ? this.left : this.right;
        }
    }
}
