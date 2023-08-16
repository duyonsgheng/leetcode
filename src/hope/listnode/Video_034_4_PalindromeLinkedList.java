package hope.listnode;

import custom.base.ListNode;

/**
 * @author Mr.Du
 * @ClassName Video_034_4_PalindromeLinkedList
 * @date 2023年08月16日
 */
// 234. 回文链表
// https://leetcode.cn/problems/palindrome-linked-list/
public class Video_034_4_PalindromeLinkedList {
    // 额外的空间复杂度O(1)
    // 使用快慢指针
    // 快指针一次2步，慢指针一次1步，快指针到尾部，慢指针正好在中间
    // 然后卡住慢指针，从慢指针到尾部这区间进行翻转，然后前部分和后部分遍历
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head, slow = head;
        //找中点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 中点事slow，从slow到后面逆序
        ListNode pre = slow, cur = pre.next, next = null;
        // 先把逆序后的尾部节点next指针置空
        pre.next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        boolean ans = true;
        ListNode left = head, right = pre;
        while (left != null && right != null) {
            if (left.val != right.val) {
                ans = false;
                break;
            }
            left = left.next;
            right = right.next;
        }
        // 还原
        cur = pre.next;
        pre.next = null;
        next = null;
        while (cur != null) {
            next = pre.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return ans;
    }
}
