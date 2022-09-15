package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_86
 * @Author Duys
 * @Description
 * @Date 2022/5/17 9:39
 **/
// 86. 分隔链表
// 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
//你应当 保留 两个分区中每个节点的初始相对位置。
//链接：https://leetcode.cn/problems/partition-list
public class LeetCode_86 {

    // 1 4 3 5 7 2 9 8  x = 7
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        List<ListNode> less = new ArrayList<>();
        List<ListNode> more = new ArrayList<>();
        ListNode root = head;
        while (root != null) {
            if (root.val < x) {
                less.add(root);
            } else {
                more.add(root);
            }
            root = root.next;
        }
        List<ListNode> all = new ArrayList<>();
        all.addAll(less);
        all.addAll(more);
        for (int i = 0; i < all.size() - 1; i++) {
            all.get(i).next = all.get(i + 1);
        }
        all.get(all.size() - 1).next = null;
        return all.get(0);
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
