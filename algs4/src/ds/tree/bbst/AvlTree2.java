package ds.tree.bbst;

import ds.tree.bst.Node;
import ds.tree.printer.BinaryTreeInfo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;

public class AvlTree2<E extends Comparable<E>> extends BBST<E> implements BinaryTreeInfo {
    private static class AvlNode<E extends Comparable<E>> extends Node<E> {
        int height = 1;
        public AvlNode(E element, Node<E> left, Node<E> right, Node<E> parent, int count) {
            super(element, left, right, parent, count);
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
        @Override
        public String toString() {
            String str = super.toString();
            return "[" + str /* + ":" + height*/ + "]";
        }
    }

    @Override
    protected AvlNode<E> createNode(E element) {
        nullCheck(element);
        return new AvlNode<>(element, null, null, null, 1);
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AvlNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AvlNode<E>)node).updateHeight();
    }

    private void rebalance(Node<E> grand) {
        // node 为 grandParent
        AvlNode<E> parent = ((AvlNode<E>)grand).tallerChild();
        AvlNode<E> node   = parent.tallerChild();
        if (parent.isLeftChild()) {
            // LL
            if (node.isLeftChild()) {
                rotateRight(grand);
            // LR
            } else {
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            // RL
            if (node.isLeftChild()) {
                rotateRight(parent);
                rotateLeft(grand);
            // RR
            } else {
                rotateLeft(grand);
            }
        }
    }

    private void rebalance0(Node<E> grand) {
        // node 为 grandParent
        AvlNode<E> parent = ((AvlNode<E>)grand).tallerChild();
        AvlNode<E> node   = parent.tallerChild();
        if (parent.isLeftChild()) {
            // LL
            if (node.isLeftChild()) {
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
                // LR
            } else {
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {
            // RL
            if (node.isLeftChild()) {
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
                // RR
            } else {
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    /**
     *  添加可能会导致所有祖先节点失衡(logN个)
     *  父节点不会失衡
     *  非祖先节点不会失衡
     *
     *  是要让高度最低的失衡节点恢复平衡，整棵树就平衡（O(1)次调整）
     */
    @Override
    protected void afterPut(Node<E> node) {
        /*
         * 失衡节点的寻找方法：
         *     找所有失衡节点最低的节点，并修复之，祖先节点都会恢复平衡,O(1)次调整
         *
         * node.parent.parent.parent.parent. ...
         */
        while ((node = node.parent) != null) {
            /*node是否平衡*/
            if (isBalanced(node)) {
                // update height
                updateHeight(node);
            } else {
                // rebalance
                rebalance(node);
//                rebalance0(node);
                // 整棵树就平衡
                break;
            }
        }
    }

    /**
     *
     *  1.  删除导致父节点的失衡
     *  导致父节点失衡的删除肯定是删除了 "高度较短的子节点"
     *  但是父节点的高度不会改变，所以在网上走祖先节点的平衡因子不会改变，只会导致父节点失衡
     *
     *  2. 删除导致的祖父节点失衡
     *  如果高度较大节点子节点被删除，可能导致父节点高度改变，所以导致祖父节点失衡
     *
     *  3. 删除只会导致以上两种情况之一发生，只会导致一个节点失衡
     *
     *  恢复平衡后，只可能会导致更高层的祖先节点失衡（O(n)次调整）
     */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        while ((node = node.parent) != null) {
            /*node是否平衡*/
            if (isBalanced(node)) {
                // update height
                updateHeight(node);
            } else {
                // rebalance
//                rebalance(node);
                rebalance0(node);
                // 整棵树就平衡
                break;
            }
        }
    }

    public static void main(String[] args) {
        AvlTree2<Integer> avl2 = new AvlTree2<>();
        AvlNode<Integer> tmp = null;
        for (int i = 0; i < 10; i++) {
            avl2.put0(i);
        }
        for (int i = 20; i >= 10; i--) {
            avl2.put0(i);
            if (i == 14) {
                tmp = (AvlNode<Integer>) avl2.node(i);
            }
        }
        BinaryTrees.println(avl2);
        StdOut.println();
        StdOut.println("删除 " + tmp);
        avl2.remove0(tmp);
        BinaryTrees.println(avl2);
    }
}
