package phase1.ds.list.skiplist;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;

public class SkipList<K, V> {

    public static void main(String[] args) {
        SkipList<String, Integer> a = new SkipList<>();
        int a1 = a.randomLevel();
        System.out.println(a1);
    }

    private int size;
    private static final int MAX_LEVEL = 32;
    private int level; // 有效层数
    private static final double P = 0.25;

    private Comparator<K> comparator;

    // 虚拟头结点: 什么数据也不放
    private Node<K, V> head;

    public SkipList() {
        this(null);
    }

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        head = new Node<>(null, null, MAX_LEVEL);
    }

    private int compare(K a, K b) {
        return Objects.nonNull(comparator) ? comparator.compare(a, b) : ((Comparable<K>) a).compareTo(b);
    }

    private static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V>[] nexts;

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[level];
        }
    }

    private int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 新添加元素层数  随机
     */
    public V put(K key, V value) {
        Objects.requireNonNull(key);
        Node<K, V> node = this.head;
        Node<K, V>[] prevs = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (Objects.nonNull(node.nexts[i]) && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            // 节点存在时直接返回
            if (cmp == 0) {
                V oldVal = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldVal;
            }
            prevs[i] = node;
        }
        // i == -1 前驱节点为 node
        int newLevel = randomLevel();
        Node<K, V> newNode = new Node<>(key, value, newLevel);
        // 设置前驱和后继
        IntStream.range(0, newLevel).forEach(i -> {
            if (i >= level) {
                head.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prevs[i].nexts[i];
                prevs[i].nexts[i] = newNode;
            }
        });
        size++;
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

    public V get(K key) {
        Objects.requireNonNull(key);
        Node<K, V> node = this.head;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (Objects.nonNull(node.nexts[i]) && (cmp = compare(key, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                return node.nexts[i].value;
            }
        }
        return null;
    }

    public V remove(K key) {
        Objects.requireNonNull(key);
        Node<K, V> node = this.head;
        Node<K, V>[] prevs = new Node[level];
        boolean exists = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (Objects.nonNull(node.nexts[i]) && (cmp = compare(key, node.nexts[i].key)) > 0) {
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
        size--;
        Node<K, V> removedNode = node.nexts[0]; // 到了此处，说明key存在，且node为被删除节点的第0层的前驱
        IntStream.range(0, removedNode.nexts.length).forEach(i -> {
            prevs[i].nexts[i] = removedNode.nexts[i];
        });
        int newLevel = level;
        while (--newLevel >= 0 && head.nexts[newLevel] == null) {
            level = newLevel;
        }
        return removedNode.value;
    }
}
