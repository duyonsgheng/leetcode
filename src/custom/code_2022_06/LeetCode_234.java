package custom.code_2022_06;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_234
 * @Author Duys
 * @Description
 * @Date 2022/6/10 15:10
 **/
//234. 回文链表
// https://leetcode.cn/problems/palindrome-linked-list/
public class LeetCode_234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode root = head;
        while (root != null) {
            list.add(root);
            root = root.next;
        }
        root = head;
        int i = list.size() - 1;
        while (root != null && i > 0) {
            if (root.val != list.get(i).val) {
                return false;
            }
            root = root.next;
            i--;
        }
        return true;
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
