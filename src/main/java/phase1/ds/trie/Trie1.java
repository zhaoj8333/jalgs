package phase1.ds.trie;

import java.util.HashMap;

/*
    Trie总结：
        优点： 效率与前缀的长度有关
        缺点： 消耗内存
 */
/**
 * @author allen
 */
public class Trie1<V> implements Itrie<V> {
    private int size;
    private Node<V> root = new Node<>(null);

    private static class Node<V> {
        private HashMap<Character, Node<V>> children;
        private V value;      // 单词对应的值
        private boolean end;  // 是否为单词的结尾
        private Node<V> parent;
        private Character character;
        public Node(Node<V> parent) {
            this.parent = parent;
        }
        public HashMap<Character, Node<V>> getChildren() {
            return children == null ? (children = new HashMap<>()) : children;
        }
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }
        public boolean isEnd() {
            return end;
        }
        public void setEnd(boolean end) {
            this.end = end;
        }
        public Character getCharacter() {
            return character;
        }
        public void setCharacter(Character character) {
            this.character = character;
        }
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
        root.children = null;
        size = 0;
    }

    @Override
    public V get(String key) {
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean contains(String key) {
        return node(key) != null;
    }

    @Override
    public V add(String key, V value) {
        keyCheck(key);
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            Node<V> childNode = node.getChildren().get(c);
            if (childNode == null) {
                childNode = new Node<>(node);
                childNode.setCharacter(c);
                node.getChildren().put(c, childNode);
            }
            node = childNode;
        }
        // 新增单词
        if (!node.isEnd()) {
            node.setEnd(true);
            node.value = value;
            size++;
            return null;
        }
        // 覆盖旧值
        V oldVal = node.getValue();
        node.setValue(value);
        return oldVal;
    }

    private Node<V> node(String key) {
        if (root == null) {
            return null;
        }
        keyCheck(key);
        if (root.children == null) {
            return null;
        }
        Node<V> node = root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            node = node.getChildren().get(c);
            if (node == null) {
                return null;
            }
        }
        return node.isEnd() ? node : null;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key cannot be null");
        }
    }

    /**
     * 删除单词
     */
    @Override
    public V remove(String str) {
        Node<V> node = node(str);
        // 不以该单词结尾，不作任何处理
        if (node == null || !node.isEnd()) {
            return null;
        }
        size--;
        V old = node.value;
        if (node.children != null && !node.children.isEmpty()) {
            node.setEnd(false);
            node.value = null;
            return old;
        }
        // 没有子节点: 从后往回删，直到发现某节点还有子节点
        Node<V> parent = null;
        while ((parent = node.parent) != null) {
            parent.children.remove(node.getCharacter());
            if (!parent.children.isEmpty()) {
                break;
            }
            node = parent;
        }
        return old;
    }

    @Override
    public boolean startsWith(String prefix) {
        keyCheck(prefix);
        Node<V> node = root;
        int len = prefix.length();
        for (int i = 0; i < len; i++) {
            char c = prefix.charAt(i);
            node = node.getChildren().get(c);
            if (node == null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        test1();
    }

    @Override
    public String toString() {
        return "Trie1{" +
                "size=" + size +
                ", root=" + root +
                '}';
    }

    private static void test1() {
        Trie1<Integer> trie = new Trie1<>();
        trie.add("cat", 1);
        trie.add("dog", 2);
        trie.add("catalog", 3);
        trie.add("cast", 4);
        trie.add("阿斯顿发", 5);
        trie.add("cat", 6);
        trie.add("cat", 7);
        System.out.println(trie);
        System.out.println("size: " + trie.size());
//        System.out.println("c   : " + trie.contains("c"));
//        System.out.println("ca  : " + trie.contains("ca"));
//        System.out.println("cat : " + trie.contains("cat"));
//        System.out.println("cata: " + trie.contains("cata"));
//        System.out.println("阿斯 : " + trie.contains("阿斯"));
        System.out.println("c   : " + trie.startsWith("c"));
        System.out.println("ca  : " + trie.startsWith("ca"));
        System.out.println("cat : " + trie.startsWith("cat"));
        System.out.println("cata: " + trie.startsWith("cata"));
        System.out.println("阿斯 : " + trie.startsWith("阿斯"));

        trie.remove("cast");
        System.out.println("cata: " + trie.contains("cata"));

    }
}
