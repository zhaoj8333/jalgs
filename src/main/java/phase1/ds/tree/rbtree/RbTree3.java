package phase1.ds.tree.rbtree;

import phase1.ds.tree.printer.BinaryTreeInfo;
import phase1.ds.tree.printer.BinaryTrees;
import java.util.Comparator;

public class RbTree3<E> implements BinaryTreeInfo {
    @Override
    public Object root() {
        return root;
    }
    @Override
    public Object left(Object node) {
        return ((RbNode<E>)node).getLeft();
    }
    @Override
    public Object right(Object node) {
        return ((RbNode<E>)node).getRight();
    }
    @Override
    public Object string(Object node) {
        return node;
    }

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    public RbTree3() {
        this(null);
    }
    private Comparator<E> comparator;
    public RbTree3(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    public RbNode<E> color(RbNode<E> node, boolean color) {
        if (node == null) {
            return null;
        }
        node.color(color);
        return node;
    }
    public RbNode<E> red(RbNode<E> node) {
        return color(node, RbNode.RED);
    }
    public RbNode<E> black(RbNode<E> node) {
        return color(node, RbNode.BLACK);
    }
    public boolean isBlack(RbNode<E> node) {
        return colorOf(node) == RbNode.BLACK;
    }
    public boolean isRed(RbNode<E> node) {
        return colorOf(node) == RbNode.RED;
    }
    public int compare(E a, E b) {
        return this.comparator == null ? ((Comparable<E>) a).compareTo(b) : this.comparator.compare(a, b);
    }
    public boolean colorOf(RbNode<E> node) {
        return node == null ? BLACK : node.getColor();
    }
    private RbNode<E> root;
    private int size;
    private int height;
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加：12种情况
     *
     * 1. 4种情况：添加时，parent为black，满足性质4，return
     * 2. 8中情况：添加时，parent为red，不满足性质4
     *     2.1. uncle节点不是red（没有uncle，默认为black）:
     *         rr: grand左旋,red(grand),black(parent)
     *         ll: grand右旋,red(grand),black(parent)
     *         rl: 右旋，左旋,black(self),red(grand)
     *         lr: 左旋，右旋,black(self),red(grand)
     *     2.2. uncle节点是red(上溢)
     *         black(parent), black(uncle)
     *         grand向上合并
     *         合并至根节点，按照2.1情况处理
     */
    public void put(E element) {
        RbNode<E> newNode = createNode(element);
        RbNode<E> parent  = null;
        RbNode<E> node    = root;
        if (node == null) {
            root = newNode;
        } else {
            int compare = 0;
            while (node != null) {
                compare = compare(element, node.element);
                parent = node;
                if (compare == 0) {
                    node.element = element;
                    return;
                }
                if (compare < 0) {
                    node = node.getLeft();
                } else {
                    node = node.getRight();
                }
            }
            if (compare < 0) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }
            newNode.setParent(parent);
        }
        afterPut(newNode);
    }

    protected void afterPut(RbNode<E> node) {
        RbNode<E> parent = node.getParent();
        if (parent == null) {
            black(node);
            return;
        }
        if (isBlack(parent)) {
            return;
        }
        RbNode<E> uncle = parent.sibling();
        RbNode<E> grand = parent.getParent();
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            afterPut(red(grand));
            return;
        }
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
        RbNode<E> parent = grand.getRight();
        RbNode<E> child  = parent.getLeft();
        grand.setRight(child);
        parent.setLeft(grand);
        parent.setParent(grand.getParent());
        if (grand.isLeftChild()) {
            grand.getParent().setLeft(parent);
        } else if (grand.isRightChild()) {
            grand.getParent().setRight(parent);
        } else {
            root = parent;
        }
        if (child != null) {
            child.setParent(grand);
        }
        grand.setParent(parent);
    }

    private void rotateRight(RbNode<E> grand) {
        RbNode<E> parent = grand.getLeft();
        RbNode<E> child  = parent.getRight();
        grand.setLeft(child);
        parent.setRight(grand);
        parent.setParent(grand.getParent());
        if (grand.isLeftChild()) {
            grand.getParent().setLeft(parent);
        } else if (grand.isRightChild()) {
            grand.getParent().setRight(parent);
        } else {
            root = parent;
        }
        if (child != null) {
            child.setParent(grand);
        }
        grand.setParent(parent);
    }

    protected void afterRemove(RbNode<E> node, RbNode<E> replacement) {
    }

    private RbNode<E> createNode(E element) {
        if (element == null) {
            throw new IllegalArgumentException("can not use null to create node");
        }
        return new RbNode<>(element, null);
    }

    public static void main(String[] args) {
        RbTree3<Integer> rb = new RbTree3<>(null);
        Integer[] data = new Integer[] {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
        for (int i = 0; i < data.length; i++) {
            rb.put(i);
        }
        BinaryTrees.println(rb);
    }
}
