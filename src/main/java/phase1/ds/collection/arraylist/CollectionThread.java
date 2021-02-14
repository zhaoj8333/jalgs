package phase1.ds.collection.arraylist;

import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionThread implements Runnable {
    // ConcurrentModificationException
    // private static ArrayList<String> list = new ArrayList<>();
    /*
        线程安全的变体ArrayList，通过对底层数组的副本实现
        迭代器不会抛出并发修改异常
     */
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("A");
        list.add("B");
        list.add("C");
    }

    @Override
    public void run() {
        for (String value : list) {
            System.out.println(value);
            list.add("a");
        }
    }
}
