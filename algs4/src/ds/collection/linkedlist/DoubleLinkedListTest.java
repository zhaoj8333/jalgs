package ds.collection.linkedlist;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DoubleLinkedListTest {
    public static void main(String[] args) {
//        demo1();
//        demo2();
        demo3();
//        LinkedList;
    }

    /**
     * LinkedList 线程安全问题
     */
    private static void demo3() {
        LinkedList<String> link = new LinkedList<>();
        Collection<String> ts = Collections.synchronizedList(link);
        ConcurrentLinkedDeque concurrentLinkedDeque = new ConcurrentLinkedDeque();
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    ts.add(UUID.randomUUID().toString().substring(0, 10));
                    concurrentLinkedDeque.add(UUID.randomUUID().toString().substring(0, 10));
                    System.out.println(concurrentLinkedDeque);
                    /*
                        [03396988-3, 3d3d80b6-8, 6229504f-6]
                        [03396988-3, 3d3d80b6-8, 6229504f-6, 32578b11-4]
                        [03396988-3, 3d3d80b6-8, 6229504f-6, 32578b11-4]
                        [03396988-3, 3d3d80b6-8, 6229504f-6]

                        或 NullPointerException
                     */
                }
            }).start();
        }
    }

    private static void demo2() {
        LinkedList<String> link = new LinkedList<>();
        link.add("a");
        link.add("b");
        link.add("c");

//        Iterator<String> itr = link.iterator();
        ListIterator<String> itr = link.listIterator();
        while (itr.hasNext()) {
//            link.add("A");
//            ConcurrentModificationException
//            System.out.println(itr.next());
            if (itr.next() == "a") {
                itr.add("A");
            }
            if (itr.next() == "b") {
                itr.remove();
            }
        }
        System.out.println(link);
    }

    private static void demo1() {
        DoubleLinkedList<String> dlink = new DoubleLinkedList<>();
        dlink.add("a");
        dlink.add("b");
        dlink.add("c");
        dlink.add("d");
        dlink.add("e");
        dlink.add("f");

//        dlink.add(1, "B");
//        dlink.remove(1);
//        dlink.remove(0);
//        dlink.remove(5);

        System.out.println(dlink);
    }
}
