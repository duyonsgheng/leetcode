package custom.code_2022_05;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName LeetCode_82
 * @Author Duys
 * @Description
 * @Date 2022/5/16 13:31
 **/
// 82. 删除排序链表中的重复元素 II
// 给定一个已排序的链表的头 head ，删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表
public class LeetCode_82 {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = 0;
        ListNode root = head;
        while (root != null) {
            n++;
            map.put(root.val, map.getOrDefault(root.val, 0) + 1);
            root = root.next;
        }
        root = head;
        ListNode newRoot = null;
        ListNode pre = null;
        while (root != null) {
            if (map.get(root.val) == 1) {
                if (newRoot == null) {
                    newRoot = new ListNode(root.val);
                    pre = newRoot;
                } else {
                    ListNode cur = new ListNode(root.val);
                    pre.next = cur;
                    pre = cur;
                }
            }
            root = root.next;
        }
        return newRoot;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
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
