package ds.set;

import ds.map.Map;
import ds.map.TreeMap;
import edu.princeton.cs.algs4.StdOut;

/**
 * 把map的key组合在一起就是set
 *
 * treemap中的key肯定是唯一的
 */
public class TreeSet<E extends Comparable<E>> implements Set<E> {

    Map<E, Object> map = new TreeMap<E, Object>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(E element) {
        return map.containsKey(element);
    }

    @Override
    public void add(E element) {
        map.put(element, null);
    }

    @Override
    public void remove(E element) {
        map.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        map.traversal(new Map.Visitor<E, Object>() {
            @Override
            protected void visit(E key, Object value) {
                StdOut.println(key);
            }
        });
    }

    public static void main(String[] args) {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("aaa");
        treeSet.add("bbb");
        treeSet.add("ccc");
        treeSet.add("ddd");
        treeSet.add("aaa");
        treeSet.traversal(null);
    }


}
