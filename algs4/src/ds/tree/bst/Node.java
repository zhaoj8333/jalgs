package ds.tree.bst;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author allen
 */
public class Node<E> {
    public E element;
    public Node<E> left, right, parent;
    public int count;

    public Node(E element, Node<E> left, Node<E> right, Node<E> parent, int count) {
        this.element = element;
        this.left    = left;
        this.right   = right;
        this.parent  = parent;
        this.count   = count;
    }

    @Override
    public String toString() {
        return /*" [" + */ element.toString() /* + ": " + count */ /*+ "] "*/;
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

    public Node<E> sibling() {
        if (isLeftChild()) {
            return parent.right;
        }
        if (isRightChild()) {
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
}

