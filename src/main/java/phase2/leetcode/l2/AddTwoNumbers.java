package phase2.leetcode.l2;

import phase2.leetcode.common.ListNode;

import java.util.Objects;

/**
 *  https://leetcode-cn.com/problems/add-two-numbers/
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4, new ListNode(5, new ListNode(9, null)));
        ListNode l2 = new ListNode(3, new ListNode(8, null));
        ListNode result = add(l1, l2);
        System.out.println(result);
    }

    /**
     * @param l1 4 -> 5 -> 9 -> null
     * @param l2 3 -> 8 -> null
     * @return new LinkedList
     */
    public static ListNode add(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) {
            return l2;
        }
        if (Objects.isNull(l2)) {
            return l1;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode next = dummyHead;
        int carry = 0;
        while (!Objects.isNull(l1) || !Objects.isNull(l2)) {
            int a = 0; int b = 0;
            if (!Objects.isNull(l1)) {
                a = l1.val;
                l1 = l1.next;
            }
            if (!Objects.isNull(l2)) {
                b = l2.val;
                l2 = l2.next;
            }
            int sum = a + b + carry;
            carry = sum / 10; // 进位值
            next.next = new ListNode(sum % 10); // sum个位数作为新节点的值
            next = next.next;
        }
        // 检查最后的进位
        if (carry > 0) {
            next.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
