package ds.tree.bbst;

import ds.tree.printer.BinaryTreeInfo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author allen
 */
public class AvlTree3<E extends Comparable<E>> implements BinaryTreeInfo {
    public Node<E> root;
    private int size;

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return (Node<E>)node;
    }

    private void nodeCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("参数不能为null");
        }
    }

    private Node<E> createNode(E element) {
        nodeCheck(element);
        return new Node<>(element);
    }

    public void put(E element) {
        Node<E> newNode = createNode(element);
        Node<E> parent = null;
        Node<E> node = root;
        if (root == null) {
            root = newNode;
        } else {
            int cmp = 0;
            while (node != null) {
                cmp = element.compareTo(node.element);
                parent = node;
                if (cmp == 0) {
                    node.element = element;
                    return;
                } else if (cmp < 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
            if (cmp < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            newNode.parent = parent;
        }
        afterPut(newNode);
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(node.balanceFactor()) <= 1;
    }

    protected void afterPut(Node<E> node) {
        // rotate
        while ((node = node.parent) != null) {
            if (! isBalanced(node)) {
                // 此处一旦恢复平衡，整棵树就恢复平衡
                rebalance(node);
            }
            node.updateHeight();
        }
    }

    private void rebalance(Node<E> grand) {
        Node<E> parent = grand.tallerChild();
        Node<E> child  = parent.tallerChild();
        if (child == null) {
            return;
        }
        if (parent.isLeftChild()) {
            // LL
            if (child.isLeftChild()) {
                rotateRight(grand);
            // LR
            } else if (child.isRightChild()) {
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else if (parent.isRightChild()) {
            // RL
            if (child.isLeftChild()) {
                rotateRight(parent);
                rotateLeft(grand);
            // RR
            } else if (child.isRightChild()) {
                rotateLeft(grand);
            }
        }
    }

    private void rotateLeft(Node<E> grand) {
        /*
            g.right = p.left
            p.left  = g
         */
        Node<E> parent = grand.right;
        Node<E> child  = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child  = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        if (child != null) {
            child.parent = grand;
        }
        grand.parent = parent;
        parent.updateHeight();
        grand.updateHeight();
    }

    private Node<E> node(E element) {
        nodeCheck(element);
        Node<E> node = root;
        while (node != null) {
            int cmp = element.compareTo(node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    private Node<E> pred(Node<E> node) {
        if (root == null) {
            return null;
        }
        Node<E> x = root.left;
        if (x != null) {
            while (x.right != null) {
                x = x.right;
            }
            return x;
        }
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    protected void remove(E element) {
        Node<E> node = node(element);
        if (node == null) {
            return;
        }
        if (node.hasTwoChildren()) {
            Node<E> predOrSucc = pred(node);
            node.element = predOrSucc.element;
            node = predOrSucc;
        }
        // 度为 1 或 0
        Node<E> child = node.left != null ? node.left : node.right;
        if (child != null) {
            child.parent = node.parent;
        }
        if (node.parent == null) {
            root = child;
        } else if (node == node.parent.left) {
            node.parent.left = child;
        } else if (node == node.parent.right) {
            node.parent.right = child;
        }
        size--;
        afterRemove(node);
    }

    protected void afterRemove(Node<E> node) {
        StdOut.println("node: " + node);
        while ((node = node.parent) != null) {
            if (!isBalanced(node)) {
                rebalance(node);
            }
            node.updateSize();
            node.updateHeight();
        }
    }

    public static void main(String[] args) {
        AvlTree3<Integer> avl = new AvlTree3<>();
        for (int i = 0; i < 5; i++) {
            avl.put(i);
        }
        for (int i = 11; i > 0; i--) {
            avl.put(i);
        }
        StdOut.println();
        BinaryTrees.println(avl);
        avl.remove(4);
        avl.remove(7);
//        avl.remove(8);
        BinaryTrees.println(avl);
    }
}
