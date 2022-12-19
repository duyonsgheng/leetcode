package week.week_2022_12_02;

import custom.base.ListNode;
import custom.base.TreeNode;

/**
 * @ClassName Code_05_LeetCode_1367
 * @Author Duys
 * @Description
 * @Date 2022/12/15 13:41
 **/
// 1367. 二叉树中的链表
public class Code_05_LeetCode_1367 {
    // 在树上玩儿KMP
    public boolean isSubPath(ListNode head, TreeNode root) {
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        int[] arr = new int[n];
        n = 0;
        while (head != null) {
            arr[n++] = head.val;
            head = head.next;
        }
        int[] next = next(arr);
        return find(root, 0, arr, next);
    }

    public boolean find(TreeNode cur, int mi, int[] arr, int[] next) {
        if (mi == arr.length) {
            return true;
        }
        if (cur == null) {
            return false;
        }
        while (mi >= 0 && cur.val != arr[mi]) {
            mi = next[mi];
        }
        return find(cur.left, mi + 1, arr, next) || find(cur.right, mi + 1, arr, next);
    }

    public int[] next(int[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[arr.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < arr.length) {
            if (arr[i - 1] == arr[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
