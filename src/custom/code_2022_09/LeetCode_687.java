package custom.code_2022_09;

import custom.base.TreeNode;

/**
 * @ClassName LeetCode_687
 * @Author Duys
 * @Description
 * @Date 2022/9/2 19:23
 **/
// 687. 最长同值路径
public class LeetCode_687 {
    int res;

    public int longestUnivaluePath1(TreeNode root) {
        res = 0;
        process(root);
        return res;
    }

    public int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int l = process(root.left);
        int r = process(root.right);
        if (root.left != null && root.left.val == root.val) {
            left = l + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            right = r + 1;
        }
        res = Math.max(res, left + right);
        return Math.max(left, right);
    }

    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process1(root).max - 1;
    }

    public static Info process1(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        TreeNode left = x.left;
        TreeNode right = x.right;

        Info leftInfo = process1(left);
        Info rightInfo = process1(right);

        int len = 1; // 从x出发，往左或者右边往下插，一直到底
        if (left != null && left.val == x.val) {
            len = leftInfo.len + 1;
        }
        if (right != null && right.val == x.val) {
            len = Math.max(rightInfo.len + 1, len);
        }
        // 不从x出发
        int max = Math.max(Math.max(leftInfo.max, rightInfo.max), len);
        // ，经过x得条件是x和左孩子和右孩子相等，那么就可以链接起来
        if (left != null && right != null && right.val == x.val && left.val == x.val) {
            max = Math.max(max, leftInfo.len + rightInfo.len + 1);
        }
        return new Info(len, max);
    }

    public static class Info {
        // 从x出发
        public int len;
        // 不从x出发
        public int max;

        public Info(int l, int m) {
            len = l;
            max = m;
        }
    }
}
