package custom.code_2023_09;

import custom.base.ListNode;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2181
 * @date 2023年09月07日
 */
public class LeetCode_2181 {
    public static ListNode mergeNodes(ListNode head) {
        ListNode root = null;
        ListNode cur = head;
        ListNode pre = null;
        // 0 1 2 0 3 4 0 5 6 0
        while (cur != null) {
            if (cur.val == 0) {
                int total = 0;
                cur = cur.next;
                while (cur != null && cur.val != 0) {
                    total += cur.val;
                    cur = cur.next;
                }
                if (total == 0) {
                    continue;
                }
                ListNode curList = new ListNode(total);
                if (root == null) {
                    root = curList;
                } else {
                    pre.next = curList;
                }
                pre = curList;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        // 0,1,0,3,0,2,2,0
        ListNode root = new ListNode(0);
        root.next = new ListNode(1);
        root.next.next = new ListNode(0);
        root.next.next.next = new ListNode(3);
        root.next.next.next.next = new ListNode(0);
        root.next.next.next.next.next = new ListNode(2);
        root.next.next.next.next.next.next = new ListNode(2);
        root.next.next.next.next.next.next.next = new ListNode(0);
        mergeNodes(root);
    }
}
