package phase1.ds.tree.re.bst;

import phase1.ds.tree.printer.BinaryTreeInfo;
import phase1.ds.tree.re.Node;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BinaryTree<E extends Comparable<E>> implements IBinaryTree<E>, BinaryTreeInfo {

    protected int size;

    protected Node<E> root;

    protected final Comparator<E> comparator = Comparable::compareTo;

    protected final TreeTraverser<E> treeTraverser = new TreeTraverser<E>((node) -> {
        System.out.print(node.toString() + ' ');
        return node;
    });

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Node<E> left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Node<E> right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public String string(Object node) {
        return ((Node<E>)node).element.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void add(E element) {
        Objects.requireNonNull(element, "element can't be null");
        if (Objects.isNull(root)) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        Node<E> node = root, parent = root;
        int cmp = 0;
        while (Objects.nonNull(node)) {
            parent = node;
            cmp = Objects.compare(element, node.element, comparator);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node.element = element;
                return;
            }
        }
        Node<E> newNode = new Node<>(element, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    protected void afterAdd(Node<E> node) {
    }

    public Node<E> get(E element) {
        Objects.requireNonNull(element);
        if (Objects.isNull(root)) {
            return null;
        }
        Node<E> node = this.root;
        while (Objects.nonNull(node)) {
            int cmp = element.compareTo(node.element);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * 删除节点：
     *  1. 叶子节点：直接删除
     *  2. 度为1的节点：用child代替node
     *  3. 度为2的节点：使用前驱或后继节点覆盖node的值，删除响应的前驱或后继节点，度为2的节点的前驱、后继节点的度只可能是1或0
     */
    @Override
    public void remove(E element) {
        Node<E> node = this.get(element);
        if (Objects.isNull(node)) {
            return;
        }
        if (node.hasTwoChildren()) {
            Node<E> succ = this.succ(node);
            node.element = succ.element;
            node = succ;
        }
        Node<E> replacement = Objects.nonNull(node.left) ? node.left : node.right;
        if (Objects.isNull(node.parent)) {
            root = replacement;
        } else if (node.isLeft()) {
            node.parent.left = replacement;
        } else if (node.isRight()) {
            node.parent.right = replacement;
        }
        if (Objects.nonNull(replacement)) {
            replacement.parent = node.parent;
        }
        size--;
        afterRemove(node);
    }

    protected void afterRemove(Node<E> node) {}

    @Override
    public void traversal() {
        System.out.println("prev:");
        this.treeTraverser.prevOrder(root);
        System.out.println("\nmid:");
        this.treeTraverser.midOrder(root);
        System.out.println("\npost:");
        this.treeTraverser.postOrder(root);
        System.out.println("\nlevel:");
        this.treeTraverser.levelOrder(root);
    }

    @Override
    public int height() {
        int height = 0;
        if (Objects.isNull(root)) {
            return height;
        }
        int levelSize = 1;
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node<E> node = q.poll();
            levelSize--;
            if (Objects.nonNull(node.left)) {
                q.offer(node.left);
            }
            if (Objects.nonNull(node.right)) {
                q.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = q.size();
                height++;
            }
        }
        return height;
    }

    @SuppressWarnings("unused")
    public int height0(Node<E> node) {
        if (Objects.isNull(node)) {
            return 0;
        }
        return Math.max(height0(node.left), height0(node.right)) + 1;
    }

    @Override
    public boolean isComplete() {
        if (Objects.isNull(root)) {
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
            if (Objects.nonNull(node.left)) {
                q.offer(node.left);
            } else if (Objects.nonNull(node.right)) {
                return false;
            }
            if (Objects.nonNull(node.right)) {
                q.offer(node.right);
            } else {
                requireLeaf = true;
            }
        }
        return true;
    }

    /**
     * 前驱结点： 中序遍历的前一个节点,左子树最右节点
     */
    public Node<E> pred(Node<E> node) {
        if (Objects.isNull(node)) return null;
        // node.left != null, pred = node.left.right.right....
        Node<E> curr = node.left;
        if (Objects.nonNull(curr)) {
            while (Objects.nonNull(curr.right)) {
                curr = curr.right;
            }
            return curr;
        }
        // node.left == null, pred = node.parent.parent ... 直至node在parent的左子树中
        while (Objects.nonNull(node.parent) && node.isLeft()) {
            node = node.parent;
        }
        // return null
        return node.parent;
    }

    /**
     * 后继节点： 中序遍历的后一个节点，右子树最小节点
     */
    @SuppressWarnings("all")
    public Node<E> succ(Node<E> node) {
        if (Objects.isNull(node)) return null;
        // node.right != null, succ = node.right.left.left...
        Node<E> curr = node.right;
        if (Objects.nonNull(curr)) {
            while (Objects.nonNull(curr.left)) {
                curr = curr.left;
            }
            return curr;
        }
        // node.right == null, succ = node.parent.parent ... 直至node在parent的右子树中
        while (Objects.nonNull(node.parent) && node.isRight()) {
            node = node.parent;
        }
        return node.parent;
    }

    @Override
    public void reverse() {
//        reverse0(root);
//        reverse1(root);
//        reverse2(root);
        reverse(root);
    }

    /**
     * reverse by levelOrder traversal
     */
    private void reverse(Node<E> node) {
        if (Objects.isNull(node)) {
            return;
        }
        Queue<Node<E>> q = new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()) {
            Node<E> curr = q.poll();
            Node<E> temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if (Objects.nonNull(curr.left)) {
                q.offer(curr.left);
            }
            if (Objects.nonNull(curr.right)) {
                q.offer(curr.right);
            }
        }
    }

    @SuppressWarnings({"unused"})
    public Node<E> reverse0(Node<E> node) {
        if (Objects.isNull(node)) {
            return null;
        }
        swap(node);
        reverse0(node.left);
        reverse0(node.right);
        return node;
    }

    @SuppressWarnings("unused")
    public Node<E> reverse1(Node<E> node) {
        if (Objects.isNull(node)) {
            return null;
        }
        reverse0(node.left);
        swap(node);
        reverse0(node.right);
        return node;
    }

    private void swap(Node<E> node) {
        Node<E> temp = node.left;
        node.left  = node.right;
        node.right = temp;
    }

    @SuppressWarnings("unused")
    public Node<E> reverse2(Node<E> node) {
        if (Objects.isNull(node)) {
            return null;
        }
        reverse0(node.left);
        reverse0(node.right);
        swap(node);
        return node;
    }
}
