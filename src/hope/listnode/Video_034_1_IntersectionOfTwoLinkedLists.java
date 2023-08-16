package hope.listnode;

import custom.base.ListNode;

/**
 * @author Mr.Du
 * @ClassName Video_034_1_IntersectionOfTwoLinkedLists
 * @date 2023年08月16日
 */
// 160. 相交链表
// https://leetcode.cn/problems/intersection-of-two-linked-lists/
public class Video_034_1_IntersectionOfTwoLinkedLists {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 找出长短链表
        int diff = 0;
        ListNode a = headA, b = headB;
        while (a != null) {
            diff++;
            a = a.next;
        }
        while (b != null) {
            diff--;
            b = b.next;
        }
        // 结束位置不等，不想交
        if (a != b) {
            return null;
        }
        if (diff >= 0) {
            a = headA;
            b = headB;
        } else {
            a = headB;
            b = headA;
        }
        diff = Math.abs(diff);
        while (diff-- != 0) {
            a = a.next;
        }
        while (a != b) {
            a = a.next;
            b = b.next;
        }
        return a;
    }
}
