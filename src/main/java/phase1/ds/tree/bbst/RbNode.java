package phase1.ds.tree.bbst;

/**
 * @author allen
 */
public class RbNode<E> {
    public E element;
    public RbNode<E> left, right, parent;
    public int size;
    public int height;
    public boolean color;

    public RbNode(E element) {
        this.element = element;
        this.left    = null;
        this.right   = null;
        this.parent  = null;
        this.size    = 1;
        this.height  = 1;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean hasChildren() {
        return this.left != null || this.right != null;
    }

    public boolean hasTwoChildren() {
        return this.left != null && this.right != null;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public boolean isLeftChild() {
        return parent != null && this == parent.left;
    }

    public boolean isRightChild() {
        return parent != null && this == parent.right;
    }

    public RbNode<E> sibling() {
        if (isLeftChild()) {
            return parent.right;
        } else if (isRightChild()) {
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

    public void updateSize() {
        if (hasTwoChildren()) {
            size = 3;
        } else if (isLeaf()) {
            size = 1;
        } else {
            size = 2;
        }
    }

    @Override
    public String toString() {
        return (color ? "B_" : "R_") + element.toString();
    }
}
