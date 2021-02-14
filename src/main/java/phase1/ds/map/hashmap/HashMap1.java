package phase1.ds.map.hashmap;

import phase1.ds.map.Map;
import phase1.ds.tree.printer.BinaryTreeInfo;
import phase1.ds.tree.printer.BinaryTrees;
import java.util.*;

/**
 * @author allen
 */
public class HashMap1<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;
    private Node<K, V>[] table;
    private static final int DEFAULT_CAPACITY = 1 << 4;

    public HashMap1() {
        table = new Node[DEFAULT_CAPACITY];
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
    public void clear() {
        if (size == 0) {
            return;
        }
        Arrays.fill(table, null);
        size = 0;
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
            table[index(grand)] = parent;
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

    private int compare(K k1, K k2, int h1, int h2) {
        int res = h1 - h2;
        if (res != 0) {
            return res;
        }
        // 比较 equals
        boolean equal = Objects.equals(k1, k2);
        if (equal) {
            return 0;
        }
        // phase1.algs.hash 相等，但是不equals
        // 比较类名
        if (k1 != null && k2 != null) {
            String k1Class = k1.getClass().getName();
            String k2Class = k2.getClass().getName();
            res = k1Class.compareTo(k2Class);
            if (res != 0) {
                return res;
            }
            // 同一种类型
            if (k1 instanceof Comparable) {
                return ((Comparable)k1).compareTo(k2);
            }
        }
        // 同一种类型，但是不具备可比较性
        // k1 不为null， k2为null
        // k1为null，k2不为null
        // 利用内存地址计算hashcode
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    @Override
    public V put(K key, V value) {
        int index = index(key);
        Node<K, V> root = table[index];
        if (root == null) {
            root = new Node<>(key, value, null);
            table[index] = root;
            size ++;
            afterPut(root);
            return null;
        }
        // 产生hash冲突， 添加新的节点到红黑树
        Node<K, V> node   = root;
        Node<K, V> parent = root;
        int cmp;
        // 查找被插入节点的父节点
        int h1 = key == null ? 0 : key.hashCode();
        do {
            cmp = compare(key, node.key, h1, node.hash);
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
        } while (node != null);
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
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    public Node<K, V> node(K key) {
        Node<K, V> node = table[index(key)];
        int cmp;
        int h1 = key == null ? 0 : key.hashCode();
        while (node != null) {
            cmp = compare(key, node.key, h1, node.hash);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    public Node<K, V> pred(Node<K, V> node) {
        if (node == null) {
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

    public V remove(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.hasTwoChildren()) {
            Node<K, V> predOrSucc = pred(node);
            // Node<E> predOrSucc = succ(node);
            node.key = predOrSucc.key;
            node.value = predOrSucc.value;
            node = predOrSucc;
        }
        // 此时node的度为1 或 0
        Node<K, V> child = node.left != null ? node.left : node.right;
        int index = index(node);
        // 非叶子
        if (child != null) {
            child.parent = node.parent;
        }
        if (node.parent == null) {
            table[index] = child;
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

    @Override
    public boolean containsValue(V value) {
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (table[i] == null) {
                continue;
            }
            Queue<Node<K, V>> q = new LinkedList<>();
            q.offer(table[i]);
            while (! q.isEmpty()) {
                Node<K, V> node = q.poll();
                if (Objects.equals(value, node.value)) {
                    return true;
                }
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        // hashmap存储没有顺序
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            if (table[i] == null) {
                continue;
            }
            Queue<Node<K, V>> q = new LinkedList<>();
            q.offer(table[i]);
            while (! q.isEmpty()) {
                Node<K, V> node = q.poll();
                System.out.println("ele: " + node);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
    }

    /**
     * 根据key生成对应的索引（在数组中的位置）
     */
    private int index(K key) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        hash = hash ^ (hash >>> 16);
        return hash & (table.length - 1);
    }
    private int index(Node<K, V> node) {
        return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
    }
    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left, right, parent;
        public Node(K key, V value, Node<K, V> parent) {
            this.hash   = key == null ? 0 : key.hashCode();
            this.key    = key;
            this.value  = value;
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
        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
    private static class Person {
        private String name;
        private int age;
        private boolean sex;
        public Person(String name, int age, boolean sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
        @Override
        public String toString() {
            return "(" + name + "_" + age + "_" + sex + ")";
        }
        @Override
        public int hashCode() {
//            默认hashcode采用内存地址
//            return super.hashCode();
            int hash = Integer.hashCode(age);
            int nameHash = name != null ? name.hashCode() : 0;
            hash = hash * 31 + nameHash;
            int sexHash  = Boolean.hashCode(sex);
            // Boolean.hashCode();
            /*
                true:  1231
                false: 1237
                two sufficiently large arbitrary prime number
             */
//            System.out.println("bool phase1.algs.hash: true " + Integer.toBinaryString(1231));  // 100 11001111
//            System.out.println("bool phase1.algs.hash: false " + Integer.toBinaryString(1237)); // 100 11010101
            hash = hash * 31 + sexHash;
//            31:  不仅符合2^n - 1, 而且是一个奇数， 有是 素数
//            素数与其他数相乘的结果更容易产生唯一性，减少哈希冲突
            return hash;
        }
        /**
         * 当哈希冲突时会被调用,用于比较两个key是否相等
         * 默认的equals是对比内存地址
         *
         * hash值相等不代表两个对象相等,两个性质不同的对象计算出的hash值可能是一致的
         * hash值不同，计算出的索引值可能是一样的
         * 所以必须实现equals方法
         * equals在产生hash冲突时对比key是否一致时调用
         *
         * <p>
         * equals与hashcode最好一起都实现
         * <p>
         * shallow comparison: The default implementation of equals in java.lang.Object simply checks if two references refer to the same object
         * deep comparison:  Comparision based on data members
         * <p>
         * 对象指甲用equals
         * 基本类型之间用 ==
         * <p>
         * 性质：
         * 自反性
         * 对称性
         * 传递性
         * 一致性
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            // hashcode一致不代表两个对象相等，不能通过hashcode的比较而进行比较
            // 不同性质的甚至相同的对象 hashcode可能会相等
            // hashcode一致，计算出的索引也有可能一致

            // 比较成员变量
            Person person = (Person) o;
            return age == person.age && sex == person.sex && Objects.equals(name, person.name);
        }
    }
    public void print() {
        if (size == 0) {
            return;
        }
        for (int i = 0; i < size; i++) {
            final Node<K, V> root = table[i];
            System.out.println("【 index: " + i + "】");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return root;
                }
                @Override
                public Object left(Object node) {
                    return ((Node<K, V>)node).left;
                }
                @Override
                public Object right(Object node) {
                    return ((Node<K, V>)node).right;
                }
                @Override
                public Object string(Object node) {
                    return node;
                }
            });
            System.out.println();
        }
    }
    @Override
    public String toString() {
        return Arrays.toString(table);
    }
    public static void main(String[] args) {
//        testMap();
        testHashcode();
    }

    private static void testHashcode() {
        Person p1 = new Person("aaa", 13, true);
        Person p2 = new Person("aaa", 13, true);
        System.out.println("p1 phase1.algs.hash: " + p1.hashCode());
        System.out.println("p2 hahs: " + p2.hashCode());
        Map<Object, Object> map = new HashMap1<>();
        map.put(p1, "abc");
        map.put(p2, "abc");
        System.out.println("size : " + map.size());
    }

    private static void testMap() {
        HashMap1<Object, Integer> mp = new HashMap1<>();
        Person p1 = new Person("a", 1, true);
        Person p2 = new Person("a", 1, true);
        mp.put(p1, 1);
        mp.put(p2, 2);
        mp.put("a", 3);
        mp.put("b", 4);
        mp.put("c", 5);
        mp.put(null, 6);

        mp.remove("b");
//        System.out.println("size : " + mp.size());
//        System.out.println("map  : " + mp);
//        System.out.println("containsKey: ");
//        System.out.println(mp.containsKey("b"));
//        System.out.println(mp.containsKey("a"));
//        System.out.println(mp.containsKey(null));

//        System.out.println("containsValue: ");
//        System.out.println(mp.containsValue(3));
//        System.out.println(mp.containsValue(10));

        mp.traversal(null);
        //        System.out.println(mp.get("a"));
//        System.out.println(mp.get("b"));
//        System.out.println(mp.get("c"));
//        System.out.println(mp.get(null));
        System.out.println("=======================================");
        System.out.println("table: " + Arrays.toString(mp.table));
        mp.print();
    }
}
