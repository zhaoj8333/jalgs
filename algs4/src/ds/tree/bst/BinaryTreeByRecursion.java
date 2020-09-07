package ds.tree.bst;

import ds.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author allen
 */
public class BinaryTreeByRecursion<E extends Comparable<E>> implements BinaryTreeInfo {

    private Node<E> root;
    private int size;

    private int size() {
        return size(root);
    }

    private int size(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            return node.count;
        }
    }

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
        return ((Node<E>)node).toString();
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
        return getByRecursion(root, element);
    }

    public Node<E> getByRecursion(Node<E> node, E element) {
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            return getByRecursion(node.left, element);
        } else if (cmp > 0) {
            return getByRecursion(node.right, element);
        }
        return node;
    }

    public void put(E element) {
        putByRecursion(element);
    }

    public void putByRecursion(E element) {
        root = putRecursion(root, element);
    }

    public Node<E> putRecursion(Node<E> node, E element) {
        if (node == null) {
            return createNode(element, null);
        }
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            node.left = putRecursion(node.left, element);
        } else if (cmp > 0) {
            node.right = putRecursion(node.right, element);
        } else {
            node.element = element;
        }
//        node.setCount(count(node.left) + count(node.right) + 1);
        return node;
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
        return min(root);
    }

    private Node<E> min(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public Node<E> max() {
        return max(root);
    }

    public Node<E> max(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    public Node<E> floor(E element) {
        Node<E> node = root;
        return floor(node, element);
    }

    private Node<E> floor(Node<E> node, E element) {
        if (node == null || element == null) {
            return null;
        }
        int cmp = element.compareTo(node.element);
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            return floor(node.left, element);
        }
        Node<E> t = floor(node.right, element);
        if (t != null) {
            return t;
        } else {
            return node;
        }
    }

    public Node<E> ceil(E element) {
        Node<E> node = root;
        return ceil(node, element);
    }

    private Node<E> ceil(Node<E> node, E element) {
        if (node == null || element == null) {
            return null;
        }
        int cmp = element.compareTo(node.element);
        if (cmp == 0) {
            return node;
        }
        if (cmp > 0) {
            return ceil(node.right, element);
        }
        Node<E> t = ceil(node.left, element);
        if (t != null) {
            return t;
        } else {
            return node;
        }
    }

    public Node<E> remove(E element) {
        return null;
    }

    public Node<E> remove(Node<E> node) {
        return null;
    }

    public E select(int k) {
        return select(root, k).element;
    }

    public Node<E> select(Node<E> node, int k) {
        if (k < 0) {
            return null;
        }
        if (node == null) {
            return null;
        }
        int t = size(node.left);
        if (t == k) {
            return node;
        } else if (t > k) {
            return select(node.left, k);
        } else {
            return select(node.right, k - t - 1);
        }
    }

    public int rank(E element) {
        return rank(root, element);
    }

    private int rank(Node<E> node, E element) {
        if (element == null || node == null) {
            return 0;
        }
        int cmp = element.compareTo(node.element);
        if (cmp == 0) {
            return size(node.left);
        } else if (cmp < 0) {
            return rank(node.left, element);
        } else {
            return 1 + rank(node.right, element) + size(node.left);
        }
    }

    public void clear() {

    }

    public Node<E> node(E element) {
        return node(root, element);
    }

    private Node<E> node(Node<E> node, E element) {
        if (node == null) {
            return null;
        }
        if (node.element == null) {
            return node;
        }
        if (node.element == element) {
            return node;
        }
        Node<E> x;
        if ((x = node(node.left, element)) != null) {
            return x;
        }
        return node(node.right, element);
    }

    public boolean contains(E element) {
        return false;
    }

    @SuppressWarnings(value = "unckecked")
    private BstIteratorByRecursion<E> iterate() {
        return new BstIteratorByRecursion(root);
    }

    public void traversal(int type, BinaryVisitor<E> visitor) {
        BstIteratorByRecursion<E> bstir = iterate();
        bstir.traversal(type, visitor);
    }

    public int depth() {
        return depth(root);
    }

    private int depth(Node<E> node) {
        if (node == null) {
            return 0;
        }
        int left  = depth(node.left);
        int right = depth(node.right);
        return Math.max(left, right) + 1;
    }

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
            } else {
                if (x.right != null) {
                    return false;
                }
            }
            if (x.right != null) {
                q.offer(x.right);
            } else {
                requireLeaf = true;
            }
        }
        return true;
    }

    public void invert(E element) {
        Node<E> x = root;
        if (element != null) {
            x = node(element);
        }
        invert(x);
    }

    private void invert(Node<E> node) {
        if (node == null) {
            return;
        }
        swap(node);
        invert(node.left);
        invert(node.right);
    }

    private void swap(Node<E> node) {
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
