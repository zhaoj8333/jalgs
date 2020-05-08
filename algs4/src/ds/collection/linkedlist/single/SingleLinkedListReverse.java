package ds.collection.linkedlist.single;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author allen
 */
public class SingleLinkedListReverse {
    public static void main(String[] args) {
        SingleLinkedlist<String> sll = new SingleLinkedlist<>();
        sll.add("AAA");
        sll.add("BBB");
        sll.add("CCC");
        sll.add("DDD");
        sll.add("EEE");
        sll.add("FFF");

        StdOut.println(sll);
        StdOut.println("size: " + sll.size());

//        method1(sll);
//        method2(sll);
//        method3(sll.first);
//        method4(sll);

//        SingleLinkedlist.Node<String> head = method3(sll.first);
//        StdOut.println(sll);

//        printReversely(sll);
    }

    private static void printReversely(SingleLinkedlist<String> sll) {
        // 1. 反转后打印
        // 2. 使用栈
        Stack<String> stac = new Stack<>();
        SingleLinkedlist.Node<String> curr = sll.first;
        while (curr != null) {
            stac.push(curr.item);
            curr = curr.next;
        }
        while (stac.size() > 0) {
            StdOut.println(stac.pop());
        }

    }

    /**
     * 使用栈
     */
    private static void method4(SingleLinkedlist<String> sll) {
        SingleLinkedlist.Node<String> curr = sll.first;
        Stack<String> stack = new Stack<>();
        while (curr != null) {
            stack.push(curr.item);
            curr = curr.next;
        }

        SingleLinkedlist<String> newSll = new SingleLinkedlist<>();
        while (stack.size() > 0) {
            newSll.add(stack.pop());
        }
        System.out.println(newSll);
    }

    /**
     * 破坏原有链表结构进行反转
     *
     * 将单链表所有的节点的next指向前驱节点即可
     *  1. 入栈出栈 得到反转链表
     *  2. 原地删除
     */
    // TODO: 4/26/20
    private static void method3(SingleLinkedlist.Node<String> head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 当前节点
        SingleLinkedlist.Node<String> curr = head.next;
        // 只想当前节点的下一个节点
        SingleLinkedlist.Node<String> next = null;
        //
        SingleLinkedlist.Node<String> reverseHead = new SingleLinkedlist.Node<>("", null);
        // 遍历原链表，每遍历一个节点，取出，放在reverseHead最前端
        while (curr != null) {
            // 暂时保存当前节点的下一个节点
            next = curr.next;
            // curr下一个指向新链表最前端，将curr下一个节点挂到最前端
            curr.next = reverseHead.next;
            // 新节点头指向当前curr节点
            reverseHead.next = curr;
            // curr往右移
            curr = next;
        }
        head.next = reverseHead.next;

        StdOut.println(reverseHead);
    }

    /**
     * 用另一链表接收，不破坏原有链表结构
     */
    private static void method2(SingleLinkedlist<String> sll) {
        SingleLinkedlist<String> reverse = new SingleLinkedlist<>();
        SingleLinkedlist.Node<String> x = sll.first;
        while (x != null) {
            reverse.add(x.item, 0);
            x = x.next;
        }

//        SingleLinkedlist.ListIterator<String> itr = (SingleLinkedlist.ListIterator<String>) sll.iterator();
//        while (itr.hasNext()) {
//            reverse.add(itr.next(), 0);
//        }

        StdOut.println(reverse);
    }

    /**
     *  每次取原链表的 第size() - 1 的节点，然后追加到另一链表
     *  复杂度较高
     */
    private static void method1(SingleLinkedlist<String> sll) {
        SingleLinkedlist<String> reverse = new SingleLinkedlist<>();

        for (int i = sll.size() - 1; i >= 0; i--) {
            SingleLinkedlist.Node x = sll.first;

            for (int j = 0; j < i; j++) {
                x = x.next;
            }
            reverse.add((String) x.item);
        }

        StdOut.println(reverse);
    }
}
