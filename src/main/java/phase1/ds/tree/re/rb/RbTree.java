package phase1.ds.tree.re.rb;

import phase1.ds.tree.printer.BinaryTreeInfo;
import phase1.ds.tree.re.bst.IBinaryTree;
import phase1.ds.tree.re.bst.TreeTraverser;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 红黑树没有平衡因子的概念
 *
 * 红黑树性质：
 *     1. 节点是RED或BLACK
 *     2. 根节点为BLACK
 *     3. 叶子节点(外部节点/空节点)都是BLACK
 *     4. RED节点的parent都是BLACK
 *        从根节点到叶子节点的所有路径上不能有两个连续的RED节点
 *     5. 从任一节点到叶子节点的所有路径都包含相同的BLACK节点
 *
 *     这些规则下，可以保证红黑树的平衡, 红黑树的平衡标准比较宽松，没有一条路径大于其他路径的2倍
 *      极端情况下：最短的路径上全是黑色，最长的路径多出来的都是红色
 *      所以红黑树的平衡是一种弱平衡，也就是黑高度的平衡
 *
 * 红黑树高度：
 *     2 * log2(N+1)，依旧是O(logN)级别
 *
 * Avl树复杂度：
 *     搜索，添加，删除都是O(logN)复杂度，其中添加仅需要O(1)次旋转
 *     删除最多需要O(logN)次旋转
 *
 * 红黑树复杂度：
 *     搜索：O(logN)
 *     添加：O(logN),O(1)次旋转操作
 *     删除：O(logN),O(1)次旋转操作
 *
 *     AVL树的添加和删除操作最坏情况在O(1)次旋转操作
 *     RB树的添加中最多2次旋转操作，而删除中，递归调用afterRemove而导致的旋转操作是不存在的，据统计，旋转操作最多不会超过三次
 *     红黑树通过牺牲了一定的平衡性，换取少量的旋转操作，整体上性能优于AVL树，实际引用中使用红黑树
 */
@SuppressWarnings("all")
public class RbTree<E extends Comparable<E>> implements IBinaryTree<E>, BinaryTreeInfo {

    protected int size;

    protected RbNode<E> root;

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
    public RbNode<E> left(Object node) {
        return ((RbNode<E>)node).left;
    }

