package ds.tree.bbst;

import com.sun.istack.internal.NotNull;
import ds.tree.bst.BinaryTree3;
import ds.tree.printer.BinaryTreeInfo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author allen
 */
public class AvlTree1<E extends Comparable<E>> extends BinaryTree3<E> implements BinaryTreeInfo {

    private static class AvlNode<E extends Comparable<E>>  extends BinaryTree3.Node<E> {

        int height = 1;

        public AvlNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight  = left  == null ? 0 : ((AvlNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AvlNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight  = left  == null ? 0 : ((AvlNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AvlNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public AvlNode<E> tallerChild() {
            int leftHeight  = left  == null ? 0 : ((AvlNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AvlNode<E>)right).height;

            if (leftHeight > rightHeight) {
                return (AvlNode<E>) left;
            } else if (leftHeight < rightHeight) {
                return (AvlNode<E>) right;
            } else {
                return (AvlNode<E>) (isLeftChild() ? left : right);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AvlNode<>(element, parent);
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AvlNode<E>)node).balanceFactor()) <= 1;
    }

    private void rebalance(Node<E> grand) {
        AvlNode<E> parent = ((AvlNode<E>) grand).tallerChild();
        AvlNode<E> node   = parent.tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { // LL
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else { // LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {
            if (node.isLeftChild()) { // RL
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else { // RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    private void rotate(
            @NotNull Node<E> r, // 子树根节点
            Node<E> a, Node<E> b, Node<E> c,
            @NotNull Node<E> d,
            Node<E> e, Node<E> f, Node<E> g
    ) {
        // 让d成为根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        // a - b - c
        b.left  = a;
        if (a != null) {
            a.parent = b;
        }
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        updateHeight(b);
        // e - f - g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        f.right = g;
        if (g != null) {
            g.parent = f;
        }
        updateHeight(f);
        // b - d - f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }

    /**
     * 恢复平衡
     *
     * grand: 高度最低的那个不平衡节点
     */
    private void rebalance0(Node<E> grand) {
        AvlNode<E> parent = ((AvlNode<E>)grand).tallerChild();
        AvlNode<E> node = parent.tallerChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { // LL
                rotateRight((AvlNode<E>) grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight((AvlNode<E>) grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotateRight(parent);
                rotateLeft((AvlNode<E>) grand);
            } else { // RR
                rotateLeft((AvlNode<E>) grand);
            }
        }
    }

    private void rotateLeft(@NotNull AvlNode<E> grand) {
        AvlNode<E> parent = (AvlNode<E>) grand.right;
        AvlNode<E> child  = (AvlNode<E>) parent.left;
        grand.right = child;
        parent.left = grand;

        parent.parent = grand.parent;
        /*
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand为root节点
            root = parent;
        }

        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;
        updateHeight(grand);
        updateHeight(parent);

         */
        afterRotate(grand, parent, child);
    }

    private void rotateRight(@NotNull AvlNode<E> grand) {
        AvlNode<E> parent = (AvlNode<E>) grand.left;
        AvlNode<E> child  = (AvlNode<E>) parent.right;
        grand.left   = child;
        parent.right = grand;
        parent.parent = grand.parent;
        /*
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        child.parent = grand;
        updateHeight(grand);
        updateHeight(parent);

         */
        afterRotate(grand, parent, child);
    }

    private void afterRotate(@NotNull AvlNode<E> grand, @NotNull AvlNode<E> parent, AvlNode<E> child) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // 根
            root = parent;
        }
        if (child != null) {
            child.parent = grand;
        }
        grand.parent = parent;
        updateHeight(grand);
        updateHeight(parent);
    }

    private void updateHeight(Node<E> node) {
        ((AvlNode<E>)node).updateHeight();
    }

    @Override
    protected void afterAdd(Node<E> node) {
        // 从node向上找parent，直到找到失衡的节点
        while ((node = node.parent) != null) {
            // node是否平衡
            // 平衡因子
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡，并更新高度
                // node为从下到上发现的第一个不平衡的节点（同时也是高度最低的不平衡节点），也就是
                rebalance0(node);
//                rebalance(node);
                // 只要node恢复平衡，整棵树就平衡
                break;
            }
        }
    }

    /**
     * 删除导致的失衡：
     *     只可能会导致父节点 及其 祖先节点失衡
     *     但是始终只会导致一个节点失衡，因为导致失衡的是因为删除了某个节点的较短的子树
     */
    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
            }
        }
    }

    private static void performance() {
        int size = 100_00000;
        Stopwatch sw = new Stopwatch();
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        StdOut.println("list: " + sw.elapsedTime());

        AvlTree1<Integer> tree = new AvlTree1<>();
        sw = new Stopwatch();
        for (int i = 0; i < size; i++) {
            tree.add(i);
        }
        StdOut.println("avl: " + sw.elapsedTime());

        sw = new Stopwatch();
        LinkedList<Integer> link = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            link.add(i);
        }
        StdOut.println("link: " + sw.elapsedTime());

    }

    public static void main(String[] args) {
//        AvlTree1<Integer> avl = new AvlTree1<>();
//        for (int i = 0; i < 20; i++) {
//            avl.add(i);
//            StdOut.println("----------------------------------------");
//            StdOut.println("【 " + i + " 】");
//            BinaryTrees.println(avl);
//        }

//        BinaryTrees.println(avl);
//        avl.remove(16);
//        BinaryTrees.println(avl);
        performance();
    }
}
