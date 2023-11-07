package custom.code_2023_08;

import custom.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2130
 * @date 2023年08月28日
 */
// 2130. 链表最大孪生和
// https://leetcode.cn/problems/maximum-twin-sum-of-a-linked-list/
public class LeetCode_2130 {
    public int pairSum1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int n = list.size();
        int ans = -1;
        // 0 1 2 3 4 5
        for (int i = 0; i < n / 2; i++) {
            ans = Math.max(ans, list.get(i) + list.get(n - i - 1));
        }
        return ans;
    }

    // 快慢指针
    public static int pairSum(ListNode head) {
        ListNode fast = head;
        ListNode low = head;
        while (low != null) {
            fast = fast.next;
            low = low.next.next;
        }
        ListNode cur = fast;
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        low = head;
        cur = pre;
        int ans = 0;
        while (cur != null) {
            ans = Math.max(ans, cur.val + low.val);
            low = low.next;
            cur = cur.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        ListNode cur = new ListNode(5);
        cur.next = new ListNode(4);
        cur.next.next = new ListNode(2);
        cur.next.next.next = new ListNode(1);
        System.out.println(pairSum(cur));
    }
}
