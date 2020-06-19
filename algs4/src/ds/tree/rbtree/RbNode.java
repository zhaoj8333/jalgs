package ds.tree.rbtree;

public class RbNode<E> {
    public static final boolean RED = false;
    public static final boolean BLACK = true;
    private boolean color;
    public E element;
    private RbNode<E> parent, left, right;
    public RbNode(E element, RbNode<E> parent) {
        this.element = element;
        this.parent  = parent;
    }

    public RbNode<E> getLeft() {
        return left;
    }

    public void setLeft(RbNode<E> left) {
        this.left = left;
    }

    public RbNode<E> getRight() {
        return right;
    }

    public void setRight(RbNode<E> right) {
        this.right = right;
    }

    public RbNode<E> getParent() {
        return parent;
    }

    public void setParent(RbNode<E> parent) {
        this.parent = parent;
    }

    public RbNode<E> color(boolean color) {
        this.color = color;
        return this;
    }

    public boolean getColor() {
        return color;
    }

    public RbNode<E> red() {
        return color(RED);
    }

    public RbNode<E> black() {
        return color(BLACK);
    }

    public boolean isBlack() {
        return this.color == BLACK;
    }

    public boolean isRed() {
        return this.color == RED;
    }

    public String colorToString() {
        return this.color == RED ? "_R" : "_B";
    }

    @Override
    public String toString() {
        return element.toString() + colorToString();
    }

    public boolean hasChildren() {
        return this.left != null || this.right != null;
    }

    public boolean hasTwoChildren() {
        return this.left != null && this.right != null;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public boolean isLeftChild() {
        return parent != null && this == parent.left;
    }

    public boolean isRightChild() {
        return parent != null && this == parent.right;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public RbNode<E> sibling() {
        if (isLeftChild()) {
            return parent.right;
        }
        if (isRightChild()) {
            return parent.left;
        }
        return null;
    }

    public RbNode<E> uncle() {
        if (parent == null) {
            return null;
        }
        return parent.sibling();
    }

}
