package phase1.ds.skip_list;

import java.util.Comparator;

/**
 * 有序链表增删改的复杂度 O(n)
 *
 * 跳表: 使用二分搜索优化有序链表增删改, 优化至O(logN)
 *
 * 跳表的设计初衷是为了替换 平衡树(红黑树)
 *
 * 跳表应用:
 *     redis中的SortedSet, LevelDB中的MemTable
 *
 * 对比平衡树的优势:
 *     跳表的实现和维护更加简单
 *     跳表搜索,删除,添加平均复杂度为O(logN)
 *
 * 跳表总结:
 *    1. 跳表按层构造, 底层是一个普通有序链表, 高层相当于底层的快速通道
 *    2. 第i层元素按照某个固定概率p(1/2或1/4)出现在第i+1层中, 产生越高的层数,概率越低
 *      元素层数恰好等于1的概率为1-p
 *      元素层数大于2概率为p, 元素恰好等于2的概率为 p*(1-p)
 */
@SuppressWarnings("all")
public class SkipList<K, V> {
    // 跳表高度限制
    private static final int MAX_LEVEL = 32;
    private static final double P = 0.25;
    // 有效层数
    private int level;
    private int size;
    private Comparator<K> comparator;
    /**
     * 首节点不存放任何K-V
     */
    private Node<K, V> first;

    public SkipList() {
        this(null);
    }

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        first  = new Node<>(null, null, MAX_LEVEL);
        first.nexts = new Node[MAX_LEVEL];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V val) {
        keyCheck(key);
        Node<K, V> node = first;
        // 每一层的前驱节点
        Node<K, V>[] prevs = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) { // key存在, 替换旧值
                V old = node.nexts[i].val;
                node.nexts[i].val = val;
                return old;
            }
            // 往下走的节点都是 新插入节点 的前驱节点
            prevs[i] = node;
        }
        // 添加新节点
        int newLevel = randomLevel();
        Node<K, V> newNode = new Node<>(key, val, newLevel);
        for (int i = 0; i < newLevel; i++) {
            if (i >= level) {
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prevs[i].nexts[i];
                prevs[i].nexts[i] = newNode;
            }
        }
        size++;
        // 计算跳表最终层数
        level = Math.max(level, newLevel);
        return null;
    }

    private int randomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    /**
     * 跳表的搜索:
     *     1. 从顶层链表首元素开始, 从左到右, 直至找到一个大于或等于目标的元素 或者到达当前层链表的尾部
     *     2. 如果该元素等于目标元素, 则表明该元素已被找到
     *     3. 如果该元素大于目标元素或到达链表尾部,则退回到当前层的前一个元素, 然后转入下一层进行搜索
     */
    public V get(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                return node.nexts[i].val;
            }
        }
        return null;
    }

    public V remove(K key) {
        keyCheck(key);
        Node<K, V> node = first;
        Node<K, V>[] prevs = new Node[level];
        boolean exists = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            prevs[i] = node;
            if (cmp == 0) {
                exists = true;
            }
        }
        if (!exists) {
            return null;
        }
        Node<K, V> removedNode = node.nexts[0];
        // 设置后继
        for (int i = 0; i < removedNode.nexts.length; i++) {
            prevs[i].nexts[i] = removedNode.nexts[i];
        }
        // 更新层数
        int newLevel = this.level;
        while (--newLevel >= 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }
        size--;
        return removedNode.val;
    }

    private void keyCheck(K k) {
        if (k == null) {
            throw new IllegalArgumentException("key不能为null");
        }
    }

    private int compare(K a, K b) {
        return comparator != null ? comparator.compare(a, b) : ((Comparable<K>) a).compareTo(b);
    }

    private static class Node<K, V> {
        K key;
        V val;
        Node<K, V>[] nexts;

        public Node(K key, V val, int level) {
            this.key = key;
            this.val = val;
            this.nexts = new Node[level];
        }

        @Override
        public String toString() {
            return "{" + key + ":" + val + "_" + nexts.length + "}";
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("level: " + level).append("\n");
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = first;
            while (node.nexts[i] != null) {
                sb.append(node.nexts[i]);
                sb.append(" ");
                node = node.nexts[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final SkipList<Integer, Integer> list = new SkipList<>();
        test(list, 20, 10);
    }

    private static void test(SkipList<Integer, Integer> list, int count, int delta) {
        for (int i = 0; i < count; i++) {
            list.put(i, i + delta);
        }
        System.out.println(list);
        for (int i = 0; i < count; i++) {
//            Assert.assertEquals((int) list.get(i), i + delta);
        }
//        Assert.assertEquals(list.size(), count);
        for (int i = 0; i < list.size(); i++) {
//            Assert.assertEquals((int) list.remove(i), i + delta);
        }
//        Assert.assertEquals(list.size, 0);
    }
}
