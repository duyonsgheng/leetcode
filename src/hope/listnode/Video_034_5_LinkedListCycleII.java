package hope.listnode;

import custom.base.ListNode;

/**
 * @author Mr.Du
 * @ClassName Video_034_5_LinkedListCycleII
 * @date 2023年08月16日
 */
// 142. 环形链表 II
// https://leetcode.cn/problems/linked-list-cycle-ii/
public class Video_034_5_LinkedListCycleII {
    // 入环节点
    // 追击问题
    // 快慢指针
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 然后fast回到开始
        fast = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
