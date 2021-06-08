package phase1.ds.tree.trie;

import java.util.HashMap;
import java.util.Objects;

/**
 * 与单词的数量无关，与单词长度有关
 */
public class PrefixTree<V> implements Itrie<V> {

    private static class Node<V> {
        private HashMap<Character, Node<V>> children;
        private V value;
        private boolean wordEnd;    // 是否为单词结尾
        private Node<V> parent;
        private Character character;

        public Node(Node<V> parent) {
            this.parent = parent;
        }

        public HashMap<Character, Node<V>> getChildren() {
            return Objects.isNull(children) ? (children = new HashMap<>()) : children;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean isWordEnd() {
            return wordEnd;
        }

        public void setWordEnd(boolean wordEnd) {
            this.wordEnd = wordEnd;
        }

        public Node<V> getParent() {
            return parent;
        }

        public void setParent(Node<V> parent) {
            this.parent = parent;
        }

        public Character getCharacter() {
            return character;
        }

        public void setCharacter(Character character) {
            this.character = character;
        }

    }

    private int size;

    private Node<V> root = new Node<>(null);

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
        root.children.clear();
        size = 0;
    }

    @Override
    public V get(String key) {
        Node<V> node = node(key);
        return Objects.isNull(node) ? null : node.value;
    }

    private Node<V> node(String key) {
        Objects.requireNonNull(key);
        if (Objects.isNull(root) || Objects.isNull(root.getChildren())) {
            return null;
        }
        Node<V> node = this.root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            node = node.getChildren().get(c);
            if (Objects.isNull(node)) {
                return null;
            }
        }
        return node.isWordEnd() ? node : null;
    }

    @Override
    public boolean contains(String str) {
        Node<V> node = node(str);
        return Objects.nonNull(node) && node.isWordEnd();
    }

    @Override
    public V add(String key, V value) {
        Objects.requireNonNull(key);
        Node<V> parent = this.root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i);
            Node<V> child = parent.getChildren().get(c);
            if (Objects.isNull(child)) {
                child = new Node<>(parent);
                child.setCharacter(c);
                parent.getChildren().put(c, child);
            }
            parent = child;
        }
        if (!parent.isWordEnd()) {
            parent.setWordEnd(true);
            parent.value = value;
            size++;
            return null;
        }
        V oldValue = parent.getValue();
        parent.setValue(value);
        return oldValue;
    }

    @Override
    public V remove(String str) {
        Objects.requireNonNull(str);
        if (Objects.isNull(root) || Objects.isNull(root.getChildren())) {
            return null;
        }
        return null;
    }

    @Override
    public boolean startsWith(String prefix) {
        Objects.requireNonNull(prefix);
        if (Objects.isNull(root) || Objects.isNull(root.getChildren())) {
            return false;
        }
        int len = prefix.length();
        Node<V> node = this.root;
        for (int i = 0; i < len; i++) {
            char c = prefix.charAt(i);
            node = node.getChildren().get(c);
            if (Objects.isNull(node)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PrefixTree<Integer> pt = new PrefixTree<>();

        pt.add("cat", 1);
        pt.add("dog", 2);
        pt.add("catalog", 3);
        pt.add("cast", 4);
        pt.add("阿斯顿发", 5);
        pt.add("发大水", 3);

        System.out.println(pt);
        System.out.println("size: " + pt.size());
        System.out.println("get dog: " + pt.get("dog"));
        System.out.println();
        System.out.println("c   : " + pt.startsWith("c"));
        System.out.println("ca  : " + pt.startsWith("ca"));
        System.out.println("cat : " + pt.startsWith("cat"));
        System.out.println("cata: " + pt.startsWith("cata"));
        System.out.println("阿斯 : " + pt.startsWith("阿斯"));
        System.out.println("阿萨达: " + pt.startsWith("阿萨达"));
//
        pt.remove("cast");
        System.out.println("cata: " + pt.contains("cata"));
    }
}
