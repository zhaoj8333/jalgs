package phase1.algs.lru;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 最近最少使用 缓存
 *     在O(1)内完成两种操作
 *
 *  HashMap + Double LinkedList
 *
 *  JDK LinkedHashMap
 * @author allen
 */
public class LruCache<K, V> {

    private int capacity;
    private LinkedList<Node<K, V>> list;
    private HashMap<K, Node<K, V>> map;

    public LruCache(int capacity) {
        this.capacity = capacity;
        list = new LinkedList<>();
        map  = new HashMap<>();
    }

    public void put(K key, Node<K, V> val) {
        if (map.containsKey(key)) {
            list.remove(map.get(key));
            list.addFirst(val);
//            map.put(key, val);
        } else {
            if (capacity == list.size()) {
                final Node<K, V> last = list.removeLast();
                map.remove(last.key);
            }
            list.addFirst(val);
            map.put(key, val);
        }
    }

    public Node<K, V> get(K key) {
        if (! map.containsKey(key)) {
            return null;
        }
        final Node<K, V> res = map.get(key);
        this.put(key, res);

        return res;
    }

    public static void main(String[] args) {
        final LruCache<String, Integer> lruCache = new LruCache<>(2);
//        lruCache.put(2, 2);

//        lruCache.get(1);

//        lruCache.put(3, 3);
//        lruCache.get(2);

//        lruCache.put(4, 4);
//        lruCache.get(1);    // -1
//        lruCache.get(3);    // 3
//        lruCache.get(4);    // 4
    }
}
