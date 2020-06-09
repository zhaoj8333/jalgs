package ds.tree.rbtree;

import ds.tree.bbst.BBST;
import ds.tree.bst.Node;
import ds.tree.printer.BinaryTreeInfo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class RBtree1<E extends Comparable<E>> extends BBST<E> implements BinaryTreeInfo {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    public RBtree1() {
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return null;
        }
        ((RBNode<E>)node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    /**
     * 红黑树的平衡，是为了保持五条性质而进行的
     *    1. parent 为black（4种情况）: 同样满足4阶b树的性质，不做任何处理
     *    2. parent 为red（8种情况）: 不满足红黑树的性质，所以需要调整
     *       uncle不是red（没有uncle红色节点 或 叔父节点是黑色）
     *           LL, RR单旋: parent染成black，grand染成red
     *           RL: parent右旋，grand左旋，node染成黑色
     *           LR: parent左旋，grand右旋，node染成黑色
     *       uncle是red:(b-树上溢)
     *            上溢LL：染色即可
     *              parent，uncle染成black，parent和uncle及节点独立出来
     *              grand上溢，染成red，当成新添加的节点处理上溢，如果继续上溢，如果持续到根节点,将根节点染成black
     *            上溢RR：
     *              parent,uncle染成black
     *              grand向上合并,染成red，当新添加节点处理
     *            上溢LR：同理
     *            上溢RL：
     *
     * @param node 新添加的节点
     */
    @Override
    protected void afterPut(Node<E> node) {
        Node<E> parent = node.parent;
        if (parent == null) {
            black(node);
            return;
        }
        if (isBlack(parent)) {
            return;
        }
        Node<E> uncle = node.uncle();
        // grand都会被染成red
        Node<E> grand = red(parent.parent);
        // 上溢
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            afterPut(grand);
            return;
        }
        //
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) { // LL
                black(parent);
            } else if (node.isRightChild()) { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else if (parent.isRightChild()) {
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else if (node.isRightChild()) { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 1. 删除时只会删除叶子节点
     *     如果是删除red节点，不违背红黑树性质，直接删除，不做任何调整
     * 2. 删除black节点时：
     *     拥有2个red子节点，不会被直接删除，找其子节点（前驱，后继节点）删除
     *     拥有1个red子节点，用于替代的子节点是红色，删除后将替代节点变黑
     *
     *     删除black叶子节点：
     *         1. sibling为黑色
     *          能否借，必须是同一层的b树节点,所以必须是黑色
     *              1. 借节点，该兄弟节点必须是黑色，且至少有一个red子节点
     *                  parent下溢，旋转，旋转之后的中心节点继承parent的颜色，左右节点染成black
     *              2. 不能借，但是依然为黑色
     *                  父节点为red，父节点直接下溢,parent变黑，sibling变红,parent不会下溢
     *              父节点为black，导致parent也下溢，将parent当做被删除的节点即可
     *         2. sibling为red
     *              nephew变兄弟: parent变红并右旋，uncle变黑，又回到了sibling为black的情况
     */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        if (isRed(node)) {
            return;
        }
        if (isRed(replacement)) {
            black(replacement);
            return;
        }
        Node<E> parent = node.parent;
        if (parent == null) {
            return;
        }
        // 黑色叶子:
        // 被删除的是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        // 被删除的在左边，兄弟节点在右边
        if (left) {
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }
            // 统一处理sibling为黑色的部分
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有red子节点, 黑色sibling不可能有black子节点,因为删除的是最后一层
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent, null);
                }
            } else { // 兄弟节点至少有一个red子节点，借元素
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        // 被删除的在右边，兄弟节点杂左边
        } else {
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }
            // 统一处理sibling为黑色的部分
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有red子节点, 黑色sibling不可能有black子节点,因为删除的是最后一层
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent, null);
                }
            } else { // 兄弟节点至少有一个red子节点，借元素
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }

    }

    @Override
    protected RBNode<E> createNode(E element) {
        return new RBNode<>(element, null);
    }

    private static class RBNode<E> extends Node<E> {
        public boolean color = RED;
        public RBNode(E element, Node<E> parent) {
            super(element, null, null, parent, 1);
        }

        @Override
        public String toString() {
//            Ansi str;
            String str;
            if (color == RED) {
                str = "R_";
            } else {
                str = "B_";
            }
            return str + super.toString();
        }
    }
    public static void main(String[] args) {
        test2();
//        test1();
    }

    private static void test2() {
        RBtree1<Integer> rb = new RBtree1<>();
        Integer[] arr = new Integer[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = StdRandom.uniform((i + 1)*3);
        }
        StdOut.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            rb.put0(arr[i]);
//            StdOut.println("================================= " + arr[i]);
//            BinaryTrees.println(rb);
//            StdOut.println();
        }
        BinaryTrees.println(rb);
        for (int i = 0; i < arr.length; i++) {
            rb.remove0(arr[i]);
            StdOut.println("removing: " + arr[i]);
            BinaryTrees.println(rb);
        }
    }

    private static void test1() {
        RBtree1<Integer> btrb = new RBtree1<>();

        Integer[] arr1 = new Integer[] {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
        StdOut.println(Arrays.toString(arr1));
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == 68) {
                btrb.put0(arr1[i]);
            }
            btrb.put0(arr1[i]);
            StdOut.println("-------------------------------------------");
            StdOut.println("[ " + arr1[i] + " ]");
            StdOut.println();
            BinaryTrees.println(btrb);
        }

    }
}
