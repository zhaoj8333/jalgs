package phase1.ds.uf.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class UFG<V> {
    protected Map<V, Node<V>> nodes = new HashMap<>();

    public Map<V, Node<V>> getNodes() {
        return nodes;
    }

    public void add(V v) {
        if (nodes.containsKey(v)) {
            return;
        }
        nodes.put(v, new Node<>(v));
    }

    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null : node.value;
    }

    /**
     *  find the root node
     */
    private Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);
        if (node == null) {
            return null;
        }
        /*
        while (index != parents[index]) {
            parents[index] = parents[parents[index]];
            index = parents[index]
        }
         */
        while (! Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }

    public void union(V v1, V v2) {
        Node<V> n1 = findNode(v1);
        Node<V> n2 = findNode(v2);
        if (n1 == null || n2 == null) {
            return;
        }
        if (Objects.equals(n1.parent, n2.parent)) {
            return;
        }
        if (n1.rank > n2.rank) {
            n2.setParent(n1.getParent());
        } else if (n1.rank < n2.rank) {
            n1.setParent(n2.getParent());
        } else {
            n2.setParent(n1.getParent());
            n1.setRank(1 + n1.getRank());
        }
    }

    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    protected static class Node<V> {
        private V value;
        private Node<V> parent = this;
        private int rank = 1;

        public Node(V value) {
            this.value = value;
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

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "Node { " + value + ", rank = " + rank + '}';
        }
    }
}
