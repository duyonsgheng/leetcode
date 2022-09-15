package custom.code_2022_05;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName LeetCode_84
 * @Author Duys
 * @Description
 * @Date 2022/5/16 14:19
 **/
public class LeetCode_83 {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int n = 0;
        ListNode root = head;
        while (root != null) {
            n++;
            root = root.next;
        }
        ListNode[] stack = new ListNode[n];
        int size = 0;
        stack[size++] = head;
        root = head.next;
        while (root != null) {
            ListNode pre = stack[size - 1];
            if (pre.val != root.val) {
                stack[size++] = root;
            }
            root = root.next;
        }
        stack[size - 1].next = null;
        for (int i = 0; i < size - 1; i++) {
            stack[i].next = stack[i + 1];
        }
        return stack[0];
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(3);
        /*ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);*/
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
       /* l5.next = l6;
        l6.next = l7;*/
        ListNode node = deleteDuplicates(l1);
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
