package custom.code_2022_05;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName LeetCode_107
 * @Author Duys
 * @Description
 * @Date 2022/5/23 17:20
 **/
// 109. 有序链表转换二叉搜索树
// 给定一个单链表的头节点 head，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
//本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差不超过 1。
//链接：https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree
public class LeetCode_109 {
    // 找中位数
    // 然后左侧就是左子树，右侧就是右子树
    public static TreeNode sortedListToBST(ListNode head) {
        return build(head, null);
    }

    public static TreeNode build(ListNode left, ListNode right) {
        if (left == right) { // 左开右闭，不算重
            return null;
        }
        ListNode mid = findMid(left, right);
        TreeNode root = new TreeNode(mid.val);
        root.left = build(left, mid);
        root.right = build(mid.next, right);
        return root;
    }

    // 快指针一次走两步
    // 慢指针一次走一步
    public static ListNode findMid(ListNode left, ListNode right) {
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            fast = fast.next;
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
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
