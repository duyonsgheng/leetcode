package custom.code_2022_04;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_25
 * @Author Duys
 * @Description
 * @Date 2022/5/9 17:31
 **/
// K 个一组翻转链表
// 给你链表的头节点 head ，每k个节点一组进行翻转，请你返回修改后的链表。
//k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
//你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
//
//链接：https://leetcode.cn/problems/reverse-nodes-in-k-group
public class LeetCode_25 {

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        if (k > list.size()) {
            return head;
        }
        int mod = list.size() % k;
        List<ListNode> ans = new ArrayList<>();

        for (int i = 0; i < list.size() - mod - 1; i += k) {
            if (i >= list.size()) {
                i = list.size() - 1;
            }
            for (int j = Math.min(i + k - 1, list.size() - 1); j >= i; j--) {
                ans.add(list.get(j));
            }
        }
        for (int i = list.size() - mod; i < list.size(); i++) {
            ans.add(list.get(i));
        }
        ans.get(ans.size() - 1).next = null;
        for (int i = ans.size() - 1; i >= 1; i--) {
            ans.get(i - 1).next = ans.get(i);
        }
        return ans.get(0);
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        //ListNode l6 = new ListNode(6);
        //ListNode l7 = new ListNode(7);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        //l5.next = l6;
        //l6.next = l7;
        ListNode listNode = reverseKGroup(l1, 2);
        ListNode head = listNode;
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static class ListNode {
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
