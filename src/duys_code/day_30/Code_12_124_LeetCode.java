package duys_code.day_30;

/**
 * @ClassName Code_12_124_LeetCode
 * @Author Duys
 * @Description 力扣: https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * @Date 2021/11/29 15:35
 **/
public class Code_12_124_LeetCode {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(-3);
        System.out.println(maxPathSum(treeNode));
    }

    // 二叉树的递归套路
    // 1.最大路径和经过x
    // 2.最大路径和不经过x
    //  2.1 最大路径和是左子树
    //  2.2 最大路径和是右子树
    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxSum;
    }

    public static Info process(TreeNode x) {
        if (x == null) {
            // 这里要封装成空树，不能成0，因为整棵树中可能有负数
            return null;
        }
        // 左树和右树的信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // 封装自己的信息
        int maxSum = x.val;
        int maxSumFromX = x.val;
        // 从x出发
        if (leftInfo != null) {
            maxSumFromX = Math.max(maxSumFromX, leftInfo.maxSumFromX + x.val);
        }
        if (rightInfo != null) {
            maxSumFromX = Math.max(maxSumFromX, rightInfo.maxSumFromX + x.val);
        }

        // 不经过x
        if (leftInfo != null) {
            maxSum = Math.max(maxSum, leftInfo.maxSum);
        }

        if (rightInfo != null) {
            maxSum = Math.max(maxSum, rightInfo.maxSum);
        }
        // 经过x，但是只往x的左树或者右树
        maxSum = Math.max(maxSumFromX, maxSum);
        // 经过x，左右都要
        if (leftInfo != null && rightInfo != null) {
            maxSum = Math.max(maxSum, leftInfo.maxSumFromX + rightInfo.maxSumFromX + x.val);
        }
        return new Info(maxSum, maxSumFromX);
    }

    public static class Info {

        // 最大路径和
        public int maxSum;
        // 经过x点的最大路径和
        public int maxSumFromX;

        public Info() {
            maxSum = 0;
            maxSumFromX = 0;
        }

        public Info(int max, int maxs) {
            maxSum = max;
            maxSumFromX = maxs;
        }
    }
}
