package phase1.ds.tree.bst;

import phase1.ds.tree.printer.BinaryTreeInfo;
import phase1.ds.tree.printer.BinaryTrees;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树：
 *     在n个整数中搜索某个数？
 *
 *     1. 动态数组，从第0个开始搜索，复杂度为O(n)
 *     2. 如果有序，则使用二分搜索，复杂度O(logn)，但是增删复杂度为O(n)
 *     3. 使用二叉树，增删改查均可优化至 O(logn)
 *
 * 二叉排序树，二叉查找树
 *     任意一个节点的值都大于其左子树所有节点的值
 *     任意一个节点的值都小于其右子树所有节点的值
 *     左右子树也是一颗二叉搜索树
 *
 *     二叉搜索树元素必须具备可比较性
 *
 *     二叉搜索树，元素没有索引概念
 *
 */
public class BST<E extends Comparable<E>> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    public void add(E ele) {
        // 第一个节点
        if (root == null) {
            root = new Node<>(ele, null);
            size++;
            return;
        }
        // 不是第一个节点
        // 找到父节点
        Node<E> parent = root;
        Node<E> node = root;
        int cmp  = 0;
        while (node != null) {
            cmp = compare(ele, node.ele);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                // 覆盖与否看需求
                node.ele = ele;
                return;
            }
        }
        Node<E> newNode = new Node<>(ele, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left  = newNode;
        }
        size++;
    }

    private int compare(E e1, E e2) {
        return e1.compareTo(e2);
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    /**
     *  前序遍历：
     *      根节点 -> 前序遍历左子树 -> 前序遍历右子树
     */
    public void preOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.ele);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * 中序遍历：
     *     中序遍历左子树、 根节点、 中序遍历右子树
     *     中序遍历右子树、 根节点、 中序遍历左子树
     *
     * 中序遍历结果是 升序 或 降序的
     */
    public void inOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.println(node.ele);
        inOrderTraversal(node.right);
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    /**
     * 后序遍历：
     *     左子树 、  右子树 、 根节点
     *
     */
    public void postOrderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.ele);
    }

    public static interface Visitor<E> {
        void visit(E ele);
    }

    /**
     * 层序遍历：
     *     从上到下，从左到右
     *
     * 使用队列，先进先出
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            System.out.println(node.ele);
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
    }

    /**
     * 前中后遍历区别：
     *     根节点的位置
     */

    public void remove(E ele) {

    }

    public boolean contains(E ele) {
        return false;
    }

    private void eleCheck(E ele) {
        if (ele == null) {
            throw new IllegalArgumentException("element not null");
        }
    }

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
        return ((Node<E>)node).ele;
    }

    private static class Node<E> {
        E ele;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E ele, Node<E> parent) {
            this.ele = ele;
            this.parent = parent;
        }
    }

    public void display() {

    }

    public void levelOrderByVisitor(Visitor<E> visitor) {
        if (root == null) {
            return;
        }
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            visitor.visit(node.ele);
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        BST<Integer> bst = new BST<>();
        for (int j = 0; j < 15; j++) {
            int ram = (int) (Math.random() * 100);
            bst.add(ram);
        }
        System.out.println();
        BinaryTrees.println(bst);
        System.out.println("====================");
//        bst.preOrderTraversal();
//        bst.inOrderTraversal();
//        bst.postOrderTraversal();
//        bst.levelOrderTraversal();
        bst.levelOrderByVisitor(ele->System.out.print("—" + ele + "—"));
     }

    private static void test1() {
        Integer[] data = new Integer[] {
            7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);
        System.out.println("====================");
//        bst.preOrderTraversal();
        bst.inOrderTraversal();
    }
}
