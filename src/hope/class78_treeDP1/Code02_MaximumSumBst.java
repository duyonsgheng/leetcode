package hope.class78_treeDP1;

import custom.base.TreeNode;

/**
 * @author Mr.Du
 * @ClassName Code02_MaximumSumBst
 * @date 2024年04月07日 下午 05:13
 */
// 二叉搜索子树的最大键值和
// 给你一棵以 root 为根的二叉树
// 请你返回 任意 二叉搜索子树的最大键值和
// 二叉搜索树的定义如下：
// 任意节点的左子树中的键值都 小于 此节点的键值
// 任意节点的右子树中的键值都 大于 此节点的键值
// 任意节点的左子树和右子树都是二叉搜索树
// 测试链接 : https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree/
public class Code02_MaximumSumBst {
    public static int maxSumBST(TreeNode root) {
        return f(root).maxBstSum;
    }

    public static Info f(TreeNode x) {
        if (x == null) {
            return new Info(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true, 0);
        }
        Info left = f(x.left);
        Info right = f(x.right);
        int max = Math.max(x.val, Math.max(left.max, right.max));
        int min = Math.min(x.val, Math.min(left.min, right.min));
        int sum = left.sum + right.sum + x.val;
        boolean isBst = left.isBst && right.isBst && x.val > left.max && x.val < right.min;
        int maxBstSum = Math.max(left.maxBstSum, right.maxBstSum);
        if (isBst) {
            maxBstSum = Math.max(maxBstSum, sum);
        }
        return new Info(max, min, sum, isBst, maxBstSum);
    }

    public static class Info {
        public int max;
        public int min;
        public int sum;
        public boolean isBst;
        public int maxBstSum;

        public Info(int a, int b, int c, boolean d, int e) {
            max = a;
            min = b;
            sum = c;
            isBst = d;
            maxBstSum = e;
        }
    }

}
