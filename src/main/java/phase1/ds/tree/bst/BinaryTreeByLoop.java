package phase1.ds.tree.bst;

import phase1.ds.tree.printer.BinaryTreeInfo;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author allen
 */
public class BinaryTreeByLoop<E extends Comparable<E>> implements BinaryTreeInfo {

    private Node<E> root;
    private int size;

    @Override
    public Node<E> root() {
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
        return node.toString();
    }

    public int count() {
        return count(root);
    }

    public int count(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    public Node<E> get(E element) {
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

    public void put(E element) {
        putByLoop(element);
    }

    public void putByLoop(E element) {
        nullCheck(element);
        if (root == null) {
            root = createNode(element, null);
            afterPut(element);
            size++;
        }
        Node<E> node   = root;
        Node<E> parent = root;
        int cmp = 0;
        // 找到要添加的节点
        while (node != null) {
            cmp = element.compareTo(node.element);
            parent = node;
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node.element = element;
                return;
            }
        }
        Node<E> newNode = createNode(element, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else if (cmp > 0) {
            parent.right = newNode;
        }
        // 更新节点计数
        Node<E> tmp = parent;
        while (tmp != null) {
//            tmp.setCount(tmp.getCount() + 1);
            tmp = tmp.parent;
        }
        size++;
        afterPut(element);
    }

    private Node<E> createNode(E element, Node<E> parent) {
        return new Node<E>(element, null, null, parent, 1);
    }

    protected void afterPut(E element) {}

    private void nullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element不能为null");
        }
    }

    public Node<E> min() {
        Node<E> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node<E> max() {
        Node<E> node = root;
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public int depth()  {
        int height = 0;
        if (root == null) {
            return height;
        }
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> x = queue.poll();
            levelSize--;
            if (x.left != null) {
                queue.offer(x.left);
            }
            if (x.right != null) {
                queue.offer(x.right);
            }
            // 每一层访问完以后，下一层的数量就是queue的长度
            // levelSize == 0 时，表示该层访问完
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 前序节点
     */
    public Node<E> pred(E element) {
        Node<E> node = node(element);
        return pred(node);
    }

    public Node<E> pred(Node<E> node) {
        if (node == null) {
            return null;
        }
        // 左子树中找
        Node<E> x = node.left;
        if (x != null) {
            while (x.right != null) {
                x = x.right;
            }
            return x;
        }
        // 向上找
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    public Node<E> succ(E element) {
        Node<E> node = node(element);
        return succ(node);
    }

    /**
     *  如果一个节点的度为2，则其前驱，后继节点的度只可能是 1 和 0
     */
    private Node<E> succ(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> x = node.right;
        // 右子树中找
        if (x != null) {
            while (x.left != null) {
                x = x.left;
            }
            return x;
        }
        // 父子树中找
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    public Node<E> remove(E element) {
        Node<E> node = node(element);
        if (node == null) {
            return null;
        }
        if (node.hasTwoChildren()) {
            Node<E> succ = succ(node);
            node.element = succ.element;
            node = succ;
            // 后边会统一删除node节点
        }
        // 来到此处，node的度必然是 1 或 0
        Node<E> child = node.left != null ? node.left : node.right;
        // hasOneChild
        if (child != null) {
            child.parent = node.parent;
        }
        // hasOneOrNoChild
        if (node.parent == null) {
            root = child;
        } else if (node == node.parent.left) {
            node.parent.left = child;
        } else if (node == node.parent.right) {
            node.parent.right = child;
        }
        size--;
//        afterRemove();
        return node;
    }

    public E select(int k) {
        return null;
    }

    public Node<E> select() {
        return null;
    }

    public int rank(E element) {
        return 0;
    }

    public void clear() {

    }

    /**
     *  通过层序遍历获取
     */
    public Node<E> node(E element) {
        if (root == null) {
            return null;
        }
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.element == element) {
                return node;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return null;
    }

    public boolean contains(E element) {
        return false;
    }

    private BstIteratorByLoop<E> iterate() {
        return new BstIteratorByLoop(root);
    }

    public void traversal(int type, BinaryVisitor<E> visitor) {
        BstIteratorByLoop<E> bstil = iterate();
        bstil.traversal(type, visitor);
    }

    public boolean isComplate() {
        return false;
    }

    public void invert(E element) {
        Node<E> x = root;
        if (element != null) {
            x = node(element);
        }
        invert(x);
    }

    /**
     *  层序遍历
     *
     *  通过逐层翻转
     */
    public void invert(Node<E> node) {
        if (node == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node<E> x = queue.poll();
            swap(x);
            if (x.left != null && x.left.hasChildren()) {
                queue.offer(x.left);
            }
            if (x.right != null && x.right.hasChildren()) {
                queue.offer(x.right);
            }

        }
    }

    private void swap(Node<E> node) {
        if (!node.hasChildren()) {
            return;
        }
        Node<E> tmp = node.left;
        node.left   = node.right;
        node.right  = tmp;
    }

    public void refactor() {

    }

    @Override
    public String toString() {
        return "BinaryTree4{" +
                "root=" + root +
                '}';
    }

}
