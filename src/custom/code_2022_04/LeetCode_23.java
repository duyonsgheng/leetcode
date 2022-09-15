package custom.code_2022_04;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_23
 * @Author Duys
 * @Description
 * @Date 2022/4/26 17:48
 **/
// https://leetcode-cn.com/problems/merge-k-sorted-lists/
// 给你一个链表数组，每个链表都已经按升序排列。
//请你将所有链表合并到一个升序链表中，返回合并后的链表。
public class LeetCode_23 {

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length <= 0) {
            return null;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> a - b);
        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            while (node != null) {
                heap.add(node.val);
                node = node.next;
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        int rootV = heap.poll();
        ListNode root = new ListNode(rootV);
        ListNode head = root;
        while (!heap.isEmpty()) {
            int cur = heap.poll();
            ListNode curNode = new ListNode(cur);
            head.next = curNode;
            head = curNode;
        }
        return root;
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
