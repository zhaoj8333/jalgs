package phase1.ds.tree.trie;

import java.util.HashMap;

/**
 * @author allen
 */
public class Trie2<V> implements Itrie<V> {
    private int size;
    private Node<V> root = new Node<>(null);

    private static class Node<V> {
        private HashMap<Character, Node<V>> children;
        private V value;
        private boolean end;
        private Node<V> parent;
        private Character character;
        public Node(Node<V> parent) {
            this.parent = parent;
        }
        public HashMap<Character, Node<V>> getChildren() {
            return children == null ? (children = new HashMap<>()) : children;
        }
        public Character getCharacter() {
            return character;
        }
        public void setCharacter(Character character) {
            this.character = character;
        }
        public boolean isEnd() {
            return end;
        }
        public void setEnd(boolean end) {
            this.end = end;
        }
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }
        public Node<V> getParent() {
            return parent;
        }
        public void setParent(Node<V> parent) {
            this.parent = parent;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "children=" + children +
                    ", character=" + character +
                    '}';
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
        return node == null ? null : node.getValue();
    }

    private Node<V> node(String key) {
        if (root == null || root.getChildren() == null) {
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
            throw new IllegalArgumentException("key can'not be null");
        }
    }

    @Override
    public boolean contains(String str) {
        return node(str) != null;
    }

    @Override
    public V add(String str, V value) {
        keyCheck(str);
        Node<V> node = root;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            HashMap<Character, Node<V>> children = node.getChildren();
            Node<V> child = children.get(c);
            if (child == null) {
                child = new Node<>(node);
                child.setCharacter(c);
                children.put(c, child);
            }
            node = child;
        }
        // 新增不一样的单词
        if (!node.isEnd()) {
            node.setEnd(true);
            node.value = value;
            size++;
            return null;
        }
        V old = node.getValue();
        node.setValue(value);
        return old;
    }

    @Override
    public V remove(String str) {
        Node<V> node = node(str);
        if (node == null || !node.isEnd()) {
            return null;
        }
        size--;
        V old = node.getValue();
        // 如果被删除的某个单词的最后一个字符节点还有子节点，表示还要其他单词使用了该路径，不能直接删除
        if (node.children != null && !node.children.isEmpty()) {
            node.setEnd(false);
            node.value = null;
            return old;
        }
        // 没有子节点的：从后往前删
        Node<V> parent = null;
        while ((parent = node.parent) != null) {
            parent.children.remove(node.getCharacter());
            if (!parent.getChildren().isEmpty()) {
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
        Trie2<Integer> trie = new Trie2();
        trie.add("dog", 1);
        trie.add("cat", 2);
        trie.add("doggy", 3);

        System.out.println(trie.root);
        System.out.println(trie.get("doggy"));
        System.out.println(trie.get("cat"));
        System.out.println(trie.startsWith("ca"));
        trie.remove("doggy");
        System.out.println(trie.root);
    }
}
