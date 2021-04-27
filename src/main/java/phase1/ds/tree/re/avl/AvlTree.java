package phase1.ds.tree.re.avl;

import phase1.ds.tree.printer.BinaryTreeInfo;
import phase1.ds.tree.re.bst.IBinaryTree;
import phase1.ds.tree.re.bst.TreeTraverser;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * AVL树： 每个节点的平衡因子只可能是 1， 0， -1, 即每个节点的左右子树高度差不超过1
 *
 * 二叉树
 *
 * 添加：O(logN)
 *     最坏情况可能会导致所有祖先节点都失衡；
 *     父节点，非祖先节点都不会失衡
 *
 *     只要让高度最低的失衡节点恢复平衡，整棵树就恢复平衡 O(1)次调整
 *
 * 删除： O(logN)
 *     会导致父节点失衡或祖先节点失衡（只有一个节点会失衡）
 *     但是父节点恢复平衡后，可能导致更高的祖先节点失衡 O(logN)次调整
 *
 * 搜索操作：O(logN)
 *
 */
public class AvlTree<E extends Comparable<E>> implements IBinaryTree<E>, BinaryTreeInfo {

    protected int size;

    protected AvlNode<E> root;

    protected final Comparator<E> comparator = Comparable::compareTo;

    protected final TreeTraverser<E> treeTraverser = new TreeTraverser<>((node) -> {
        System.out.print(node.toString() + ' ');
        return node;
    });

    @Override
    public Object root() {
        return root;
    }

    @Override
    public AvlNode<E> left(Object node) {
        return ((AvlNode<E>)node).left;
    }

    @Override
    public AvlNode<E> right(Object node) {
        return ((AvlNode<E>)node).right;
    }

