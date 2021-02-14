package phase1.ds.tree.bst;

public class Node5<E> {
    public E element;
    public Node5<E> left, right, parent;
    public int count;

    public Node5(E element, Node5<E> left, Node5<E> right, Node5<E> parent, int count) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.count = count;
    }

    @Override
    public String toString() {
        return
//                    "Node{" +
                element.toString();
//                    ", left=" + left +
//                    ", right=" + right +
//                    ", parent=" + parent +
//                    ", count=" + count +
//                    '}';
    }

    public boolean hasChildren() {
        return this.left != null || this.right != null;
    }

    public boolean hasTwoChildren() {
        return this.left != null && this.right != null;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
        // return this.count == 0
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public boolean isLeftChild() {
        return this.parent != null && this.parent.left == this;
    }

    public boolean isRightChild() {
        return this.parent != null && this.parent.right == this;
    }

    public Node5<E> sibling() {
        if (isLeftChild()) {
            return this.parent.right;
        }
        if (isRightChild()) {
            return this.parent.left;
        }
        return null;
    }

    public Node5<E> uncle() {
        if (this.parent == null) {
            return null;
        }
        return this.parent.sibling();
    }

}
