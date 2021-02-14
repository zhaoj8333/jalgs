package phase1.ds.tree.bbst;

public class Node<E> {
    public E element;
    public Node<E> left, right, parent;
    public int size;
    public int height;

    public Node(E element) {
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

    public Node<E> sibling() {
        if (isLeftChild()) {
            return parent.right;
        } else if (isRightChild()) {
            return parent.left;
        }
        return null;
    }

    public Node<E> uncle() {
        if (parent == null) {
            return null;
        }
        return parent.sibling();
    }

    public int balanceFactor() {
        int leftHeight  = left  == null ? 0 : left.height;
        int rightHeight = right == null ? 0 : right.height;
//            System.out.println(leftHeight + ":" + rightHeight);
//            System.out.println(this);
        return leftHeight - rightHeight;
    }

    public void updateHeight() {
        int leftHeight  = left  == null ? 0 : left.height;
        int rightHeight = right == null ? 0 : right.height;
        height = 1 + Math.max(leftHeight, rightHeight);
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

    public Node<E> tallerChild() {
        int leftHeight  = left  == null ? 0 : left.height;
        int rightHeight = right == null ? 0 : right.height;
        if (leftHeight > rightHeight) {
            return left;
        } else if (leftHeight < rightHeight) {
            return right;
        } else {
            return isLeftChild() ? left : right;
        }
    }

    @Override
    public String toString() {
        return "[" + element.toString()/* + " (" + size + "," + height */ + "]";
    }
}
