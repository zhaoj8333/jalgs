package phase1.ds.tree.bbst;

import phase1.ds.tree.bst.BinaryTree3;
import phase1.ds.tree.printer.BinaryTreeInfo;
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
            Node<E> r, // ???????????????
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g
    ) {
        // ???d???????????????
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
     * ????????????
     *
     * grand: ????????????????????????????????????
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

    private void rotateLeft(AvlNode<E> grand) {
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
        } else { // grand???root??????
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

    private void rotateRight(AvlNode<E> grand) {
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

    private void afterRotate(AvlNode<E> grand, AvlNode<E> parent, AvlNode<E> child) {
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // ???
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
        // ???node?????????parent??????????????????????????????
        while ((node = node.parent) != null) {
            // node????????????
            // ????????????
            if (isBalanced(node)) {
                // ????????????
                updateHeight(node);
            } else {
                // ??????????????????????????????
                // node???????????????????????????????????????????????????????????????????????????????????????????????????????????????
                rebalance0(node);
//                rebalance(node);
                // ??????node?????????????????????????????????
                break;
            }
        }
    }

    /**
     * ????????????????????????
     *     ??????????????????????????? ?????? ??????????????????
     *     ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????
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
//        Stopwatch sw = new Stopwatch();
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
//        System.out.println("list: " + sw.elapsedTime());

        AvlTree1<Integer> tree = new AvlTree1<>();
//        sw = new Stopwatch();
        for (int i = 0; i < size; i++) {
            tree.add(i);
        }
//        System.out.println("avl: " + sw.elapsedTime());

//        sw = new Stopwatch();
        LinkedList<Integer> link = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            link.add(i);
        }
//        System.out.println("link: " + sw.elapsedTime());

    }

    public static void main(String[] args) {
//        AvlTree1<Integer> avl = new AvlTree1<>();
//        for (int i = 0; i < 20; i++) {
//            avl.add(i);
//            System.out.println("----------------------------------------");
//            System.out.println("??? " + i + " ???");
//            BinaryTrees.println(avl);
//        }

//        BinaryTrees.println(avl);
//        avl.remove(16);
//        BinaryTrees.println(avl);
        performance();
    }
}