    @Override
    public RbNode<E> right(Object node) {
        return ((RbNode<E>)node).right;
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
    public void clear() { }

    @Override
    public void add(E element) {
        Objects.requireNonNull(element, "element can't be null");
        RbNode<E> newNode;
        if (Objects.isNull(root)) {
            newNode = root = new RbNode<>(element, null);
            size++;
        } else {
            RbNode<E> node = root, parent = root;
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
            newNode = new RbNode<>(element, parent);
            if (cmp < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            size++;
        }
        afterAdd(newNode);
    }

    public RbNode<E> get(E element) {
        Objects.requireNonNull(element);
        if (Objects.isNull(root)) {
            return null;
        }
        RbNode<E> node = this.root;
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

    @Override
    public void remove(E element) {
        RbNode<E> node = this.get(element);
        if (Objects.isNull(node)) {
            return;
        }
        if (node.hasTwoChildren()) {    // 度为2的节点
            RbNode<E> pred = this.pred(node);
            node.element = pred.element;
            node = pred;
        }
        boolean left = node.parent.isLeft();
        RbNode<E> replacement = Objects.nonNull(node.left) ? node.left : node.right;
        if (Objects.nonNull(replacement)) { // node度为1
            replacement.parent = node.parent;
            if (Objects.isNull(node.parent)) {
                root = replacement;
            } else if (node.isLeft()) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            afterRemove(replacement);
        } else if (Objects.isNull(node.parent)) {
            root = null;
            afterRemove(node);
        } else {    // 叶子节点
            if (node.isLeft()) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
        // 此处不能设置 node.parent = null; 因为afterRemove中的平衡操作还会用到
        size--;
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
        Queue<RbNode<E>> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            RbNode<E> node = q.poll();
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
    public int height0(RbNode<E> node) {
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
        Queue<RbNode<E>> q = new LinkedList<>();
        q.offer(root);
        boolean requireLeaf = false;
        while (!q.isEmpty()) {
            RbNode<E> node = q.poll();
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

    @Override
    public void reverse() { }

    /**
     * 前驱结点： 中序遍历的前一个节点,左子树最右节点
     */
    public RbNode<E> pred(RbNode<E> node) {
        if (Objects.isNull(node)) return null;
        // node.left != null, pred = node.left.right.right....
        RbNode<E> curr = node.left;
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
    public RbNode<E> succ(RbNode<E> node) {
        if (Objects.isNull(node)) return null;
        // node.right != null, succ = node.right.left.left...
        RbNode<E> curr = node.right;
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

    /**
     * 有4种情况满足红黑树的性质4，同样也满足4阶B树的性质，不做额外处理
     *    parent为BLACK： 4种情况
     *
     * 剩下8种情况不满足红黑树的性质,所以也是需要进行调整的几种情况
     *    parent为RED：(Double Red)
     *
     * @param node 被添加节点
     */
    protected void afterAdd(RbNode<E> node) {
        if (Objects.isNull(node.parent)) {
            RbNode.black(root);
            return;
        }
        RbNode<E> parent = node.parent;
        if (RbNode.isBlack(parent)) {
            return;
        }
        // parent为RED的情况
        //  需要进行上溢的情况, uncle为RED
        RbNode<E> grand = parent.parent;
        RbNode<E> uncle = node.uncle();
        if (RbNode.isRed(uncle)) {
            RbNode.black(parent);
            RbNode.black(uncle);
            afterAdd(RbNode.red(grand));
            return;
        }
        //  需要进行旋转的情况, uncle为BLACK
        if (parent.isLeft()) {
            if (node.isLeft()) {    // LL
                RbNode.black(parent);
            } else {    // LR
                RbNode.black(node);
                rotateLeft(parent);
            }
            rotateRight(RbNode.red(grand));
        } else if (parent.isRight()) {
            if (node.isLeft()) {    // RL
                RbNode.black(node);
                rotateRight(parent);
            } else {    // RR
                RbNode.black(parent);
            }
            rotateLeft(RbNode.red(grand));
        }
    }

    private void rotateLeft(RbNode<E> grand) {
        RbNode<E> parent = grand.right;
        RbNode<E> child  = parent.left;

        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    private void rotateRight(RbNode<E> grand) {
        RbNode<E> parent = grand.left;
        RbNode<E> child  = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    private void afterRotate(RbNode<E> grand, RbNode<E> parent, RbNode<E> child) {
        parent.parent = grand.parent;
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
    }

    /**
     * 删除节点
     *
     * 被删除的节点都是最底层的节点
     *
     *  1. 删除RED节点：不会违背红黑树的性质，不做进一步处理
     *        如果被删除的RED为叶子节点，直接删除
     *        如果被删除的RED为非叶子节点，定会查找其前期或后继节点，具体的删除逻辑得取决于该前驱或后继节点的性质，要么是1的情况，要么是2的情况
     *
     *  2. 删除黑色节点：
     *      拥有两个RED子节点的black节点，不会被直接删除，会使用其子节点/或子子节点...（红色节点）替代删除，会转化为1的情况
     *      拥有一个RED子节点的black节点： 判定条件为，用以替代的子节点是RED
     *          将替代的子节点染黑，即可保持红黑树性质
     *      BLACK叶子节点：
     *
     * @param node 被删除的节点 或 被删除节点的子节点(度为1)
     */
    protected void afterRemove(RbNode<E> node) {
        // if (RbNode.isRed(node)) return; // 删除的是红色的叶子节点，被删除之后被染黑，不会受到影响
        // 被删除的是黑色
        if (RbNode.isRed(node)) {   // 如果被删除的是黑色节点且取代该节点的颜色为红色，node会被传参为replacement，染黑即可
            RbNode.black(node);
            return;
        }
        // 删除的是黑色叶子节点
        //  该节点为根节点时
        RbNode<E> parent = node.parent;
        if (Objects.isNull(parent)) return;
        // 处理下溢：从 兄弟节点借元素 具有RED子节点的BLACK sibling：兄弟节点借出一个子节点，并进行旋转操作
        boolean left = Objects.isNull(parent.left) || node.isLeft();
        RbNode<E> sibling = left ? parent.right : parent.left;
        if (left) {
            if (RbNode.isRed(sibling)) {
                RbNode.black(sibling);
                RbNode.red(parent);
                rotateLeft(parent);
                sibling = parent.right;
            }
//            if (Objects.nonNull(sibling)) {
                // 兄弟节点必然是黑色
                if (RbNode.isBlack(sibling.left) && RbNode.isBlack(sibling.right)) { // 兄弟没有红色子节点,父节点要向下合并
                    boolean parentBlack = RbNode.isBlack(parent);
                    RbNode.black(parent);
                    RbNode.red(sibling);
                    if (parentBlack) {
                        afterRemove(parent);
                    }
                } else { // 兄弟节点至少有一个红色子节点，向兄弟节点借元素
                    if (RbNode.isBlack(sibling.right)) {
                        rotateRight(sibling);
                        sibling = parent.right;
                    }
                    RbNode.color(sibling, RbNode.colorOf(parent));
                    RbNode.black(parent);
                    RbNode.black(sibling.right);
                    rotateLeft(parent);
                }
//            }
        } else {
            if (RbNode.isRed(sibling)) {
                RbNode.black(sibling);
                RbNode.red(parent);
                rotateRight(parent);
                sibling = parent.left;
            }
            // 兄弟节点必然是黑色
//            if (Objects.nonNull(sibling)) {
                if (RbNode.isBlack(sibling.left) && RbNode.isBlack(sibling.right)) { // 兄弟没有红色子节点,父节点要向下合并
                    boolean parentBlack = RbNode.isBlack(parent);
                    RbNode.black(parent);
                    RbNode.red(sibling);
                    if (parentBlack) {
                        afterRemove(parent);
                    }
                } else { // 兄弟节点至少有一个红色子节点，向兄弟节点借元素
                    if (RbNode.isBlack(sibling.left)) {
                        rotateLeft(sibling);
                        sibling = parent.left;
                    }
                    RbNode.color(sibling, RbNode.colorOf(parent));
                    RbNode.black(parent);
                    RbNode.black(sibling.left);
                    rotateRight(parent);
                }
//            }
        }
        //    sibling为BLACK，但是没有RED子节点：父节点下溢并与兄弟节点合并，red(sibling),black(parent)
        //        如果parent为black，会导致parent也下溢，此时只需把parent当做被删除的节点处理即可
        //        能借节点的必然是黑色的兄弟节点，但是black兄弟节点没有红色子节点，此时父节点必须下溢

        //    sibling为RED：
        //        sibling染成BLACK，parent染成RED，parent旋转，回到sibling为黑色的情况
    }

}
