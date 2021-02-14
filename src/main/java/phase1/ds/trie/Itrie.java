package phase1.ds.trie;

public interface Itrie<V> {
    int size();
    boolean isEmpty();
    void clear();
    V get(String key);
    boolean contains(String str);
    V add(String str, V value);
    V remove(String str);
    boolean startsWith(String prefix);
}
