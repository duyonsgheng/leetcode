package hope.listnode;

import custom.base.ListNode;

/**
 * @author Mr.Du
 * @ClassName Video_034_6_SortList
 * @date 2023年08月16日
 */
// 148. 排序链表
// https://leetcode.cn/problems/sort-list/
public class Video_034_6_SortList {
    public ListNode start;
    public ListNode end;

    // 归并排序，在链表上额外空间O(1)
    public ListNode sortList(ListNode head) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        ListNode l1, r1, l2, r2, next, lastTeamEnd;
        for (int step = 1; step < n; step <<= 1) {
            // 第一组很特殊，因为要决定整个链表的头，所以单独处理
            l1 = head;
            r1 = findEnd(l1, step);
            l2 = r1.next;
            r2 = findEnd(l2, step);
            next = r2.next;
            r1.next = null;
            r2.next = null;
            merge(l1, r1, l2, r2);
            head = start;
            lastTeamEnd = end;
            while (next != null) {
                l1 = next;
                r1 = findEnd(l1, step);
                l2 = r1.next;
                if (l2 == null) {
                    lastTeamEnd.next = l1;
                    break;
                }
                r2 = findEnd(l2, step);
                next = r2.next;
                r1.next = null;
                r2.next = null;
                merge(l1, r1, l2, r2);
                lastTeamEnd.next = start;
                lastTeamEnd = end;
            }
        }
        return head;
    }

    public ListNode findEnd(ListNode head, int k) {
        while (--k != 0 && head.next != null) {
            head = head.next;
        }
        return head;
    }

    public void merge(ListNode l1, ListNode r1, ListNode l2, ListNode r2) {
        ListNode pre;
        if (l1.val <= l2.val) {
            start = l1;
            pre = l1;
            l1 = l1.next;
        } else {
            start = l2;
            pre = l2;
            l2 = l2.next;
        }
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                pre = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            pre.next = l1;
            end = r1;
        } else {
            pre.next = l2;
            end = r2;
        }
    }
}
