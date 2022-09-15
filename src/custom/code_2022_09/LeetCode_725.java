package custom.code_2022_09;

import custom.base.ListNode;

/**
 * @ClassName LeetCode_725
 * @Author Duys
 * @Description
 * @Date 2022/9/14 15:39
 **/
// 725. 分隔链表
public class LeetCode_725 {

    public ListNode[] splitListToParts(ListNode head, int k) {
        if (k <= 0) {
            return null;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        ListNode[] ans = new ListNode[k];
        int partition = len / k; // 每部分至少几个
        int pre = len % k; // 前面几个部分长度需要+1
        cur = head;
        for (int i = 0; i < k && cur != null; i++) {
            ans[i] = cur;
            int curSize = partition + (i < pre ? 1 : 0);
            for (int j = 1; j < curSize; j++) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return ans;
    }
}
