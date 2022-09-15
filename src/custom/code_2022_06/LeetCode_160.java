package custom.code_2022_06;

/**
 * @ClassName LeetCode_160
 * @Author Duys
 * @Description
 * @Date 2022/6/9 16:38
 **/
// 160. 相交链表
// 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
public class LeetCode_160 {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        if (headA == headB) {
            return headA;
        }
        if (headA.next == headB.next) {
            return headA.next;
        }
        // 找出两个链表的长度
        int lenA = 0;
        int lenB = 0;
        ListNode la = headA;
        while (la != null) {
            lenA++;
            la = la.next;
        }
        ListNode lb = headB;
        while (lb != null) {
            lenB++;
            lb = lb.next;
        }
        int diff = Math.abs(lenA - lenB);
        ListNode len = null;
        ListNode sort = null;
        if (lenA >= lenB) {
            len = headA;
            sort = headB;
        } else {
            len = headB;
            sort = headA;
        }
        while (diff >= 0) {
            len = len.next;
            diff--;
        }
        while (len != null && sort != null) {
            if (len == sort) {
                return len;
            }
            len = len.next;
            sort = sort.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(8);
        l1.next = null;
        ListNode l2 = new ListNode(4);
        l2.next = new ListNode(1);
        l2.next.next = l1;
        l2.next.next.next = new ListNode(4);
        getIntersectionNode(l1, l2);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
