package phase1.algs.lru;

public class Node<K, V> {
    public K key;
    private V val;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String toString() {
        return "{" + key + "->" + val + "}";
    }
}
