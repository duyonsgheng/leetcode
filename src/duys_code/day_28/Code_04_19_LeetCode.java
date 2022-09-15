package duys_code.day_28;

/**
 * @ClassName Code_04_19_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * @Date 2021/11/23 11:23
 **/
public class Code_04_19_LeetCode {
    /**
     * 删除倒数第n个
     * 快指针 先走n去
     * 慢指针开始，快指针走到尾巴，慢指针来到的位置就是要删除的
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            n--;
            // cur先走了n步骤
            // pre 开始
            if (n == -1) {
                pre = head;
            }
            // pre开始走
            if (n < -1) {
                pre = pre.next;
            }
            cur = cur.next;
        }
        // 比如总共10个 ，删除倒是第11个，没有
        if (n > 0) {
            return head;
        }
        // 总共10个，删除倒是第10个，那就是头节点
        if (pre == null) {
            return head.next;
        }
        pre.next = pre.next.next;
        return head;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
