package phase2.leetcode.l203;

import phase2.leetcode.common.ListNode;

public class RemoveLinkedlistElements {
    public static void main(String[] args) {
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode newHead = null;
        ListNode newTail = null;
        while (head != null) {
            if (head.val != val) {
                if (newTail == null) {
                    newHead = head;
                    newTail = head;
                } else {
                    newTail = newTail.next = head;
                }
            }
            head = head.next;
        }
        if (newTail == null) {
            return null;
        } else {
            // 尾节点next为空
            newTail.next = null;
        }
        return newHead;
    }

    public static ListNode removeElementsEnhance(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(0);
        ListNode newTail = newHead;
        while (head != null) {
            if (head.val != val) {
                newTail.next = head;
                newTail = head;
            }
            head = head.next;
        }
        newTail.next = null;
        return newHead.next;
    }
}
