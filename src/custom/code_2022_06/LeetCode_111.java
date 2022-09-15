package custom.code_2022_06;

/**
 * @ClassName LeetCode_111
 * @Author Duys
 * @Description
 * @Date 2022/6/14 11:12
 **/
// 111. 二叉树的最小深度
// 给定一个二叉树，找出其最小深度。
//最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//说明：叶子节点是指没有子节点的节点。
public class LeetCode_111 {
    public static int minDepth(TreeNode root) {
        return process(root);
    }

    public static int process(TreeNode cur) {
        if (cur == null) {
            return 0;
        }
        // 线性问题
        if (cur.left == null && cur.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (cur.left != null) {
            min = Math.min(process(cur.left), min);
        }
        if (cur.right != null) {
            min = Math.min(process(cur.right), min);
        }
        return min + 1;
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
}
