package hope.listnode;

import custom.base.ListNode;

/**
 * @author Mr.Du
 * @ClassName Video_034_2_ReverseNodesInkGroup
 * @date 2023年08月16日
 */
// 25. K 个一组翻转链表
// https://leetcode.cn/problems/reverse-nodes-in-k-group/
public class Video_034_2_ReverseNodesInkGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        // 第一组特殊，因为要换头
        ListNode end = teamEnd(head, k);
        if (end == null) {
            return start;
        }
        // 换头
        head = end;
        // start到end之间开始翻转
        reverse(start, end);
        // 翻转之后start就是上一个区间的结尾
        ListNode lastEnd = start;
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = teamEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
    }

    // a -> b -> c -> d
    public void reverse(ListNode head, ListNode end) {
        end = end.next;
        ListNode pre = null, cur = head, next = null;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = end;
    }

    public ListNode teamEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }
}
