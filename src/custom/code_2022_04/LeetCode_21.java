package custom.code_2022_04;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_21
 * @Author Duys
 * @Description
 * @Date 2022/4/26 16:16
 **/
// https://leetcode-cn.com/problems/merge-two-sorted-lists/
// 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
public class LeetCode_21 {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> a - b);
        while (list1 != null) {
            heap.add(list1.val);
            list1 = list1.next;
        }
        while (list2 != null) {
            heap.add(list2.val);
            list2 = list2.next;
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

    public static void main(String[] args) {
        /*ListNode l1 = new ListNode(1);
        ListNode l11 = new ListNode(2);
        ListNode l111 = new ListNode(4);
        l1.next = l11;
        l11.next = l111;

        ListNode l2 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        ListNode l211 = new ListNode(4);
        l2.next = l21;
        l21.next = l211;*/
        ListNode l1 = null;
        ListNode l2 = new ListNode();
        ListNode listNode = mergeTwoLists(l1, l2);
        while (listNode != null) {
            System.out.print(" " + listNode.val);
            listNode = listNode.next;
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
