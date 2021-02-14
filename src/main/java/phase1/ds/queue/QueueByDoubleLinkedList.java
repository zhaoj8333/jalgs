package phase1.ds.queue;

import java.util.LinkedList;

/**
 * @author allen
 */
public class QueueByDoubleLinkedList<E> {

    private LinkedList<E> list = new LinkedList<E>();

    public int size() {
        return list.size();
    }

    public void enQueue(E elem) {
        list.add(elem);
    }

    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {
        QueueByDoubleLinkedList<String> qbdl = new QueueByDoubleLinkedList<>();
        qbdl.enQueue("AA");
        qbdl.enQueue("BB");
        qbdl.enQueue("CC");
        qbdl.enQueue("DD");
        qbdl.enQueue("EE");
        qbdl.enQueue("FF");

        while (!qbdl.isEmpty()) {
            System.out.println(qbdl.deQueue());
        }
    }


}
