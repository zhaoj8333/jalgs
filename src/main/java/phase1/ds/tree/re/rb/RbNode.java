package phase1.ds.tree.re.rb;

import java.util.Objects;

public class RbNode<E> {

    public E element;
    public RbNode<E> left;
    public RbNode<E> right;
    public RbNode<E> parent;
    public boolean color;

    public RbNode(E element, RbNode<E> parent) {
        this.element = element;
        this.parent  = parent;
        this.color   = RED;
    }

    public final static boolean RED = false;
    public final static boolean BLACK = true;

    public static RbNode color(RbNode node, boolean color) {
        if (Objects.isNull(node)) return null;
        node.color = color;
        return node;
    }

    public static RbNode red(RbNode node) {
        return color(node, RED);
    }

    public static RbNode black(RbNode node) {
        return color(node, BLACK);
    }

    public static boolean colorOf(RbNode node) {
        return Objects.isNull(node) ? BLACK : node.color;
    }

    public static boolean isBlack(RbNode node) {
        return colorOf(node) == BLACK;
    }

    public static boolean isRed(RbNode node) {
        return colorOf(node) == RED;
    }

    @Override
    public String toString() {
//        return element.toString();
        if (isRed(this)) {
            return element.toString() + "_R";
        } else {
            return element.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RbNode<?> AvlNode = (RbNode<?>) o;
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

    public RbNode<E> sibling() {
        if (isLeft()) {
            return parent.right;
        }
        if (isRight()) {
            return parent.left;
        }
        return null;
    }

    public RbNode<E> uncle() {
        return parent.sibling();
    }
}
