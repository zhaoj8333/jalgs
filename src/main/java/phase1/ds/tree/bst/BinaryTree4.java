package phase1.ds.tree.bst;

import phase1.ds.tree.printer.BinaryTreeInfo;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author allen
 */
public class BinaryTree4<E extends Comparable<E>> implements BinaryTreeInfo {

    protected Node<E> root;
    private int size;
    private int height;
    private int count() {
        return size(root);
    }

    private int size(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            return node.count;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void put0(E element) {
        Node<E> newNode = createNode(element);
        if (root == null) {
            root = newNode;
            size = 1;
            afterPut(root);
            return;
        }
        // get the node and add element to its left or right
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = element.compareTo(node.element);
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
        updateCount(parent);
        size++;
        afterPut(newNode);
    }

    protected void afterPut(Node<E> node) { }

    private void updateCount(Node<E> tmp) {
        // 更新节点计数
        while (tmp != null) {
            tmp.count++;
            tmp = tmp.parent;
        }
    }

    public void put(E element) {
        root = put(root, element);
    }

    public Node<E> put(Node<E> node, E element) {
        if (node == null) {
            return createNode(element);
        }
        int cmp = element.compareTo(node.element);
        if (cmp == 0) {
            node.element = element;
        } else if (cmp < 0) {
            node.left = put(node.left, element);
        } else {
            node.right = put(node.right, element);
        }
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Node<E> node(E element) {
        nullCheck(element);
        if (root == null) {
            return null;
        }
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = element.compareTo(node.element);
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

    public Node<E> node0(E element) {
        nullCheck(element);
        return node(root, element);
    }

    private Node<E> node(Node<E> node, E element) {
        if (node == null) {
            return null;
        }
        int cmp = element.compareTo(node.element);
        if (cmp == 0) {
            return node;
        } else if (cmp < 0) {
            return node(node.left, element);
        } else {
            return node(node.right, element);
        }
    }

    public ArrayList<Node<E>> iterate(int type, boolean loop) {
        Bst4Iterator<E> bstItr = new Bst4Iterator<>(root);
        return bstItr.traversal(type, loop);
    }

    public int height() {
        int height = 0;
        if (root == null) {
            return height;
        }
        int levelSize = 1;
        Node<E> node = root;
        LinkedList<Node<E>> q = new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()) {
            Node<E> x = q.poll();
            levelSize--;
            if (x.left != null) {
                q.offer(x.left);
            }
            if (x.right != null) {
                q.offer(x.right);
            }
            if (levelSize == 0) {
                levelSize = q.size();
                height++;
            }
        }
        return height;
    }

    public int height0() {
        return height0(root);
    }

    private int height0(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height0(node.left), height0(node.right)) + 1;
    }

    public void invert() {
       invert(root);
    }

    public void invert(Node<E> node) {
        if (node == null) {
            return;
        }
        Node<E> tmp = node.left;
        node.left   = node.right;
        node.right  = tmp;
        invert(node.left);
        invert(node.right);
    }

    public void invert0() {
        if (root == null) {
            return;
        }
        LinkedList<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (! queue.isEmpty()) {
            Node<E> x = queue.poll();
            Node<E> tmp = x.left;
            x.left  = x.right;
            x.right = tmp;
            if (x.left != null) {
                queue.offer(x.left);
            }
            if (x.right != null) {
                queue.offer(x.right);
            }
        }
    }

    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        LinkedList<Node<E>> q = new LinkedList<>();
        q.offer(root);
        boolean requireLeaf = false;
        while (! q.isEmpty()) {
            Node<E> x = q.poll();
            if (requireLeaf && ! x.isLeaf()) {
                return false;
            }
            if (x.left != null) {
                q.offer(x.left);
            } else { // x.left == null
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

    public Node<E> max() {
        if (root == null) {
            return null;
        }
        Node<E> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public Node<E> max0() {
        return max0(root);
    }

    private Node<E> max0(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return max0(node.right);
    }

    public Node<E> min() {
        if (root == null) {
            return null;
        }
        Node<E> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node<E> min0() {
        return min0(root);
    }

    private Node<E> min0(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return min0(node.left);
    }

    public Node<E> pred(Node<E> node) {
        checkNode(node);
        if (root == null) {
            return null;
        }
        Node<E> x = node.left;
        if (x != null) {
            while (x.right != null) {
                x = x.right;
            }
            return x;
        }
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    public Node<E> succ(Node<E> node) {
        if (root == null) {
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

    public void remove0(Node<E> node) {
        checkNode(node);
        if (root == null) {
            return;
        }
        if (node.hasTwoChildren()) {
            Node<E> predOrSucc = pred(node);
            // Node<E> predOrSucc = succ(node);
            node.element = predOrSucc.element;
            node = predOrSucc;
        }
        // 此时node的度为1 或 0
        Node<E> child = node.left != null ? node.left : node.right;
        // 非叶子
        if (child != null) {
            child.parent = node.parent;
        }
        if (node.parent == null) {
            root = child;
        } else if (node == node.parent.left) {
            node.parent.left = child;
        } else if (node == node.parent.right) {
            node.parent.right = child;
        }
        size--;
        afterRemove(node, child);
    }

    protected void afterRemove(Node<E> node, Node<E> replacement) { }

    public void remove0(E element) {
        remove0(root);
    }

    private Node<E> remove(Node<E> node, E element) {
        if (node == null) {
            return null;
        }
        int cmp = element.compareTo(node.element);
        if (cmp < 0) {
            node.left = remove(node.left, element);
        } else if (cmp > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node<E> t = node;
            node = min0(t.right);
            node.right = removeMin(t.right);
            node.left = t.left;
        }

        return node;
    }

    public void removeMin() {
        checkNode(root);
        root = removeMin(this.root);
    }

    private Node<E> removeMin(Node<E> node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public void removeMax() {
        root = removeMax(root);
    }

    private Node<E> removeMax(Node<E> node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    protected Node<E> createNode(E element) {
        nullCheck(element);
        return new Node<>(element, null, null, null, 1);
    }
    public void nullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("could not be null");
        }
    }
    public void checkNode(Node<E> node) {
        if (node == null) {
            throw new IllegalArgumentException("node can not be null");
        }
    }
    public Node<E> getRoot() {
        return root;
    }
    public void setRoot(Node<E> root) {
        this.root = root;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
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
        return (Node<E>)node;
    }
}
