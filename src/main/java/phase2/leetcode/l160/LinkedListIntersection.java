package phase2.leetcode.l160;

import phase2.leetcode.common.ListNode;

/**
 * 相交链表
 *     寻找两个单链表的相交起始节点
 */
public class LinkedListIntersection {
    public static void main(String[] args) {
        ListNode l12 = new ListNode(12);
        ListNode l11 = new ListNode(11, l12);
        ListNode l10 = new ListNode(10, l11);
        ListNode headA = new ListNode(1, new ListNode(3, new ListNode(5, l10)));
        ListNode headB = new ListNode(2, new ListNode(4, l10));

        headA = new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(10))));
        headB = new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(10))));
        System.out.println("A: " + headA);
        System.out.println("B: " + headB);
        System.out.println();

//        ListNode result = find(headA, headB);
        ListNode result = find0(headA, headB);
        System.out.println(result);
    }

    private static ListNode find0(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode curA = headA, curB = headB;
        while (curA != curB) {
            curA = (curA == null) ? headB : curA.next;
            curB = (curB == null) ? headA : curB.next;
        }
        return curA;
    }

    private static ListNode find(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            if (a == null) {
                a = headB;
            } else {
                a = a.next;
            }
            if (b == null) {
                b = headA;
            } else {
                b = b.next;
            }
        }
        return a;
    }
}
