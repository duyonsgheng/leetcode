package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_92
 * @Author Duys
 * @Description
 * @Date 2022/5/17 11:00
 **/
// 92. 反转链表 II
// 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
//链接：https://leetcode.cn/problems/reverse-linked-list-ii
public class LeetCode_92 {

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left < 0 || right < 0 || right < left) {
            return head;
        }
        int index = 0;
        ListNode root = head;
        while (root != null) {
            index++;
            root = root.next;
        }
        ListNode[] arr = new ListNode[index];
        root = head;
        index = 0;
        while (root != null) {
            arr[index++] = root;
            root = root.next;
        }

        left = left - 1;
        right = right - 1;
        while (left <= right) {
            swap(arr, left++, right--);
        }
        for (int i = 0; i < index - 1; i++) {
            arr[i].next = arr[i + 1];
        }
        arr[index - 1].next = null;
        return arr[0];
    }

    public static void swap(ListNode[] arr, int i, int j) {
        ListNode tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        /*ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);*/
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
       /* l5.next = l6;
        l6.next = l7;*/
        ListNode node = reverseBetween(l1, 2, 4);
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