    @Override
    public String string(Object node) {
        return node.toString();
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
            root = new AvlNode<>(element, null);
            size++;
            return;
        }
        AvlNode<E> node = root, parent = root;
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
        AvlNode<E> newNode = new AvlNode<>(element, parent);
        if (cmp < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    public AvlNode<E> get(E element) {
        Objects.requireNonNull(element);
        if (Objects.isNull(root)) {
            return null;
        }
        AvlNode<E> node = this.root;
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
        AvlNode<E> node = this.get(element);
        if (Objects.isNull(node)) {
            return;
        }
        if (node.hasTwoChildren()) {
            AvlNode<E> succ = this.succ(node);
            node.element = succ.element;
            node = succ;
        }
        AvlNode<E> replacement = Objects.nonNull(node.left) ? node.left : node.right;
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
        // 此处不能设置 node.parent = null; 因为afterRemove中的平衡操作还会用到
        size--;
        afterRemove(node);
    }

    @Override
    public void traversal() {
//        System.out.println("prev:");
//        this.treeTraverser.prevOrder(root);
//        System.out.println("\nmid:");
//        this.treeTraverser.midOrder(root);
//        System.out.println("\npost:");
//        this.treeTraverser.postOrder(root);
//        System.out.println("\nlevel:");
//        this.treeTraverser.levelOrder(root);
    }

    @Override
    public int height() {
        int height = 0;
        if (Objects.isNull(root)) {
            return height;
        }
        int levelSize = 1;
        Queue<AvlNode<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            AvlNode<E> node = q.poll();
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
    public int height0(AvlNode<E> node) {
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
        Queue<AvlNode<E>> q = new LinkedList<>();
        q.offer(root);
        boolean requireLeaf = false;
        while (!q.isEmpty()) {
            AvlNode<E> node = q.poll();
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
    public AvlNode<E> pred(AvlNode<E> node) {
        if (Objects.isNull(node)) return null;
        // node.left != null, pred = node.left.right.right....
        AvlNode<E> curr = node.left;
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
    public AvlNode<E> succ(AvlNode<E> node) {
        if (Objects.isNull(node)) return null;
        // node.right != null, succ = node.right.left.left...
        AvlNode<E> curr = node.right;
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
    private void reverse(AvlNode<E> node) {
        if (Objects.isNull(node)) {
            return;
        }
        Queue<AvlNode<E>> q = new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()) {
            AvlNode<E> curr = q.poll();
            AvlNode<E> temp = curr.left;
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
    public AvlNode<E> reverse0(AvlNode<E> node) {
        if (Objects.isNull(node)) {
            return null;
        }
        swap(node);
        reverse0(node.left);
        reverse0(node.right);
        return node;
    }

    @SuppressWarnings("unused")
    public AvlNode<E> reverse1(AvlNode<E> node) {
        if (Objects.isNull(node)) {
            return null;
        }
        reverse0(node.left);
        swap(node);
        reverse0(node.right);
        return node;
    }

    private void swap(AvlNode<E> node) {
        AvlNode<E> temp = node.left;
        node.left  = node.right;
        node.right = temp;
    }

    @SuppressWarnings("unused")
    public AvlNode<E> reverse2(AvlNode<E> node) {
        if (Objects.isNull(node)) {
            return null;
        }
        reverse0(node.left);
        reverse0(node.right);
        swap(node);
        return node;
    }

    private boolean isBalanced(AvlNode<E> node) {
        return Math.abs(node.balanceFactor()) <= 1;
    }

    protected void afterAdd(AvlNode<E> node) {
        // 添加的节点 node 肯定是 叶子节点
        while (Objects.nonNull(node = node.parent)) {
            updateHeight(node);
            if (!isBalanced(node)) {
                rebalance(node);
                break;
            }
        }
    }

    /**
     * 另一种的思路的平衡
     * @param r 子树的根节点
//     * @param a
     * @param b
     * @param c
     * @param d 让d成为子树的根节点
     * @param e
     * @param f
//     * @param g
     */
    private void rotate(
            AvlNode<E> r,
//            AvlNode<E> a,
            AvlNode<E> b, AvlNode<E> c,
            AvlNode<E> d,
            AvlNode<E> e, AvlNode<E> f
//            AvlNode<E> g
    ) {
        // make d the root of this subtree
        d.parent = r.parent;
        if (r.isLeft()) {
            r.parent.left = d;
        } else if (r.isRight()) {
            r.parent.right = d;
        } else {
            root = d;
        }
        // a - b - c
//        b.left = a;
//        if (Objects.nonNull(a)) {
//            a.parent = b;
//        }
        b.right = c;
        if (Objects.nonNull(c)) {
            c.parent = b;
        }

        // e - f - g
        f.left = e;
        if (Objects.nonNull(e)) {
            e.parent = f;
        }
//        f.right = g;
//        if (Objects.nonNull(g)) {
//            g.parent = f;
//        }
        // b - d - f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;

        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    /**
     * @param grand 高度最低的那个 不平衡节点
     */
    private void rebalance(AvlNode<E> grand) {
        AvlNode<E> parent = grand.tallerChild();
        AvlNode<E> child = parent.tallerChild();
        if (parent.isLeft() && child.isLeft()) { // LL
            rotate(grand, child, child.right, parent, parent.right, grand);
//            rotateRight(grand);
        } else if (parent.isLeft() && child.isRight()) { // LR
//            rotateLeft(parent);
//            rotateRight(grand);
            rotate(grand, parent, child.left, child, child.right, grand);
        } else if (parent.isRight() && child.isLeft()) { // RL
//            rotateRight(parent);
//            rotateLeft(grand);
            rotate(grand, grand, child.left, child, child.right, parent);
        } else { // RR
//            rotateLeft(grand);
            rotate(grand, grand, parent.left, parent, child.left, child);
        }
    }

    private void rotateRight(AvlNode<E> grand) {
        AvlNode<E> parent = grand.left;
        AvlNode<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        parent.parent = grand.parent;
        afterRotate(grand, parent, child);
    }

    private void rotateLeft(AvlNode<E> grand) {
        AvlNode<E> parent = grand.right;
        AvlNode<E> child  = parent.left;

        grand.right = child;
        parent.left = grand;
        parent.parent = grand.parent;
        afterRotate(grand, parent, child);
    }

    private void afterRotate(AvlNode<E> grand, AvlNode<E> parent, AvlNode<E> child) {
        if (grand.isLeft()) {
            grand.parent.left = parent;
        } else if (grand.isRight()) {
            grand.parent.right = parent;
        } else {
            root = parent;
        }
        if (Objects.nonNull(child)) {
            child.parent = grand;
        }
        grand.parent = parent;
        updateHeight(grand);
        updateHeight(parent);
    }

    /**
     * 删除只可能导致父节点失衡
     * 如果失衡的节点恢复平衡后，可能会导致高高层的祖先节点失衡，需要再次恢复平衡，然后有可能导致更高层次的祖先节点失衡
     * 极端情况下，所有祖先节点会进行恢复平衡操作，共O(logn)次调整
     *
     * @param node 被删除的节点
     */
    protected void afterRemove(AvlNode<E> node) {
        while (Objects.nonNull(node = node.parent)) {
            updateHeight(node);
            if (!isBalanced(node)) {
                rebalance(node);
//                break;
            }
        }
    }

    private void updateHeight(AvlNode<E> node) {
        node.updateHeight();
    }
}
