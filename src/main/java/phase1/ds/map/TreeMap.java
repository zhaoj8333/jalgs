package phase1.ds.map;

import java.util.LinkedList;
import java.util.Queue;

public class TreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    protected int size;
    protected Node<K, V> root;

    private static class Node<K, V> {
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left, right, parent;
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
        public boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }
        public boolean isLeaf() {
            return left == null && right == null;
        }
        public boolean hasTowChildren() {
            return left != null && right != null;
        }
        public boolean hasChildren() {
            return this.left != null || this.right != null;
        }
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }
        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }
        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
        public Node<K, V> uncle() {
            if (parent == null) {
                return null;
            }
            return parent.sibling();
        }
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }
    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }
    protected void rotateLeft(Node<K, V> grand) {
        Node<K,V> parent = grand.right;
        Node<K,V> child  = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }
    protected void afterRotate(Node<K,V> grand, Node<K,V> parent, Node<K,V> child) {
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
    protected void rotateRight(Node<K,V> grand) {
        Node<K,V> parent = grand.left;
        Node<K,V> child  = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }
    private boolean colorOf(Node<K, V> node) {
        // 空间点为黑色
        return node == null ? BLACK : ((Node<K, V>)node).color;
    }
    public boolean isBLACK(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }
    public boolean isRED(Node<K, V> node) {
        return colorOf(node) == RED;
    }
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) {
            return null;
        }
        node.color = color;
        return node;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    private void keyNotNull(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key 不能为null");
        }
    }
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    @Override
    public V put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value, null);
            size++;
            afterPut(root);
            return null;
        }
        Node<K, V> node   = root;
        Node<K, V> parent = root;
        int cmp = 0;
        // 查找被插入节点的父节点
        while (node != null) {
            cmp = key.compareTo(node.key);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                return oldValue;
            }
        }
        Node<K, V> newNode = new Node<>(key, value, parent);
        // 查找要插入到父节点的具体位置
        if (cmp > 0) {
            parent.right =  newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterPut(newNode);

        return null;
    }
    public Node<K, V> node(K key) {
        keyNotNull(key);
        if (root == null) {
            return null;
        }
        Node<K, V> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = key.compareTo(node.key);
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
    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node == null ? null : node.value;
    }

    public Node<K, V> pred(Node<K, V> node) {
        if (root == null) {
            return null;
        }
        Node<K, V> x = node.left;
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

    @Override
    public V remove(K key) {
        keyNotNull(key);
        if (root == null) {
            return null;
        }
        Node<K, V> node = node(key);
        if (node.hasTwoChildren()) {
            Node<K, V> predOrSucc = pred(node);
            // Node<E> predOrSucc = succ(node);
            node.key = predOrSucc.key;
            node.value = predOrSucc.value;
            node = predOrSucc;
        }
        // 此时node的度为1 或 0
        Node<K, V> child = node.left != null ? node.left : node.right;
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
        return null;
    }

    protected void afterRemove(Node<K, V> node, Node<K, V> replacement) {
        if (isRed(node)) {
            return;
        }
        if (isRed(replacement)) {
            black(replacement);
            return;
        }
        Node<K, V> parent = node.parent;
        if (parent == null) {
            return;
        }
        // 黑色叶子:
        // 被删除的是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
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
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    private void afterPut(Node<K, V> node) {
        Node<K,V> parent = node.parent;
        if (parent == null) {
            black(node);
            return;
        }
        if (isBlack(parent)) {
            return;
        }
        Node<K,V> uncle = node.uncle();
        // grand都会被染成red
        Node<K,V> grand = red(parent.parent);
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

    @Override
    public boolean containsValue(V value) {
        // 层序遍历 O(n)
        if (root == null) {
            return false;
        }
        Queue<Node<K, V>> q = new LinkedList<>();
        q.offer(root);
        while (! q.isEmpty()) {
            Node<K, V> x = q.poll();
            if (value.equals(x.value)) {
                return true;
            }
            if (x.left != null) {
                q.offer(x.left);
            }
            if (x.right != null) {
                q.offer(x.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor visitor) {
        if (root == null) {
            return;
        }
        Queue<Node<K, V>> q = new LinkedList<>();
        q.offer(root);
        while (! q.isEmpty()) {
            Node<K, V> x = q.poll();
            System.out.println("key: " + x.key + ", val: " + x.value);
            if (x.left != null) {
                q.offer(x.left);
            }
            if (x.right != null) {
                q.offer(x.right);
            }
        }
        return;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("class", 2);
        map.put("public", 3);
        map.put("private", 4);
        map.put("private", 10);

        map.traversal(null);
    }


}
