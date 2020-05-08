package ds.collection.linkedlist.single;

import ds.collection.linkedlist.circle.SingleCircularLinkedlist;
import edu.princeton.cs.algs4.StdOut;

/**
 * 判断链表是否有环
 */
public class CycleDetect {
    public static void main(String[] args) {
//        SingleLinkedlist<String> sll = new SingleLinkedlist<>();
        SingleCircularLinkedlist<String> sll = new SingleCircularLinkedlist<>();
        sll.add("AA");
        sll.add("BB");
        sll.add("CC");
        sll.add("DD");
        sll.add("EE");
        sll.add("FF");
        sll.add("II");
        sll.add("JJ");
        sll.add("KK");
        sll.add("LL");
        sll.add("MM");
        sll.add("NN");
        sll.add("OO");
        sll.add("PP");

        StdOut.println(hasCircle(sll.getFirst()));
        StdOut.println("count: " + count);
    }

    private static int count = 0;

    /**
     * 1. 尾部连接到了某一个节点
     * 2.
     * 3. 只有一个节点时，链表中没有环
     *
     * 快慢指针:
     *  如果指针相遇，则有环
     *  如果快指针先为null，则无环
     *
     * 快指针一次走2步（不能再多），慢指针一次走1步，
     */
    public static boolean hasCircle(SingleCircularLinkedlist.Node<String> head) {
        if (head == null || head.next == null) {
            return false;
        }

        SingleCircularLinkedlist.Node<String> slow = head;
        SingleCircularLinkedlist.Node<String> fast = head.next;
        while (fast != null && fast.next != null) {
            count++;
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
            StdOut.println("slow: " + slow);
            StdOut.println("fast: " + fast);
            StdOut.println("===");
        }

        return false;
    }


}
