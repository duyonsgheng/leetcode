package duys_code.day_35;

/**
 * @ClassName Code_06_687_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/longest-univalue-path/
 * @Date 2021/12/8 13:47
 **/
public class Code_06_687_LeetCode {

    // 二叉树递归套路
    // 1.与x无关
    // 2.与x有关
    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).max - 1;
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        TreeNode left = x.left;
        TreeNode right = x.right;

        Info leftInfo = process(left);
        Info rightInfo = process(right);

        int len = 1; // 从x出发
        if (left != null && left.val == x.val) {
            len = leftInfo.len + 1;
        }
        if (right != null && right.val == x.val) {
            len = Math.max(rightInfo.len + 1, len);
        }
        // 不从x出发
        int max = Math.max(Math.max(leftInfo.max, rightInfo.max), len);
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


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }
}
