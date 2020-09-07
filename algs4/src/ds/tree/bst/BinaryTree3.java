package ds.tree.bst;

import algs.cmp.Todo;
import ds.tree.printer.BinaryTreeInfo;
import ds.tree.printer.BinaryTrees;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree3<E extends Comparable<E>> implements BinaryTreeInfo {

    public Node<E> root;

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
        return ((Node<E>)node).element;
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    public static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent  = parent;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        public boolean hasTowChildren() {
            return this.left != null && this.right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }
    }

    private void checkElement(E element) {
        if (element == null) {
            throw new IllegalArgumentException("参数不能为null");
        }
    }

    private int size;

    private int height;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public Node<E> tmp = null;
    public static int counter = 0;
    public static int random  = 0;

    public void add(E element) {
        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }
        Node<E> node   = root;
        Node<E> parent = root;
        int cmp = 0;

        // 查找被插入节点的父节点
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        counter++;
        if (counter == random) {
            tmp = newNode;
        }
        // 查找要插入到父节点的具体位置
        if (cmp > 0) {
            parent.right =  newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    /**
     *  新添加之后的Node调整
     */
    protected void afterAdd(Node<E> node) {}

    public int compare(E a, E b) {
        if (a.compareTo(b) > 0) {
            return 1;
        } else if (a.compareTo(b) < 0) {
            return -1;
        }
        return 0;
    }

    /**
     * node被删除的节点
     *  如果是叶子节点： node.parent.left = null 或 node.parent.right = null
     *  如果是node.parent == null, root = nullg
     *  1. 度为1的节点： 用子节点替换其位置即可
     *      child.parent = node.parent
     *      node.parent.left  = child
     *
     *      child.parent = node.parent
     *      node.parent.right = child
     *
     *    root为根节点：
     *      root = child
     *      child.parent = null
     *
     *  2. 度为2的节点： 必须保证二叉树性质不能被破坏
     *  从左子树或右子树 找到一个去掉node的节点，
     *     该节点应该为前驱(左子树中的最大值)，后继(右子树的最小值)
     *     然后删除相应的前驱或后继节点的值
     *
     *  如果一个节点的度为2，则它的前驱，后继节点的度只可能是 1或 0
     *
     */
    public void remove(E element) {
        Node<E> node = node(element);
        remove(node);
    }

    public Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = element.compareTo(node.element);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    @Todo
    public void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        // 度 为2的节点
        if (node.hasTowChildren()) {
            Node<E> s = successor(node);
            // Node<E> s = predecessor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.element = s.element;
            // 删除后继节点
            node = s;
        }
        // 删除node
        // 此时node的度必然是1或0
        // 度为1时
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) { // node 度为 1
            replacement.parent = node.parent;
        }
        if (node.parent == null) {
            root = replacement;
        } else if (node == node.parent.left) {
            node.parent.left = replacement;
        } else if (node == node.parent.right) {
            node.parent.right = replacement;
        }
        size--;
        afterRemove(node);
    }

    protected void afterRemove(Node<E> node) {}

    public boolean contains(E element) {
        return node(element) != null;
    }

    public void traversal(int type) {
        // 前序
        if (type == 1) {
            preOrder(root);
        //  中序
        } else if (type == 2) {
            midOrder(root);
        // 后序
        } else if (type == 3) {
            postOrder(root);
        // 层序
        } else if (type == 4) {
            levelOrder(root);
        }
    }

    public void traversal(int type, Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        // 前序
        if (type == 1) {
            preOrder(root, visitor);
            //  中序
        } else if (type == 2) {
            midOrder(root, visitor);
            // 后序
        } else if (type == 3) {
            postOrder(root, visitor);
            // 层序
        } else if (type == 4) {
            levelOrder(visitor);
        }
    }

    public void preOrder(Node<E> node, Visitor<E> visitor) {
        if (visitor.stop) {
            return;
        }
        if (node == null) {
            return;
        }
        if (visitor.stop = visitor.visit(node.element)) {
            return;
        }
        preOrder(node.left, visitor);
        preOrder(node.right, visitor);
    }

    @Todo
    public void midOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        midOrder(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        if (visitor.stop = visitor.visit(node.element)) {
            return;
        }
        midOrder(node.right, visitor);
    }

    public void postOrder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) {
            return;
        }
        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        if (visitor.stop) {
            return; // 递归调用依然会执行
        }
        if (visitor.stop = visitor.visit(node.element)) {
            return;
        }
    }

    /**
     *  使用队列方式 遍历
     */
    public void levelOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> x = q.poll();
            if (x.left != null) {
                q.offer(x.left);
            }
            if (x.right != null) {
                q.offer(x.right);
            }
            StdOut.print(x.element + "  ");
        }
    }

    public void levelOrder(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> x = q.poll();
            if (visitor.visit(x.element)) {
                return;
            }
            if (x.left != null) {
                q.offer(x.left);
            }
            if (x.right != null) {
                q.offer(x.right);
            }
        }
    }

    // 根 -> 左 -> 右
    public void preOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        StdOut.print(node.element.toString() + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 左 -> 根 -> 右
    public void midOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        midOrder(node.left);
        StdOut.print(node.element.toString() + " ");
        midOrder(node.right);
    }

    // 左 -> 右 -> 根
    public void postOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        StdOut.print(node.element.toString() + " ");
    }

    /**
     *  如果返回true就终止遍历
     */
    public static abstract class Visitor<E> {
        boolean stop;
        abstract boolean visit(E element);
    }

    /**
     * 完全二叉树是指除了最后一层之外，其他每一层的结点数都是满的。最后一层如果也满了，
     * 是一颗满二叉树，也是完全二叉树。最后一层如果不满，缺少的结点也全部的集中在左边，
     * 那也是一颗完全二叉树。
     *
     */
    /*

    // 此处有重复判断
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);

        boolean requireLeaf = false;
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            if (requireLeaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null && node.right != null) {
                q.offer(node.left);
                q.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                requireLeaf = true;
                if (node.left != null) {
                    q.offer(node.left);
                }
            }
            // 层序遍历
            // 如果node.left == null && node.right != null false
            // 如果node.left != null && node.right == null 或 node.left == null && node.right == null
            //      则node节点之后, 遍历的节点都应该为叶子节点，才是完全二叉树，否则返回false
        }
        return true;
    }

     */

    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        boolean requireLeaf = false;
        while (!q.isEmpty()) {
            Node<E> x = q.poll();
            if (requireLeaf && !x.isLeaf()) {
                return false;
            }
            if (x.left != null) {
                q.offer(x.left);
            } else if (x.right != null) {
                return false;
            }
            if (x.right != null) {
                q.offer(x.right);
            } else {
                requireLeaf = true;
            }
        }
        return true;
    }

    /**
     * 根节点到最远节点的高度
     */
    public int height() {
        return getHeight(root);
    }

    public int getHeight() {
        int height = 0;
        if (root == null) {
            return height;
        }
        // 队列中存储每一层的元素数量,默认为 1
        int levelSize = 1;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            levelSize--;
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
            // 意味着本层已经访问完， 即将访问下一级
            if (levelSize == 0) {
                levelSize = q.size();
                height++;
            }
        }
        return height;
    }

    /**
     *  by recursively
     */
    public int getHeight(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    public void invertRecursively() {
        invert(root);
    }

    public void invert(Node<E> node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            return;
        }
//        invert(node.right);
        invert(node.left);

        Node<E> temp = node.left;
        node.left  = node.right;
        node.right = temp;

//        invert(node.right);
        invert(node.left);
    }

    public void invert() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> x = q.poll();
            Node<E> tmp = x.left;
            x.left = x.right;
            x.right = tmp;

            if (x.left != null) {
                q.offer(x.left);
            }
            if (x.right != null) {
                q.offer(x.right);
            }
        }
    }

    @Todo
    public void refactorFromTraversalResult() {
        /*
            以下结果可以保证重构出唯一的一颗二叉树：
                前序遍历 + 中序遍历:    前序： root left right
                后续遍历 + 中序遍历:    中序： left root right

                前序 + 后续： 如果是一棵真二叉树，结果是唯一的，否则结果不唯一



         */
    }

    @Todo
    public void refactorByPreAndMid() {
        // 前: 4 2 1 3 6 5
        // 中: 1 2 3 4 5 6
        /*
            root: 4
         */
    }

    @Todo
    public void refactor() {

    }

    @Override
    public String toString() {
        return "BinaryTree3{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

    public Node<E> get(E element) {
        if (root == null) {
            return null;
        }
        Node<E> x = root;
        while (x != null) {
            int cmp = element.compareTo(x.element);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x;
            }
        }
        return null;
    }

    /**
     * 前驱节点:
     *     中序遍历时的前一个节点(前一个比其小的节点)，绝对是其左子树的最大节点
     */
    public Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 从左子节点找前驱
        Node<E> x = node.left;
        if (x != null) {
            while (x.right != null) {
                x = x.right;
            }
            return x;
        }

        // 从父节点中查找
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        // node.parent == null
        // node == node.parent.right
        return node.parent;
    }

    /**
     * 右子树中最小
     * 当前节点为父节点的左子树时则为父节点
     */
    public Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> x = node.right;
        if (x != null) {
            while (x.left != null) {
                x = x.left;
            }
            return x;
        }
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    public E min() {
        Node<E> node = min(root);
        if (node == null) {
            return null;
        }
        return node.element;
    }

    public Node<E> min(Node<E> node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    /**
     * 非递归版本
     */
    public E min1() {
        if (root == null) {
            return null;
        }
        Node<E> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.element;
    }

    public E max() {
        Node<E> node = max(root);
        if (node == null) {
            return null;
        }
        return node.element;
    }

    public Node<E> max(Node<E> node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    public E max1() {
        if (root == null) {
            return null;
        }
        Node<E> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.element;
    }

    /**
     * 前驱节点递归版本
     */
    public E floor(E element) {
        Node<E> node = floor(root, element);
        if (node == null) {
            return null;
        }
        return node.element;
    }

    private Node<E> floor(Node<E> node, E element) {
        if (node == null) {
            return null;
        }
        int cmp = element.compareTo(node.element);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return floor(node.left, element);
        }
        Node<E> x = floor(node.right, element);
        if (x == null) {
            return node;
        } else {
            return x;
        }
    }

    /**
     * 后继节点：
     *     中序遍历的后一个节点
     */
//    public static void

    private static void printComplete() {
        BinaryTree3<Integer> bt3 = findComplateBinaryTree();
        BinaryTrees.println(bt3);
        StdOut.println();

        StdOut.println("前序：");
        bt3.traversal(1);
        StdOut.println();
        StdOut.println("中序：");
        bt3.traversal(2);
        StdOut.println();
        StdOut.println("后续：");
        bt3.traversal(3);
    }

    private static BinaryTree3<Integer> findComplateBinaryTree() {
        while (true) {
            Integer[] arr = new Integer[40];
            BinaryTree3<Integer> bt3 = new BinaryTree3<>();
            for (int i = 0; i < 40; i++) {
                Integer tmp = StdRandom.uniform(20);
                bt3.add(tmp);
                arr[i] = tmp;
            }
            boolean isComplate = bt3.isComplete();
            if (isComplate) {
                return bt3;
//                BinaryTrees.println(bt3);
//                StdOut.println(isComplate);
//                StdOut.println(Arrays.toString(arr));
//                break;
            }
        }
    }

    private static void traverse(BinaryTree3<Integer> bt3) {
        StdOut.print("前序：");
        bt3.traversal(1, new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                StdOut.print(element.toString() + "  ");
                if (element == 6) {
                    return true;
                }
                return false;
            }
        });
        StdOut.println();
        StdOut.print("中序：");
        bt3.traversal(2, new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                StdOut.print(element.toString() + "  ");
                if (element == 6) {
                    return true;
                }
                return false;
            }
        });
        StdOut.println();
        StdOut.print("后序：");
        bt3.traversal(3, new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                StdOut.print(element.toString() + "  ");
                if (element == 6) {
                    return true;
                }
                return false;
            }
        });
        StdOut.println();
        StdOut.print("层序：");
        bt3.traversal(4, new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                StdOut.print(element.toString() + "  ");
                if (element == 6) {
                    return true;
                }
                return false;
            }
        });
    }

    public static void main(String[] args) {
        int num = 50;
        Integer[] arr = new Integer[num];
        BinaryTree3<Integer> bt3 = new BinaryTree3<>();
        random = StdRandom.uniform(num);
        for (int i = 0; i < num; i++) {
            Integer tmp = StdRandom.uniform(20);
//            tmp = arr[i];
//            arr[i] = tmp;
            bt3.add(tmp);
        }
//        StdOut.println(Arrays.toString(arr));
//        StdOut.println("是否为完全树: " + bt3.isComplete());
//        bt3.traversal(4);
//        traverse(bt3);

//        StdOut.println("二叉树高度 递归： "  + bt3.height());
//        StdOut.println("二叉树高度 迭代： "  + bt3.getHeight());
//        StdOut.println("是否为完全二叉树：" + bt3.isComplete());
//        findComplateBinaryTree();
        BinaryTrees.println(bt3);
//        bt3.invertRecursively();
//        bt3.invert();
//        printComplete();

//        bt3.testPredSucc(bt3);
//        bt3.testMaxMin();
//        bt3.testFloor();
//        bt3.testGet(bt3);
        bt3.testRemove(bt3);
    }

    public void testRemove(BinaryTree3<Integer> bt3) {
        StdOut.println("");
        bt3.remove(11);
        BinaryTrees.println(bt3);
    }

    public void testGet(BinaryTree3<Integer> bt3) {
        BinaryTree3.Node<Integer> node = bt3.get(11);
        if (node != null) {
            StdOut.println(node.element);
            StdOut.println("predecessor: " + bt3.predecessor(node).element);
            StdOut.println("succcessor:  " + bt3.successor(node).element);
        }
    }

    private void testMaxMin() {
        StdOut.println("max: " + max());
        StdOut.println("max1: " + max1());
        StdOut.println("min: " + min());
        StdOut.println("min1: " + min1());
    }

    private void testPredSucc(BinaryTree3<Integer> bt3) {
//        BinaryTrees.println(bt3);
        if (tmp != null) {
            StdOut.println("tmp节点： " + tmp.element.toString());
            BinaryTree3.Node<Integer> predecessor = bt3.predecessor(bt3.tmp);
            StdOut.println("前序节点： " + (predecessor == null ? "null" : predecessor.element.toString()));
            BinaryTree3.Node<Integer> successor = bt3.successor(bt3.tmp);
            StdOut.println("后续节点： " + (successor == null ? "null" : successor.element.toString()));
        }
    }
}
