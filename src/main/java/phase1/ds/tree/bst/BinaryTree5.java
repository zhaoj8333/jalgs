package phase1.ds.tree.bst;

import phase1.ds.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree5<E extends Comparable<E>> implements BinaryTreeInfo {
    private Node5<E> root;
    private int size;
    private int height;

    private int count() {
        return size(root);
    }

    private int size(Node5<E> node) {
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
        this.root = null;
        size = 0;
    }

    // 是否为完全二叉树
    public boolean isComplete() {
        if (root == null) {
            return false;
        }
        Queue<Node5<E>> q = new LinkedList<>();
        q.offer(root);
        boolean requireleaf = false;
        while (! q.isEmpty()) {
            final Node5<E> node = q.poll();
            if (requireleaf && ! node.isLeaf()) {
                return false;
            }
            //
            if (node.left != null) {
                q.offer(node.left);
            } else {
                if (node.right != null) {
                    return false;
                }
            }

            if (node.right != null) {
                q.offer(node.right);
            } else {
                requireleaf = true;
            }
        }
        return true;
    }

    public boolean isComplete0() {
        if (root == null) {
            return false;
        }
        Queue<Node5<E>> q = new LinkedList<>();
        q.offer(root);
        boolean requireleaf = false;
        while (! q.isEmpty()) {
            final Node5<E> node = q.poll();
            if (requireleaf && ! node.isLeaf()) {
                return false;
            }
            if (node.left != null && node.right != null) {
                q.offer(node.left);
                q.offer(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else { // 之后的节点必须是叶子
                requireleaf = true;
            }
        }
        return true;
    }

    public int getHeight() {
        int height = 0;
        if (root == null) {
            return height;
        }
        int levelSize = 1;
        Queue<Node5<E>> q = new LinkedList<>();
        q.offer(root);
        while (! q.isEmpty()) {
            final Node5<E> node = q.poll();
            levelSize--;
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = q.size();
                height++;
            }
        }
        return height;
    }

    public int getHeight0() {
        return getHeight0(root);
    }

    public int getHeight0(Node5<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight0(node.left), getHeight0(node.right));
    }

    // 从上到下, 从左到右 依次访问
    public void levelOrderTraversal() {
        Queue<Node5<E>> q = new LinkedList<>();
        q.offer(root);
        while (! q.isEmpty()) {
            final Node5<E> head = q.poll();
            System.out.print(head.element + " ");
            if (head.left != null) {
                q.offer(head.left);
            }
            if (head.right != null) {
                q.offer(head.right);
            }
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
//        preOrderTraversal0();
    }

    public void midOrderTraversalAsc() {
        System.out.print("midOrder: ");
        midOrderTraversalAsc(root);
        System.out.println();
        System.out.print("midOrder: ");
        midOrderTraversalDesc(root);
    }

    // 左 -> 右 -> 中
    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    public void postOrderTraversal(Node5<E> node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.element + " ");
    }

    // 左 -> 根 -> 右
    public void midOrderTraversalAsc(Node5<E> node) {
        if (node == null) {
            return;
        }
        midOrderTraversalAsc(node.left);
        System.out.print(node.element + " ");
        midOrderTraversalAsc(node.right);
    }

    public void midOrderTraversalDesc(Node5<E> node) {
        if (node == null) {
            return;
        }
        midOrderTraversalDesc(node.right);
        System.out.print(node.element + " ");
        midOrderTraversalDesc(node.left);
    }

    // 根 -> 左 -> 右
    public void preOrderTraversal(Node5<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.element + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public void put(E element) {
//        put0(element);
        root = put0(element, root);
    }

    private void put0(E element) {
        final Node5<E> newNode = createNode(element);
        if (root == null) {
            root = newNode;
            size = 1;
            afterPut(root);
            return;
        }
        Node5<E> parent = root;
        Node5<E> node = root;
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
        updateCount(newNode);
        size++;
        afterPut(newNode);
    }

    private Node5<E> put0(E element, Node5<E> node) {
        if (node == null) {
            return createNode(element);
        }
        int cmp = element.compareTo(node.element);
        if (cmp == 0) {
            node.element = element;
        } else if (cmp < 0) {
            node.left = put0(element, node.left);
        } else {
            node.right = put0(element, node.right);
        }
        return node;
    }

    private void updateCount(Node5<E> node) {
        while (node != null) {
            node.count++;
            node = node.parent;
        }
    }

    protected void afterPut(Node5<E> node) {}

    private Node5<E> createNode(E element) {
        nullCheck(element);
        return new Node5<>(element, null, null, null, 1);
    }

    private void nullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("could not be null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node5<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node5<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }
}
