package phase1.ds.list.linkedlist.single;

import java.util.Stack;

import static phase1.ds.list.linkedlist.single.SingleLinkedlist.*;

/**
 * @author allen
 */
public class SingleLinkedListReverse {
    public static void main(String[] args) {
//        method1(sll);
//        method2(sll);
//        method3(sll.first);
//        method4(sll);

//        SingleLinkedlist.Node<String> head = method3(sll.first);
//        System.out.println(sll);

//        printReversely(sll);
//        reverseByLoop();
        final SingleLinkedlist<String> list = new SingleLinkedlist<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        System.out.println(list);



        /*
        Node<String> node = list.first;
        while (true) {
            System.out.println(node);
            if (node.next == null) {
                break;
            }
            node = node.next;
        }
         */

        Node<String> node = list.first;
//        Node<String> newNode = reverseByReversely(node);
        Node<String> newNode = reverseByLoop(node);

        while (newNode.next != null) {
            System.out.println(newNode);
            newNode = newNode.next;
        }

        System.out.println(hasCycle(list.first));
    }

    private static boolean hasCycle(Node<String> head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node<String> slow = head;
        Node<String> fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    private static Node<String> reverseByReversely(Node<String> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<String> newHead = reverseByReversely(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    private static Node<String> reverseByLoop(Node<String> head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<String> newHead = null;
        while (head != null) {
            Node<String> tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    private static void printReversely(SingleLinkedlist<String> sll) {
        // 1. ???????????????
        // 2. ?????????
        Stack<String> stac = new Stack<>();
        SingleLinkedlist.Node<String> curr = sll.first;
        while (curr != null) {
            stac.push(curr.item);
            curr = curr.next;
        }
        while (stac.size() > 0) {
            System.out.println(stac.pop());
        }

    }

    /**
     * ?????????
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
     * ????????????????????????????????????
     *
     * ??????????????????????????????next????????????????????????
     *  1. ???????????? ??????????????????
     *  2. ????????????
     */
    // TODO: 4/26/20
    private static void method3(SingleLinkedlist.Node<String> head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        // ????????????
        SingleLinkedlist.Node<String> curr = head.next;
        // ????????????????????????????????????
        SingleLinkedlist.Node<String> next = null;
        //
        SingleLinkedlist.Node<String> reverseHead = new SingleLinkedlist.Node<>("", null);
        // ?????????????????????????????????????????????????????????reverseHead?????????
        while (curr != null) {
            // ??????????????????????????????????????????
            next = curr.next;
            // curr???????????????????????????????????????curr??????????????????????????????
            curr.next = reverseHead.next;
            // ????????????????????????curr??????
            reverseHead.next = curr;
            // curr?????????
            curr = next;
        }
        head.next = reverseHead.next;

    }

    /**
     * ???????????????????????????????????????????????????
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

    }

    /**
     *  ????????????????????? ???size() - 1 ???????????????????????????????????????
     *  ???????????????
     */
    private static void method1(SingleLinkedlist<String> sll) {
        SingleLinkedlist<String> reverse = new SingleLinkedlist<>();

        for (int i = sll.size() - 1; i >= 0; i--) {
            Node x = sll.first;

            for (int j = 0; j < i; j++) {
                x = x.next;
            }
            reverse.add((String) x.item);
        }

    }
}
