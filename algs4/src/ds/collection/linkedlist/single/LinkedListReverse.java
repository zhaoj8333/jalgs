package ds.collection.linkedlist.single;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author allen
 */
public class LinkedListReverse {
    public static void main(String[] args) {
        SingleLinkedlist<String> sll = new SingleLinkedlist<>();
        sll.add("A");
        sll.add("B");
        sll.add("C");
        sll.add("D");
        sll.add("E");

//        SingleLinkedlist.Node<String> newHead = reverseListReversely(sll.first);
        SingleLinkedlist.Node<String> newHead = reverseListByLoop(sll.first);

        StdOut.println(newHead);

//        newHead = reverseList(sll.first.next);
//        StdOut.println(sll.first.next);
//        StdOut.println(newHead);

    }

    public static SingleLinkedlist.Node<String> reverseListByLoop(SingleLinkedlist.Node<String> head) {
        SingleLinkedlist.Node<String> newHead = new SingleLinkedlist.Node<>("", null);
        while (head != null) {
            SingleLinkedlist.Node<String> tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }

        return newHead;
    }

    public static SingleLinkedlist.Node<String> reverseListReversely(SingleLinkedlist.Node<String> head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 递
        SingleLinkedlist.Node<String> newHead = reverseListReversely(head.next);
        // 归
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
