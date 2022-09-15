package custom.code_2022_06;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_206
 * @Author Duys
 * @Description
 * @Date 2022/6/9 17:50
 **/
// 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
// https://leetcode.cn/problems/reverse-linked-list/
public class LeetCode_206 {


    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        for (int i = list.size() - 1; i >= 1; i--) {
            list.get(i).next = list.get(i - 1);
        }
        list.get(0).next = null;
        return list.get(list.size() - 1);
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
