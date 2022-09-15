package custom.code_2022_05;

import java.util.Arrays;

/**
 * @ClassName LeetCode_61
 * @Author Duys
 * @Description
 * @Date 2022/5/6 15:50
 **/
// 旋转链表
//给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
public class LeetCode_61 {

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k <= 0) {
            return head;
        }
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        int[] nodes = new int[n];
        cur = head;
        int index = 0;
        while (cur != null) {
            nodes[index++] = cur.val;
            cur = cur.next;
        }
        int move = k;
        if (n < k) {
            move = k % n;
        }
        // move = 2
        // 1 2 3 4 5
        // 4 5 1 2 3
        int[] ans = new int[n];
        System.arraycopy(nodes, 0, ans, move, n - move);
        System.arraycopy(nodes, n - move, ans, 0, move);
        ListNode root = new ListNode(ans[0]);
        ListNode curAns = root;
        for (int i = 1; i < ans.length; i++) {
            ListNode c = new ListNode(ans[i]);
            curAns.next = c;
            curAns = c;
        }
        return root;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode cur = root;
        for (int i = 2; i <= 5; i++) {
            ListNode c = new ListNode(i);
            cur.next = c;
            cur = c;
        }
        ListNode listNode = rotateRight(root, 2);
        System.out.println();
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
