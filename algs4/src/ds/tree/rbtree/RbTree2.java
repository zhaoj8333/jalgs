package ds.tree.rbtree;

import ds.tree.bbst.AvlTree3;
import ds.tree.bbst.RbNode;
import ds.tree.printer.BinaryTreeInfo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author allen
 */
public class RbTree2<E extends Comparable<E>> implements BinaryTreeInfo {
    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    public RbNode<E> root;

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((RbNode<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((RbNode<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }

    private RbNode<E> red(RbNode<E> node) {
        return color(node, RED);
    }

    private RbNode<E> black(RbNode<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(RbNode<E> node) {
        // 空间点为黑色
        return node == null ? BLACK : ((RbNode<E>)node).color;
    }

    public boolean isBLACK(RbNode<E> node) {
        return colorOf(node) == BLACK;
    }

    public boolean isRED(RbNode<E> node) {
        return colorOf(node) == RED;
    }

    private RbNode<E> color(RbNode<E> node, boolean color) {
        if (node == null) {
            return null;
        }
        node.color = color;
        return node;
    }

    private void nodeCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("参数不能为null");
        }
    }

    private RbNode<E> createNode(E element) {
        nodeCheck(element);
        return new RbNode<>(element);
    }

    public void put(E element) {
        RbNode<E> newNode = createNode(element);
        RbNode<E> parent = null;
        RbNode<E> node = root;
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

    public void remove(E element) {}

    protected void afterPut(RbNode<E> node) {
        /*
            新元素 必定是添加到叶子节点中
            4阶b树所有节点元素个数x都符合 1 <= x <= 3
            新添加的节点默认为red，这样可以让红黑树的性质尽量满足(除了性质4)
            如果添加的是根节点，则染成black
            1. parent为black: 不做任何处理  4
            2. parent为red: double red    8
         */
        RbNode<E> parent = node.parent;
        // root
        // 必须是黑色
        if (parent == null) {
            black(node);
            return;
        }
        // 父节点是黑色不作任何处理
        if (isBLACK(parent)) {
            return;
        }
        // uncle节点
        RbNode<E> uncle = parent.sibling();
        RbNode<E> grand = parent.parent;
        if (isRED(uncle)) {
            // 染色
            black(parent);
            black(uncle);
            afterPut(red(grand));
            return;
        }
        // uncle不是red
        red(grand);
        if (parent.isLeftChild()) {
            // LL
            if (node.isLeftChild()) {
                black(parent);
            // LR
            } else if (node.isRightChild()) {
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else if (parent.isRightChild()) {
            // RL
            if (node.isLeftChild()) {
                black(node);
                rotateRight(parent);
            // RR
            } else if (node.isRightChild()) {
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    private void rotateLeft(RbNode<E> grand) {
        RbNode<E> parent = grand.right;
        RbNode<E> child  = parent.left;
        grand.right = child;
        parent.left = grand;
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
    }

    private void rotateRight(RbNode<E> grand) {
        RbNode<E> parent = grand.left;
        RbNode<E> child  = parent.right;
        grand.left = child;
        parent.right = grand;
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
    }

    public static void main(String[] args) {
        testRb();
//        testAvl();
    }

    private static void testAvl() {
        AvlTree3<Integer> avl = new AvlTree3<>();
//        rb.put(1);
//        rb.put(2);
//        rb.put(3);
//        rb.put(4);
//        rb.put(5);
//        rb.put(6);

        for (int i = 64; i > 0; i--) {
            avl.put(i);
        }
        for (int i = 20; i > 4; i--) {
//            rb.put(i);
        }
//        Integer[] data = new Integer[] {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
//        for (int i = 0; i < data.length; i++) {
//            rb.put(data[i]);
//        }
        StdOut.println();
        BinaryTrees.println(avl);
    }

    private static void testRb() {
        RbTree2<Integer> rb = new RbTree2<>();
//        rb.put(1);
//        rb.put(2);
//        rb.put(3);
//        rb.put(4);
//        rb.put(5);
//        rb.put(6);

        for (int i = 64; i > 0; i--) {
//            rb.put(i);
        }
        for (int i = 20; i > 4; i--) {
//            rb.put(i);
        }
        Integer[] data = new Integer[] {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
        for (int i = 0; i < data.length; i++) {
            rb.put(i);
        }
        StdOut.println();
        BinaryTrees.println(rb);
//        avl.remove(8);
    }


}
