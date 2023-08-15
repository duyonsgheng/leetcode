package custom.code_2023_08;

import custom.base.ListNode;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2095
 * @date 2023年08月14日
 */
// 2095. 删除链表的中间节点
// https://leetcode.cn/problems/delete-the-middle-node-of-a-linked-list/
public class LeetCode_2095 {
    public ListNode deleteMiddle(ListNode head) {
        ListNode cur = head;
        int n = 0;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        int t = n / 2;
        n = 0;
        cur = head;
        ListNode pre = null;
        while (cur != null) {
            if (n == t) {
                if (pre != null) {
                    pre.next = cur.next;
                } else if (pre == null) {
                    return null;
                }
                break;
            }
            pre = cur;
            cur = cur.next;
            n++;
        }
        return head;
    }
}
